package org.roboquant.common;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\bB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"},
   d2 = {"Lorg/roboquant/common/NewsItem;", "Lorg/roboquant/common/Item;", "items", "", "Lorg/roboquant/common/NewsItem$NewsItem;", "(Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "NewsItem", "roboquant"}
)
public final class NewsItem implements Item {
   @NotNull
   private final List items;

   public NewsItem(@NotNull List items) {
      Intrinsics.checkNotNullParameter(items, "items");
      super();
      this.items = items;
   }

   @NotNull
   public final List getItems() {
      return this.items;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0006\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"},
      d2 = {"Lorg/roboquant/common/NewsItem$NewsItem;", "", "content", "", "meta", "", "(Ljava/lang/String;Ljava/util/Map;)V", "getContent", "()Ljava/lang/String;", "getMeta", "()Ljava/util/Map;", "roboquant"}
   )
   public static final class NewsItem {
      @NotNull
      private final String content;
      @NotNull
      private final Map meta;

      public NewsItem(@NotNull String content, @NotNull Map meta) {
         Intrinsics.checkNotNullParameter(content, "content");
         Intrinsics.checkNotNullParameter(meta, "meta");
         super();
         this.content = content;
         this.meta = meta;
      }

      @NotNull
      public final String getContent() {
         return this.content;
      }

      @NotNull
      public final Map getMeta() {
         return this.meta;
      }
   }
}
