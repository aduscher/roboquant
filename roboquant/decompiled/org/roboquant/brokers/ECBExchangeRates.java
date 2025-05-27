package org.roboquant.brokers;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Config;
import org.roboquant.common.Currency;
import org.roboquant.common.Logging;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000bH\u0002J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"},
   d2 = {"Lorg/roboquant/brokers/ECBExchangeRates;", "Lorg/roboquant/brokers/TimedExchangeRates;", "url", "", "compressed", "", "useCache", "(Ljava/lang/String;ZZ)V", "logger", "Lorg/roboquant/common/Logging$Logger;", "cache", "Ljava/net/URL;", "load", "", "urlString", "Companion", "roboquant"}
)
@SourceDebugExtension({"SMAP\nECBExchangeRates.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ECBExchangeRates.kt\norg/roboquant/brokers/ECBExchangeRates\n+ 2 Logging.kt\norg/roboquant/common/Logging$Logger\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,174:1\n52#2,3:175\n45#2,3:178\n45#2,3:181\n66#2,3:184\n46#2,2:205\n1549#3:187\n1620#3,3:188\n766#3:191\n857#3,2:192\n1549#3:194\n1620#3,3:195\n372#4,7:198\n*S KotlinDebug\n*F\n+ 1 ECBExchangeRates.kt\norg/roboquant/brokers/ECBExchangeRates\n*L\n83#1:175,3\n100#1:178,3\n137#1:181,3\n140#1:184,3\n167#1:205,2\n148#1:187\n148#1:188,3\n150#1:191\n150#1:192,2\n150#1:194\n150#1:195,3\n163#1:198,7\n*E\n"})
public final class ECBExchangeRates extends TimedExchangeRates {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Logging.Logger logger;
   @NotNull
   private static final String DEFAULT_ECB_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist.zip";

   public ECBExchangeRates(@NotNull String url, boolean compressed, boolean useCache) {
      Intrinsics.checkNotNullParameter(url, "url");
      super(Currency.Companion.getEUR());
      this.logger = Logging.INSTANCE.getLogger(Reflection.getOrCreateKotlinClass(ECBExchangeRates.class));
      this.load(url, compressed, useCache);
      Logging.Logger $this$iv = this.logger;
      Throwable throwable$iv = null;
      int $i$f$info = 0;
      if ($this$iv.isInfoEnabled()) {
         int var7 = 0;
         $this$iv.info("loaded conversion rates for " + this.getExchangeRates().size() + " currencies", throwable$iv);
      }

   }

   // $FF: synthetic method
   public ECBExchangeRates(String var1, boolean var2, boolean var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      this(var1, var2, var3);
   }

   private final URL cache(URL url) {
      String fileName = (new File(url.getPath())).getName();
      long day = LocalDate.now().toEpochDay();
      Path var10000 = Config.INSTANCE.getHome().resolve(day + "-" + fileName);
      Intrinsics.checkNotNullExpressionValue(var10000, "resolve(...)");
      Path cacheFile = var10000;
      LinkOption[] var10001 = new LinkOption[0];
      if (Files.notExists(cacheFile, (LinkOption[])Arrays.copyOf(var10001, var10001.length))) {
         Logging.Logger $this$iv = this.logger;
         Throwable throwable$iv = null;
         int $i$f$debug = 0;
         if ($this$iv.isDebugEnabled()) {
            int var10 = 0;
            $this$iv.debug("Caching " + url + " as " + cacheFile + " ", throwable$iv);
         }

         Closeable var17 = (Closeable)url.openStream();
         throwable$iv = null;

         try {
            InputStream it = (InputStream)var17;
            int var21 = 0;
            CopyOption[] var11 = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING};
            long var20 = Files.copy(it, cacheFile, var11);
         } catch (Throwable var15) {
            throwable$iv = var15;
            throw var15;
         } finally {
            CloseableKt.closeFinally(var17, throwable$iv);
         }
      }

