package org.roboquant.common;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0002\b\f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001:B\u0015\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0002\u0010\u0005B\u001f\u0012\u0010\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\u00070\u0004j\u0002`\b\u0012\u0006\u0010\u0003\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0000J\u0010\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u001b\u001a\u00020\fJ\u0011\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001eH\u0086\u0002J&\u0010\u001f\u001a\u00020 2\u0018\u0010!\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020 0\"H\u0086\bø\u0001\u0000J$\u0010#\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00000$2\u0006\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020)J\u0010\u0010*\u001a\u00020\u00002\b\b\u0002\u0010+\u001a\u00020\u0018J\u0006\u0010,\u001a\u00020-J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00020/H\u0096\u0002J\u0006\u00100\u001a\u00020\u0002J\u0006\u00101\u001a\u00020\u0002J\u0011\u00102\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001eH\u0086\u0002J\u0006\u00103\u001a\u00020\u0000J\u0011\u00104\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001eH\u0086\u0002J\u0010\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u001b\u001a\u00020\fJ\u0006\u00106\u001a\u00020\u0018J\u0011\u00107\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001eH\u0086\u0002J\u0006\u00108\u001a\u00020\tJ\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\u00070\u0004j\u0002`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0003\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006;"},
   d2 = {"Lorg/roboquant/common/TimeSeries;", "", "Lorg/roboquant/common/Observation;", "values", "", "(Ljava/util/List;)V", "timeline", "Ljava/time/Instant;", "Lorg/roboquant/common/Timeline;", "", "(Ljava/util/List;[D)V", "size", "", "getSize", "()I", "timeframe", "Lorg/roboquant/common/Timeframe;", "getTimeframe", "()Lorg/roboquant/common/Timeframe;", "getTimeline", "()Ljava/util/List;", "getValues", "()[D", "average", "", "clean", "diff", "n", "div", "other", "", "forEach", "", "block", "Lkotlin/Function2;", "groupBy", "", "", "period", "Ljava/time/temporal/ChronoUnit;", "zoneId", "Ljava/time/ZoneId;", "index", "start", "isNotEmpty", "", "iterator", "", "max", "min", "minus", "normalize", "plus", "returns", "sum", "times", "toDoubleArray", "toList", "ObservationIterator", "roboquant"}
)
@SourceDebugExtension({"SMAP\nTimeSeries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeSeries.kt\norg/roboquant/common/TimeSeries\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,258:1\n1#2:259\n1549#3:260\n1620#3,3:261\n1477#3:264\n1502#3,3:265\n1505#3,3:275\n1238#3,4:280\n1549#3:284\n1620#3,3:285\n372#4,7:268\n453#4:278\n403#4:279\n*S KotlinDebug\n*F\n+ 1 TimeSeries.kt\norg/roboquant/common/TimeSeries\n*L\n55#1:260\n55#1:261,3\n204#1:264\n204#1:265,3\n204#1:275,3\n207#1:280,4\n226#1:284\n226#1:285,3\n204#1:268,7\n207#1:278\n207#1:279\n*E\n"})
public final class TimeSeries implements Iterable, KMappedMarker {
   @NotNull
   private final List timeline;
   @NotNull
   private final double[] values;

   public TimeSeries(@NotNull List timeline, @NotNull double[] values) {
      Intrinsics.checkNotNullParameter(timeline, "timeline");
      Intrinsics.checkNotNullParameter(values, "values");
      super();
      this.timeline = timeline;
      this.values = values;
      if (this.timeline.size() != this.values.length) {
         int var3 = 0;
         String var4 = "timeline and values should be of equal size";
         throw new IllegalArgumentException(var4.toString());
      }
   }

   @NotNull
   public final List getTimeline() {
      return this.timeline;
   }

   @NotNull
   public final double[] getValues() {
      return this.values;
   }

   public TimeSeries(@NotNull List values) {
      Intrinsics.checkNotNullParameter(values, "values");
      Iterable $this$map$iv = (Iterable)values;
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         Observation it = (Observation)item$iv$iv;
         int var10 = 0;
         destination$iv$iv.add(it.getTime());
      }

