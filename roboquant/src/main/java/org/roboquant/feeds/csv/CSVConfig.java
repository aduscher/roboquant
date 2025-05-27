package org.roboquant.feeds.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Asset;
import org.roboquant.common.Crypto;
import org.roboquant.common.Currency;
import org.roboquant.common.Exchange;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.Forex;
import org.roboquant.common.Logging;
import org.roboquant.common.PriceItem;
import org.roboquant.common.Stock;
import org.roboquant.common.TimeSpan;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 P2\u00020\u0001:\u0001PBQ\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\t\u00105\u001a\u00020\u0003HÆ\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0007HÆ\u0003J\t\u00108\u001a\u00020\tHÆ\u0003J\t\u00109\u001a\u00020\u000bHÆ\u0003J\t\u0010:\u001a\u00020\rHÆ\u0003J\t\u0010;\u001a\u00020\u000fHÆ\u0003J\u0014\u0010<\u001a\u00020=2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005JU\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010@\u001a\u00020\u00072\b\u0010A\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010B\u001a\u00020CHÖ\u0001J\u001c\u0010D\u001a\u00020=2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030FH\u0002J\u001c\u0010G\u001a\u00020H2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0006\u0010J\u001a\u00020KJ\u000e\u0010L\u001a\u00020\u00072\u0006\u0010M\u001a\u00020NJ\t\u0010O\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010\"\u001a\n $*\u0004\u0018\u00010#0#8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b%\u0010&R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104¨\u0006Q"},
   d2 = {"Lorg/roboquant/feeds/csv/CSVConfig;", "", "filePattern", "", "fileSkip", "", "hasHeader", "", "separator", "", "timeParser", "Lorg/roboquant/feeds/csv/TimeParser;", "priceParser", "Lorg/roboquant/feeds/csv/PriceParser;", "assetBuilder", "Lorg/roboquant/feeds/csv/AssetBuilder;", "(Ljava/lang/String;Ljava/util/List;ZCLorg/roboquant/feeds/csv/TimeParser;Lorg/roboquant/feeds/csv/PriceParser;Lorg/roboquant/feeds/csv/AssetBuilder;)V", "getAssetBuilder", "()Lorg/roboquant/feeds/csv/AssetBuilder;", "setAssetBuilder", "(Lorg/roboquant/feeds/csv/AssetBuilder;)V", "getFilePattern", "()Ljava/lang/String;", "setFilePattern", "(Ljava/lang/String;)V", "getFileSkip", "()Ljava/util/List;", "setFileSkip", "(Ljava/util/List;)V", "getHasHeader", "()Z", "setHasHeader", "(Z)V", "isInitialized", "pattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "getPattern", "()Ljava/util/regex/Pattern;", "pattern$delegate", "Lkotlin/Lazy;", "getPriceParser", "()Lorg/roboquant/feeds/csv/PriceParser;", "setPriceParser", "(Lorg/roboquant/feeds/csv/PriceParser;)V", "getSeparator", "()C", "setSeparator", "(C)V", "getTimeParser", "()Lorg/roboquant/feeds/csv/TimeParser;", "setTimeParser", "(Lorg/roboquant/feeds/csv/TimeParser;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "configure", "", "header", "copy", "equals", "other", "hashCode", "", "merge", "config", "", "processLine", "Lorg/roboquant/feeds/csv/PriceEntry;", "line", "asset", "Lorg/roboquant/common/Asset;", "shouldInclude", "file", "Ljava/io/File;", "toString", "Companion", "roboquant"}
)
@SourceDebugExtension({"SMAP\nCSVConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CSVConfig.kt\norg/roboquant/feeds/csv/CSVConfig\n+ 2 Logging.kt\norg/roboquant/common/Logging$Logger\n*L\n1#1,262:1\n45#2,3:263\n*S KotlinDebug\n*F\n+ 1 CSVConfig.kt\norg/roboquant/feeds/csv/CSVConfig\n*L\n223#1:263,3\n*E\n"})
public final class CSVConfig {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private String filePattern;
   @NotNull
   private List fileSkip;
   private boolean hasHeader;
   private char separator;
   @NotNull
   private TimeParser timeParser;
   @NotNull
   private PriceParser priceParser;
   @NotNull
   private AssetBuilder assetBuilder;
   @NotNull
   private final Lazy pattern$delegate;
   private boolean isInitialized;
   @NotNull
   private static final String CONFIG_FILE = "config.properties";
   @NotNull
   private static final Logging.Logger logger;