      URL var22 = cacheFile.toUri().toURL();
      Intrinsics.checkNotNullExpressionValue(var22, "toURL(...)");
      return var22;
   }

   private final void load(String urlString, boolean compressed, boolean cache) {
      Object url = null;
      Object var29 = (new URI(urlString)).toURL();
      if (cache) {
         String var10000 = var29.getProtocol();
         Intrinsics.checkNotNullExpressionValue(var10000, "getProtocol(...)");
         var10000 = var10000.toUpperCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var10000, "toUpperCase(...)");
         if (StringsKt.startsWith$default(var10000, "HTTP", false, 2, (Object)null)) {
            Intrinsics.checkNotNullExpressionValue(var29, "element");
            var29 = this.cache(var29);
         }
      }

      InputStream var70 = var29.openStream();
      Intrinsics.checkNotNullExpressionValue(var70, "openStream(...)");
      InputStream input = var70;
      if (compressed) {
         ZipInputStream zis = new ZipInputStream(input);
         ZipEntry entry = zis.getNextEntry();
         if (entry != null) {
            Logging.Logger $this$iv = this.logger;
            Throwable throwable$iv = null;
            int $i$f$debug = 0;
            if ($this$iv.isDebugEnabled()) {
               int var11 = 0;
               $this$iv.debug("Found file " + entry.getName() + " from " + var29, throwable$iv);
            }

            var70 = (InputStream)zis;
         } else {
            Logging.Logger $this$iv = this.logger;
            Throwable throwable$iv = null;
            int $i$f$error = 0;
            if ($this$iv.isErrorEnabled()) {
               int var12 = 0;
               $this$iv.error("File " + urlString + " is not compressed", throwable$iv);
            }

            input.close();
            InputStream lines = var29.openStream();
            Intrinsics.checkNotNull(lines);
            var70 = lines;
         }

         input = var70;
      }

      InputStreamReader inputReader = new InputStreamReader(input);
      CsvReader reader = CsvReader.builder().ofCsvRecord((Reader)inputReader);
      Intrinsics.checkNotNull(reader);
      Iterable $this$map$iv = (Iterable)reader;
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         CsvRecord it = (CsvRecord)item$iv$iv;
         int var17 = 0;
         destination$iv$iv.add(it.getFields());
      }

      List lines = (List)destination$iv$iv;
      reader.close();
      Object var72 = CollectionsKt.first(lines);
      Intrinsics.checkNotNullExpressionValue(var72, "first(...)");
      Iterable $this$filter$iv = (Iterable)CollectionsKt.drop((Iterable)var72, 1);
      int $i$f$filter = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$filterTo = 0;

      for(Object element$iv$iv : $this$filter$iv) {
         String it = (String)element$iv$iv;
         int var18 = 0;
         Intrinsics.checkNotNull(it);
         if (!StringsKt.isBlank((CharSequence)it)) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      $this$filter$iv = (Iterable)((List)destination$iv$iv);
      $i$f$filter = 0;
      destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filter$iv, 10)));
      $i$f$filterTo = 0;

      for(Object item$iv$iv : $this$filter$iv) {
         String it = (String)item$iv$iv;
         int var63 = 0;
         Currency.Companion var73 = Currency.Companion;
         Intrinsics.checkNotNull(it);
         destination$iv$iv.add(var73.getInstance(it));
      }

      List currencies = (List)destination$iv$iv;
      ZoneId zoneId = ZoneId.of("Europe/Brussels");
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(zoneId);

      for(List line : CollectionsKt.drop((Iterable)lines, 1)) {
         Instant instant = ZonedDateTime.parse((CharSequence)(line.get(0) + " 16:00:00"), dtf).toInstant();
         Intrinsics.checkNotNull(line);
         Iterator var56 = CollectionsKt.drop((Iterable)line, 1).iterator();
         int var59 = 0;

         while(var56.hasNext()) {
            int i = var59++;
            String rateStr = (String)var56.next();

            try {
               Intrinsics.checkNotNull(rateStr);
               double v = (double)1.0F / Double.parseDouble(rateStr);
               Currency k = (Currency)currencies.get(i);
               Map $this$getOrPut$iv = this.getExchangeRates();
               int $i$f$getOrPut = 0;
               Object value$iv = $this$getOrPut$iv.get(k);
               if (value$iv == null) {
                  int var26 = 0;
                  Object answer$iv = (NavigableMap)(new TreeMap());
                  $this$getOrPut$iv.put(k, answer$iv);
                  var72 = answer$iv;
               } else {
                  var72 = value$iv;
               }

               NavigableMap map = (NavigableMap)var72;
               Double var67 = v;
               ((Map)map).put(instant, var67);
            } catch (NumberFormatException ex) {
               Logging.Logger this_$iv = this.logger;
               int $i$f$debug = 0;
               if (this_$iv.isDebugEnabled()) {
                  int map = 0;
                  this_$iv.debug("Encounter number format exception for string " + rateStr, (Throwable)ex);
               }
            }
         }
      }

   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"},
      d2 = {"Lorg/roboquant/brokers/ECBExchangeRates$Companion;", "", "()V", "DEFAULT_ECB_URL", "", "fromFile", "Lorg/roboquant/brokers/ECBExchangeRates;", "path", "compressed", "", "fromWeb", "useCache", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final ECBExchangeRates fromWeb(boolean useCache) {
         return new ECBExchangeRates("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist.zip", true, useCache);
      }

      // $FF: synthetic method
      public static ECBExchangeRates fromWeb$default(Companion var0, boolean var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = true;
         }

         return var0.fromWeb(var1);
      }

      @NotNull
      public final ECBExchangeRates fromFile(@NotNull String path, boolean compressed) {
         Intrinsics.checkNotNullParameter(path, "path");
         return new ECBExchangeRates("file:" + path, compressed, false, 4, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public static ECBExchangeRates fromFile$default(Companion var0, String var1, boolean var2, int var3, Object var4) {
         if ((var3 & 2) != 0) {
            var2 = false;
         }

         return var0.fromFile(var1, var2);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
