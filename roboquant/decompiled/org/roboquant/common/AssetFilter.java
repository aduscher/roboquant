package org.roboquant.common;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"},
   d2 = {"Lorg/roboquant/common/AssetFilter;", "", "filter", "", "asset", "Lorg/roboquant/common/Asset;", "time", "Ljava/time/Instant;", "Companion", "roboquant"}
)
public interface AssetFilter {
   @NotNull
   Companion Companion = AssetFilter.Companion.$$INSTANCE;

   boolean filter(@NotNull Asset var1, @NotNull Instant var2);

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u001f\u0010\u0007\u001a\u00020\u00062\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\n¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u0007\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\fJ\u001f\u0010\r\u001a\u00020\u00062\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\n¢\u0006\u0002\u0010\u000bJ\u0014\u0010\r\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\fJ\f\u0010\u000e\u001a\u00020\n*\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"},
      d2 = {"Lorg/roboquant/common/AssetFilter$Companion;", "", "()V", "regEx", "Lkotlin/text/Regex;", "all", "Lorg/roboquant/common/AssetFilter;", "excludeSymbols", "symbols", "", "", "([Ljava/lang/String;)Lorg/roboquant/common/AssetFilter;", "", "includeSymbols", "standardize", "roboquant"}
   )
   @SourceDebugExtension({"SMAP\nAssetFilter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AssetFilter.kt\norg/roboquant/common/AssetFilter$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,87:1\n1549#2:88\n1620#2,3:89\n1549#2:92\n1620#2,3:93\n*S KotlinDebug\n*F\n+ 1 AssetFilter.kt\norg/roboquant/common/AssetFilter$Companion\n*L\n63#1:88\n63#1:89,3\n79#1:92\n79#1:93,3\n*E\n"})
   public static final class Companion {
      // $FF: synthetic field
      static final Companion $$INSTANCE = new Companion();
      @NotNull
      private static final Regex regEx = new Regex("[^A-Z0-9]");

      private Companion() {
      }

      @NotNull
      public final AssetFilter all() {
         return Companion::all$lambda$0;
      }

      private final String standardize(String $this$standardize) {
         String var10000 = $this$standardize.toUpperCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var10000, "toUpperCase(...)");
         CharSequence var2 = (CharSequence)var10000;
         Regex var3 = regEx;
         String var4 = ".";
         return var3.replace(var2, var4);
      }

      @NotNull
      public final AssetFilter includeSymbols(@NotNull String... symbols) {
         Intrinsics.checkNotNullParameter(symbols, "symbols");
         return this.includeSymbols((Collection)ArraysKt.toSet(symbols));
      }

      @NotNull
      public final AssetFilter includeSymbols(@NotNull Collection symbols) {
         Intrinsics.checkNotNullParameter(symbols, "symbols");
         Iterable $this$map$iv = (Iterable)symbols;
         int $i$f$map = 0;
         Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
         int $i$f$mapTo = 0;

         for(Object item$iv$iv : $this$map$iv) {
            String it = (String)item$iv$iv;
            int var11 = 0;
            destination$iv$iv.add($$INSTANCE.standardize(it));
         }

         Set set = CollectionsKt.toSet((Iterable)((List)destination$iv$iv));
         return Companion::includeSymbols$lambda$2;
      }

      @NotNull
      public final AssetFilter excludeSymbols(@NotNull String... symbols) {
         Intrinsics.checkNotNullParameter(symbols, "symbols");
         return this.excludeSymbols((Collection)ArraysKt.toSet(symbols));
      }

      @NotNull
      public final AssetFilter excludeSymbols(@NotNull Collection symbols) {
         Intrinsics.checkNotNullParameter(symbols, "symbols");
         Iterable $this$map$iv = (Iterable)symbols;
         int $i$f$map = 0;
         Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
         int $i$f$mapTo = 0;

         for(Object item$iv$iv : $this$map$iv) {
            String it = (String)item$iv$iv;
            int var11 = 0;
            destination$iv$iv.add($$INSTANCE.standardize(it));
         }

         Set set = CollectionsKt.toSet((Iterable)((List)destination$iv$iv));
         return Companion::excludeSymbols$lambda$4;
      }

      private static final boolean all$lambda$0(Asset var0, Instant var1) {
         Intrinsics.checkNotNullParameter(var0, "<anonymous parameter 0>");
         Intrinsics.checkNotNullParameter(var1, "<anonymous parameter 1>");
         return true;
      }

      private static final boolean includeSymbols$lambda$2(Set $set, Asset asset, Instant var2) {
         Intrinsics.checkNotNullParameter($set, "$set");
         Intrinsics.checkNotNullParameter(asset, "asset");
         Intrinsics.checkNotNullParameter(var2, "<anonymous parameter 1>");
         return $set.contains($$INSTANCE.standardize(asset.getSymbol()));
      }

      private static final boolean excludeSymbols$lambda$4(Set $set, Asset asset, Instant var2) {
         Intrinsics.checkNotNullParameter($set, "$set");
         Intrinsics.checkNotNullParameter(asset, "asset");
         Intrinsics.checkNotNullParameter(var2, "<anonymous parameter 1>");
         return !$set.contains($$INSTANCE.standardize(asset.getSymbol()));
      }
   }
}
