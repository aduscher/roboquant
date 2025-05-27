package org.roboquant.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"},
   d2 = {"Lorg/roboquant/common/DoesNotComputeException;", "Lorg/roboquant/common/RoboquantException;", "msg", "", "(Ljava/lang/String;)V", "roboquant"}
)
public final class DoesNotComputeException extends RoboquantException {
   public DoesNotComputeException(@NotNull String msg) {
      Intrinsics.checkNotNullParameter(msg, "msg");
      super(msg);
   }
}
