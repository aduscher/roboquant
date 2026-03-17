package org.roboquant.brokers;

import org.roboquant.common.*;
import org.roboquant.common.Amount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Simulated Broker that is used as the broker during back testing and live testing. It simulates both broker and
 * exchange behavior. It can be configured with various plug-ins during initiation time that determine its behavior.
 */
public class SimBroker implements Broker {

    private final Wallet initialDeposit;
    private final AccountModel accountModel;
    private final Map<String, LocalDate> orderEntry = new LinkedHashMap<>();
    private final ZoneId exchangeZoneId;
    private final List<Order> pendingOrders = new ArrayList<>();
    private final InternalAccount account;
    private final Logger logger = LoggerFactory.getLogger(SimBroker.class);
    private int nextOrderId = 0;
    private double slippage = 0.0;

    public SimBroker(Wallet initialDeposit, Currency baseCurrency, AccountModel accountModel, ZoneId exchangeZoneId) {
        this.initialDeposit = initialDeposit;
        this.accountModel = accountModel != null ? accountModel : (AccountModel) new CashAccount();
        this.exchangeZoneId = exchangeZoneId != null ? exchangeZoneId : ZoneId.of("UTC");
        this.account = new InternalAccount(baseCurrency != null ? baseCurrency : initialDeposit.getCurrencies().iterator().next());
        reset();
    }

    public SimBroker() {
        this(new Wallet(new Amount(Currency.getInstance("USD"), 1_000_000.0)), null, null, null);
    }

    public SimBroker(Number deposit, String currencyCode) {
        this(new Amount(Currency.getInstance(currencyCode), deposit).toWallet());
    }

    public SimBroker(Wallet initialDeposit) {
        this(initialDeposit, null, null, null);
    }

    public SimBroker(Wallet initialDeposit, Currency baseCurrency, double slippage, AccountModel accountModel, ZoneId exchangeZoneId) {
        this(initialDeposit, baseCurrency, accountModel, exchangeZoneId);
        this.slippage = slippage;
    }

    public Wallet getInitialDeposit() {
        return initialDeposit;
    }

    private void deleteOrder(Order order) {
        account.deleteOrder(order);
    }

    private double updatePosition(Asset asset, Size size, double price) {
        Position position = account.getPositions().get(asset);

        if (position == null) {
            account.getPositions().put(asset, new Position(size, price, price));
            return 0.0;
        }

        Size newSize = position.getSize().plus(size);

        if (newSize.isZero()) {
            account.getPositions().remove(asset);
            return asset.value(size, position.getAvgPrice() - price).getValue();
        }

        double avgPrice;
        if (position.getSize().getSign() == size.getSign()) {
            avgPrice = (size.toDouble() * position.getAvgPrice() + size.toDouble() * price) / newSize.toDouble();
        } else {
            avgPrice = price;
        }

        account.getPositions().put(asset, new Position(newSize, avgPrice, price));

        if(size.absoluteValue().compareTo(position.getSize().absoluteValue()) <= 0) {
            return asset.value(size, position.getAvgPrice() - price).getValue();
        }

        return asset.value(position.getSize(), price - position.getAvgPrice()).getValue();
    }

    public boolean isExpired(Order order, Instant time) {
        if (order.getTif() == Order.TIF.GTC) return false;

        LocalDate orderDate = orderEntry.get(order.getId());
        if (orderDate != null) {
            LocalDate currentDate = LocalDate.ofInstant(time, exchangeZoneId);
            return currentDate.isAfter(orderDate);
        }
        orderEntry.put(order.getId(), LocalDate.ofInstant(time, exchangeZoneId));
        return false;
    }

    private void simulateMarket(Event event) {
        Instant time = event.getTime();

        for (Order order : new ArrayList<>(account.getOrders())) {
            if (isExpired(order, time)) {
                deleteOrder(order);
                continue;
            }

            Object priceItem = event.getPrices().get(order.getAsset());
            if (priceItem != null) {
                double price = getExecutionPrice(order, priceItem);
                if (order.isExecutable(price)) {
                    Size fill = order.getRemaining();
                    double fee = getFee(order, fill, price);
                    double pnl = updatePosition(order.getAsset(), fill, price) - fee;
                    Amount cost = order.getAsset().value(fill, price).plus(fee);
                    account.getCash().withdraw(cost);
                    Trade trade = new Trade(order.getAsset(), event.getTime(), order.getSize(), price, pnl);
                    order.setFill(order.getFill().plus(fill));
                    account.getTrades().add(trade);
                    if (order.getRemaining().isZero()) {
                        deleteOrder(order);
                    }
                }
            }
        }
    }

    public double getExecutionPrice(Order order, Object priceItem) {
        double corr = order.isBuy() ? (1.0 + slippage) : (1.0 - slippage);
        if (priceItem instanceof Item.PriceQuote quote) {
            return order.isBuy() ? quote.getAskPrice() * corr : quote.getBidPrice() * corr;
        } else if (priceItem instanceof Item.PriceBar bar) {
            return bar.getOpen() * corr;
        } else if (priceItem instanceof Item.PriceItem pi) {
            return pi.getPrice() * corr;
        }
        return 0.0;
    }

    public double getFee(Order order, Size fill, double price) {
        return 0.0;
    }

    public Size getFill(Order order, double price) {
        return order.getRemaining();
    }

    @Override
    public synchronized Account sync(Event event) {
        for (Order order : pendingOrders) {
            if (order.getSize().isZero()) {
                boolean removed = account.getOrders().removeIf(o -> o.getId().equals(order.getId()));
                if (!removed) logger.warn("Skipping cancellation " + order);
            } else if (!order.getId().isEmpty()) {
                boolean removed = account.getOrders().removeIf(o -> o.getId().equals(order.getId()));
                if (removed) {
                    account.getOrders().add(order);
                } else {
                    logger.warn("Skipping modify " + order);
                }
            } else {
                order.setId(String.valueOf(nextOrderId++));
                account.getOrders().add(order);
            }
        }
        pendingOrders.clear();

        if (event != null) {
            simulateMarket(event);
            account.updateMarketPrices(event);
            account.setLastUpdate(event.getTime());
            accountModel.updateAccount(account);
        }

        return account.toAccount();
    }

    @Override
    public synchronized void placeOrders(List<Order> orders) {
        logger.trace("Received orders=" + orders.size());
        pendingOrders.addAll(orders);
    }

    public void reset() {
        account.clear();
        account.getCash().deposit(initialDeposit);
        accountModel.updateAccount(account);
    }

}
