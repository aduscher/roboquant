package org.roboquant.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u000eJ\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u000eJ\u0016\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000eJ\b\u0010\u001e\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006 "},
   d2 = {"Lorg/roboquant/common/Exchange;", "", "exchangeCode", "", "zoneId", "Ljava/time/ZoneId;", "tradingCalendar", "Lorg/roboquant/common/TradingCalendar;", "(Ljava/lang/String;Ljava/time/ZoneId;Lorg/roboquant/common/TradingCalendar;)V", "getExchangeCode", "()Ljava/lang/String;", "getZoneId", "()Ljava/time/ZoneId;", "getClosingTime", "Ljava/time/Instant;", "date", "Ljava/time/LocalDate;", "getInstant", "dateTime", "Ljava/time/LocalDateTime;", "getLocalDate", "time", "getOpeningTime", "getTradingHours", "Lorg/roboquant/common/Timeframe;", "isTrading", "", "sameDay", "first", "second", "toString", "Companion", "roboquant"}
)
public final class Exchange {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final String exchangeCode;
   @NotNull
   private final ZoneId zoneId;
   @NotNull
   private final TradingCalendar tradingCalendar;
   @NotNull
   private static final ConcurrentHashMap instances = new ConcurrentHashMap();
   @NotNull
   private static final String NY_TIMEZONE = "America/New_York";
   @NotNull
   private static final Exchange DEFAULT;
   @NotNull
   private static final Exchange US;
   @NotNull
   private static final Exchange NYSE;
   @NotNull
   private static final Exchange NASDAQ;
   @NotNull
   private static final Exchange BATS;
   @NotNull
   private static final Exchange CBOE;
   @NotNull
   private static final Exchange ARCA;
   @NotNull
   private static final Exchange AMEX;
   @NotNull
   private static final Exchange TSX;
   @NotNull
   private static final Exchange AEB;
   @NotNull
   private static final Exchange LSE;
   @NotNull
   private static final Exchange DEX;
   @NotNull
   private static final Exchange SIX;
   @NotNull
   private static final Exchange PAR;
   @NotNull
   private static final Exchange JPX;
   @NotNull
   private static final Exchange SSE;
   @NotNull
   private static final Exchange SEKH;
   @NotNull
   private static final Exchange SSX;
   @NotNull
   private static final Exchange CRYPTO;
   @NotNull
   private static final Exchange FOREX;

   private Exchange(String exchangeCode, ZoneId zoneId, TradingCalendar tradingCalendar) {
      this.exchangeCode = exchangeCode;
      this.zoneId = zoneId;
      this.tradingCalendar = tradingCalendar;
   }

   @NotNull
   public final String getExchangeCode() {
      return this.exchangeCode;
   }

   @NotNull
   public final ZoneId getZoneId() {
      return this.zoneId;
   }

   public final boolean sameDay(@NotNull Instant first, @NotNull Instant second) {
      Intrinsics.checkNotNullParameter(first, "first");
      Intrinsics.checkNotNullParameter(second, "second");
      LocalDate dt1 = LocalDate.ofInstant(first, this.zoneId);
      LocalDate dt2 = LocalDate.ofInstant(second, this.zoneId);
      return Intrinsics.areEqual(dt1, dt2);
   }

   @NotNull
   public final LocalDate getLocalDate(@NotNull Instant time) {
      Intrinsics.checkNotNullParameter(time, "time");
      LocalDate var10000 = LocalDate.ofInstant(time, this.zoneId);
      Intrinsics.checkNotNullExpressionValue(var10000, "ofInstant(...)");
      return var10000;
   }

   @NotNull
   public final Instant getOpeningTime(@NotNull LocalDate date) {
      Intrinsics.checkNotNullParameter(date, "date");
      LocalTime opening = this.tradingCalendar.getOpeningTime(date);
      if (opening == null) {
         throw new NoTradingException(date);
      } else {
         ZonedDateTime zdt = ZonedDateTime.of(date, opening, this.zoneId);
         Instant var10000 = zdt.toInstant();
         Intrinsics.checkNotNullExpressionValue(var10000, "toInstant(...)");
         return var10000;
      }
   }

   @NotNull
   public final Instant getClosingTime(@NotNull LocalDate date) {
      Intrinsics.checkNotNullParameter(date, "date");
      LocalTime closing = this.tradingCalendar.getClosingTime(date);
      if (closing == null) {
         throw new NoTradingException(date);
      } else {
         ZonedDateTime zdt = ZonedDateTime.of(date, closing, this.zoneId);
         Instant var10000 = zdt.toInstant();
         Intrinsics.checkNotNullExpressionValue(var10000, "toInstant(...)");
         return var10000;
      }
   }

   private final Timeframe getTradingHours(LocalDate date) {
      return new Timeframe(this.getOpeningTime(date), this.getClosingTime(date), false, 4, (DefaultConstructorMarker)null);
   }

