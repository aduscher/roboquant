package org.roboquant.optimize;

import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0002J\u000e\u0010\u000b\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002¨\u0006\f"},
   d2 = {"Lorg/roboquant/optimize/Params;", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "()V", "getDouble", "", "name", "getInt", "", "getString", "roboquant"}
)
public final class Params extends LinkedHashMap {
   @NotNull
   public final String getString(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Object var10000 = this.get((Object)name);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.String");
      return (String)var10000;
   }

   public final int getInt(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Object var10000 = this.get((Object)name);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.Int");
      return (Integer)var10000;
   }

   public final double getDouble(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Object var10000 = this.get((Object)name);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.Double");
      return (Double)var10000;
   }
}
