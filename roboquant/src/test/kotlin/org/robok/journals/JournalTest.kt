package org.robok.journals

import org.junit.jupiter.api.assertDoesNotThrow
import org.robok.common.years
import org.robok.run
import org.robok.feeds.random.RandomWalk
import org.robok.journals.metrics.AccountMetric
import org.robok.strategies.EMACrossover
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue


internal class JournalTest {

    @Test
    fun basic() {
        val feed = RandomWalk.lastYears(5)
        val journal = _root_ide_package_.org.robok.journals.BasicJournal()
        run(feed, EMACrossover(), journal = journal)
        assertTrue(journal.nEvents > 0)
        assertTrue(journal.nOrders > 0)
        assertTrue(journal.maxPositions > 0)
        assertTrue(journal.nItems > 0)
        assertTrue(journal.nSignals > 0)
        assertTrue(journal.lastTime!! > Instant.MIN)
    }

    @Test
    fun multiRun() {
        val feed = RandomWalk.lastYears(5)
        val mrj = _root_ide_package_.org.robok.journals.MultiRunJournal {
            _root_ide_package_.org.robok.journals.MemoryJournal(AccountMetric())
        }
        val tfs = feed.timeframe.split(1.years)
        for (tf in tfs) {
            run(feed, EMACrossover(), journal = mrj.getJournal(), timeframe = tf)
        }
        assertContains(mrj.getMetricNames(), "account.equity")
        assertEquals(tfs.size, mrj.getRuns().size)
        assertTrue(mrj.getMetric("account.equity").isNotEmpty())
        assertDoesNotThrow {
            mrj.load(listOf("run-1"))
            mrj.getMetric("fdjshfdhsf", "fhdjkfhdjk")
        }
    }
}
