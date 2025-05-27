package org.roboquant.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.Job.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005J4\u0010\f\u001a\u00020\u00052'\u0010\u000f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0010¢\u0006\u0002\b\u0012¢\u0006\u0002\u0010\u0013J\u0006\u0010\u0014\u001a\u00020\rJ\u000e\u0010\u0015\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u0016J\u0006\u0010\u0017\u001a\u00020\rR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"},
   d2 = {"Lorg/roboquant/common/ParallelJobs;", "", "()V", "jobs", "", "Lkotlinx/coroutines/Job;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "size", "", "getSize", "()I", "add", "", "job", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "cancelAll", "joinAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinAllBlocking", "roboquant"}
)
@SourceDebugExtension({"SMAP\nParallelJobs.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ParallelJobs.kt\norg/roboquant/common/ParallelJobs\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,88:1\n1855#2,2:89\n*S KotlinDebug\n*F\n+ 1 ParallelJobs.kt\norg/roboquant/common/ParallelJobs\n*L\n67#1:89,2\n*E\n"})
public final class ParallelJobs {
   @NotNull
   private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope((CoroutineContext)Dispatchers.getDefault());
   @NotNull
   private final List jobs = (List)(new ArrayList());

   @Nullable
   public final Object joinAll(@NotNull Continuation var1) {
      label20: {
         if (var1 instanceof Continuation $continuation) {
            if (($continuation.label & Integer.MIN_VALUE) != 0) {
               $continuation.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         $continuation = new ContinuationImpl(var1) {
            Object L$0;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return ParallelJobs.this.joinAll((Continuation)this);
            }
         };
      }

      Object $result = $continuation.result;
      Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure($result);
            Collection var10000 = (Collection)this.jobs;
            $continuation.L$0 = this;
            $continuation.label = 1;
            if (AwaitKt.joinAll(var10000, $continuation) == var4) {
               return var4;
            }
            break;
         case 1:
            this = (ParallelJobs)$continuation.L$0;
            ResultKt.throwOnFailure($result);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      this.jobs.clear();
      return Unit.INSTANCE;
   }

   public final void joinAllBlocking() {
      BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            Object var2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  Collection var10000 = (Collection)ParallelJobs.this.jobs;
                  Continuation var10001 = (Continuation)this;
                  this.label = 1;
                  if (AwaitKt.joinAll(var10000, var10001) == var2) {
                     return var2;
                  }
                  break;
               case 1:
                  ResultKt.throwOnFailure($result);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            ParallelJobs.this.jobs.clear();
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
   }

   public final int getSize() {
      return this.jobs.size();
   }

   public final void cancelAll() {
      Iterable $this$forEach$iv = (Iterable)this.jobs;
      int $i$f$forEach = 0;

      for(Object element$iv : $this$forEach$iv) {
         Job it = (Job)element$iv;
         int var6 = 0;
         DefaultImpls.cancel$default(it, (CancellationException)null, 1, (Object)null);
      }

      this.jobs.clear();
   }

   @NotNull
   public final Job add(@NotNull Function2 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      Job job = BuildersKt.launch$default(this.scope, (CoroutineContext)null, (CoroutineStart)null, block, 3, (Object)null);
      this.jobs.add(job);
      return job;
   }

   public final void add(@NotNull Job job) {
      Intrinsics.checkNotNullParameter(job, "job");
      this.jobs.add(job);
   }
}
