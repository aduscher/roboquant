package org.roboquant.journals;

import java.time.Instant;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Account;
import org.roboquant.common.Event;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010 \u001a\u00020!H\u0016J4\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020,0)H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u001a\u0010\u001d\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"},
   d2 = {"Lorg/roboquant/journals/BasicJournal;", "Lorg/roboquant/journals/Journal;", "printToConsole", "", "(Z)V", "lastTime", "Ljava/time/Instant;", "getLastTime", "()Ljava/time/Instant;", "setLastTime", "(Ljava/time/Instant;)V", "maxPositions", "", "getMaxPositions", "()I", "setMaxPositions", "(I)V", "nEvents", "", "getNEvents", "()J", "setNEvents", "(J)V", "nItems", "getNItems", "setNItems", "nOrders", "getNOrders", "setNOrders", "nSignals", "getNSignals", "setNSignals", "toString", "", "track", "", "event", "Lorg/roboquant/common/Event;", "account", "Lorg/roboquant/common/Account;", "signals", "", "Lorg/roboquant/common/Signal;", "orders", "Lorg/roboquant/common/Order;", "roboquant"}
)
public final class BasicJournal implements Journal {
   private final boolean printToConsole;
   private long nItems;
   private long nOrders;
   private long nEvents;
   private int maxPositions;
   private long nSignals;
   @Nullable
   private Instant lastTime;

   public BasicJournal(boolean printToConsole) {
      this.printToConsole = printToConsole;
   }

   // $FF: synthetic method
   public BasicJournal(boolean var1, int var2, DefaultConstructorMarker var3) {
      if ((var2 & 1) != 0) {
         var1 = false;
      }

      this(var1);
   }

   public final long getNItems() {
      return this.nItems;
   }

   public final void setNItems(long var1) {
      this.nItems = var1;
   }

   public final long getNOrders() {
      return this.nOrders;
   }

   public final void setNOrders(long var1) {
      this.nOrders = var1;
   }

   public final long getNEvents() {
      return this.nEvents;
   }

   public final void setNEvents(long var1) {
      this.nEvents = var1;
   }

   public final int getMaxPositions() {
      return this.maxPositions;
   }

   public final void setMaxPositions(int var1) {
      this.maxPositions = var1;
   }

   public final long getNSignals() {
      return this.nSignals;
   }

   public final void setNSignals(long var1) {
      this.nSignals = var1;
   }

   @Nullable
   public final Instant getLastTime() {
      return this.lastTime;
   }

   public final void setLastTime(@Nullable Instant var1) {
      this.lastTime = var1;
   }

   public void track(@NotNull Event event, @NotNull Account account, @NotNull List signals, @NotNull List orders) {
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(orders, "orders");
      this.nItems += (long)event.getItems().size();
      this.nOrders += (long)orders.size();
      this.nSignals += (long)signals.size();
      ++this.nEvents;
      this.lastTime = event.getTime();
      this.maxPositions = Math.max(this.maxPositions, account.getPositions().size());
      if (this.printToConsole) {
         System.out.println(this);
      }

   }

   @NotNull
   public String toString() {
      return "time=" + this.lastTime + " items=" + this.nItems + " signals=" + this.nSignals + " orders=" + this.nOrders + " max-positions=" + this.maxPositions;
   }

   public BasicJournal() {
      this(false, 1, (DefaultConstructorMarker)null);
   }
}