   public final boolean isTrading(@NotNull Instant time) {
      Intrinsics.checkNotNullParameter(time, "time");
      LocalDate date = LocalDate.from((TemporalAccessor)time.atZone(this.zoneId));
      TradingCalendar var10000 = this.tradingCalendar;
      Intrinsics.checkNotNull(date);
      return var10000.isTradingDay(date) && this.getTradingHours(date).contains(time);
   }

   @NotNull
   public final Instant getInstant(@NotNull LocalDateTime dateTime) {
      Intrinsics.checkNotNullParameter(dateTime, "dateTime");
      ZonedDateTime zdt = ZonedDateTime.of(dateTime, this.zoneId);
      Instant var10000 = zdt.toInstant();
      Intrinsics.checkNotNullExpressionValue(var10000, "toInstant(...)");
      return var10000;
   }

   @NotNull
   public String toString() {
      return this.exchangeCode;
   }

   // $FF: synthetic method
   public Exchange(String exchangeCode, ZoneId zoneId, TradingCalendar tradingCalendar, DefaultConstructorMarker $constructor_marker) {
      this(exchangeCode, zoneId, tradingCalendar);
   }

   static {
      DEFAULT = Exchange.Companion.addInstance$default(Companion, "", "America/New_York", (String)null, (String)null, 12, (Object)null);
      US = Exchange.Companion.addInstance$default(Companion, "US", "America/New_York", (String)null, (String)null, 12, (Object)null);
      NYSE = Exchange.Companion.addInstance$default(Companion, "NYSE", "America/New_York", (String)null, (String)null, 12, (Object)null);
      NASDAQ = Exchange.Companion.addInstance$default(Companion, "NASDAQ", "America/New_York", (String)null, (String)null, 12, (Object)null);
      BATS = Exchange.Companion.addInstance$default(Companion, "BATS", "America/New_York", (String)null, (String)null, 12, (Object)null);
      CBOE = Exchange.Companion.addInstance$default(Companion, "CBOE", "America/New_York", (String)null, (String)null, 12, (Object)null);
      ARCA = Exchange.Companion.addInstance$default(Companion, "ARCA", "America/New_York", (String)null, (String)null, 12, (Object)null);
      AMEX = Exchange.Companion.addInstance$default(Companion, "AMEX", "America/New_York", (String)null, (String)null, 12, (Object)null);
      TSX = Exchange.Companion.addInstance$default(Companion, "TSX", "America/Toronto", (String)null, (String)null, 12, (Object)null);
      AEB = Companion.addInstance("AEB", "Europe/Amsterdam", "09:00", "17:30");
      LSE = Companion.addInstance("LSE", "Europe/London", "08:00", "16:30");
      DEX = Companion.addInstance("DEX", "Europe/Berlin", "09:00", "17:30");
      SIX = Companion.addInstance("SIX", "Europe/Zurich", "09:00", "17:20");
      PAR = Companion.addInstance("PAR", "Europe/Paris", "09:00", "17:30");
      JPX = Companion.addInstance("JPX", "Asia/Tokyo", "09:00", "15:00");
      SSE = Companion.addInstance("SSE", "Asia/Shanghai", "09:30", "15:00");
      SEKH = Companion.addInstance("SEHK", "Asia/Hong_Kong", "09:30", "16:00");
      SSX = Companion.addInstance("SSX", "Australia/Sydney", "10:00", "16:00");
      CRYPTO = Companion.addInstance("CRYPTO", "UTC", "00:00", "23:59:59.999");
      FOREX = Companion.addInstance("FOREX", "UTC", "00:00", "23:59:59.999");
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00020 2\u0006\u00107\u001a\u00020 2\b\b\u0002\u00108\u001a\u00020 2\b\b\u0002\u00109\u001a\u00020 J\u000e\u0010:\u001a\u00020\u00042\u0006\u00106\u001a\u00020 R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0011\u0010\u001b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0011\u0010\u001d\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u000e\u0010\u001f\u001a\u00020 X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010!\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0011\u0010#\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0011\u0010%\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0011\u0010'\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u0011\u0010)\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0006R\u0011\u0010+\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0011\u0010-\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0004008F¢\u0006\u0006\u001a\u0004\b1\u00102R\u001a\u00103\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u000404X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"},
      d2 = {"Lorg/roboquant/common/Exchange$Companion;", "", "()V", "AEB", "Lorg/roboquant/common/Exchange;", "getAEB", "()Lorg/roboquant/common/Exchange;", "AMEX", "getAMEX", "ARCA", "getARCA", "BATS", "getBATS", "CBOE", "getCBOE", "CRYPTO", "getCRYPTO", "DEFAULT", "getDEFAULT", "DEX", "getDEX", "FOREX", "getFOREX", "JPX", "getJPX", "LSE", "getLSE", "NASDAQ", "getNASDAQ", "NYSE", "getNYSE", "NY_TIMEZONE", "", "PAR", "getPAR", "SEKH", "getSEKH", "SIX", "getSIX", "SSE", "getSSE", "SSX", "getSSX", "TSX", "getTSX", "US", "getUS", "exchanges", "", "getExchanges", "()Ljava/util/Collection;", "instances", "Ljava/util/concurrent/ConcurrentHashMap;", "addInstance", "exchangeCode", "zone", "opening", "closing", "getInstance", "roboquant"}
   )
   @SourceDebugExtension({"SMAP\nExchange.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Exchange.kt\norg/roboquant/common/Exchange$Companion\n+ 2 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,254:1\n72#2,2:255\n1#3:257\n*S KotlinDebug\n*F\n+ 1 Exchange.kt\norg/roboquant/common/Exchange$Companion\n*L\n127#1:255,2\n127#1:257\n*E\n"})
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Collection getExchanges() {
         Collection var10000 = Exchange.instances.values();
         Intrinsics.checkNotNullExpressionValue(var10000, "<get-values>(...)");
         return var10000;
      }

