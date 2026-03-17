/*
 * Copyright 2020-2026 Neural Layer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.roboquant.journals;

import org.roboquant.common.TimeSeries;

import java.util.*;

/**
 * Journal that supports multiple runs and tracks them separately.
 */
public class MultiRunJournal {

    private static int cnt = 0;

    private final java.util.function.Function<String, MetricsJournal> journalFactory;
    private final Map<String, MetricsJournal> journals;

    public MultiRunJournal(java.util.function.Function<String, MetricsJournal> journalFactory) {
        this.journalFactory = journalFactory;
        this.journals = new LinkedHashMap<>();
    }

    public static String nextRun() {
        return "run-" + (cnt++);
    }

    public synchronized MetricsJournal getJournal(String run) {
        if (!journals.containsKey(run)) {
            MetricsJournal journal = journalFactory.apply(run);
            journals.put(run, journal);
        }
        return journals.get(run);
    }

    public synchronized MetricsJournal getJournal() {
        return getJournal(nextRun());
    }

    public void load(Collection<String> runs) {
        for (String run : runs) {
            getJournal(run);
        }
    }

    public Set<String> getRuns() {
        return journals.keySet();
    }

    public Map<String, TimeSeries> getMetric(String name) {
        Map<String, TimeSeries> result = new LinkedHashMap<>();
        for (Map.Entry<String, MetricsJournal> entry : journals.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getMetric(name));
        }
        return result;
    }

    public TimeSeries getMetric(String name, String run) {
        MetricsJournal journal = journals.get(run);
        if (journal != null) {
            TimeSeries ts = journal.getMetric(name);
            if (ts != null) {
                return ts;
            }
        }
        return new TimeSeries(Collections.emptyList(), new double[0]);
    }

    public Set<String> getMetricNames() {
        Set<String> result = new LinkedHashSet<>();
        for (MetricsJournal journal : journals.values()) {
            result.addAll(journal.getMetricNames());
        }
        return result;
    }

    public synchronized void reset() {
        journals.clear();
    }
}
