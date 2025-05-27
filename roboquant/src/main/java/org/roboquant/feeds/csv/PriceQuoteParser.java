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
import org.roboquant.common.PriceQuote;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u001e\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"},
   d2 = {"Lorg/roboquant/feeds/csv/PriceQuoteParser;", "Lorg/roboquant/feeds/csv/PriceParser;", "ask", "", "bid", "bidVolume", "askVolume", "autodetect", "", "(IIIIZ)V", "init", "", "header", "", "", "parse", "Lorg/roboquant/common/PriceQuote;", "line", "asset", "Lorg/roboquant/common/Asset;", "validate", "roboquant"}
)
@SourceDebugExtension({"SMAP\nPriceParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PriceParser.kt\norg/roboquant/feeds/csv/PriceQuoteParser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,200:1\n1#2:201\n1864#3,3:202\n*S KotlinDebug\n*F\n+ 1 PriceParser.kt\norg/roboquant/feeds/csv/PriceQuoteParser\n*L\n130#1:202,3\n*E\n"})
public final class PriceQuoteParser implements PriceParser {
   private int ask;
   private int bid;
   private int bidVolume;
   private int askVolume;
   private boolean autodetect;

   public PriceQuoteParser(int ask, int bid, int bidVolume, int askVolume, boolean autodetect) {
      this.ask = ask;
      this.bid = bid;
      this.bidVolume = bidVolume;
      this.askVolume = askVolume;
      this.autodetect = autodetect;
   }

   // $FF: synthetic method
   public PriceQuoteParser(int var1, int var2, int var3, int var4, boolean var5, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 1) != 0) {
         var1 = -1;
      }

      if ((var6 & 2) != 0) {
         var2 = -1;
      }

      if ((var6 & 4) != 0) {
         var3 = -1;
      }

      if ((var6 & 8) != 0) {
         var4 = -1;
      }

      if ((var6 & 16) != 0) {
         var5 = true;
      }

      this(var1, var2, var3, var4, var5);
   }

   private final void validate() {
      if (this.ask == -1) {
         int var3 = 0;
         String var4 = "No ask-prices column";
         throw new IllegalArgumentException(var4.toString());
      } else if (this.bid == -1) {
         int var1 = 0;
         String var2 = "No bid-prices column found";
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
            switch (var14) {
               case "ASK":
                  this.ask = index;
                  break;
               case "BID":
                  this.bid = index;
                  break;
               case "ASKSIZE":
                  this.askVolume = index;
                  break;
               case "BIDSIZE":
                  this.bidVolume = index;
                  break;
               case "ASKVOLUME":
                  this.askVolume = index;
                  break;
               case "BIDVOLUME":
                  this.bidVolume = index;
            }
         }
      }

      this.validate();
   }

   @NotNull
   public PriceQuote parse(@NotNull List line, @NotNull Asset asset) {
      Intrinsics.checkNotNullParameter(line, "line");
      Intrinsics.checkNotNullParameter(asset, "asset");
      double volume1 = this.askVolume != -1 ? Double.parseDouble((String)line.get(this.askVolume)) : Double.NaN;
      double volume2 = this.bidVolume != -1 ? Double.parseDouble((String)line.get(this.bidVolume)) : Double.NaN;
      return new PriceQuote(asset, Double.parseDouble((String)line.get(this.ask)), volume1, Double.parseDouble((String)line.get(this.bid)), volume2);
   }

   public PriceQuoteParser() {
      this(0, 0, 0, 0, false, 31, (DefaultConstructorMarker)null);
   }
}
