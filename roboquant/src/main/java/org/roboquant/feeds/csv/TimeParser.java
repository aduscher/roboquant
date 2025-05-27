package org.roboquant.feeds.csv;

import java.time.Instant;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\n"},
   d2 = {"Lorg/roboquant/feeds/csv/TimeParser;", "", "init", "", "header", "", "", "parse", "Ljava/time/Instant;", "line", "roboquant"}
)
public interface TimeParser {
   void init(@NotNull List var1);

   @NotNull
   Instant parse(@NotNull List var1);

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      public static void init(@NotNull TimeParser $this, @NotNull List header) {
         Intrinsics.checkNotNullParameter(header, "header");
      }
   }
}
