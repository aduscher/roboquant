package org.roboquant.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\bf\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0016J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0000H\u0096\u0002J\b\u0010\r\u001a\u00020\u0007H&J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"},
   d2 = {"Lorg/roboquant/common/Asset;", "", "currency", "Lorg/roboquant/common/Currency;", "getCurrency", "()Lorg/roboquant/common/Currency;", "symbol", "", "getSymbol", "()Ljava/lang/String;", "compareTo", "", "other", "serialize", "value", "Lorg/roboquant/common/Amount;", "size", "Lorg/roboquant/common/Size;", "price", "", "value-u_zS35g", "(JD)Lorg/roboquant/common/Amount;", "Companion", "roboquant"}
)
public interface Asset extends Comparable {
   @NotNull
   Companion Companion = Asset.Companion.$$INSTANCE;
   @NotNull
   String SEP = ";";

   @NotNull
   String getSymbol();

   @NotNull
   Currency getCurrency();

   @NotNull
   String serialize();

   @NotNull
   Amount value_u_zS35g/* $FF was: value-u_zS35g*/(long var1, double var3);

   int compareTo(@NotNull Asset var1);

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"},
      d2 = {"Lorg/roboquant/common/Asset$Companion;", "", "()V", "SEP", "", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "Lorg/roboquant/common/Asset;", "registry", "", "Lkotlin/Function1;", "getRegistry", "()Ljava/util/Map;", "deserialize", "value", "roboquant"}
   )
   @SourceDebugExtension({"SMAP\nAsset.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Asset.kt\norg/roboquant/common/Asset$Companion\n+ 2 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,186:1\n72#2,2:187\n1#3:189\n*S KotlinDebug\n*F\n+ 1 Asset.kt\norg/roboquant/common/Asset$Companion\n*L\n58#1:187,2\n58#1:189\n*E\n"})
   public static final class Companion {
      // $FF: synthetic field
      static final Companion $$INSTANCE = new Companion();
      @NotNull
      public static final String SEP = ";";
      @NotNull
      private static final ConcurrentHashMap cache = new ConcurrentHashMap();
      @NotNull
      private static final Map registry = (Map)(new LinkedHashMap());

      private Companion() {
      }

      @NotNull
      public final Map getRegistry() {
         return registry;
      }

      @NotNull
      public final Asset deserialize(@NotNull String value) {
         Intrinsics.checkNotNullParameter(value, "value");
         ConcurrentMap $this$getOrPut$iv = (ConcurrentMap)cache;
         int $i$f$getOrPut = 0;
         Object var10000 = $this$getOrPut$iv.get(value);
         if (var10000 == null) {
            int var4 = 0;
            CharSequence var11 = (CharSequence)value;
            String assetType = new String[]{";"};
            List var6 = StringsKt.split$default(var11, assetType, false, 2, 2, (Object)null);
            assetType = (String)var6.get(0);
            String serString = (String)var6.get(1);
            Object default$iv = (Asset)((Function1)MapsKt.getValue(registry, assetType)).invoke(serString);
            int var9 = 0;
            var10000 = $this$getOrPut$iv.putIfAbsent(value, default$iv);
            if (var10000 == null) {
               var10000 = default$iv;
            }
         }

         Intrinsics.checkNotNullExpressionValue(var10000, "getOrPut(...)");
         return (Asset)var10000;
      }

      static {
         registry.put("Crypto", new Function1(Crypto.Companion) {
            @NotNull
            public final Asset invoke(@NotNull String p0) {
               Intrinsics.checkNotNullParameter(p0, "p0");
               return ((Crypto.Companion)this.receiver).deserialize(p0);
            }
         });
         registry.put("Stock", new Function1(Stock.Companion) {
            @NotNull
            public final Asset invoke(@NotNull String p0) {
               Intrinsics.checkNotNullParameter(p0, "p0");
               return ((Stock.Companion)this.receiver).deserialize(p0);
            }
         });
         registry.put("Option", new Function1(Option.Companion) {
            @NotNull
            public final Asset invoke(@NotNull String p0) {
               Intrinsics.checkNotNullParameter(p0, "p0");
               return ((Option.Companion)this.receiver).deserialize(p0);
            }
         });
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      @NotNull
      public static Amount value_u_zS35g/* $FF was: value-u_zS35g*/(@NotNull Asset $this, long size, double price) {
         return Size.getIszero-impl(size) ? new Amount($this.getCurrency(), (double)0.0F) : new Amount($this.getCurrency(), Size.toDouble-impl(size) * price);
      }

      public static int compareTo(@NotNull Asset $this, @NotNull Asset other) {
         Intrinsics.checkNotNullParameter(other, "other");
         return $this.getSymbol().compareTo(other.getSymbol());
      }
   }
}
