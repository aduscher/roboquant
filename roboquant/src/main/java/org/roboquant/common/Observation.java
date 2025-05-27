package org.roboquant.common;

import java.time.Instant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0000H\u0096\u0002J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\fHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0017"},
   d2 = {"Lorg/roboquant/common/Observation;", "", "time", "Ljava/time/Instant;", "value", "", "(Ljava/time/Instant;D)V", "getTime", "()Ljava/time/Instant;", "getValue", "()D", "compareTo", "", "other", "component1", "component2", "copy", "equals", "", "", "hashCode", "toString", "", "roboquant"}
)
public final class Observation implements Comparable {
   @NotNull
   private final Instant time;
   private final double value;

   public Observation(@NotNull Instant time, double value) {
      Intrinsics.checkNotNullParameter(time, "time");
      super();
      this.time = time;
      this.value = value;
   }

   @NotNull
   public final Instant getTime() {
      return this.time;
   }

   public final double getValue() {
      return this.value;
   }

   public int compareTo(@NotNull Observation other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return this.time.compareTo(other.time);
   }

   @NotNull
   public final Instant component1() {
      return this.time;
   }

   public final double component2() {
      return this.value;
   }

   @NotNull
   public final Observation copy(@NotNull Instant time, double value) {
      Intrinsics.checkNotNullParameter(time, "time");
      return new Observation(time, value);
   }

   // $FF: synthetic method
   public static Observation copy$default(Observation var0, Instant var1, double var2, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.time;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.value;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "Observation(time=" + this.time + ", value=" + this.value + ")";
   }

   public int hashCode() {
      int result = this.time.hashCode();
      result = result * 31 + Double.hashCode(this.value);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Observation)) {
         return false;
      } else {
         Observation var2 = (Observation)other;
         if (!Intrinsics.areEqual(this.time, var2.time)) {
            return false;
         } else {
            return Double.compare(this.value, var2.value) == 0;
         }
      }
   }
}