   public CSVConfig(@NotNull String filePattern, @NotNull List fileSkip, boolean hasHeader, char separator, @NotNull TimeParser timeParser, @NotNull PriceParser priceParser, @NotNull AssetBuilder assetBuilder) {
      Intrinsics.checkNotNullParameter(filePattern, "filePattern");
      Intrinsics.checkNotNullParameter(fileSkip, "fileSkip");
      Intrinsics.checkNotNullParameter(timeParser, "timeParser");
      Intrinsics.checkNotNullParameter(priceParser, "priceParser");
      Intrinsics.checkNotNullParameter(assetBuilder, "assetBuilder");
      super();
      this.filePattern = filePattern;
      this.fileSkip = fileSkip;
      this.hasHeader = hasHeader;
      this.separator = separator;
      this.timeParser = timeParser;
      this.priceParser = priceParser;
      this.assetBuilder = assetBuilder;
      this.pattern$delegate = LazyKt.lazy(new Function0() {
         public final Pattern invoke() {
            return Pattern.compile(CSVConfig.this.getFilePattern());
         }
      });
   }

   // $FF: synthetic method
   public CSVConfig(String var1, List var2, boolean var3, char var4, TimeParser var5, PriceParser var6, AssetBuilder var7, int var8, DefaultConstructorMarker var9) {
      if ((var8 & 1) != 0) {
         var1 = ".*.csv";
      }

      if ((var8 & 2) != 0) {
         var2 = CollectionsKt.emptyList();
      }

      if ((var8 & 4) != 0) {
         var3 = true;
      }

      if ((var8 & 8) != 0) {
         var4 = ',';
      }

      if ((var8 & 16) != 0) {
         var5 = new AutoDetectTimeParser(0, (Exchange)null, 3, (DefaultConstructorMarker)null);
      }

      if ((var8 & 32) != 0) {
         var6 = new PriceBarParser(0, 0, 0, 0, 0, 0, false, false, (TimeSpan)null, 511, (DefaultConstructorMarker)null);
      }

      if ((var8 & 64) != 0) {
         var7 = new StockBuilder((Currency)null, 1, (DefaultConstructorMarker)null);
      }

      this(var1, var2, var3, var4, var5, var6, var7);
   }

   @NotNull
   public final String getFilePattern() {
      return this.filePattern;
   }

   public final void setFilePattern(@NotNull String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.filePattern = var1;
   }

   @NotNull
   public final List getFileSkip() {
      return this.fileSkip;
   }

   public final void setFileSkip(@NotNull List var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.fileSkip = var1;
   }

   public final boolean getHasHeader() {
      return this.hasHeader;
   }

   public final void setHasHeader(boolean var1) {
      this.hasHeader = var1;
   }

   public final char getSeparator() {
      return this.separator;
   }

   public final void setSeparator(char var1) {
      this.separator = var1;
   }

   @NotNull
   public final TimeParser getTimeParser() {
      return this.timeParser;
   }

   public final void setTimeParser(@NotNull TimeParser var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.timeParser = var1;
   }

   @NotNull
   public final PriceParser getPriceParser() {
      return this.priceParser;
   }

   public final void setPriceParser(@NotNull PriceParser var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.priceParser = var1;
   }

   @NotNull
   public final AssetBuilder getAssetBuilder() {
      return this.assetBuilder;
   }

   public final void setAssetBuilder(@NotNull AssetBuilder var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.assetBuilder = var1;
   }

