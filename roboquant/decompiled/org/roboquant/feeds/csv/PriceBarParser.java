package org.roboquant.feeds.csv;

import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Asset;
import org.roboquant.common.PriceBar;
import org.roboquant.common.TimeSpan;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001Ba\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u001e\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0002R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"},
   d2 = {"Lorg/roboquant/feeds/csv/PriceBarParser;", "Lorg/roboquant/feeds/csv/PriceParser;", "open", "", "high", "low", "close", "volume", "adjustedClose", "priceAdjust", "", "autodetect", "timeSpan", "Lorg/roboquant/common/TimeSpan;", "(IIIIIIZZLorg/roboquant/common/TimeSpan;)V", "init", "", "header", "", "", "parse", "Lorg/roboquant/common/PriceBar;", "line", "asset", "Lorg/roboquant/common/Asset;", "validate", "roboquant"}
)
@SourceDebugExtension({"SMAP\nPriceParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PriceParser.kt\norg/roboquant/feeds/csv/PriceBarParser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,200:1\n1#2:201\n1864#3,3:202\n*S KotlinDebug\n*F\n+ 1 PriceParser.kt\norg/roboquant/feeds/csv/PriceBarParser\n*L\n72#1:202,3\n*E\n"})
public final class PriceBarParser implements PriceParser {
   private int open;
   private int high;
   private int low;
   private int close;
   private int volume;
   private int adjustedClose;
   private boolean priceAdjust;
   private boolean autodetect;
   @Nullable
   private TimeSpan timeSpan;

   public PriceBarParser(int open, int high, int low, int close, int volume, int adjustedClose, boolean priceAdjust, boolean autodetect, @Nullable TimeSpan timeSpan) {
      this.open = open;
      this.high = high;
      this.low = low;
      this.close = close;
      this.volume = volume;
      this.adjustedClose = adjustedClose;
      this.priceAdjust = priceAdjust;
      this.autodetect = autodetect;
      this.timeSpan = timeSpan;
   }

   // $FF: synthetic method
   public PriceBarParser(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7, boolean var8, TimeSpan var9, int var10, DefaultConstructorMarker var11) {
      if ((var10 & 1) != 0) {
         var1 = -1;
      }

      if ((var10 & 2) != 0) {
         var2 = -1;
      }

      if ((var10 & 4) != 0) {
         var3 = -1;
      }

      if ((var10 & 8) != 0) {
         var4 = -1;
      }

      if ((var10 & 16) != 0) {
         var5 = -1;
      }

      if ((var10 & 32) != 0) {
         var6 = -1;
      }

      if ((var10 & 64) != 0) {
         var7 = false;
      }

      if ((var10 & 128) != 0) {
         var8 = true;
      }

      if ((var10 & 256) != 0) {
         var9 = null;
      }

      this(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   private final void validate() {
      if (this.open == -1) {
         int var9 = 0;
         String var10 = "No open-prices column";
         throw new IllegalArgumentException(var10.toString());
      } else if (this.low == -1) {
         int var7 = 0;
         String var8 = "No low-prices column found";
         throw new IllegalArgumentException(var8.toString());
      } else if (this.high == -1) {
         int var5 = 0;
         String var6 = "No high-prices column found";
         throw new IllegalArgumentException(var6.toString());
      } else if (this.close == -1) {
         int var3 = 0;
         String var4 = "No close-prices column found";
         throw new IllegalArgumentException(var4.toString());
      } else if (this.priceAdjust && this.adjustedClose == -1) {
         int var1 = 0;
         String var2 = "No adjusted close prices column found";
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
               case "VOLUME":
                  this.volume = index;
                  break;
               case "ADJUSTEDCLOSE":
                  this.adjustedClose = index;
                  break;
               case "ADJCLOSE":
                  this.adjustedClose = index;
                  break;
               case "LOW":
                  this.low = index;
                  break;
               case "VOL":
                  this.volume = index;
                  break;
               case "HIGH":
                  this.high = index;
                  break;
               case "OPEN":
                  this.open = index;
                  break;
               case "CLOSE":
                  this.close = index;
            }
         }
      }

      this.validate();
   }

   @NotNull
   public PriceBar parse(@NotNull List line, @NotNull Asset asset) {
      Intrinsics.checkNotNullParameter(line, "line");
      Intrinsics.checkNotNullParameter(asset, "asset");
      double var10000;
      if (this.volume != -1) {
         String str = (String)line.get(this.volume);
         var10000 = StringsKt.isBlank((CharSequence)str) ? Double.NaN : Double.parseDouble(str);
      } else {
         var10000 = Double.NaN;
      }

      double volume = var10000;
      PriceBar action = new PriceBar(asset, (Number)Double.parseDouble((String)line.get(this.open)), (Number)Double.parseDouble((String)line.get(this.high)), (Number)Double.parseDouble((String)line.get(this.low)), (Number)Double.parseDouble((String)line.get(this.close)), (Number)volume, this.timeSpan);
      if (this.priceAdjust) {
         action.adjustClose((Number)Double.parseDouble((String)line.get(this.adjustedClose)));
      }

      return action;
   }

   public PriceBarParser() {
      this(0, 0, 0, 0, 0, 0, false, false, (TimeSpan)null, 511, (DefaultConstructorMarker)null);
   }
}
