package org.roboquant.common;

import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\b\u001a\u00020\t*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\u001a\u0016\u0010\n\u001a\u00020\t*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\u001a\u0016\u0010\u000b\u001a\u00020\t*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"-\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00018F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"-\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00018F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\f"},
   d2 = {"long", "", "Lorg/roboquant/common/Asset;", "Lorg/roboquant/common/Position;", "getLong", "(Ljava/util/Map;)Ljava/util/Map;", "short", "getShort", "exposure", "Lorg/roboquant/common/Wallet;", "marketValue", "pnl", "roboquant"}
)
@SourceDebugExtension({"SMAP\nPosition.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Position.kt\norg/roboquant/common/PositionKt\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,121:1\n494#2,7:122\n494#2,7:129\n*S KotlinDebug\n*F\n+ 1 Position.kt\norg/roboquant/common/PositionKt\n*L\n83#1:122,7\n89#1:129,7\n*E\n"})
public final class PositionKt {
   @NotNull
   public static final Map getLong(@NotNull Map $this$long) {
      Intrinsics.checkNotNullParameter($this$long, "<this>");
      int $i$f$filterValues = 0;
      LinkedHashMap result$iv = new LinkedHashMap();

      for(Map.Entry entry$iv : $this$long.entrySet()) {
         Position it = (Position)entry$iv.getValue();
         int var7 = 0;
         if (it.getLong()) {
            result$iv.put(entry$iv.getKey(), entry$iv.getValue());
         }
      }

      return (Map)result$iv;
   }

   @NotNull
   public static final Map getShort(@NotNull Map $this$short) {
      Intrinsics.checkNotNullParameter($this$short, "<this>");
      int $i$f$filterValues = 0;
      LinkedHashMap result$iv = new LinkedHashMap();

      for(Map.Entry entry$iv : $this$short.entrySet()) {
         Position it = (Position)entry$iv.getValue();
         int var7 = 0;
         if (it.getShort()) {
            result$iv.put(entry$iv.getKey(), entry$iv.getValue());
         }
      }

      return (Map)result$iv;
   }

   @NotNull
   public static final Wallet marketValue(@NotNull Map $this$marketValue) {
      Intrinsics.checkNotNullParameter($this$marketValue, "<this>");
      Wallet result = new Wallet((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);

      for(Map.Entry var3 : $this$marketValue.entrySet()) {
         Asset asset = (Asset)var3.getKey();
         Position position = (Position)var3.getValue();
         Amount positionValue = asset.value-u_zS35g(position.getSize-vehRhPc(), position.getMktPrice());
         result.deposit(positionValue);
      }

      return result;
   }

   @NotNull
   public static final Wallet pnl(@NotNull Map $this$pnl) {
      Intrinsics.checkNotNullParameter($this$pnl, "<this>");
      Wallet result = new Wallet((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);

      for(Map.Entry var3 : $this$pnl.entrySet()) {
         Asset asset = (Asset)var3.getKey();
         Position position = (Position)var3.getValue();
         Amount positionValue = asset.value-u_zS35g(position.getSize-vehRhPc(), position.getMktPrice() - position.getAvgPrice());
         result.deposit(positionValue);
      }

      return result;
   }

   @NotNull
   public static final Wallet exposure(@NotNull Map $this$exposure) {
      Intrinsics.checkNotNullParameter($this$exposure, "<this>");
      Wallet result = new Wallet((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);

      for(Map.Entry var3 : $this$exposure.entrySet()) {
         Asset asset = (Asset)var3.getKey();
         Position position = (Position)var3.getValue();
         Amount positionValue = asset.value-u_zS35g(Size.getAbsoluteValue-vehRhPc(position.getSize-vehRhPc()), position.getMktPrice());
         result.deposit(positionValue);
      }

      return result;
   }
}
