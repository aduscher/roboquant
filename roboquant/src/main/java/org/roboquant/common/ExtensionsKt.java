package org.roboquant.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import org.hipparchus.stat.descriptive.moment.StandardDeviation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000t\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0004\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\r\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\u000f2\b\u0010\u0010\u001a\u0004\u0018\u0001H\u000e¢\u0006\u0002\u0010\u0011\u001a\n\u0010\u0012\u001a\u00020\u0013*\u00020\u0013\u001a\u0015\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086\u0002\u001a\u0014\u0010\u0019\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u0015\u001a\u0015\u0010\u001b\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0013H\u0086\u0002\u001a\u0015\u0010\u001b\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0002H\u0086\u0002\u001a\u0015\u0010\u001b\u001a\u00020\u001d*\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0086\u0002\u001a'\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u000e0 \"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0 2\u0006\u0010!\u001a\u00020\"H\u0086\u0002\u001a\u0014\u0010#\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u0015\u001a\u0014\u0010$\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010%\u001a\u00020\u0001\u001a\n\u0010&\u001a\u00020\u0015*\u00020\u0013\u001a\n\u0010'\u001a\u00020\u0015*\u00020\u0013\u001a\u0014\u0010(\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u0015\u001a\u0015\u0010)\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0013H\u0086\u0002\u001a\u0015\u0010)\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0002H\u0086\u0002\u001a\n\u0010*\u001a\u00020\u0013*\u00020\u0013\u001a\u0015\u0010+\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0013H\u0086\u0002\u001a\u0015\u0010+\u001a\u00020\u0013*\u00020\u00132\u0006\u0010,\u001a\u00020\u0002H\u0086\u0002\u001a\u0014\u0010-\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u0015\u001a\u0014\u0010.\u001a\u00020/*\u00020\u00022\b\b\u0002\u00100\u001a\u00020\u0015\u001a0\u00101\u001a\u000202\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e032\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020605H\u0086\bø\u0001\u0000\u001a\u0015\u00107\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0013H\u0086\u0002\u001a\u0015\u00107\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0002H\u0086\u0002\u001a\u0018\u00108\u001a\u0010\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0018\u000109*\u00020\u001d\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\n\u0010\b\"\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\f\u0010\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006;"},
   d2 = {"bips", "", "", "getBips", "(Ljava/lang/Number;)D", "iszero", "", "getIszero", "(D)Z", "nonzero", "getNonzero", "percent", "getPercent", "addNotNull", "T", "", "elem", "(Ljava/util/Collection;Ljava/lang/Object;)Z", "clean", "", "compareTo", "", "Ljava/time/Instant;", "timeframe", "Lorg/roboquant/common/Timeframe;", "diff", "n", "div", "a", "", "other", "get", "", "range", "Lkotlin/ranges/IntRange;", "growthRates", "index", "start", "indexOfMax", "indexOfMin", "logGrowthRates", "minus", "normalize", "plus", "number", "returns", "round", "Ljava/math/BigDecimal;", "fractions", "sumOf", "Lorg/roboquant/common/Wallet;", "", "selector", "Lkotlin/Function1;", "Lorg/roboquant/common/Amount;", "times", "toCurrencyPair", "Lkotlin/Pair;", "Lorg/roboquant/common/Currency;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nextensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 extensions.kt\norg/roboquant/common/ExtensionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,352:1\n1#2:353\n3846#3:354\n4367#3,2:355\n1330#3,2:357\n1855#4,2:359\n*S KotlinDebug\n*F\n+ 1 extensions.kt\norg/roboquant/common/ExtensionsKt\n*L\n160#1:354\n160#1:355,2\n191#1:357,2\n336#1:359,2\n*E\n"})
public final class ExtensionsKt {
   public static final int compareTo(@NotNull Instant $this$compareTo, @NotNull Timeframe timeframe) {
      Intrinsics.checkNotNullParameter($this$compareTo, "<this>");
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      boolean var2 = timeframe.getInclusive();
      int var10000;
      if (var2) {
         var10000 = $this$compareTo.compareTo(timeframe.getEnd()) > 0 ? 1 : ($this$compareTo.compareTo(timeframe.getStart()) < 0 ? -1 : 0);
      } else {
         if (var2) {
            throw new NoWhenBranchMatchedException();
         }

         var10000 = $this$compareTo.compareTo(timeframe.getEnd()) >= 0 ? 1 : ($this$compareTo.compareTo(timeframe.getStart()) < 0 ? -1 : 0);
      }

      return var10000;
   }

   @NotNull
   public static final List get(@NotNull List $this$get, @NotNull IntRange range) {
      Intrinsics.checkNotNullParameter($this$get, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      return $this$get.subList(Integer.max(0, range.getFirst()), Integer.min($this$get.size(), range.getLast() + 1));
   }

   public static final boolean addNotNull(@NotNull Collection $this$addNotNull, @Nullable Object elem) {
      Intrinsics.checkNotNullParameter($this$addNotNull, "<this>");
      return elem != null ? $this$addNotNull.add(elem) : false;
   }

   @NotNull
   public static final double[] div(@NotNull double[] $this$div, @NotNull Number a) {
      Intrinsics.checkNotNullParameter($this$div, "<this>");
      Intrinsics.checkNotNullParameter(a, "a");
      double[] result = (double[])$this$div.clone();
      double n = a.doubleValue();
      int i = 0;

      for(int var6 = $this$div.length; i < var6; ++i) {
         result[i] /= n;
      }

      return result;
   }

   @NotNull
   public static final double[] times(@NotNull double[] $this$times, @NotNull Number a) {
      Intrinsics.checkNotNullParameter($this$times, "<this>");
      Intrinsics.checkNotNullParameter(a, "a");
      double[] result = (double[])$this$times.clone();
      double n = a.doubleValue();
      int i = 0;

      for(int var6 = $this$times.length; i < var6; ++i) {
         result[i] *= n;
      }

      return result;
   }

   @NotNull
   public static final double[] minus(@NotNull double[] $this$minus, @NotNull Number a) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(a, "a");
      double[] result = (double[])$this$minus.clone();
      double n = a.doubleValue();
      int i = 0;

      for(int var6 = $this$minus.length; i < var6; ++i) {
         result[i] -= n;
      }

      return result;
   }

   @NotNull
   public static final double[] plus(@NotNull double[] $this$plus, @NotNull Number number) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(number, "number");
      double[] result = (double[])$this$plus.clone();
      double n = number.doubleValue();
      int i = 0;

      for(int var6 = $this$plus.length; i < var6; ++i) {
         result[i] += n;
      }

      return result;
   }

   @NotNull
   public static final double[] minus(@NotNull double[] $this$minus, @NotNull double[] a) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(a, "a");
      if (a.length != $this$minus.length) {
         int var6 = 0;
         String var7 = "Arrays have to be of equal size";
         throw new IllegalArgumentException(var7.toString());
      } else {
         double[] result = (double[])$this$minus.clone();
         int i = 0;

         for(int var4 = $this$minus.length; i < var4; ++i) {
            result[i] -= a[i];
         }

         return result;
      }
   }

   @NotNull
   public static final double[] times(@NotNull double[] $this$times, @NotNull double[] a) {
      Intrinsics.checkNotNullParameter($this$times, "<this>");
      Intrinsics.checkNotNullParameter(a, "a");
      if (a.length != $this$times.length) {
         int var6 = 0;
         String var7 = "Arrays have to be of equal size";
         throw new IllegalArgumentException(var7.toString());
      } else {
         double[] result = (double[])$this$times.clone();
         int i = 0;

         for(int var4 = $this$times.length; i < var4; ++i) {
            result[i] *= a[i];
         }

         return result;
      }
   }

   @NotNull
   public static final double[] div(@NotNull double[] $this$div, @NotNull double[] a) {
      Intrinsics.checkNotNullParameter($this$div, "<this>");
      Intrinsics.checkNotNullParameter(a, "a");
      if (a.length != $this$div.length) {
         int var6 = 0;
         String var7 = "Arrays have to be of equal size";
         throw new IllegalArgumentException(var7.toString());
      } else {
         double[] result = (double[])$this$div.clone();
         int i = 0;

         for(int var4 = $this$div.length; i < var4; ++i) {
            result[i] /= a[i];
         }

         return result;
      }
   }

   @NotNull
   public static final double[] plus(@NotNull double[] $this$plus, @NotNull double[] a) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(a, "a");
      if (a.length != $this$plus.length) {
         int var6 = 0;
         String var7 = "Arrays have to be of equal size";
         throw new IllegalArgumentException(var7.toString());
      } else {
         double[] result = (double[])$this$plus.clone();
         int i = 0;

         for(int var4 = $this$plus.length; i < var4; ++i) {
            result[i] += a[i];
         }

         return result;
      }
   }

   @NotNull
   public static final double[] clean(@NotNull double[] $this$clean) {
      Intrinsics.checkNotNullParameter($this$clean, "<this>");
      int $i$f$filter = 0;
      double[] $this$filterTo$iv$iv = $this$clean;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$filterTo = 0;
      int var6 = 0;

      for(int var7 = $this$clean.length; var6 < var7; ++var6) {
         double element$iv$iv = $this$filterTo$iv$iv[var6];
         int var12 = 0;
         if (!Double.isInfinite(element$iv$iv) && !Double.isNaN(element$iv$iv)) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      return CollectionsKt.toDoubleArray((Collection)((List)destination$iv$iv));
   }

   @NotNull
   public static final double[] returns(@NotNull double[] $this$returns, int n) {
      Intrinsics.checkNotNullParameter($this$returns, "<this>");
      if ($this$returns.length <= n) {
         return new double[0];
      } else {
         double[] result = new double[$this$returns.length - n];
         int i = n;
         int var4 = ArraysKt.getLastIndex($this$returns);
         if (n <= var4) {
            while(true) {
               result[i - n] = $this$returns[i] / $this$returns[i - n] - (double)1.0F;
               if (i == var4) {
                  break;
               }

               ++i;
            }
         }

         return result;
      }
   }

   // $FF: synthetic method
   public static double[] returns$default(double[] var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      return returns(var0, var1);
   }

   @NotNull
   public static final double[] diff(@NotNull double[] $this$diff, int n) {
      Intrinsics.checkNotNullParameter($this$diff, "<this>");
      if ($this$diff.length <= n) {
         return new double[0];
      } else {
         double[] result = new double[$this$diff.length - n];
         int i = n;
         int var4 = ArraysKt.getLastIndex($this$diff);
         if (n <= var4) {
            while(true) {
               result[i - n] = $this$diff[i] - $this$diff[i - n];
               if (i == var4) {
                  break;
               }

               ++i;
            }
         }

         return result;
      }
   }

   // $FF: synthetic method
   public static double[] diff$default(double[] var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      return diff(var0, var1);
   }

   @NotNull
   public static final double[] index(@NotNull double[] $this$index, double start) {
      Intrinsics.checkNotNullParameter($this$index, "<this>");
      double[] $this$firstOrNull$iv = $this$index;
      int $i$f$firstOrNull = 0;
      int var7 = 0;
      int var8 = $this$index.length;

      Double var10000;
      while(true) {
         if (var7 >= var8) {
            var10000 = null;
            break;
         }

         double element$iv = $this$firstOrNull$iv[var7];
         int var13 = 0;
         if (!Double.isInfinite(element$iv) && !Double.isNaN(element$iv)) {
            var10000 = element$iv;
            break;
         }

         ++var7;
      }

      double first = (var10000 != null ? var10000 : (double)1.0F) / start;
      return div($this$index, (Number)first);
   }

   // $FF: synthetic method
   public static double[] index$default(double[] var0, double var1, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = (double)1.0F;
      }

      return index(var0, var1);
   }

   @NotNull
   public static final double[] normalize(@NotNull double[] $this$normalize) {
      Intrinsics.checkNotNullParameter($this$normalize, "<this>");
      double[] data = clean($this$normalize);
      double mean = ArraysKt.average(data);
      double std = (new StandardDeviation()).evaluate(data, mean) + 1.0E-6;
      return div(minus($this$normalize, (Number)mean), (Number)std);
   }

   public static final int indexOfMax(@NotNull double[] $this$indexOfMax) {
      Intrinsics.checkNotNullParameter($this$indexOfMax, "<this>");
      if ($this$indexOfMax.length == 0) {
         return -1;
      } else {
         int maxAt = 0;
         int i = 0;

         for(int var3 = $this$indexOfMax.length; i < var3; ++i) {
            if ($this$indexOfMax[i] > $this$indexOfMax[maxAt]) {
               maxAt = i;
            }
         }

         return maxAt;
      }
   }

   @NotNull
   public static final String div(@NotNull String $this$div, @NotNull String other) {
      Intrinsics.checkNotNullParameter($this$div, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      String[] var3 = new String[]{other};
      Path p = Path.of($this$div, var3).normalize();
      return p.toString();
   }

   public static final int indexOfMin(@NotNull double[] $this$indexOfMin) {
      Intrinsics.checkNotNullParameter($this$indexOfMin, "<this>");
      if ($this$indexOfMin.length == 0) {
         return -1;
      } else {
         int minAt = 0;
         int i = 0;

         for(int var3 = $this$indexOfMin.length; i < var3; ++i) {
            if ($this$indexOfMin[i] < $this$indexOfMin[minAt]) {
               minAt = i;
            }
         }

         return minAt;
      }
   }

   @NotNull
   public static final double[] growthRates(@NotNull double[] $this$growthRates, int n) {
      Intrinsics.checkNotNullParameter($this$growthRates, "<this>");
      if ($this$growthRates.length <= n) {
         return new double[0];
      } else {
         double[] result = new double[$this$growthRates.length - n];
         int i = n;
         int var4 = ArraysKt.getLastIndex($this$growthRates);
         if (n <= var4) {
            while(true) {
               result[i - n] = $this$growthRates[i] / $this$growthRates[i - n];
               if (i == var4) {
                  break;
               }

               ++i;
            }
         }

         return result;
      }
   }

   // $FF: synthetic method
   public static double[] growthRates$default(double[] var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      return growthRates(var0, var1);
   }

   @NotNull
   public static final double[] logGrowthRates(@NotNull double[] $this$logGrowthRates, int n) {
      Intrinsics.checkNotNullParameter($this$logGrowthRates, "<this>");
      if ($this$logGrowthRates.length <= n) {
         return new double[0];
      } else {
         double[] result = new double[$this$logGrowthRates.length - n];
         int i = 1;
         int var4 = ArraysKt.getLastIndex($this$logGrowthRates);
         if (i <= var4) {
            while(true) {
               result[i - n] = Math.log($this$logGrowthRates[i] / $this$logGrowthRates[i - n]);
               if (i == var4) {
                  break;
               }

               ++i;
            }
         }

         return result;
      }
   }

   // $FF: synthetic method
   public static double[] logGrowthRates$default(double[] var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      return logGrowthRates(var0, var1);
   }

   public static final boolean getIszero(double $this$iszero) {
      return Math.abs($this$iszero) < 1.0E-10;
   }

   public static final boolean getNonzero(double $this$nonzero) {
      return Math.abs($this$nonzero) >= 1.0E-10;
   }

   public static final double getPercent(@NotNull Number $this$percent) {
      Intrinsics.checkNotNullParameter($this$percent, "<this>");
      return $this$percent.doubleValue() / (double)100.0F;
   }

   public static final double getBips(@NotNull Number $this$bips) {
      Intrinsics.checkNotNullParameter($this$bips, "<this>");
      return $this$bips.doubleValue() / (double)10000.0F;
   }

   @NotNull
   public static final BigDecimal round(@NotNull Number $this$round, int fractions) {
      Intrinsics.checkNotNullParameter($this$round, "<this>");
      BigDecimal var10000 = BigDecimal.valueOf($this$round.doubleValue()).setScale(fractions, RoundingMode.HALF_DOWN);
      Intrinsics.checkNotNullExpressionValue(var10000, "setScale(...)");
      return var10000;
   }

   // $FF: synthetic method
   public static BigDecimal round$default(Number var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 2;
      }

      return round(var0, var1);
   }

   @Nullable
   public static final Pair toCurrencyPair(@NotNull String $this$toCurrencyPair) {
      Intrinsics.checkNotNullParameter($this$toCurrencyPair, "<this>");
      CharSequence var10000 = (CharSequence)$this$toCurrencyPair;
      char[] var2 = new char[]{'_', '-', ' ', '/', '.', ':'};
      List codes = StringsKt.split$default(var10000, var2, false, 0, 6, (Object)null);
      Pair var9;
      if (codes.size() == 2) {
         Currency.Companion var7 = Currency.Companion;
         String var10001 = ((String)CollectionsKt.first(codes)).toUpperCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var10001, "toUpperCase(...)");
         Currency c1 = var7.getInstance(var10001);
         var7 = Currency.Companion;
         var10001 = ((String)CollectionsKt.last(codes)).toUpperCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var10001, "toUpperCase(...)");
         Currency c2 = var7.getInstance(var10001);
         var9 = new Pair(c1, c2);
      } else if (codes.size() == 1 && $this$toCurrencyPair.length() == 6) {
         Currency.Companion var10 = Currency.Companion;
         String var13 = $this$toCurrencyPair.substring(0, 3);
         Intrinsics.checkNotNullExpressionValue(var13, "substring(...)");
         var13 = var13.toUpperCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var13, "toUpperCase(...)");
         Currency c1 = var10.getInstance(var13);
         var10 = Currency.Companion;
         var13 = $this$toCurrencyPair.substring(3, 6);
         Intrinsics.checkNotNullExpressionValue(var13, "substring(...)");
         var13 = var13.toUpperCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var13, "toUpperCase(...)");
         Currency c2 = var10.getInstance(var13);
         var9 = new Pair(c1, c2);
      } else {
         var9 = null;
      }

      return var9;
   }

   @NotNull
   public static final Wallet sumOf(@NotNull Collection $this$sumOf, @NotNull Function1 selector) {
      Intrinsics.checkNotNullParameter($this$sumOf, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      int $i$f$sumOf = 0;
      Wallet result = new Wallet((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);
      if ($this$sumOf.isEmpty()) {
         return result;
      } else {
         Currency currency = ((Amount)selector.invoke(CollectionsKt.first((Iterable)$this$sumOf))).getCurrency();
         double value = (double)0.0F;
         boolean singleCurrency = false;
         singleCurrency = true;
         Iterable $this$forEach$iv = (Iterable)$this$sumOf;
         int $i$f$forEach = 0;

         for(Object element$iv : $this$forEach$iv) {
            int var11 = 0;
            Amount amount = (Amount)selector.invoke(element$iv);
            if (singleCurrency) {
               if (amount.getCurrency() == currency) {
                  value += amount.getValue();
               } else {
                  singleCurrency = false;
                  result.deposit(new Amount(currency, value));
                  result.deposit(amount);
               }
            } else {
               result.deposit(amount);
            }
         }

         return singleCurrency ? (new Amount(currency, value)).toWallet() : result;
      }
   }
}
