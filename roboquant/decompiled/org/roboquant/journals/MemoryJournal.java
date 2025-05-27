package org.roboquant.journals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.TimeSeries;
import org.roboquant.journals.metrics.Metric;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0016J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u0011H\u0016J4\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0019H\u0016R&\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u001d"},
   d2 = {"Lorg/roboquant/journals/MemoryJournal;", "Lorg/roboquant/journals/MetricsJournal;", "metrics", "", "Lorg/roboquant/journals/metrics/Metric;", "([Lorg/roboquant/journals/metrics/Metric;)V", "history", "Ljava/util/TreeMap;", "Ljava/time/Instant;", "", "", "", "[Lorg/roboquant/journals/metrics/Metric;", "getMetric", "Lorg/roboquant/common/TimeSeries;", "name", "getMetricNames", "", "track", "", "event", "Lorg/roboquant/common/Event;", "account", "Lorg/roboquant/common/Account;", "signals", "", "Lorg/roboquant/common/Signal;", "orders", "Lorg/roboquant/common/Order;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nMemoryJournal.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MemoryJournal.kt\norg/roboquant/journals/MemoryJournal\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,74:1\n1549#2:75\n1620#2,3:76\n*S KotlinDebug\n*F\n+ 1 MemoryJournal.kt\norg/roboquant/journals/MemoryJournal\n*L\n51#1:75\n51#1:76,3\n*E\n"})
public final class MemoryJournal implements MetricsJournal {
   @NotNull
   private final Metric[] metrics;
   @NotNull
   private final TreeMap history;

   public MemoryJournal(@NotNull Metric... metrics) {
      Intrinsics.checkNotNullParameter(metrics, "metrics");
      super();
      this.metrics = metrics;
      this.history = new TreeMap();
   }

   public void track(@NotNull Event event, @NotNull Account account, @NotNull List signals, @NotNull List orders) {
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(orders, "orders");
      Map result = (Map)(new LinkedHashMap());
      Metric[] var6 = this.metrics;
      int var7 = 0;

      for(int var8 = var6.length; var7 < var8; ++var7) {
         Metric metric = var6[var7];
         Map values = metric.calculate(event, account, signals, orders);
         result.putAll(values);
      }

      ((Map)this.history).put(event.getTime(), result);
   }

   @NotNull
   public Set getMetricNames() {
      Collection var10000 = this.history.values();
      Intrinsics.checkNotNullExpressionValue(var10000, "<get-values>(...)");
      Iterable $this$map$iv = (Iterable)var10000;
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         Map it = (Map)item$iv$iv;
         int var9 = 0;
         destination$iv$iv.add(it.keySet());
      }

      return CollectionsKt.toSet((Iterable)CollectionsKt.flatten((Iterable)((List)destination$iv$iv)));
   }

   @NotNull
   public TimeSeries getMetric(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      List timeline = (List)(new ArrayList());
      List values = (List)(new ArrayList());

      for(Map.Entry var5 : ((Map)this.history).entrySet()) {
         Instant t = (Instant)var5.getKey();
         Map d = (Map)var5.getValue();
         if (d.containsKey(name)) {
            timeline.add(t);
            values.add(MapsKt.getValue(d, name));
         }
      }

      return new TimeSeries(timeline, CollectionsKt.toDoubleArray((Collection)values));
   }
}
