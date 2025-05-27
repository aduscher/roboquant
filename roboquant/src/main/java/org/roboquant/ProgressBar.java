package org.roboquant;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Timeframe;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0019"},
   d2 = {"Lorg/roboquant/ProgressBar;", "", "timeframe", "Lorg/roboquant/common/Timeframe;", "(Lorg/roboquant/common/Timeframe;)V", "currentPercent", "", "lastOutput", "", "nextUpdate", "Ljava/time/Instant;", "kotlin.jvm.PlatformType", "post", "pre", "progressChar", "", "getTimeframe", "()Lorg/roboquant/common/Timeframe;", "draw", "", "start", "stop", "update", "time", "Companion", "roboquant"}
)
final class ProgressBar {
   @NotNull
   private static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Timeframe timeframe;
   private int currentPercent;
   private final char progressChar;
   @NotNull
   private String pre;
   @NotNull
   private String post;
   private Instant nextUpdate;
   @NotNull
   private String lastOutput;
   private static final int TOTAL_BAR_LENGTH = 36;

   public ProgressBar(@NotNull Timeframe timeframe) {
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      super();
      this.timeframe = timeframe;
      this.currentPercent = -1;
      this.progressChar = Companion.getProgressChar();
      this.pre = "";
      this.post = "";
      this.nextUpdate = Instant.MIN;
      this.lastOutput = "";
      this.currentPercent = 0;
      this.post = "";
      this.pre = this.timeframe.toPrettyString() + " | ";
      this.nextUpdate = Instant.MIN;
      this.lastOutput = "";
   }

   @NotNull
   public final Timeframe getTimeframe() {
      return this.timeframe;
   }

   public final void start() {
      this.draw();
   }

   public final void update(@NotNull Instant time) {
      Intrinsics.checkNotNullParameter(time, "time");
      Duration totalDuration = this.timeframe.getDuration();
      Duration currentDuration = (new Timeframe(this.timeframe.getStart(), time, false, 4, (DefaultConstructorMarker)null)).getDuration();
      int percent = MathKt.roundToInt((double)currentDuration.getSeconds() * (double)100.0F / (double)totalDuration.getSeconds());
      percent = Math.min(percent, 100);
      if (percent != this.currentPercent) {
         Instant now = Instant.now();
         if (now.compareTo(this.nextUpdate) >= 0) {
            this.nextUpdate = now.plusMillis(500L);
            this.currentPercent = percent;
            this.draw();
         }
      }
   }

   private final void draw() {
      StringBuilder sb = new StringBuilder(100);
      sb.append('\r').append(this.pre);
      StringCompanionObject var10001 = StringCompanionObject.INSTANCE;
      Locale var3 = Locale.ENGLISH;
      String it = "%3d";
      Object[] var5 = new Object[]{this.currentPercent};
      String var10 = String.format(var3, it, Arrays.copyOf(var5, var5.length));
      Intrinsics.checkNotNullExpressionValue(var10, "format(...)");
      sb.append(var10).append("% |");
      int filled = this.currentPercent * 36 / 100;
      byte var7 = 36;

      for(int it = 0; it < var7; ++it) {
         int var6 = 0;
         if (it <= filled) {
            sb.append(this.progressChar);
         } else {
            sb.append(' ');
         }
      }

      sb.append(this.post);
      if (this.currentPercent == 100) {
         sb.append("\n");
      }

      String var10000 = sb.toString();
      Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      String str = var10000;
      if (!Intrinsics.areEqual(str, this.lastOutput)) {
         System.out.print(str);
         this.lastOutput = str;
         System.out.flush();
      }

   }

   public final void stop() {
      if (this.currentPercent < 100) {
         this.currentPercent = 100;
         this.draw();
         System.out.flush();
      }

   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"},
      d2 = {"Lorg/roboquant/ProgressBar$Companion;", "", "()V", "TOTAL_BAR_LENGTH", "", "getProgressChar", "", "roboquant"}
   )
   private static final class Companion {
      private Companion() {
      }

      private final char getProgressChar() {
         String var10000 = System.getProperty("os.name");
         Intrinsics.checkNotNullExpressionValue(var10000, "getProperty(...)");
         return (char)(StringsKt.startsWith$default(var10000, "Win", false, 2, (Object)null) ? '=' : '█');
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
