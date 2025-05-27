package org.roboquant.feeds.csv;

import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.ConfigurationException;
import org.roboquant.common.Exchange;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0016\u0010\u000f\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0011H\u0016J\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0011H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"},
   d2 = {"Lorg/roboquant/feeds/csv/AutoDetectTimeParser;", "Lorg/roboquant/feeds/csv/TimeParser;", "timeColumn", "", "exchange", "Lorg/roboquant/common/Exchange;", "(ILorg/roboquant/common/Exchange;)V", "getExchange", "()Lorg/roboquant/common/Exchange;", "parser", "Lorg/roboquant/feeds/csv/AuteDetectParser;", "detect", "", "sample", "", "init", "header", "", "parse", "Ljava/time/Instant;", "line", "Patterns", "roboquant"}
)
@SourceDebugExtension({"SMAP\nTimeParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeParser.kt\norg/roboquant/feeds/csv/AutoDetectTimeParser\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,150:1\n1864#2,3:151\n288#2,2:154\n*S KotlinDebug\n*F\n+ 1 TimeParser.kt\norg/roboquant/feeds/csv/AutoDetectTimeParser\n*L\n95#1:151,3\n142#1:154,2\n*E\n"})
public final class AutoDetectTimeParser implements TimeParser {
   @NotNull
   private static final Patterns Patterns = new Patterns((DefaultConstructorMarker)null);
   private int timeColumn;
   @NotNull
   private final Exchange exchange;
   private AuteDetectParser parser;
   @NotNull
   private static final List patterns;

   public AutoDetectTimeParser(int timeColumn, @NotNull Exchange exchange) {
      Intrinsics.checkNotNullParameter(exchange, "exchange");
      super();
      this.timeColumn = timeColumn;
      this.exchange = exchange;
   }