      @NotNull
      public final Exchange getInstance(@NotNull String exchangeCode) {
         Intrinsics.checkNotNullParameter(exchangeCode, "exchangeCode");
         ConcurrentMap $this$getOrPut$iv = (ConcurrentMap)Exchange.instances;
         int $i$f$getOrPut = 0;
         Object var10000 = $this$getOrPut$iv.get(exchangeCode);
         if (var10000 == null) {
            int var5 = 0;
            Object default$iv = new Exchange(exchangeCode, Exchange.Companion.getDEFAULT().getZoneId(), Exchange.Companion.getDEFAULT().tradingCalendar, (DefaultConstructorMarker)null);
            int var7 = 0;
            var10000 = $this$getOrPut$iv.putIfAbsent(exchangeCode, default$iv);
            if (var10000 == null) {
               var10000 = default$iv;
            }
         }

         Exchange result = (Exchange)var10000;
         Intrinsics.checkNotNull(result);
         return result;
      }

      @NotNull
      public final Exchange addInstance(@NotNull String exchangeCode, @NotNull String zone, @NotNull String opening, @NotNull String closing) {
         Intrinsics.checkNotNullParameter(exchangeCode, "exchangeCode");
         Intrinsics.checkNotNullParameter(zone, "zone");
         Intrinsics.checkNotNullParameter(opening, "opening");
         Intrinsics.checkNotNullParameter(closing, "closing");
         ZoneId zoneId = ZoneId.of(zone);
         SimpleTradingCalendar tradingCalendar = new SimpleTradingCalendar(opening, closing);
         Intrinsics.checkNotNull(zoneId);
         Exchange instance = new Exchange(exchangeCode, zoneId, tradingCalendar, (DefaultConstructorMarker)null);
         ((Map)Exchange.instances).put(exchangeCode, instance);
         return instance;
      }

      // $FF: synthetic method
      public static Exchange addInstance$default(Companion var0, String var1, String var2, String var3, String var4, int var5, Object var6) {
         if ((var5 & 4) != 0) {
            var3 = "09:30";
         }

         if ((var5 & 8) != 0) {
            var4 = "16:00";
         }

         return var0.addInstance(var1, var2, var3, var4);
      }

      @NotNull
      public final Exchange getDEFAULT() {
         return Exchange.DEFAULT;
      }

      @NotNull
      public final Exchange getUS() {
         return Exchange.US;
      }

      @NotNull
      public final Exchange getNYSE() {
         return Exchange.NYSE;
      }

      @NotNull
      public final Exchange getNASDAQ() {
         return Exchange.NASDAQ;
      }

      @NotNull
      public final Exchange getBATS() {
         return Exchange.BATS;
      }

      @NotNull
      public final Exchange getCBOE() {
         return Exchange.CBOE;
      }

      @NotNull
      public final Exchange getARCA() {
         return Exchange.ARCA;
      }

      @NotNull
      public final Exchange getAMEX() {
         return Exchange.AMEX;
      }

      @NotNull
      public final Exchange getTSX() {
         return Exchange.TSX;
      }

      @NotNull
      public final Exchange getAEB() {
         return Exchange.AEB;
      }

      @NotNull
      public final Exchange getLSE() {
         return Exchange.LSE;
      }

      @NotNull
      public final Exchange getDEX() {
         return Exchange.DEX;
      }

      @NotNull
      public final Exchange getSIX() {
         return Exchange.SIX;
      }

      @NotNull
      public final Exchange getPAR() {
         return Exchange.PAR;
      }

      @NotNull
      public final Exchange getJPX() {
         return Exchange.JPX;
      }

      @NotNull
      public final Exchange getSSE() {
         return Exchange.SSE;
      }

      @NotNull
      public final Exchange getSEKH() {
         return Exchange.SEKH;
      }

      @NotNull
      public final Exchange getSSX() {
         return Exchange.SSX;
      }

      @NotNull
      public final Exchange getCRYPTO() {
         return Exchange.CRYPTO;
      }

      @NotNull
      public final Exchange getFOREX() {
         return Exchange.FOREX;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