   private final Pattern getPattern() {
      Lazy var1 = this.pattern$delegate;
      return (Pattern)var1.getValue();
   }

   public final boolean shouldInclude(@NotNull File file) {
      Intrinsics.checkNotNullParameter(file, "file");
      String name = file.getName();
      return file.isFile() && this.getPattern().matcher((CharSequence)name).matches() && !this.fileSkip.contains(name);
   }

   private final void merge(Map config) {
      for(Map.Entry var3 : config.entrySet()) {
         String key = (String)var3.getKey();
         String value = (String)var3.getValue();
         Logging.Logger $this$iv = logger;
         Throwable throwable$iv = null;
         int $i$f$debug = 0;
         if ($this$iv.isDebugEnabled()) {
            int var9 = 0;
            $this$iv.debug("Found property key=" + key + " value=" + value, throwable$iv);
         }

         if (Intrinsics.areEqual(key, "file.pattern")) {
            this.filePattern = value;
         } else if (Intrinsics.areEqual(key, "file.skip")) {
            CharSequence var10001 = (CharSequence)value;
            String[] var11 = new String[]{","};
            this.fileSkip = StringsKt.split$default(var10001, var11, false, 0, 6, (Object)null);
         }
      }

   }

   @NotNull
   public final PriceEntry processLine(@NotNull List line, @NotNull Asset asset) {
      Intrinsics.checkNotNullParameter(line, "line");
      Intrinsics.checkNotNullParameter(asset, "asset");
      Instant now = this.timeParser.parse(line);
      PriceItem action = this.priceParser.parse(line, asset);
      return new PriceEntry(now, action);
   }

   public final synchronized void configure(@NotNull List header) {
      Intrinsics.checkNotNullParameter(header, "header");
      if (!this.isInitialized) {
         this.timeParser.init(header);
         this.priceParser.init(header);
         this.isInitialized = true;
      }
   }

   @NotNull
   public final String component1() {
      return this.filePattern;
   }

   @NotNull
   public final List component2() {
      return this.fileSkip;
   }

   public final boolean component3() {
      return this.hasHeader;
   }

   public final char component4() {
      return this.separator;
   }

   @NotNull
   public final TimeParser component5() {
      return this.timeParser;
   }

   @NotNull
   public final PriceParser component6() {
      return this.priceParser;
   }

   @NotNull
   public final AssetBuilder component7() {
      return this.assetBuilder;
   }

   @NotNull
   public final CSVConfig copy(@NotNull String filePattern, @NotNull List fileSkip, boolean hasHeader, char separator, @NotNull TimeParser timeParser, @NotNull PriceParser priceParser, @NotNull AssetBuilder assetBuilder) {
      Intrinsics.checkNotNullParameter(filePattern, "filePattern");
      Intrinsics.checkNotNullParameter(fileSkip, "fileSkip");
      Intrinsics.checkNotNullParameter(timeParser, "timeParser");
      Intrinsics.checkNotNullParameter(priceParser, "priceParser");
      Intrinsics.checkNotNullParameter(assetBuilder, "assetBuilder");
      return new CSVConfig(filePattern, fileSkip, hasHeader, separator, timeParser, priceParser, assetBuilder);
   }

   // $FF: synthetic method
   public static CSVConfig copy$default(CSVConfig var0, String var1, List var2, boolean var3, char var4, TimeParser var5, PriceParser var6, AssetBuilder var7, int var8, Object var9) {
      if ((var8 & 1) != 0) {
         var1 = var0.filePattern;
      }

      if ((var8 & 2) != 0) {
         var2 = var0.fileSkip;
      }

      if ((var8 & 4) != 0) {
         var3 = var0.hasHeader;
      }

      if ((var8 & 8) != 0) {
         var4 = var0.separator;
      }

      if ((var8 & 16) != 0) {
         var5 = var0.timeParser;
      }

      if ((var8 & 32) != 0) {
         var6 = var0.priceParser;
      }

      if ((var8 & 64) != 0) {
         var7 = var0.assetBuilder;
      }

      return var0.copy(var1, var2, var3, var4, var5, var6, var7);
   }

