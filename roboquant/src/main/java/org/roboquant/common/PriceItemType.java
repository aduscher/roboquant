package org.roboquant.common;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"},
   d2 = {"Lorg/roboquant/common/PriceItemType;", "", "(Ljava/lang/String;I)V", "BAR", "QUOTE", "TRADE", "BOOK", "roboquant"}
)
public enum PriceItemType {
   BAR,
   QUOTE,
   TRADE,
   BOOK;

   // $FF: synthetic field
   private static final EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

   @NotNull
   public static EnumEntries getEntries() {
      return $ENTRIES;
   }

   // $FF: synthetic method
   private static final PriceItemType[] $values() {
      PriceItemType[] var0 = new PriceItemType[]{BAR, QUOTE, TRADE, BOOK};
      return var0;
   }
}
