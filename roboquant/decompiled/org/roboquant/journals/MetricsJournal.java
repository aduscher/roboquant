package org.roboquant.journals;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.TimeSeries;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&¨\u0006\b"},
   d2 = {"Lorg/roboquant/journals/MetricsJournal;", "Lorg/roboquant/journals/Journal;", "getMetric", "Lorg/roboquant/common/TimeSeries;", "name", "", "getMetricNames", "", "roboquant"}
)
public interface MetricsJournal extends Journal {
   @NotNull
   Set getMetricNames();

   @NotNull
   TimeSeries getMetric(@NotNull String var1);
}
