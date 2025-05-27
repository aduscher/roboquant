package org.roboquant.journals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.TimeSeries;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0004J\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u0004J\u0016\u0010\u000b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004J\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0010J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0010J\u0014\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0015J\u0006\u0010\u0016\u001a\u00020\u0013R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"},
   d2 = {"Lorg/roboquant/journals/MultiRunJournal;", "", "fn", "Lkotlin/Function1;", "", "Lorg/roboquant/journals/MetricsJournal;", "(Lkotlin/jvm/functions/Function1;)V", "journals", "", "getJournal", "run", "getMetric", "", "Lorg/roboquant/common/TimeSeries;", "name", "getMetricNames", "", "getRuns", "load", "", "runs", "", "reset", "Companion", "roboquant"}
)
@SourceDebugExtension({"SMAP\nMultiRunJournal.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultiRunJournal.kt\norg/roboquant/journals/MultiRunJournal\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,66:1\n453#2:67\n403#2:68\n1238#3,4:69\n1549#3:73\n1620#3,3:74\n*S KotlinDebug\n*F\n+ 1 MultiRunJournal.kt\norg/roboquant/journals/MultiRunJournal\n*L\n46#1:67\n46#1:68\n46#1:69,4\n54#1:73\n54#1:74,3\n*E\n"})
public final class MultiRunJournal {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Function1 fn;
   @NotNull
   private final Map journals;
   private static int cnt;

   public MultiRunJournal(@NotNull Function1 fn) {
      Intrinsics.checkNotNullParameter(fn, "fn");
      super();
      this.fn = fn;
      this.journals = (Map)(new LinkedHashMap());
   }

   @NotNull
   public final synchronized MetricsJournal getJournal(@NotNull String run) {
      Intrinsics.checkNotNullParameter(run, "run");
      if (!this.journals.containsKey(run)) {
         MetricsJournal journal = (MetricsJournal)this.fn.invoke(run);
         this.journals.put(run, journal);
      }

      return (MetricsJournal)MapsKt.getValue(this.journals, run);
   }

   // $FF: synthetic method
   public static MetricsJournal getJournal$default(MultiRunJournal var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Companion.nextRun();
      }

      return var0.getJournal(var1);
   }

   public final void load(@NotNull Collection runs) {
      Intrinsics.checkNotNullParameter(runs, "runs");

      for(String run : runs) {
         this.getJournal(run);
      }

   }

   @NotNull
   public final Set getRuns() {
      return this.journals.keySet();
   }

   @NotNull
   public final Map getMetric(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Map $this$mapValues$iv = this.journals;
      int $i$f$mapValues = 0;
      Map destination$iv$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity($this$mapValues$iv.size())));
      int $i$f$mapValuesTo = 0;
      Iterable $this$associateByTo$iv$iv$iv = (Iterable)$this$mapValues$iv.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         Map.Entry it$iv$iv = (Map.Entry)element$iv$iv$iv;
         int var13 = 0;
         Object var10001 = it$iv$iv.getKey();
         Map.Entry it = (Map.Entry)element$iv$iv$iv;
         Object var17 = var10001;
         int var15 = 0;
         TimeSeries var18 = ((MetricsJournal)it.getValue()).getMetric(name);
         destination$iv$iv.put(var17, var18);
      }

      return destination$iv$iv;
   }

   @NotNull
   public final TimeSeries getMetric(@NotNull String name, @NotNull String run) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(run, "run");
      MetricsJournal var10000 = (MetricsJournal)this.journals.get(run);
      TimeSeries var3;
      if (var10000 != null) {
         var3 = var10000.getMetric(name);
         if (var3 != null) {
            return var3;
         }
      }

      var3 = new TimeSeries(CollectionsKt.emptyList(), new double[0]);
      return var3;
   }

   @NotNull
   public final Set getMetricNames() {
      Iterable $this$map$iv = (Iterable)this.journals.values();
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         MetricsJournal it = (MetricsJournal)item$iv$iv;
         int var9 = 0;
         destination$iv$iv.add(it.getMetricNames());
      }

      return CollectionsKt.toSet((Iterable)CollectionsKt.flatten((Iterable)((List)destination$iv$iv)));
   }

   public final synchronized void reset() {
      this.journals.clear();
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"},
      d2 = {"Lorg/roboquant/journals/MultiRunJournal$Companion;", "", "()V", "cnt", "", "nextRun", "", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final synchronized String nextRun() {
         int var1 = MultiRunJournal.cnt;
         MultiRunJournal.cnt = var1 + 1;
         return "run-" + var1;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
