package org.roboquant.common;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.NamedCsvRecord;
import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R \u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"},
   d2 = {"Lorg/roboquant/common/SP500;", "Lorg/roboquant/common/Universe;", "()V", "assets", "", "Lkotlin/Pair;", "Lorg/roboquant/common/Asset;", "Lorg/roboquant/common/Timeframe;", "fileName", "", "startSP500", "Ljava/time/LocalDate;", "kotlin.jvm.PlatformType", "getAssets", "time", "Ljava/time/Instant;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nUniverse.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Universe.kt\norg/roboquant/common/SP500\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,88:1\n1549#2:89\n1620#2,3:90\n766#2:93\n857#2,2:94\n1549#2:96\n1620#2,3:97\n*S KotlinDebug\n*F\n+ 1 Universe.kt\norg/roboquant/common/SP500\n*L\n68#1:89\n68#1:90,3\n84#1:93\n84#1:94,2\n84#1:96\n84#1:97,3\n*E\n"})
final class SP500 implements Universe {
   private final LocalDate startSP500 = LocalDate.parse((CharSequence)"1960-01-01");
   @NotNull
   private final List assets;
   @NotNull
   private final String fileName = "/sp500.csv";

   public SP500() {
      InputStream var10000 = SP500.class.getResourceAsStream(this.fileName);
      if (var10000 == null) {
         throw new RoboquantException("Couldn't find file " + this.fileName);
      } else {
         InputStream stream = var10000;
         Closeable var2 = (Closeable)stream;
         Throwable var3 = null;

         try {
            InputStream inputStream = (InputStream)var2;
            int var5 = 0;
            byte[] var33 = inputStream.readAllBytes();
            Intrinsics.checkNotNullExpressionValue(var33, "readAllBytes(...)");
            byte[] var6 = var33;
            Charset var34 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(var34, "UTF_8");
            Charset var7 = var34;
            String content = new String(var6, var7);
            CsvReader builder = CsvReader.builder().fieldSeparator(';').ofNamedCsvRecord(content);
            Exchange us = Exchange.Companion.getInstance("US");
            Intrinsics.checkNotNull(builder);
            Iterable $this$map$iv = (Iterable)builder;
            int $i$f$map = 0;
            Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
            int $i$f$mapTo = 0;

            for(Object item$iv$iv : $this$map$iv) {
               NamedCsvRecord it = (NamedCsvRecord)item$iv$iv;
               int var19 = 0;
               String symbol = it.getField("Symbol");
               Intrinsics.checkNotNull(symbol);
               Stock asset = new Stock(symbol, Currency.Companion.getUSD());
               String date = it.getField("Date");
               Intrinsics.checkNotNull(date);
               LocalDate startDate = ((CharSequence)date).length() > 0 ? LocalDate.parse((CharSequence)it.getField("Date")) : this.startSP500;
               Instant start = startDate.atTime(0, 0).atZone(us.getZoneId()).toInstant();
               Intrinsics.checkNotNull(start);
               Timeframe timeframe = new Timeframe(start, Timeframe.Companion.getMAX(), false, 4, (DefaultConstructorMarker)null);
               destination$iv$iv.add(new Pair(asset, timeframe));
            }

            this.assets = (List)destination$iv$iv;
            Unit var30 = Unit.INSTANCE;
         } catch (Throwable var28) {
            var3 = var28;
            throw var28;
         } finally {
            CloseableKt.closeFinally(var2, var3);
         }

      }
   }

   @NotNull
   public List getAssets(@NotNull Instant time) {
      Intrinsics.checkNotNullParameter(time, "time");
      Iterable $this$filter$iv = (Iterable)this.assets;
      int $i$f$filter = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$filterTo = 0;

      for(Object element$iv$iv : $this$filter$iv) {
         Pair it = (Pair)element$iv$iv;
         int var10 = 0;
         if (((Timeframe)it.getSecond()).contains(time)) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      $this$filter$iv = (Iterable)((List)destination$iv$iv);
      $i$f$filter = 0;
      destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filter$iv, 10)));
      $i$f$filterTo = 0;

      for(Object item$iv$iv : $this$filter$iv) {
         Pair it = (Pair)item$iv$iv;
         int var19 = 0;
         destination$iv$iv.add((Asset)it.getFirst());
      }

      return (List)destination$iv$iv;
   }
}
