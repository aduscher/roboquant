package org.roboquant.feeds.csv;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Exchange;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"},
   d2 = {"Lorg/roboquant/feeds/csv/LocalDateParser;", "Lorg/roboquant/feeds/csv/AuteDetectParser;", "pattern", "", "exchange", "Lorg/roboquant/common/Exchange;", "(Ljava/lang/String;Lorg/roboquant/common/Exchange;)V", "dtf", "Ljava/time/format/DateTimeFormatter;", "getExchange", "()Lorg/roboquant/common/Exchange;", "parse", "Ljava/time/Instant;", "text", "roboquant"}
)
final class LocalDateParser implements AuteDetectParser {
   @NotNull
   private final Exchange exchange;
   @NotNull
   private final DateTimeFormatter dtf;

   public LocalDateParser(@NotNull String pattern, @NotNull Exchange exchange) {
      Intrinsics.checkNotNullParameter(pattern, "pattern");
      Intrinsics.checkNotNullParameter(exchange, "exchange");
      super();
      this.exchange = exchange;
      DateTimeFormatter var10001 = DateTimeFormatter.ofPattern(pattern);
      Intrinsics.checkNotNullExpressionValue(var10001, "ofPattern(...)");
      this.dtf = var10001;
   }

   // $FF: synthetic method
   public LocalDateParser(String var1, Exchange var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = Exchange.Companion.getUS();
      }

      this(var1, var2);
   }

   @NotNull
   public final Exchange getExchange() {
      return this.exchange;
   }

   @NotNull
   public Instant parse(@NotNull String text) {
      Intrinsics.checkNotNullParameter(text, "text");
      LocalDate date = LocalDate.parse((CharSequence)text, this.dtf);
      Exchange var10000 = this.exchange;
      Intrinsics.checkNotNull(date);
      return var10000.getClosingTime(date);
   }
}
