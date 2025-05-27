package org.roboquant.feeds.csv;

import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Asset;
import org.roboquant.common.TradePrice;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u001e\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"},
   d2 = {"Lorg/roboquant/feeds/csv/TradePriceParser;", "Lorg/roboquant/feeds/csv/PriceParser;", "price", "", "volume", "autodetect", "", "(IIZ)V", "init", "", "header", "", "", "parse", "Lorg/roboquant/common/TradePrice;", "line", "asset", "Lorg/roboquant/common/Asset;", "validate", "roboquant"}
)
@SourceDebugExtension({"SMAP\nPriceParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PriceParser.kt\norg/roboquant/feeds/csv/TradePriceParser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,200:1\n1#2:201\n1864#3,3:202\n*S KotlinDebug\n*F\n+ 1 PriceParser.kt\norg/roboquant/feeds/csv/TradePriceParser\n*L\n177#1:202,3\n*E\n"})
public final class TradePriceParser implements PriceParser {
   private int price;
   private int volume;
   private boolean autodetect;

   public TradePriceParser(int price, int volume, boolean autodetect) {
      this.price = price;
      this.volume = volume;
      this.autodetect = autodetect;
   }

   // $FF: synthetic method
   public TradePriceParser(int var1, int var2, boolean var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 1) != 0) {
         var1 = -1;
      }

      if ((var4 & 2) != 0) {
         var2 = -1;
      }

      if ((var4 & 4) != 0) {
         var3 = true;
      }

      this(var1, var2, var3);
   }

   private final void validate() {
      if (this.price == -1) {
         int var1 = 0;
         String var2 = "No ask-prices column";
         throw new IllegalArgumentException(var2.toString());
      }
   }

   public void init(@NotNull List header) {
      Intrinsics.checkNotNullParameter(header, "header");
      if (this.autodetect) {
         Regex notCapital = new Regex("[^A-Z]");
         Iterable $this$forEachIndexed$iv = (Iterable)header;
         int $i$f$forEachIndexed = 0;
         int index$iv = 0;

         for(Object item$iv : $this$forEachIndexed$iv) {
            int index = index$iv++;
            if (index < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            String column = (String)item$iv;
            int var11 = 0;
            String var15 = column.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(var15, "toUpperCase(...)");
            CharSequence var12 = (CharSequence)var15;
            String var13 = "";
            String var14 = notCapital.replace(var12, var13);
            if (Intrinsics.areEqual(var14, "PRICE")) {
               this.price = index;
            } else if (Intrinsics.areEqual(var14, "VOLUME")) {
               this.volume = index;
            }
         }
      }

      this.validate();
   }

   @NotNull
   public TradePrice parse(@NotNull List line, @NotNull Asset asset) {
      Intrinsics.checkNotNullParameter(line, "line");
      Intrinsics.checkNotNullParameter(asset, "asset");
      double volume = this.volume != -1 ? Double.parseDouble((String)line.get(this.volume)) : Double.NaN;
      return new TradePrice(asset, Double.parseDouble((String)line.get(this.price)), volume);
   }

   public TradePriceParser() {
      this(0, 0, false, 7, (DefaultConstructorMarker)null);
   }
}
