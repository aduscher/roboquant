package org.roboquant.optimize;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.ClosedRange;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u001c\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\u001a!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u0002H\u0086\u0004¨\u0006\u0004"},
   d2 = {"step", "", "", "Lkotlin/ranges/ClosedRange;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nSearchSpace.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SearchSpace.kt\norg/roboquant/optimize/SearchSpaceKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,186:1\n1#2:187\n*E\n"})
public final class SearchSpaceKt {
   @NotNull
   public static final Iterable step(@NotNull final ClosedRange $this$step, final double step) {
      Intrinsics.checkNotNullParameter($this$step, "<this>");
      double var3 = ((Number)$this$step.getStart()).doubleValue();
      if (Double.isInfinite(var3) || Double.isNaN(var3)) {
         String var9 = "Failed requirement.";
         throw new IllegalArgumentException(var9.toString());
      } else {
         var3 = ((Number)$this$step.getEndInclusive()).doubleValue();
         if (Double.isInfinite(var3) || Double.isNaN(var3)) {
            String var8 = "Failed requirement.";
            throw new IllegalArgumentException(var8.toString());
         } else if (!(step > (double)0.0F)) {
            int var4 = 0;
            String var7 = "Step must be positive, was: " + step + ".";
            throw new IllegalArgumentException(var7.toString());
         } else {
            Sequence sequence = SequencesKt.generateSequence($this$step.getStart(), new Function1() {
               @Nullable
               public final Double invoke(double previous) {
                  if (previous == Double.POSITIVE_INFINITY) {
                     return null;
                  } else {
                     double next = previous + step;
                     return next > ((Number)$this$step.getEndInclusive()).doubleValue() ? null : next;
                  }
               }
            });
            return SequencesKt.asIterable(sequence);
         }
      }
   }
}
