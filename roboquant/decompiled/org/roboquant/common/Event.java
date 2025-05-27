package org.roboquant.common;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 !2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001!B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0011\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0000H\u0096\u0002J\u001f\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\u001b¢\u0006\u0002\u0010\u001cJ\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\b\u0010 \u001a\u00020\u001bH\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR'\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\""},
   d2 = {"Lorg/roboquant/common/Event;", "", "time", "Ljava/time/Instant;", "items", "", "Lorg/roboquant/common/Item;", "(Ljava/time/Instant;Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "prices", "", "Lorg/roboquant/common/Asset;", "Lorg/roboquant/common/PriceItem;", "getPrices", "()Ljava/util/Map;", "prices$delegate", "Lkotlin/Lazy;", "getTime", "()Ljava/time/Instant;", "compareTo", "", "other", "getPrice", "", "asset", "type", "", "(Lorg/roboquant/common/Asset;Ljava/lang/String;)Ljava/lang/Double;", "isEmpty", "", "isNotEmpty", "toString", "Companion", "roboquant"}
)
public final class Event implements Comparable {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Instant time;
   @NotNull
   private final List items;
   @NotNull
   private final Lazy prices$delegate;

   public Event(@NotNull Instant time, @NotNull List items) {
      Intrinsics.checkNotNullParameter(time, "time");
      Intrinsics.checkNotNullParameter(items, "items");
      super();
      this.time = time;
      this.items = items;
      this.prices$delegate = LazyKt.lazy(new Function0() {
         @NotNull
         public final HashMap invoke() {
            HashMap result = new HashMap(Event.this.getItems().size());

            for(Item action : Event.this.getItems()) {
               if (action instanceof PriceItem) {
                  ((Map)result).put(((PriceItem)action).getAsset(), action);
               }
            }

            return result;
         }
      });
   }

   @NotNull
   public final Instant getTime() {
      return this.time;
   }

   @NotNull
   public final List getItems() {
      return this.items;
   }

   @NotNull
   public final Map getPrices() {
      Lazy var1 = this.prices$delegate;
      return (Map)var1.getValue();
   }

   @Nullable
   public final Double getPrice(@NotNull Asset asset, @NotNull String type) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(type, "type");
      PriceItem var10000 = (PriceItem)this.getPrices().get(asset);
      return var10000 != null ? var10000.getPrice(type) : null;
   }

   // $FF: synthetic method
   public static Double getPrice$default(Event var0, Asset var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = "DEFAULT";
      }

      return var0.getPrice(var1, var2);
   }

   public int compareTo(@NotNull Event other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return this.time.compareTo(other.time);
   }

   public final boolean isNotEmpty() {
      return !((Collection)this.items).isEmpty();
   }

   public final boolean isEmpty() {
      return this.items.isEmpty();
   }

   @NotNull
   public String toString() {
      Instant var10000 = this.time;
      return "Event(time=" + var10000 + " actions=" + this.items.size() + ")";
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"},
      d2 = {"Lorg/roboquant/common/Event$Companion;", "", "()V", "empty", "Lorg/roboquant/common/Event;", "time", "Ljava/time/Instant;", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Event empty(@NotNull Instant time) {
         Intrinsics.checkNotNullParameter(time, "time");
         return new Event(time, CollectionsKt.emptyList());
      }

      // $FF: synthetic method
      public static Event empty$default(Companion var0, Instant var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            Instant var10000 = Instant.now();
            Intrinsics.checkNotNullExpressionValue(var10000, "now(...)");
            var1 = var10000;
         }

         return var0.empty(var1);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