      List var10001 = (List)destination$iv$iv;
      $this$map$iv = (Iterable)values;
      List var12 = var10001;
      $i$f$map = 0;
      destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         Observation it = (Observation)item$iv$iv;
         int var21 = 0;
         destination$iv$iv.add(it.getValue());
      }

      List var13 = (List)destination$iv$iv;
      this(var12, CollectionsKt.toDoubleArray((Collection)var13));
   }

   @NotNull
   public final Timeframe getTimeframe() {
      return TimelineKt.getTimeframe(this.timeline);
   }

   public final int getSize() {
      return this.values.length;
   }

   public final void forEach(@NotNull Function2 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$forEach = 0;
      int i = 0;

      for(int var4 = this.getValues().length; i < var4; ++i) {
         block.invoke(this.getTimeline().get(i), this.getValues()[i]);
      }

   }

   @NotNull
   public final TimeSeries plus(@NotNull Number other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return new TimeSeries(this.timeline, ExtensionsKt.plus(this.values, other));
   }

   @NotNull
   public final TimeSeries minus(@NotNull Number other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return new TimeSeries(this.timeline, ExtensionsKt.minus(this.values, other));
   }

   @NotNull
   public final TimeSeries times(@NotNull Number other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return new TimeSeries(this.timeline, ExtensionsKt.times(this.values, other));
   }

   @NotNull
   public final TimeSeries div(@NotNull Number other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return new TimeSeries(this.timeline, ExtensionsKt.div(this.values, other));
   }

   @NotNull
   public final TimeSeries returns(int n) {
      return new TimeSeries(CollectionsKt.drop((Iterable)this.timeline, n), ExtensionsKt.returns(this.values, n));
   }

   // $FF: synthetic method
   public static TimeSeries returns$default(TimeSeries var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      return var0.returns(var1);
   }

   @NotNull
   public final TimeSeries index(double start) {
      return new TimeSeries(this.timeline, ExtensionsKt.index(this.values, start));
   }

   // $FF: synthetic method
   public static TimeSeries index$default(TimeSeries var0, double var1, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = (double)1.0F;
      }

      return var0.index(var1);
   }

   @NotNull
   public final TimeSeries normalize() {
      return new TimeSeries(this.timeline, ExtensionsKt.normalize(this.values));
   }

   @NotNull
   public final Observation max() {
      int idx = ExtensionsKt.indexOfMax(this.values);
      return new Observation((Instant)this.timeline.get(idx), this.values[idx]);
   }

   @NotNull
   public final Observation min() {
      int idx = ExtensionsKt.indexOfMin(this.values);
      return new Observation((Instant)this.timeline.get(idx), this.values[idx]);
   }

   @NotNull
   public final TimeSeries clean() {
      ArrayList x = new ArrayList(this.getSize());
      ArrayList y = new ArrayList(this.getSize());
      int i = 0;

      for(int var4 = this.values.length; i < var4; ++i) {
         double value = this.values[i];
         if (!Double.isInfinite(value) && !Double.isNaN(value)) {
            x.add(this.timeline.get(i));
            y.add(value);
         }
      }

      return new TimeSeries((List)x, CollectionsKt.toDoubleArray((Collection)y));
   }

   public final double average() {
      return ArraysKt.average(this.values);
   }

   @NotNull
   public final TimeSeries diff(int n) {
      return new TimeSeries(CollectionsKt.drop((Iterable)this.timeline, n), ExtensionsKt.diff(this.values, n));
   }

   // $FF: synthetic method
   public static TimeSeries diff$default(TimeSeries var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      return var0.diff(var1);
   }

   public final double sum() {
      return ArraysKt.sum(this.values);
   }

   @NotNull
   public final Map groupBy(@NotNull ChronoUnit period, @NotNull ZoneId zoneId) {
      Intrinsics.checkNotNullParameter(period, "period");
      Intrinsics.checkNotNullParameter(zoneId, "zoneId");
      SimpleDateFormat var10000;
      switch (TimeSeries.WhenMappings.$EnumSwitchMapping$0[period.ordinal()]) {
         case 1 -> var10000 = new SimpleDateFormat("yyyy");
         case 2 -> var10000 = new SimpleDateFormat("yyyy-MM");
         case 3 -> var10000 = new SimpleDateFormat("yyyy-ww");
         case 4 -> var10000 = new SimpleDateFormat("yyyy-DDD");
         case 5 -> var10000 = new SimpleDateFormat("yyyy-DDD-HH");
         case 6 -> var10000 = new SimpleDateFormat("yyyy-DDD-HH-mm");
         default -> throw new IllegalArgumentException("Unsupported value for period: " + period);
      }

      SimpleDateFormat formatter = var10000;
      formatter.setTimeZone(TimeZone.getTimeZone(zoneId));
      Iterable $this$groupBy$iv = (Iterable)this.toList();
      int $i$f$groupBy = 0;
      Map destination$iv$iv = (Map)(new LinkedHashMap());
      int $i$f$groupByTo = 0;

      for(Object element$iv$iv : $this$groupBy$iv) {
         Observation it = (Observation)element$iv$iv;
         int var12 = 0;
         Date date = Date.from(it.getTime());
         Object key$iv$iv = formatter.format(date);
         int $i$f$getOrPut = 0;
         Object value$iv$iv$iv = destination$iv$iv.get(key$iv$iv);
         Object var34;
         if (value$iv$iv$iv == null) {
            int var18 = 0;
            Object answer$iv$iv$iv = (List)(new ArrayList());
            destination$iv$iv.put(key$iv$iv, answer$iv$iv$iv);
            var34 = answer$iv$iv$iv;
         } else {
            var34 = value$iv$iv$iv;
         }

         List list$iv$iv = (List)var34;
         list$iv$iv.add(element$iv$iv);
      }

      $i$f$groupBy = 0;
      Map $this$groupByTo$iv$iv = destination$iv$iv;
      destination$iv$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv.size())));
      $i$f$groupByTo = 0;
      Iterable $this$associateByTo$iv$iv$iv = (Iterable)$this$groupByTo$iv$iv.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         Map.Entry it$iv$iv = (Map.Entry)element$iv$iv$iv;
         int $this$getOrPut$iv$iv$iv = 0;
         Object var10001 = it$iv$iv.getKey();
         Map.Entry it = (Map.Entry)element$iv$iv$iv;
         Object var21 = var10001;
         int var32 = 0;
         TimeSeries var22 = new TimeSeries((List)it.getValue());
         destination$iv$iv.put(var21, var22);
      }

      return destination$iv$iv;
   }

   // $FF: synthetic method
   public static Map groupBy$default(TimeSeries var0, ChronoUnit var1, ZoneId var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         ZoneOffset var10000 = ZoneOffset.UTC;
         Intrinsics.checkNotNullExpressionValue(var10000, "UTC");
         var2 = (ZoneId)var10000;
      }

      return var0.groupBy(var1, var2);
   }

   @NotNull
   public Iterator iterator() {
      return new ObservationIterator(this.timeline, this.values);
   }

   @NotNull
   public final double[] toDoubleArray() {
      return this.values;
   }

   @NotNull
   public final List toList() {
      Iterable $this$map$iv = (Iterable)ArraysKt.zip(this.values, (Iterable)this.timeline);
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         Pair it = (Pair)item$iv$iv;
         int var9 = 0;
         destination$iv$iv.add(new Observation((Instant)it.getSecond(), ((Number)it.getFirst()).doubleValue()));
      }

      return (List)destination$iv$iv;
   }

   public final boolean isNotEmpty() {
      return this.getSize() > 0;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0010H\u0096\u0002J\t\u0010\u0011\u001a\u00020\u0002H\u0096\u0002R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"},
      d2 = {"Lorg/roboquant/common/TimeSeries$ObservationIterator;", "", "Lorg/roboquant/common/Observation;", "times", "", "Ljava/time/Instant;", "values", "", "(Ljava/util/List;[D)V", "count", "", "getCount", "()I", "setCount", "(I)V", "hasNext", "", "next", "roboquant"}
   )
   private static final class ObservationIterator implements Iterator, KMappedMarker {
      @NotNull
      private final List times;
      @NotNull
      private final double[] values;
      private int count;

      public ObservationIterator(@NotNull List times, @NotNull double[] values) {
         Intrinsics.checkNotNullParameter(times, "times");
         Intrinsics.checkNotNullParameter(values, "values");
         super();
         this.times = times;
         this.values = values;
      }

      public final int getCount() {
         return this.count;
      }

      public final void setCount(int var1) {
         this.count = var1;
      }

      public boolean hasNext() {
         return this.count < this.values.length;
      }

      @NotNull
      public Observation next() {
         if (this.count >= this.values.length) {
            throw new NoSuchElementException();
         } else {
            Observation result = new Observation((Instant)this.times.get(this.count), this.values[this.count]);
            int var2 = this.count++;
            return result;
         }
      }

      public void remove() {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
   }

   // $FF: synthetic class
   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public class WhenMappings {
      // $FF: synthetic field
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[ChronoUnit.values().length];

         try {
            var0[ChronoUnit.YEARS.ordinal()] = 1;
         } catch (NoSuchFieldError var7) {
         }

         try {
            var0[ChronoUnit.MONTHS.ordinal()] = 2;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[ChronoUnit.WEEKS.ordinal()] = 3;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[ChronoUnit.DAYS.ordinal()] = 4;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[ChronoUnit.HOURS.ordinal()] = 5;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[ChronoUnit.MINUTES.ordinal()] = 6;
         } catch (NoSuchFieldError var2) {
         }

         $EnumSwitchMapping$0 = var0;
      }
   }
}
