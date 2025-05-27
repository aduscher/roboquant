package org.roboquant.feeds.csv;

import de.siegmar.fastcsv.reader.CloseableIterator;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FileWalkDirection;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Asset;
import org.roboquant.common.Logging;
import org.roboquant.common.ParallelJobs;
import org.roboquant.feeds.HistoricPriceFeed;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B(\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n¢\u0006\u0002\u0010\u000bB.\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n¢\u0006\u0002\u0010\rJ\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0016\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u001aH\u0082@¢\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00180\u00132\u0006\u0010\u0002\u001a\u00020\u001aH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"},
   d2 = {"Lorg/roboquant/feeds/csv/CSVFeed;", "Lorg/roboquant/feeds/HistoricPriceFeed;", "path", "", "config", "Lorg/roboquant/feeds/csv/CSVConfig;", "(Ljava/lang/String;Lorg/roboquant/feeds/csv/CSVConfig;)V", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "pathStr", "(Ljava/lang/String;Lorg/roboquant/feeds/csv/CSVConfig;Lkotlin/jvm/functions/Function1;)V", "getConfig", "()Lorg/roboquant/feeds/csv/CSVConfig;", "logger", "Lorg/roboquant/common/Logging$Logger;", "readFile", "", "Lorg/roboquant/feeds/csv/PriceEntry;", "asset", "Lorg/roboquant/common/Asset;", "file", "Ljava/io/File;", "readFiles", "Ljava/nio/file/Path;", "(Ljava/nio/file/Path;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readPath", "roboquant"}
)
@SourceDebugExtension({"SMAP\nCSVFeed.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CSVFeed.kt\norg/roboquant/feeds/csv/CSVFeed\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Logging.kt\norg/roboquant/common/Logging$Logger\n*L\n1#1,154:1\n1#2:155\n52#3,3:156\n59#3,3:159\n45#3,3:162\n46#3,2:165\n59#3,3:167\n*S KotlinDebug\n*F\n+ 1 CSVFeed.kt\norg/roboquant/feeds/csv/CSVFeed\n*L\n66#1:156,3\n91#1:159,3\n94#1:162,3\n129#1:165,2\n134#1:167,3\n*E\n"})
public final class CSVFeed extends HistoricPriceFeed {
   @NotNull
   private final CSVConfig config;
   @NotNull
   private final Logging.Logger logger;

