package org.roboquant.feeds;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.ParallelJobs;
import org.roboquant.common.Timeframe;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u000eR\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"},
   d2 = {"Lorg/roboquant/feeds/CombinedLiveFeed;", "Lorg/roboquant/feeds/Feed;", "feeds", "", "Lorg/roboquant/feeds/LiveFeed;", "([Lorg/roboquant/feeds/LiveFeed;)V", "getFeeds", "()[Lorg/roboquant/feeds/LiveFeed;", "[Lorg/roboquant/feeds/LiveFeed;", "close", "", "play", "channel", "Lorg/roboquant/feeds/EventChannel;", "(Lorg/roboquant/feeds/EventChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nCombinedLiveFeed.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CombinedLiveFeed.kt\norg/roboquant/feeds/CombinedLiveFeed\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,53:1\n13309#2,2:54\n*S KotlinDebug\n*F\n+ 1 CombinedLiveFeed.kt\norg/roboquant/feeds/CombinedLiveFeed\n*L\n48#1:54,2\n*E\n"})
public final class CombinedLiveFeed implements Feed {
   @NotNull
   private final LiveFeed[] feeds;

   public CombinedLiveFeed(@NotNull LiveFeed... feeds) {
      Intrinsics.checkNotNullParameter(feeds, "feeds");
      super();
      this.feeds = feeds;
   }

   @NotNull
   public final LiveFeed[] getFeeds() {
      return this.feeds;
   }

   @Nullable
   public Object play(@NotNull final EventChannel channel, @NotNull Continuation $completion) {
      ParallelJobs jobs = new ParallelJobs();
      LiveFeed[] var4 = this.feeds;
      int var5 = 0;

      for(int var6 = var4.length; var5 < var6; ++var5) {
         final LiveFeed feed = var4[var5];
         jobs.add(new Function2((Continuation)null) {
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               Object var2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               switch (this.label) {
                  case 0:
                     ResultKt.throwOnFailure($result);
                     LiveFeed var10000 = feed;
                     EventChannel var10001 = channel;
                     Continuation var10002 = (Continuation)this;
                     this.label = 1;
                     if (var10000.play(var10001, var10002) == var2) {
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
         });
      }

      Object var10000 = jobs.joinAll($completion);
      return var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var10000 : Unit.INSTANCE;
   }

   public void close() {
      Object[] $this$forEach$iv = this.feeds;
      int $i$f$forEach = 0;
      int var3 = 0;

      for(int var4 = $this$forEach$iv.length; var3 < var4; ++var3) {
         Object element$iv = $this$forEach$iv[var3];
         int var7 = 0;
         ((LiveFeed)element$iv).close();
      }

   }

   @NotNull
   public Timeframe getTimeframe() {
      return Feed.DefaultImpls.getTimeframe(this);
   }

   @NotNull
   public Job playBackground(@NotNull EventChannel channel) {
      return Feed.DefaultImpls.playBackground(this, channel);
   }
}
