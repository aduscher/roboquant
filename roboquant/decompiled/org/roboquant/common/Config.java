package org.roboquant.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import kotlin.KotlinVersion;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.brokers.ExchangeRates;
import org.roboquant.brokers.NoExchangeRates;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u00011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010)\u001a\u0004\u0018\u00010\u000b2\u0006\u0010*\u001a\u00020\u000bJ\u0016\u0010)\u001a\u00020+2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020+J\u0016\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\bJ\u0016\u0010)\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u000bJ\u0006\u0010-\u001a\u00020.J\u0016\u0010/\u001a\u00020.2\u0006\u0010*\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R \u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u001a\u001a\u00020\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u00062"},
   d2 = {"Lorg/roboquant/common/Config;", "", "()V", "DEFAULT_SEED", "", "EPS", "", "ONE_MB", "", "env", "", "", "getEnv", "()Ljava/util/Map;", "exchangeRates", "Lorg/roboquant/brokers/ExchangeRates;", "getExchangeRates", "()Lorg/roboquant/brokers/ExchangeRates;", "setExchangeRates", "(Lorg/roboquant/brokers/ExchangeRates;)V", "home", "Ljava/nio/file/Path;", "getHome", "()Ljava/nio/file/Path;", "home$delegate", "Lkotlin/Lazy;", "info", "Lorg/roboquant/common/Config$EnvInfo;", "getInfo", "()Lorg/roboquant/common/Config$EnvInfo;", "info$delegate", "logger", "Lorg/roboquant/common/Logging$Logger;", "properties", "", "random", "Lkotlin/random/Random;", "getRandom", "()Lkotlin/random/Random;", "setRandom", "(Lkotlin/random/Random;)V", "getProperty", "name", "", "default", "printInfo", "", "setProperty", "value", "EnvInfo", "roboquant"}
)
@SourceDebugExtension({"SMAP\nConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Config.kt\norg/roboquant/common/Config\n+ 2 Logging.kt\norg/roboquant/common/Logging$Logger\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,219:1\n38#2,3:220\n38#2,3:223\n38#2,3:226\n38#2,3:229\n38#2,3:236\n125#3:232\n152#3,3:233\n*S KotlinDebug\n*F\n+ 1 Config.kt\norg/roboquant/common/Config\n*L\n166#1:220,3\n174#1:223,3\n182#1:226,3\n190#1:229,3\n208#1:236,3\n215#1:232\n215#1:233,3\n*E\n"})
public final class Config {
   @NotNull
   public static final Config INSTANCE = new Config();
   @NotNull
   private static final Logging.Logger logger;
   @NotNull
   private static final Map properties;
   private static final int ONE_MB = 1048576;
   private static final long DEFAULT_SEED = 42L;
   public static final double EPS = 1.0E-10;
   @NotNull
   private static final Lazy info$delegate;
   @NotNull
   private static ExchangeRates exchangeRates;
   @NotNull
   private static Random random;
   @NotNull
   private static final Lazy home$delegate;

   private Config() {
   }

   @NotNull
   public final EnvInfo getInfo() {
      Lazy var1 = info$delegate;
      return (EnvInfo)var1.getValue();
   }

   public final void setProperty(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      properties.put(name, value);
   }

   @NotNull
   public final ExchangeRates getExchangeRates() {
      return exchangeRates;
   }

   public final void setExchangeRates(@NotNull ExchangeRates var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      exchangeRates = var1;
   }

   @NotNull
   public final Random getRandom() {
      return random;
   }

   public final void setRandom(@NotNull Random var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      random = var1;
   }

   public final void printInfo() {
      String var10000 = this.getInfo().getVersion();
      String msg = "             _______\n            | $   $ |             roboquant\n            |   o   |             version: " + var10000 + "\n            |_[___]_|             build: " + this.getInfo().getBuild() + "\n        ___ ___|_|___ ___         os: " + this.getInfo().getOs() + "\n       ()___)       ()___)        home: " + this.getHome() + "\n      /  / |         | \\  \\       jvm: " + this.getInfo().getJvm() + "\n     (___) |_________| (___)      kotlin: " + KotlinVersion.CURRENT + "\n      | |   __/___\\__   | |       memory: " + this.getInfo().getMemory() + "MB\n      /_\\  |_________|  /_\\       cpu cores: " + this.getInfo().getCores() + "\n     // \\\\  |||   |||  // \\\\\n     \\\\ //  |||   |||  \\\\ //\n           ()__) ()__)\n           ///     \\\\\\\n        __///_     _\\\\\\__\n       |______|   |______|";
      System.out.println(msg);
      System.out.flush();
   }

   @NotNull
   public final Path getHome() {
      Lazy var1 = home$delegate;
      return (Path)var1.getValue();
   }

   @NotNull
   public final String getProperty(@NotNull String name, @NotNull String var2) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(var2, "default");
      Logging.Logger $this$iv = logger;
      Throwable throwable$iv = null;
      int $i$f$trace = 0;
      if ($this$iv.isTraceEnabled()) {
         int var6 = 0;
         $this$iv.trace("finding property " + name, throwable$iv);
      }

      String var10000 = this.getProperty(name);
      if (var10000 == null) {
         var10000 = var2;
      }

