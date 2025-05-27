package org.roboquant.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"},
   d2 = {"Lorg/roboquant/common/CorporateItem;", "Lorg/roboquant/common/Item;", "asset", "Lorg/roboquant/common/Asset;", "type", "", "value", "", "(Lorg/roboquant/common/Asset;Ljava/lang/String;D)V", "getAsset", "()Lorg/roboquant/common/Asset;", "getType", "()Ljava/lang/String;", "getValue", "()D", "roboquant"}
)
public final class CorporateItem implements Item {
   @NotNull
   private final Asset asset;
   @NotNull
   private final String type;
   private final double value;

   public CorporateItem(@NotNull Asset asset, @NotNull String type, double value) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(type, "type");
      super();
      this.asset = asset;
      this.type = type;
      this.value = value;
   }

   @NotNull
   public final Asset getAsset() {
      return this.asset;
   }

   @NotNull
   public final String getType() {
      return this.type;
   }

   public final double getValue() {
      return this.value;
   }
}