   // $FF: synthetic method
   public AutoDetectTimeParser(int var1, Exchange var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 1) != 0) {
         var1 = -1;
      }

      if ((var3 & 2) != 0) {
         var2 = Exchange.Companion.getUS();
      }

      this(var1, var2);
   }

   @NotNull
   public final Exchange getExchange() {
      return this.exchange;
   }

   public void init(@NotNull List header) {
      Intrinsics.checkNotNullParameter(header, "header");
      if (this.timeColumn == -1) {
         Regex notCapital = new Regex("[^A-Z]");
         Iterable $this$forEachIndexed$iv = (Iterable)header;
         int $i$f$forEachIndexed = 0;
         int index$iv = 0;

         for(Object item$iv : $this$forEachIndexed$iv) {
            int index = index$iv++;
            if (index < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            String column = (String)item$iv;
            int var11 = 0;
            String var15 = column.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(var15, "toUpperCase(...)");
            CharSequence var12 = (CharSequence)var15;
            String var13 = "";
            switch (var14) {
               case "DATETIME":
                  this.timeColumn = index;
                  break;
               case "TIMESTAMP":
                  this.timeColumn = index;
                  break;
               case "DAY":
                  this.timeColumn = index;
                  break;
               case "DATE":
                  this.timeColumn = index;
                  break;
               case "TIME":
                  this.timeColumn = index;
            }
         }

      }
   }

   @NotNull
   public Instant parse(@NotNull List line) {
      Intrinsics.checkNotNullParameter(line, "line");
      String text = (String)line.get(this.timeColumn);
      if (this.parser == null) {
         this.detect(text);
      }

      AuteDetectParser var10000 = this.parser;
      if (var10000 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("parser");
         var10000 = null;
      }

      return var10000.parse(text);
   }

   private final void detect(String sample) {
      synchronized(this){}

      try {
         int var3 = 0;
         if (this.parser == null) {
            Iterable $this$firstOrNull$iv = (Iterable)patterns;
            int $i$f$firstOrNull = 0;
            Iterator var6 = $this$firstOrNull$iv.iterator();

            Object var10000;
            while(true) {
               if (var6.hasNext()) {
                  Object element$iv = var6.next();
                  Pair it = (Pair)element$iv;
                  int var9 = 0;
                  if (!((Regex)it.getFirst()).matches((CharSequence)sample)) {
                     continue;
                  }

                  var10000 = element$iv;
                  break;
               }

               var10000 = null;
               break;
            }

            Pair var14 = (Pair)var10000;
            if (var14 == null) {
               throw new ConfigurationException("No suitable time parser found for time=" + sample);
            }

            Pair match = var14;
            this.parser = (AuteDetectParser)match.getSecond();
         }

         Unit var13 = Unit.INSTANCE;
      } finally {
         ;
      }

   }

   private static final Instant patterns$lambda$3(String text) {
      Intrinsics.checkNotNullParameter(text, "text");
      Instant var10000 = Instant.parse((CharSequence)text);
      Intrinsics.checkNotNullExpressionValue(var10000, "parse(...)");
      return var10000;
   }

   private static final Instant patterns$lambda$4(String text) {
      Intrinsics.checkNotNullParameter(text, "text");
      Instant var10000 = Instant.ofEpochMilli(Long.parseLong(text));
      Intrinsics.checkNotNullExpressionValue(var10000, "ofEpochMilli(...)");
      return var10000;
   }

   public AutoDetectTimeParser() {
      this(0, (Exchange)null, 3, (DefaultConstructorMarker)null);
   }

   static {
      Pair[] var0 = new Pair[]{TuplesKt.to(new Regex("19\\d{6}"), new LocalDateParser("yyyyMMdd", (Exchange)null, 2, (DefaultConstructorMarker)null)), TuplesKt.to(new Regex("20\\d{6}"), new LocalDateParser("yyyyMMdd", (Exchange)null, 2, (DefaultConstructorMarker)null)), TuplesKt.to(new Regex("\\d{8} \\d{6}"), new LocalTimeParser("yyyyMMdd HHmmss", (Exchange)null, 2, (DefaultConstructorMarker)null)), TuplesKt.to(new Regex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"), AutoDetectTimeParser::patterns$lambda$3), TuplesKt.to(new Regex("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"), new LocalTimeParser("yyyy-MM-dd HH:mm:ss", (Exchange)null, 2, (DefaultConstructorMarker)null)), TuplesKt.to(new Regex("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}"), new LocalTimeParser("yyyy-MM-dd HH:mm", (Exchange)null, 2, (DefaultConstructorMarker)null)), TuplesKt.to(new Regex("\\d{4}-\\d{2}-\\d{2}"), new LocalDateParser("yyyy-MM-dd", (Exchange)null, 2, (DefaultConstructorMarker)null)), TuplesKt.to(new Regex("\\d{8} \\d{2}:\\d{2}:\\d{2}"), new LocalTimeParser("yyyyMMdd HH:mm:ss", (Exchange)null, 2, (DefaultConstructorMarker)null)), TuplesKt.to(new Regex("\\d{8}  \\d{2}:\\d{2}:\\d{2}"), new LocalTimeParser("yyyyMMdd  HH:mm:ss", (Exchange)null, 2, (DefaultConstructorMarker)null)), TuplesKt.to(new Regex("-?\\d{1,19}"), AutoDetectTimeParser::patterns$lambda$4)};
      patterns = CollectionsKt.listOf(var0);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R&\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002¨\u0006\t"},
      d2 = {"Lorg/roboquant/feeds/csv/AutoDetectTimeParser$Patterns;", "", "()V", "patterns", "", "Lkotlin/Pair;", "Lkotlin/text/Regex;", "Lorg/roboquant/feeds/csv/AuteDetectParser;", "getPatterns$annotations", "roboquant"}
   )
   private static final class Patterns {
      private Patterns() {
      }

      /** @deprecated */
      // $FF: synthetic method
      private static void getPatterns$annotations() {
      }

      // $FF: synthetic method
      public Patterns(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