      return var10000;
   }

   public final boolean getProperty(@NotNull String name, boolean var2) {
      Intrinsics.checkNotNullParameter(name, "name");
      Logging.Logger $this$iv = logger;
      Throwable throwable$iv = null;
      int $i$f$trace = 0;
      if ($this$iv.isTraceEnabled()) {
         int var6 = 0;
         $this$iv.trace("finding property " + name, throwable$iv);
      }

      String var10000 = this.getProperty(name);
      return var10000 != null ? Boolean.parseBoolean(var10000) : var2;
   }

   public final int getProperty(@NotNull String name, int var2) {
      Intrinsics.checkNotNullParameter(name, "name");
      Logging.Logger $this$iv = logger;
      Throwable throwable$iv = null;
      int $i$f$trace = 0;
      if ($this$iv.isTraceEnabled()) {
         int var6 = 0;
         $this$iv.trace("finding property " + name, throwable$iv);
      }

      String var10000 = this.getProperty(name);
      return var10000 != null ? Integer.parseInt(var10000) : var2;
   }

   @Nullable
   public final String getProperty(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Logging.Logger $this$iv = logger;
      Throwable throwable$iv = null;
      int $i$f$trace = 0;
      if ($this$iv.isTraceEnabled()) {
         int var5 = 0;
         $this$iv.trace("finding property " + name, throwable$iv);
      }

      String var10000 = (String)properties.get(name);
      if (var10000 == null) {
         var10000 = System.getProperty(name);
         if (var10000 == null) {
            var10000 = StringsKt.replace$default(name, '.', '_', false, 4, (Object)null).toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(var10000, "toUpperCase(...)");
            var10000 = System.getenv(var10000);
            if (var10000 == null) {
               var10000 = System.getenv(name);
               if (var10000 == null) {
                  var10000 = (String)this.getEnv().get(name);
               }
            }
         }
      }

      return var10000;
   }

   private final Map getEnv() {
      Properties prop = new Properties();
      Path var10001 = this.getHome().resolve(".env");
      Intrinsics.checkNotNullExpressionValue(var10001, "resolve(...)");
      _get_env_$load(prop, var10001);
      var10001 = Path.of(".env");
      Intrinsics.checkNotNullExpressionValue(var10001, "of(...)");
      _get_env_$load(prop, var10001);
      var10001 = Path.of("dotenv");
      Intrinsics.checkNotNullExpressionValue(var10001, "of(...)");
      _get_env_$load(prop, var10001);
      Map $this$map$iv = (Map)prop;
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList($this$map$iv.size()));
      int $i$f$mapTo = 0;

      for(Map.Entry item$iv$iv : $this$map$iv.entrySet()) {
         int var10 = 0;
         destination$iv$iv.add(TuplesKt.to(item$iv$iv.getKey().toString(), item$iv$iv.getValue().toString()));
      }

      return MapsKt.toMap((Iterable)((List)destination$iv$iv));
   }

   private static final void _get_env_$load(Properties prop, Path path) {
      if (Files.exists(path, new LinkOption[0])) {
         File var10001 = path.toFile();
         Intrinsics.checkNotNullExpressionValue(var10001, "toFile(...)");
         File var2 = var10001;
         prop.load((InputStream)(new FileInputStream(var2)));
         Logging.Logger $this$iv = logger;
         Throwable throwable$iv = null;
         int $i$f$trace = 0;
         if ($this$iv.isTraceEnabled()) {
            int var5 = 0;
            $this$iv.trace("Found property file at " + path, throwable$iv);
         }
      }

   }

   // $FF: synthetic method
   public static final Logging.Logger access$getLogger$p() {
      return logger;
   }

   static {
      logger = Logging.INSTANCE.getLogger(Reflection.getOrCreateKotlinClass(Config.class));
      properties = (Map)(new LinkedHashMap());
      info$delegate = LazyKt.lazy(null.INSTANCE);
      exchangeRates = new NoExchangeRates();
      random = RandomKt.Random(42L);
      home$delegate = LazyKt.lazy(null.INSTANCE);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006\u0015"},
      d2 = {"Lorg/roboquant/common/Config$EnvInfo;", "", "jvm", "", "os", "memory", "", "cores", "", "version", "build", "(Ljava/lang/String;Ljava/lang/String;JILjava/lang/String;Ljava/lang/String;)V", "getBuild", "()Ljava/lang/String;", "getCores", "()I", "getJvm", "getMemory", "()J", "getOs", "getVersion", "roboquant"}
   )
   public static final class EnvInfo {
      @NotNull
      private final String jvm;
      @NotNull
      private final String os;
      private final long memory;
      private final int cores;
      @NotNull
      private final String version;
      @NotNull
      private final String build;

      public EnvInfo(@NotNull String jvm, @NotNull String os, long memory, int cores, @NotNull String version, @NotNull String build) {
         Intrinsics.checkNotNullParameter(jvm, "jvm");
         Intrinsics.checkNotNullParameter(os, "os");
         Intrinsics.checkNotNullParameter(version, "version");
         Intrinsics.checkNotNullParameter(build, "build");
         super();
         this.jvm = jvm;
         this.os = os;
         this.memory = memory;
         this.cores = cores;
         this.version = version;
         this.build = build;
      }

      @NotNull
      public final String getJvm() {
         return this.jvm;
      }

      @NotNull
      public final String getOs() {
         return this.os;
      }

      public final long getMemory() {
         return this.memory;
      }

      public final int getCores() {
         return this.cores;
      }

      @NotNull
      public final String getVersion() {
         return this.version;
      }

      @NotNull
      public final String getBuild() {
         return this.build;
      }
   }
}