   public CSVFeed(@NotNull String pathStr, @NotNull CSVConfig config, @NotNull Function1 configure) {
      final Path path;
      boolean var10000;
      label20: {
         Intrinsics.checkNotNullParameter(pathStr, "pathStr");
         Intrinsics.checkNotNullParameter(config, "config");
         Intrinsics.checkNotNullParameter(configure, "configure");
         super();
         this.config = config;
         this.logger = Logging.INSTANCE.getLogger(Reflection.getOrCreateKotlinClass(CSVFeed.class));
         path = Path.of(pathStr);
         Intrinsics.checkNotNull(path);
         LinkOption[] var10001 = new LinkOption[0];
         if (!Files.isDirectory(path, (LinkOption[])Arrays.copyOf(var10001, var10001.length))) {
            var10001 = new LinkOption[0];
            if (!Files.isRegularFile(path, (LinkOption[])Arrays.copyOf(var10001, var10001.length))) {
               var10000 = false;
               break label20;
            }
         }

         var10000 = true;
      }

      if (!var10000) {
         int throwable$iv = 0;
         String var11 = path + " does not exist";
         throw new IllegalArgumentException(var11.toString());
      } else {
         configure.invoke(this.config);
         BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               Object var2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               switch (this.label) {
                  case 0:
                     ResultKt.throwOnFailure($result);
                     CSVFeed var10000 = CSVFeed.this;
                     Path var10001 = path;
                     Intrinsics.checkNotNullExpressionValue(var10001, "$path");
                     Continuation var10002 = (Continuation)this;
                     this.label = 1;
                     if (var10000.readFiles(var10001, var10002) == var2) {
                        return var2;
                     }
                     break;
                  case 1:
                     ResultKt.throwOnFailure($result);
                     break;
                  default:
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               return Unit.INSTANCE;
            }

            @NotNull
            public final Continuation create(@Nullable Object value, @NotNull Continuation $completion) {
               return (Continuation)(new <anonymous constructor>($completion));
            }

            @Nullable
            public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation p2) {
               return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }
         }, 1, (Object)null);
         Logging.Logger $this$iv = this.logger;
         Throwable throwable$iv = null;
         int $i$f$info = 0;
         if ($this$iv.isInfoEnabled()) {
            int var8 = 0;
            $this$iv.info("events=" + this.getTimeline().size() + " assets=" + this.getAssets().size() + " timeframe=" + this.getTimeframe(), throwable$iv);
         }

      }
   }

   @NotNull
   public final CSVConfig getConfig() {
      return this.config;
   }

   public CSVFeed(@NotNull String path, @NotNull CSVConfig config) {
      Intrinsics.checkNotNullParameter(path, "path");
      Intrinsics.checkNotNullParameter(config, "config");
      this(path, config, null.INSTANCE);
   }

   // $FF: synthetic method
   public CSVFeed(String var1, CSVConfig var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = CSVConfig.Companion.fromFile(var1);
      }

      this(var1, var2);
   }

   public CSVFeed(@NotNull String path, @NotNull Function1 configure) {
      Intrinsics.checkNotNullParameter(path, "path");
      Intrinsics.checkNotNullParameter(configure, "configure");
      this(path, CSVConfig.Companion.fromFile(path), configure);
   }

   private final List readPath(Path path) {
      File entry = path.toFile();
      List var10000;
      if (entry.isFile()) {
         var10000 = CollectionsKt.listOf(entry);
      } else {
         Intrinsics.checkNotNull(entry);
         var10000 = SequencesKt.toList(SequencesKt.map(SequencesKt.filter((Sequence)FilesKt.walk$default(entry, (FileWalkDirection)null, 1, (Object)null), new Function1() {
            @NotNull
            public final Boolean invoke(@NotNull File it) {
               Intrinsics.checkNotNullParameter(it, "it");
               return CSVFeed.this.getConfig().shouldInclude(it);
            }
         }), null.INSTANCE));
      }

      return var10000;
   }

   private final Object readFiles(Path path, Continuation $completion) {
      List files = this.readPath(path);
      if (files.isEmpty()) {
         Logging.Logger $this$iv = this.logger;
         Throwable throwable$iv = null;
         int $i$f$warn = 0;
         if ($this$iv.isWarnEnabled()) {
            int asset = 0;
            $this$iv.warn("Found no CSV files at " + path, throwable$iv);
         }

         return Unit.INSTANCE;
      } else {
         Logging.Logger $this$iv = this.logger;
         Throwable throwable$iv = null;
         int $i$f$debug = 0;
         if ($this$iv.isDebugEnabled()) {
            int var7 = 0;
            $this$iv.debug("Found " + files.size() + " CSV files", throwable$iv);
         }

         ParallelJobs jobs = new ParallelJobs();

         for(final File file : files) {
            final Asset asset = this.config.getAssetBuilder().build(FilesKt.getNameWithoutExtension(file));
            jobs.add(new Function2((Continuation)null) {
               int label;

               @Nullable
               public final Object invokeSuspend(@NotNull Object $result) {
                  IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  switch (this.label) {
                     case 0:
                        ResultKt.throwOnFailure($result);

                        for(PriceEntry step : CSVFeed.this.readFile(asset, file)) {
                           CSVFeed.this.add(step.getTime(), step.getPrice());
                        }

                        return Unit.INSTANCE;
                     default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }
               }

               @NotNull
               public final Continuation create(@Nullable Object value, @NotNull Continuation $completion) {
                  return (Continuation)(new <anonymous constructor>($completion));
               }

               @Nullable
               public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation p2) {
                  return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
               }
            });
         }

         Object var10000 = jobs.joinAll($completion);
         return var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var10000 : Unit.INSTANCE;
      }
   }

   private final List readFile(Asset asset, File file) {
      CsvReader reader = CsvReader.builder().fieldSeparator(this.config.getSeparator()).skipEmptyLines(true).ofCsvRecord((Reader)(new FileReader(file)));
      Closeable var4 = (Closeable)reader;
      Throwable var5 = null;

      List var18;
      try {
         CsvReader it = (CsvReader)var4;
         int var7 = 0;
         List result = (List)(new ArrayList());
         int errors = 0;
         boolean isHeader = this.config.getHasHeader();
         CloseableIterator var11 = it.iterator();

         while(var11.hasNext()) {
            CsvRecord row = (CsvRecord)var11.next();
            if (isHeader) {
               CSVConfig var29 = this.config;
               List var30 = row.getFields();
               Intrinsics.checkNotNullExpressionValue(var30, "getFields(...)");
               var29.configure(var30);
               isHeader = false;
            } else {
               try {
                  CSVConfig var10000 = this.config;
                  List var10001 = row.getFields();
                  Intrinsics.checkNotNullExpressionValue(var10001, "getFields(...)");
                  PriceEntry step = var10000.processLine(var10001, asset);
                  ((Collection)result).add(step);
               } catch (Throwable t) {
                  System.out.println(t);
                  Logging.Logger this_$iv = this.logger;
                  int $i$f$debug = 0;
                  if (this_$iv.isDebugEnabled()) {
                     int var17 = 0;
                     this_$iv.debug(asset.getSymbol() + " " + row, t);
                  }

                  ++errors;
               }
            }
         }

         if (errors > 0) {
            Logging.Logger $this$iv = this.logger;
            Throwable throwable$iv = null;
            int $i$f$warn = 0;
            if ($this$iv.isWarnEnabled()) {
               int var28 = 0;
               $this$iv.warn("Skipped " + errors + " lines due to errors in " + file, throwable$iv);
            }
         }

         var18 = result;
      } catch (Throwable var23) {
         var5 = var23;
         throw var23;
      } finally {
         CloseableKt.closeFinally(var4, var5);
      }

      return var18;
   }
}