   @NotNull
   public String toString() {
      return "CSVConfig(filePattern=" + this.filePattern + ", fileSkip=" + this.fileSkip + ", hasHeader=" + this.hasHeader + ", separator=" + this.separator + ", timeParser=" + this.timeParser + ", priceParser=" + this.priceParser + ", assetBuilder=" + this.assetBuilder + ")";
   }

   public int hashCode() {
      int result = this.filePattern.hashCode();
      result = result * 31 + this.fileSkip.hashCode();
      result = result * 31 + Boolean.hashCode(this.hasHeader);
      result = result * 31 + Character.hashCode(this.separator);
      result = result * 31 + this.timeParser.hashCode();
      result = result * 31 + this.priceParser.hashCode();
      result = result * 31 + this.assetBuilder.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof CSVConfig)) {
         return false;
      } else {
         CSVConfig var2 = (CSVConfig)other;
         if (!Intrinsics.areEqual(this.filePattern, var2.filePattern)) {
            return false;
         } else if (!Intrinsics.areEqual(this.fileSkip, var2.fileSkip)) {
            return false;
         } else if (this.hasHeader != var2.hasHeader) {
            return false;
         } else if (this.separator != var2.separator) {
            return false;
         } else if (!Intrinsics.areEqual(this.timeParser, var2.timeParser)) {
            return false;
         } else if (!Intrinsics.areEqual(this.priceParser, var2.priceParser)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.assetBuilder, var2.assetBuilder);
         }
      }
   }

   public CSVConfig() {
      this((String)null, (List)null, false, '\u0000', (TimeParser)null, (PriceParser)null, (AssetBuilder)null, 127, (DefaultConstructorMarker)null);
   }

   static {
      logger = Logging.INSTANCE.getLogger(Reflection.getOrCreateKotlinClass(CSVConfig.class));
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u0006\u0010\n\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\bJ\u001c\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u001c\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\t\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\bJ\u0010\u0010\u0015\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"},
      d2 = {"Lorg/roboquant/feeds/csv/CSVConfig$Companion;", "", "()V", "CONFIG_FILE", "", "logger", "Lorg/roboquant/common/Logging$Logger;", "fromFile", "Lorg/roboquant/feeds/csv/CSVConfig;", "path", "histData", "kraken", "mt5", "priceQuote", "", "timeSpan", "Lorg/roboquant/common/TimeSpan;", "readConfigFile", "", "Ljava/nio/file/Path;", "stooq", "yahoo", "currency", "Lorg/roboquant/common/Currency;", "roboquant"}
   )
   @SourceDebugExtension({"SMAP\nCSVConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CSVConfig.kt\norg/roboquant/feeds/csv/CSVConfig$Companion\n+ 2 Logging.kt\norg/roboquant/common/Logging$Logger\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,262:1\n45#2,3:263\n38#2,3:266\n125#3:269\n152#3,3:270\n*S KotlinDebug\n*F\n+ 1 CSVConfig.kt\norg/roboquant/feeds/csv/CSVConfig$Companion\n*L\n199#1:263,3\n201#1:266,3\n203#1:269\n203#1:270,3\n*E\n"})
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final CSVConfig stooq() {
         return new CSVConfig(".*.txt", (List)null, false, '\u0000', new AutoDetectTimeParser(2, (Exchange)null, 2, (DefaultConstructorMarker)null), (PriceParser)null, Companion::stooq$lambda$0, 46, (DefaultConstructorMarker)null);
      }

      @NotNull
      public final CSVConfig mt5(boolean priceQuote, @Nullable TimeSpan timeSpan) {
         final DateTimeFormatter dtf = priceQuote ? DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss.SSS") : DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
         PriceParser priceParser = priceQuote ? (PriceParser)(new PriceQuoteParser(3, 2, 0, 0, false, 28, (DefaultConstructorMarker)null)) : (PriceParser)(new PriceBarParser(2, 3, 4, 5, 6, 0, false, false, timeSpan, 224, (DefaultConstructorMarker)null));
         AssetBuilder var5 = Companion::mt5$lambda$1;
         TimeParser var6 = new TimeParser() {
            @NotNull
            public final Instant parse(@NotNull List a) {
               Intrinsics.checkNotNullParameter(a, "a");
               return CSVConfig.Companion.mt5$parse(dtf, a);
            }

            public void init(@NotNull List header) {
               TimeParser.DefaultImpls.init(this, header);
            }
         };
         return new CSVConfig((String)null, (List)null, false, '\t', var6, priceParser, var5, 7, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public static CSVConfig mt5$default(Companion var0, boolean var1, TimeSpan var2, int var3, Object var4) {
         if ((var3 & 1) != 0) {
            var1 = false;
         }

         if ((var3 & 2) != 0) {
            var2 = null;
         }

         return var0.mt5(var1, var2);
      }

      @NotNull
      public final CSVConfig histData() {
         PriceBarParser var2 = new PriceBarParser(1, 2, 3, 4, 0, 0, false, false, (TimeSpan)null, 496, (DefaultConstructorMarker)null);
         AutoDetectTimeParser var3 = new AutoDetectTimeParser(0, (Exchange)null, 2, (DefaultConstructorMarker)null);
         AssetBuilder var4 = Companion::histData$lambda$2;
         CSVConfig result = new CSVConfig((String)null, (List)null, false, ';', var3, var2, var4, 3, (DefaultConstructorMarker)null);
         return result;
      }

      @NotNull
      public final CSVConfig yahoo(@NotNull Currency currency) {
         Intrinsics.checkNotNullParameter(currency, "currency");
         PriceBarParser var3 = new PriceBarParser(1, 2, 3, 4, 0, 0, false, false, (TimeSpan)null, 368, (DefaultConstructorMarker)null);
         TimeParser var4 = null.INSTANCE;
         AssetBuilder var5 = Companion::yahoo$lambda$3;
         CSVConfig result = new CSVConfig((String)null, (List)null, true, ',', var4, var3, var5, 3, (DefaultConstructorMarker)null);
         return result;
      }

      // $FF: synthetic method
      public static CSVConfig yahoo$default(Companion var0, Currency var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = Currency.Companion.getUSD();
         }

         return var0.yahoo(var1);
      }

      @NotNull
      public final CSVConfig kraken() {
         TradePriceParser var2 = new TradePriceParser(1, 2, false, 4, (DefaultConstructorMarker)null);
         TimeParser var3 = null.INSTANCE;
         AssetBuilder var4 = Companion::kraken$lambda$4;
         CSVConfig result = new CSVConfig((String)null, (List)null, false, '\u0000', var3, var2, var4, 11, (DefaultConstructorMarker)null);
         return result;
      }

      @NotNull
      public final CSVConfig fromFile(@NotNull String path) {
         Intrinsics.checkNotNullParameter(path, "path");
         CSVConfig result = new CSVConfig((String)null, (List)null, false, '\u0000', (TimeParser)null, (PriceParser)null, (AssetBuilder)null, 127, (DefaultConstructorMarker)null);
         Path var10001 = Path.of(path);
         Intrinsics.checkNotNullExpressionValue(var10001, "of(...)");
         Map cfg = this.readConfigFile(var10001);
         result.merge(cfg);
         return result;
      }

      private final Map readConfigFile(Path path) {
         Path var10000 = path.resolve("config.properties");
         Intrinsics.checkNotNullExpressionValue(var10000, "resolve(...)");
         Path filePath = var10000;
         File file = filePath.toFile();
         Properties prop = new Properties();
         if (file.exists()) {
            Logging.Logger $this$iv = CSVConfig.logger;
            Throwable throwable$iv = null;
            int $i$f$debug = 0;
            if ($this$iv.isDebugEnabled()) {
               int var8 = 0;
               $this$iv.debug("Found configuration file " + file, throwable$iv);
            }

            Intrinsics.checkNotNull(file);
            prop.load((InputStream)(new FileInputStream(file)));
            $this$iv = CSVConfig.logger;
            throwable$iv = null;
            $i$f$debug = 0;
            if ($this$iv.isTraceEnabled()) {
               int var20 = 0;
               String var22 = prop.toString();
               Intrinsics.checkNotNullExpressionValue(var22, "toString(...)");
               $this$iv.trace(var22, throwable$iv);
            }
         }

         Map $this$map$iv = (Map)prop;
         int $i$f$map = 0;
         Collection destination$iv$iv = (Collection)(new ArrayList($this$map$iv.size()));
         int $i$f$mapTo = 0;

         for(Map.Entry item$iv$iv : $this$map$iv.entrySet()) {
            int var13 = 0;
            destination$iv$iv.add(TuplesKt.to(item$iv$iv.getKey().toString(), item$iv$iv.getValue().toString()));
         }

         return MapsKt.toMap((Iterable)((List)destination$iv$iv));
      }

      private static final Asset stooq$lambda$0(String name) {
         Intrinsics.checkNotNullParameter(name, "name");
         String var10002 = StringsKt.replace$default(StringsKt.removeSuffix(StringsKt.removeSuffix(name, (CharSequence)".us.txt"), (CharSequence)".us"), '-', '.', false, 4, (Object)null).toUpperCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var10002, "toUpperCase(...)");
         return new Stock(var10002, Currency.Companion.getUSD());
      }

      private static final Asset mt5$assetBuilder(String name) {
         CharSequence var10000 = (CharSequence)name;
         char[] var2 = new char[]{'_'};
         String var3 = ((String)CollectionsKt.first(StringsKt.split$default(var10000, var2, false, 0, 6, (Object)null))).toUpperCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var3, "toUpperCase(...)");
         String symbol = var3;
         return new Stock(symbol, Currency.Companion.getUSD());
      }

      private static final Instant mt5$parse(DateTimeFormatter dtf, List line) {
         Object var10000 = line.get(0);
         String text = var10000 + " " + line.get(1);
         LocalDateTime dt = LocalDateTime.parse((CharSequence)text, dtf);
         Instant var4 = dt.toInstant(ZoneOffset.UTC);
         Intrinsics.checkNotNullExpressionValue(var4, "toInstant(...)");
         return var4;
      }

      private static final Asset mt5$lambda$1(String it) {
         Intrinsics.checkNotNullParameter(it, "it");
         return mt5$assetBuilder(it);
      }

      private static final Asset histData$lambda$2(String name) {
         Intrinsics.checkNotNullParameter(name, "name");
         CharSequence var10000 = (CharSequence)name;
         char[] var2 = new char[]{'_'};
         String symbol = (String)StringsKt.split$default(var10000, var2, false, 0, 6, (Object)null).get(2);
         Pair var4 = ExtensionsKt.toCurrencyPair(symbol);
         Intrinsics.checkNotNull(var4);
         Pair currency = var4;
         return new Forex(symbol, (Currency)currency.getSecond());
      }

      private static final Asset yahoo$lambda$3(Currency $currency, String name) {
         Intrinsics.checkNotNullParameter($currency, "$currency");
         Intrinsics.checkNotNullParameter(name, "name");
         CharSequence var10000 = (CharSequence)name;
         char[] var3 = new char[]{' '};
         String var4 = ((String)StringsKt.split$default(var10000, var3, false, 0, 6, (Object)null).get(0)).toUpperCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var4, "toUpperCase(...)");
         String symbol = var4;
         return new Stock(symbol, $currency);
      }

      private static final Asset kraken$lambda$4(String name) {
         Intrinsics.checkNotNullParameter(name, "name");
         Pair var10000 = ExtensionsKt.toCurrencyPair(name);
         Intrinsics.checkNotNull(var10000);
         Pair currencyPair = var10000;
         return new Crypto(name, (Currency)currencyPair.getSecond());
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
