package org.roboquant.feeds.random;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Event;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.Logging;
import org.roboquant.common.PriceItemType;
import org.roboquant.common.TimeSpan;
import org.roboquant.common.TimeSpanKt;
import org.roboquant.common.Timeframe;
import org.roboquant.feeds.EventChannel;
import org.roboquant.feeds.HistoricFeed;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 !2\u00020\u0001:\u0001!BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0096@¢\u0006\u0002\u0010 R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\f\u0012\u0004\u0012\u00020\u00180\u0017j\u0002`\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""},
   d2 = {"Lorg/roboquant/feeds/random/RandomWalk;", "Lorg/roboquant/feeds/HistoricFeed;", "timeframe", "Lorg/roboquant/common/Timeframe;", "timeSpan", "Lorg/roboquant/common/TimeSpan;", "nAssets", "", "priceType", "Lorg/roboquant/common/PriceItemType;", "volumeRange", "priceChange", "", "seed", "(Lorg/roboquant/common/Timeframe;Lorg/roboquant/common/TimeSpan;ILorg/roboquant/common/PriceItemType;IDI)V", "assets", "", "Lorg/roboquant/common/Asset;", "getAssets", "()Ljava/util/Set;", "getTimeframe", "()Lorg/roboquant/common/Timeframe;", "timeline", "", "Ljava/time/Instant;", "Lorg/roboquant/common/Timeline;", "getTimeline", "()Ljava/util/List;", "play", "", "channel", "Lorg/roboquant/feeds/EventChannel;", "(Lorg/roboquant/feeds/EventChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "roboquant"}
)
@SourceDebugExtension({"SMAP\nRandomWalk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RandomWalk.kt\norg/roboquant/feeds/random/RandomWalk\n+ 2 Logging.kt\norg/roboquant/common/Logging$Logger\n*L\n1#1,124:1\n45#2,3:125\n*S KotlinDebug\n*F\n+ 1 RandomWalk.kt\norg/roboquant/feeds/random/RandomWalk\n*L\n78#1:125,3\n*E\n"})
public final class RandomWalk implements HistoricFeed {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Timeframe timeframe;
   @NotNull
   private final TimeSpan timeSpan;
   @NotNull
   private final PriceItemType priceType;
   private final int volumeRange;
   private final double priceChange;
   private final int seed;
   @NotNull
   private final Set assets;
   @NotNull
   private static final Logging.Logger logger;

   public RandomWalk(@NotNull Timeframe timeframe, @NotNull TimeSpan timeSpan, int nAssets, @NotNull PriceItemType priceType, int volumeRange, double priceChange, int seed) {
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      Intrinsics.checkNotNullParameter(timeSpan, "timeSpan");
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      super();
      this.timeframe = timeframe;
      this.timeSpan = timeSpan;
      this.priceType = priceType;
      this.volumeRange = volumeRange;
      this.priceChange = priceChange;
      this.seed = seed;
      this.assets = RandomPriceGeneratorKt.randomAssets(nAssets);
      Logging.Logger $this$iv = logger;
      Throwable throwable$iv = null;
      int $i$f$debug = 0;
      if ($this$iv.isDebugEnabled()) {
         int var12 = 0;
         $this$iv.debug("assets=" + nAssets + " timeframe=" + this.getTimeframe(), throwable$iv);
      }

   }

   // $FF: synthetic method
   public RandomWalk(Timeframe var1, TimeSpan var2, int var3, PriceItemType var4, int var5, double var6, int var8, int var9, DefaultConstructorMarker var10) {
      if ((var9 & 2) != 0) {
         var2 = TimeSpanKt.getDays(1);
      }

      if ((var9 & 4) != 0) {
         var3 = 10;
      }

      if ((var9 & 8) != 0) {
         var4 = PriceItemType.BAR;
      }

      if ((var9 & 16) != 0) {
         var5 = 1000;
      }

      if ((var9 & 32) != 0) {
         var6 = ExtensionsKt.getBips((Number)10);
      }

      if ((var9 & 64) != 0) {
         var8 = 42;
      }

      this(var1, var2, var3, var4, var5, var6, var8);
   }

   @NotNull
   public Timeframe getTimeframe() {
      return this.timeframe;
   }

   @NotNull
   public Set getAssets() {
      return this.assets;
   }

   @NotNull
   public List getTimeline() {
      List result = (List)(new ArrayList());

      for(Instant time = this.getTimeframe().getStart(); this.getTimeframe().contains(time); time = TimeSpanKt.plus(time, this.timeSpan)) {
         result.add(time);
      }

      return result;
   }

   @Nullable
   public Object play(@NotNull EventChannel channel, @NotNull Continuation var2) {
      label35: {
         if (var2 instanceof Continuation $continuation) {
            if (($continuation.label & Integer.MIN_VALUE) != 0) {
               $continuation.label -= Integer.MIN_VALUE;
               break label35;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return RandomWalk.this.play((EventChannel)null, (Continuation)this);
            }
         };
      }

      Object $result = $continuation.result;
      Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      RandomPriceGenerator gen;
      Instant time;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure($result);
            gen = new RandomPriceGenerator(CollectionsKt.toList((Iterable)this.getAssets()), this.priceChange, this.volumeRange, this.timeSpan, this.priceType, this.seed);
            time = this.getTimeframe().getStart();
            break;
         case 1:
            time = (Instant)$continuation.L$3;
            gen = (RandomPriceGenerator)$continuation.L$2;
            channel = (EventChannel)$continuation.L$1;
            this = (RandomWalk)$continuation.L$0;
            ResultKt.throwOnFailure($result);
            time = TimeSpanKt.plus(time, this.timeSpan);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while(this.getTimeframe().contains(time)) {
         List actions = gen.next$roboquant();
         Event event = new Event(time, actions);
         $continuation.L$0 = this;
         $continuation.L$1 = channel;
         $continuation.L$2 = gen;
         $continuation.L$3 = time;
         $continuation.label = 1;
         if (channel.send(event, $continuation) == var9) {
            return var9;
         }

         time = TimeSpanKt.plus(time, this.timeSpan);
      }

      return Unit.INSTANCE;
   }

   @NotNull
   public List sample(int size, int samples, @NotNull Random random) {
      return HistoricFeed.DefaultImpls.sample(this, size, samples, random);
   }

   @NotNull
   public List split(@NotNull TimeSpan period, @NotNull TimeSpan overlap) {
      return HistoricFeed.DefaultImpls.split(this, period, overlap);
   }

   @NotNull
   public List split(int size) {
      return HistoricFeed.DefaultImpls.split(this, size);
   }

   public void close() {
      HistoricFeed.DefaultImpls.close(this);
   }

   @NotNull
   public Job playBackground(@NotNull EventChannel channel) {
      return HistoricFeed.DefaultImpls.playBackground(this, channel);
   }

   static {
      logger = Logging.INSTANCE.getLogger(Reflection.getOrCreateKotlinClass(RandomWalk.class));
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bJ$\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"},
      d2 = {"Lorg/roboquant/feeds/random/RandomWalk$Companion;", "", "()V", "logger", "Lorg/roboquant/common/Logging$Logger;", "lastDays", "Lorg/roboquant/feeds/random/RandomWalk;", "days", "", "nAssets", "priceType", "Lorg/roboquant/common/PriceItemType;", "lastYears", "years", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final RandomWalk lastYears(int years, int nAssets, @NotNull PriceItemType priceType) {
         Intrinsics.checkNotNullParameter(priceType, "priceType");
         int lastYear = LocalDate.now().getYear();
         Timeframe tf = Timeframe.Companion.fromYears$default(Timeframe.Companion, lastYear - years, lastYear, (ZoneId)null, 4, (Object)null);
         return new RandomWalk(tf, TimeSpanKt.getDays(1), nAssets, priceType, 0, (double)0.0F, 0, 112, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public static RandomWalk lastYears$default(Companion var0, int var1, int var2, PriceItemType var3, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = 1;
         }

         if ((var4 & 2) != 0) {
            var2 = 10;
         }

         if ((var4 & 4) != 0) {
            var3 = PriceItemType.BAR;
         }

         return var0.lastYears(var1, var2, var3);
      }

      @NotNull
      public final RandomWalk lastDays(int days, int nAssets, @NotNull PriceItemType priceType) {
         Intrinsics.checkNotNullParameter(priceType, "priceType");
         Instant last = Instant.now();
         Intrinsics.checkNotNull(last);
         Instant first = TimeSpanKt.minus(last, TimeSpanKt.getDays(days));
         Timeframe tf = new Timeframe(first, last, false, 4, (DefaultConstructorMarker)null);
         return new RandomWalk(tf, TimeSpanKt.getMinutes(1), nAssets, priceType, 0, (double)0.0F, 0, 112, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public static RandomWalk lastDays$default(Companion var0, int var1, int var2, PriceItemType var3, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = 1;
         }

         if ((var4 & 2) != 0) {
            var2 = 10;
         }

         if ((var4 & 4) != 0) {
            var3 = PriceItemType.BAR;
         }

         return var0.lastDays(var1, var2, var3);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
