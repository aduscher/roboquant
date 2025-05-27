package org.roboquant.ta;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
   d2 = {"Lorg/roboquant/ta/InsufficientData;", "", "indicator", "", "minSize", "", "(Ljava/lang/String;I)V", "getMinSize", "()I", "roboquant"}
)
public final class InsufficientData extends Throwable {
   private final int minSize;

   public InsufficientData(@NotNull String indicator, int minSize) {
      Intrinsics.checkNotNullParameter(indicator, "indicator");
      super("innsuffient data to calculate " + indicator + ", miniumum is " + minSize, (Throwable)null, true, false);
      this.minSize = minSize;
   }

   public final int getMinSize() {
      return this.minSize;
   }
}
