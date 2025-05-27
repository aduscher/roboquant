package org.roboquant.feeds.csv;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Asset;
import org.roboquant.common.Currency;
import org.roboquant.common.Stock;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"},
   d2 = {"Lorg/roboquant/feeds/csv/StockBuilder;", "Lorg/roboquant/feeds/csv/AssetBuilder;", "currency", "Lorg/roboquant/common/Currency;", "(Lorg/roboquant/common/Currency;)V", "notCapital", "Lkotlin/text/Regex;", "build", "Lorg/roboquant/common/Asset;", "name", "", "roboquant"}
)
public final class StockBuilder implements AssetBuilder {
   @NotNull
   private final Currency currency;
   @NotNull
   private final Regex notCapital;

   public StockBuilder(@NotNull Currency currency) {
      Intrinsics.checkNotNullParameter(currency, "currency");
      super();
      this.currency = currency;
      this.notCapital = new Regex("[^A-Z]");
   }

   // $FF: synthetic method
   public StockBuilder(Currency var1, int var2, DefaultConstructorMarker var3) {
      if ((var2 & 1) != 0) {
         var1 = Currency.Companion.getUSD();
      }

      this(var1);
   }

   @NotNull
   public Asset build(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      String symbol = StringsKt.removeSuffix(StringsKt.removeSuffix(name, (CharSequence)".csv"), (CharSequence)".txt");
      String var10000 = symbol.toUpperCase(Locale.ROOT);
      Intrinsics.checkNotNullExpressionValue(var10000, "toUpperCase(...)");
      CharSequence var3 = (CharSequence)var10000;
      Regex var4 = this.notCapital;
      String var5 = ".";
      symbol = var4.replace(var3, var5);
      return new Stock(symbol, this.currency);
   }

   public StockBuilder() {
      this((Currency)null, 1, (DefaultConstructorMarker)null);
   }
}
