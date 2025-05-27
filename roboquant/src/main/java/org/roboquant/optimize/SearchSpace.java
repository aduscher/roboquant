package org.roboquant.optimize;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"},
   d2 = {"Lorg/roboquant/optimize/SearchSpace;", "", "Lorg/roboquant/optimize/Params;", "size", "", "getSize", "()I", "update", "", "params", "score", "", "roboquant"}
)
public interface SearchSpace extends Iterable, KMappedMarker {
   void update(@NotNull Params var1, double var2);

   int getSize();

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      public static void update(@NotNull SearchSpace $this, @NotNull Params params, double score) {
         Intrinsics.checkNotNullParameter(params, "params");
      }
   }
}
