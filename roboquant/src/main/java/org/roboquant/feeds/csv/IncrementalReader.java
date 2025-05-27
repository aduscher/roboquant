package org.roboquant.feeds.csv;

import de.siegmar.fastcsv.reader.CloseableIterator;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Asset;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00150\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"},
   d2 = {"Lorg/roboquant/feeds/csv/IncrementalReader;", "", "asset", "Lorg/roboquant/common/Asset;", "file", "Ljava/io/File;", "config", "Lorg/roboquant/feeds/csv/CSVConfig;", "(Lorg/roboquant/common/Asset;Ljava/io/File;Lorg/roboquant/feeds/csv/CSVConfig;)V", "getAsset", "()Lorg/roboquant/common/Asset;", "getConfig", "()Lorg/roboquant/feeds/csv/CSVConfig;", "errors", "", "getErrors", "()J", "setErrors", "(J)V", "reader", "Lde/siegmar/fastcsv/reader/CloseableIterator;", "Lde/siegmar/fastcsv/reader/CsvRecord;", "kotlin.jvm.PlatformType", "close", "", "next", "Lorg/roboquant/feeds/csv/PriceEntry;", "roboquant"}
)
final class IncrementalReader {
   @NotNull
   private final Asset asset;
   @NotNull
   private final CSVConfig config;
   @NotNull
   private final CloseableIterator reader;
   private long errors;

   public IncrementalReader(@NotNull Asset asset, @NotNull File file, @NotNull CSVConfig config) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(file, "file");
      Intrinsics.checkNotNullParameter(config, "config");
      super();
      this.asset = asset;
      this.config = config;
      CloseableIterator var10001 = CsvReader.builder().fieldSeparator(this.config.getSeparator()).skipEmptyLines(true).ofCsvRecord((Reader)(new FileReader(file))).iterator();
      Intrinsics.checkNotNullExpressionValue(var10001, "iterator(...)");
      this.reader = var10001;
      if (this.config.getHasHeader() && this.reader.hasNext()) {
         List line = ((CsvRecord)this.reader.next()).getFields();
         CSVConfig var10000 = this.config;
         Intrinsics.checkNotNull(line);
         var10000.configure(line);
      }

   }

   @NotNull
   public final Asset getAsset() {
      return this.asset;
   }

   @NotNull
   public final CSVConfig getConfig() {
      return this.config;
   }

   public final long getErrors() {
      return this.errors;
   }

   public final void setErrors(long var1) {
      this.errors = var1;
   }

   @Nullable
   public final PriceEntry next() {
      while(this.reader.hasNext()) {
         List line = ((CsvRecord)this.reader.next()).getFields();

         try {
            CSVConfig var10000 = this.config;
            Intrinsics.checkNotNull(line);
            return var10000.processLine(line, this.asset);
         } catch (Throwable var5) {
            int var3 = this.errors++;
         }
      }

      this.reader.close();
      return null;
   }

   public final void close() {
      this.reader.close();
   }
}
