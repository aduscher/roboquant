package org.roboquant.common;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.spi.LoggingEventBuilder;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\nB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007J\u0012\u0010\u0003\u001a\u00020\u00042\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t¨\u0006\u000b"},
   d2 = {"Lorg/roboquant/common/Logging;", "", "()V", "getLogger", "Lorg/roboquant/common/Logging$Logger;", "name", "", "getLogger$roboquant", "clazz", "Lkotlin/reflect/KClass;", "Logger", "roboquant"}
)
public final class Logging {
   @NotNull
   public static final Logging INSTANCE = new Logging();

   private Logging() {
   }

   @NotNull
   public final Logger getLogger(@NotNull KClass clazz) {
      Intrinsics.checkNotNullParameter(clazz, "clazz");
      String var10001 = clazz.getQualifiedName();
      if (var10001 == null) {
         var10001 = String.valueOf(clazz);
      }

      return this.getLogger$roboquant(var10001);
   }

   @NotNull
   public final Logger getLogger$roboquant(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      org.slf4j.Logger var10002 = LoggerFactory.getLogger(name);
      Intrinsics.checkNotNullExpressionValue(var10002, "getLogger(...)");
      return new Logger(var10002);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0016J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0016J\u0019\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010H\u0096\u0001J)\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J9\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00130\u00132\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001JX\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u001028\u0010\u0012\u001a(\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013 \u0011*\u0014\u0012\u000e\b\u0001\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00150\u0015\"\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001¢\u0006\u0002\u0010\u0016J)\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00170\u0017H\u0096\u0001J&\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\u001aH\u0086\bø\u0001\u0000J)\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010H\u0096\u0001J9\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001JI\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u00132\u000e\u0010\u001c\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001Jh\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u001028\u0010\u0014\u001a(\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013 \u0011*\u0014\u0012\u000e\b\u0001\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00150\u0015\"\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001¢\u0006\u0002\u0010\u001dJ9\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00170\u0017H\u0096\u0001J\u0019\u0010\u001e\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010H\u0096\u0001J)\u0010\u001e\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J9\u0010\u001e\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00130\u00132\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001JX\u0010\u001e\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u001028\u0010\u0012\u001a(\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013 \u0011*\u0014\u0012\u000e\b\u0001\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00150\u0015\"\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001¢\u0006\u0002\u0010\u0016J)\u0010\u001e\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00170\u0017H\u0096\u0001J&\u0010\u001e\u001a\u00020\u000e2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\u001aH\u0086\bø\u0001\u0000J)\u0010\u001e\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010H\u0096\u0001J9\u0010\u001e\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001JI\u0010\u001e\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u00132\u000e\u0010\u001c\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001Jh\u0010\u001e\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u001028\u0010\u0014\u001a(\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013 \u0011*\u0014\u0012\u000e\b\u0001\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00150\u0015\"\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001¢\u0006\u0002\u0010\u001dJ9\u0010\u001e\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00170\u0017H\u0096\u0001J\u0011\u0010\u001f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010H\u0096\u0001J\u0019\u0010 \u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010H\u0096\u0001J)\u0010 \u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J9\u0010 \u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00130\u00132\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001JX\u0010 \u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u001028\u0010\u0012\u001a(\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013 \u0011*\u0014\u0012\u000e\b\u0001\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00150\u0015\"\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001¢\u0006\u0002\u0010\u0016J)\u0010 \u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00170\u0017H\u0096\u0001J&\u0010 \u001a\u00020\u000e2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\u001aH\u0086\bø\u0001\u0000J)\u0010 \u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010H\u0096\u0001J9\u0010 \u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001JI\u0010 \u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u00132\u000e\u0010\u001c\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001Jh\u0010 \u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u001028\u0010\u0014\u001a(\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013 \u0011*\u0014\u0012\u000e\b\u0001\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00150\u0015\"\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001¢\u0006\u0002\u0010\u001dJ9\u0010 \u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00170\u0017H\u0096\u0001J\t\u0010!\u001a\u00020\"H\u0096\u0001J\u0019\u0010!\u001a\u00020\"2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001bH\u0096\u0001J\u0012\u0010#\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\t\u0010$\u001a\u00020\"H\u0096\u0001J\u0019\u0010$\u001a\u00020\"2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001bH\u0096\u0001J\t\u0010%\u001a\u00020\"H\u0096\u0001J\u0019\u0010%\u001a\u00020\"2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001bH\u0096\u0001J\t\u0010&\u001a\u00020\"H\u0096\u0001J\u0019\u0010&\u001a\u00020\"2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001bH\u0096\u0001J\t\u0010'\u001a\u00020\"H\u0096\u0001J\u0019\u0010'\u001a\u00020\"2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001bH\u0096\u0001J\u0014\u0010(\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0019\u0010)\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010H\u0096\u0001J)\u0010)\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J9\u0010)\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00130\u00132\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001JX\u0010)\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u001028\u0010\u0012\u001a(\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013 \u0011*\u0014\u0012\u000e\b\u0001\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00150\u0015\"\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001¢\u0006\u0002\u0010\u0016J)\u0010)\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00170\u0017H\u0096\u0001J&\u0010)\u001a\u00020\u000e2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\u001aH\u0086\bø\u0001\u0000J)\u0010)\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010H\u0096\u0001J9\u0010)\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001JI\u0010)\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u00132\u000e\u0010\u001c\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001Jh\u0010)\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u001028\u0010\u0014\u001a(\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013 \u0011*\u0014\u0012\u000e\b\u0001\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00150\u0015\"\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001¢\u0006\u0002\u0010\u001dJ9\u0010)\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00170\u0017H\u0096\u0001J\u0019\u0010*\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010H\u0096\u0001J)\u0010*\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001J9\u0010*\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00130\u00132\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001JX\u0010*\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u001028\u0010\u0012\u001a(\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013 \u0011*\u0014\u0012\u000e\b\u0001\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00150\u0015\"\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001¢\u0006\u0002\u0010\u0016J)\u0010*\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00170\u0017H\u0096\u0001J&\u0010*\u001a\u00020\u000e2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\u001aH\u0086\bø\u0001\u0000J)\u0010*\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010H\u0096\u0001J9\u0010*\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001JI\u0010*\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00130\u00132\u000e\u0010\u001c\u001a\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001Jh\u0010*\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u001028\u0010\u0014\u001a(\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013 \u0011*\u0014\u0012\u000e\b\u0001\u0012\n \u0011*\u0004\u0018\u00010\u00130\u0013\u0018\u00010\u00150\u0015\"\n \u0011*\u0004\u0018\u00010\u00130\u0013H\u0096\u0001¢\u0006\u0002\u0010\u001dJ9\u0010*\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u001b0\u001b2\u000e\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u00102\u000e\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00170\u0017H\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006+"},
      d2 = {"Lorg/roboquant/common/Logging$Logger;", "Lorg/slf4j/Logger;", "slf4jLogger", "(Lorg/slf4j/Logger;)V", "atDebug", "Lorg/slf4j/spi/LoggingEventBuilder;", "atError", "atInfo", "atLevel", "level", "Lorg/slf4j/event/Level;", "atTrace", "atWarn", "debug", "", "p0", "", "kotlin.jvm.PlatformType", "p1", "", "p2", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "", "throwable", "messageProducer", "Lkotlin/Function0;", "Lorg/slf4j/Marker;", "p3", "(Lorg/slf4j/Marker;Ljava/lang/String;[Ljava/lang/Object;)V", "error", "getName", "info", "isDebugEnabled", "", "isEnabledForLevel", "isErrorEnabled", "isInfoEnabled", "isTraceEnabled", "isWarnEnabled", "makeLoggingEventBuilder", "trace", "warn", "roboquant"}
   )
   public static final class Logger implements org.slf4j.Logger {
      @NotNull
      private final org.slf4j.Logger slf4jLogger;

      public Logger(@NotNull org.slf4j.Logger slf4jLogger) {
         Intrinsics.checkNotNullParameter(slf4jLogger, "slf4jLogger");
         super();
         this.slf4jLogger = slf4jLogger;
      }

      public void debug(String p0) {
         this.slf4jLogger.debug(p0);
      }

      public void debug(String p0, Object p1) {
         this.slf4jLogger.debug(p0, p1);
      }

      public void debug(String p0, Object p1, Object p2) {
         this.slf4jLogger.debug(p0, p1, p2);
      }

      public void debug(String p0, Object... p1) {
         this.slf4jLogger.debug(p0, p1);
      }

      public void debug(String p0, Throwable p1) {
         this.slf4jLogger.debug(p0, p1);
      }

      public void debug(Marker p0, String p1) {
         this.slf4jLogger.debug(p0, p1);
      }

      public void debug(Marker p0, String p1, Object p2) {
         this.slf4jLogger.debug(p0, p1, p2);
      }

      public void debug(Marker p0, String p1, Object p2, Object p3) {
         this.slf4jLogger.debug(p0, p1, p2, p3);
      }

      public void debug(Marker p0, String p1, Object... p2) {
         this.slf4jLogger.debug(p0, p1, p2);
      }

      public void debug(Marker p0, String p1, Throwable p2) {
         this.slf4jLogger.debug(p0, p1, p2);
      }

      public void error(String p0) {
         this.slf4jLogger.error(p0);
      }

      public void error(String p0, Object p1) {
         this.slf4jLogger.error(p0, p1);
      }

      public void error(String p0, Object p1, Object p2) {
         this.slf4jLogger.error(p0, p1, p2);
      }

      public void error(String p0, Object... p1) {
         this.slf4jLogger.error(p0, p1);
      }

      public void error(String p0, Throwable p1) {
         this.slf4jLogger.error(p0, p1);
      }

      public void error(Marker p0, String p1) {
         this.slf4jLogger.error(p0, p1);
      }

      public void error(Marker p0, String p1, Object p2) {
         this.slf4jLogger.error(p0, p1, p2);
      }

      public void error(Marker p0, String p1, Object p2, Object p3) {
         this.slf4jLogger.error(p0, p1, p2, p3);
      }

      public void error(Marker p0, String p1, Object... p2) {
         this.slf4jLogger.error(p0, p1, p2);
      }

      public void error(Marker p0, String p1, Throwable p2) {
         this.slf4jLogger.error(p0, p1, p2);
      }

      public String getName() {
         return this.slf4jLogger.getName();
      }

      public void info(String p0) {
         this.slf4jLogger.info(p0);
      }

      public void info(String p0, Object p1) {
         this.slf4jLogger.info(p0, p1);
      }

      public void info(String p0, Object p1, Object p2) {
         this.slf4jLogger.info(p0, p1, p2);
      }

      public void info(String p0, Object... p1) {
         this.slf4jLogger.info(p0, p1);
      }

      public void info(String p0, Throwable p1) {
         this.slf4jLogger.info(p0, p1);
      }

      public void info(Marker p0, String p1) {
         this.slf4jLogger.info(p0, p1);
      }

      public void info(Marker p0, String p1, Object p2) {
         this.slf4jLogger.info(p0, p1, p2);
      }

      public void info(Marker p0, String p1, Object p2, Object p3) {
         this.slf4jLogger.info(p0, p1, p2, p3);
      }

      public void info(Marker p0, String p1, Object... p2) {
         this.slf4jLogger.info(p0, p1, p2);
      }

      public void info(Marker p0, String p1, Throwable p2) {
         this.slf4jLogger.info(p0, p1, p2);
      }

      public boolean isDebugEnabled() {
         return this.slf4jLogger.isDebugEnabled();
      }

      public boolean isDebugEnabled(Marker p0) {
         return this.slf4jLogger.isDebugEnabled(p0);
      }

      public boolean isErrorEnabled() {
         return this.slf4jLogger.isErrorEnabled();
      }

      public boolean isErrorEnabled(Marker p0) {
         return this.slf4jLogger.isErrorEnabled(p0);
      }

      public boolean isInfoEnabled() {
         return this.slf4jLogger.isInfoEnabled();
      }

      public boolean isInfoEnabled(Marker p0) {
         return this.slf4jLogger.isInfoEnabled(p0);
      }

      public boolean isTraceEnabled() {
         return this.slf4jLogger.isTraceEnabled();
      }

      public boolean isTraceEnabled(Marker p0) {
         return this.slf4jLogger.isTraceEnabled(p0);
      }

      public boolean isWarnEnabled() {
         return this.slf4jLogger.isWarnEnabled();
      }

      public boolean isWarnEnabled(Marker p0) {
         return this.slf4jLogger.isWarnEnabled(p0);
      }

      public void trace(String p0) {
         this.slf4jLogger.trace(p0);
      }

      public void trace(String p0, Object p1) {
         this.slf4jLogger.trace(p0, p1);
      }

      public void trace(String p0, Object p1, Object p2) {
         this.slf4jLogger.trace(p0, p1, p2);
      }

      public void trace(String p0, Object... p1) {
         this.slf4jLogger.trace(p0, p1);
      }

      public void trace(String p0, Throwable p1) {
         this.slf4jLogger.trace(p0, p1);
      }

      public void trace(Marker p0, String p1) {
         this.slf4jLogger.trace(p0, p1);
      }

      public void trace(Marker p0, String p1, Object p2) {
         this.slf4jLogger.trace(p0, p1, p2);
      }

      public void trace(Marker p0, String p1, Object p2, Object p3) {
         this.slf4jLogger.trace(p0, p1, p2, p3);
      }

      public void trace(Marker p0, String p1, Object... p2) {
         this.slf4jLogger.trace(p0, p1, p2);
      }

      public void trace(Marker p0, String p1, Throwable p2) {
         this.slf4jLogger.trace(p0, p1, p2);
      }

      public void warn(String p0) {
         this.slf4jLogger.warn(p0);
      }

      public void warn(String p0, Object p1) {
         this.slf4jLogger.warn(p0, p1);
      }

      public void warn(String p0, Object p1, Object p2) {
         this.slf4jLogger.warn(p0, p1, p2);
      }

      public void warn(String p0, Object... p1) {
         this.slf4jLogger.warn(p0, p1);
      }

      public void warn(String p0, Throwable p1) {
         this.slf4jLogger.warn(p0, p1);
      }

      public void warn(Marker p0, String p1) {
         this.slf4jLogger.warn(p0, p1);
      }

      public void warn(Marker p0, String p1, Object p2) {
         this.slf4jLogger.warn(p0, p1, p2);
      }

      public void warn(Marker p0, String p1, Object p2, Object p3) {
         this.slf4jLogger.warn(p0, p1, p2, p3);
      }

      public void warn(Marker p0, String p1, Object... p2) {
         this.slf4jLogger.warn(p0, p1, p2);
      }

      public void warn(Marker p0, String p1, Throwable p2) {
         this.slf4jLogger.warn(p0, p1, p2);
      }

      public final void trace(@Nullable Throwable throwable, @NotNull Function0 messageProducer) {
         Intrinsics.checkNotNullParameter(messageProducer, "messageProducer");
         int $i$f$trace = 0;
         if (this.isTraceEnabled()) {
            this.trace((String)messageProducer.invoke(), throwable);
         }

      }

      // $FF: synthetic method
      public static void trace$default(Logger $this, Throwable throwable, Function0 messageProducer, int $i$f$trace, Object var4) {
         if (($i$f$trace & 1) != 0) {
            throwable = null;
         }

         Intrinsics.checkNotNullParameter(messageProducer, "messageProducer");
         $i$f$trace = 0;
         if ($this.isTraceEnabled()) {
            $this.trace((String)messageProducer.invoke(), throwable);
         }

      }

      public final void debug(@Nullable Throwable throwable, @NotNull Function0 messageProducer) {
         Intrinsics.checkNotNullParameter(messageProducer, "messageProducer");
         int $i$f$debug = 0;
         if (this.isDebugEnabled()) {
            this.debug((String)messageProducer.invoke(), throwable);
         }

      }

      // $FF: synthetic method
      public static void debug$default(Logger $this, Throwable throwable, Function0 messageProducer, int $i$f$debug, Object var4) {
         if (($i$f$debug & 1) != 0) {
            throwable = null;
         }

         Intrinsics.checkNotNullParameter(messageProducer, "messageProducer");
         $i$f$debug = 0;
         if ($this.isDebugEnabled()) {
            $this.debug((String)messageProducer.invoke(), throwable);
         }

      }

      public final void info(@Nullable Throwable throwable, @NotNull Function0 messageProducer) {
         Intrinsics.checkNotNullParameter(messageProducer, "messageProducer");
         int $i$f$info = 0;
         if (this.isInfoEnabled()) {
            this.info((String)messageProducer.invoke(), throwable);
         }

      }

      // $FF: synthetic method
      public static void info$default(Logger $this, Throwable throwable, Function0 messageProducer, int $i$f$info, Object var4) {
         if (($i$f$info & 1) != 0) {
            throwable = null;
         }

         Intrinsics.checkNotNullParameter(messageProducer, "messageProducer");
         $i$f$info = 0;
         if ($this.isInfoEnabled()) {
            $this.info((String)messageProducer.invoke(), throwable);
         }

      }

      public final void warn(@Nullable Throwable throwable, @NotNull Function0 messageProducer) {
         Intrinsics.checkNotNullParameter(messageProducer, "messageProducer");
         int $i$f$warn = 0;
         if (this.isWarnEnabled()) {
            this.warn((String)messageProducer.invoke(), throwable);
         }

      }

      // $FF: synthetic method
      public static void warn$default(Logger $this, Throwable throwable, Function0 messageProducer, int $i$f$warn, Object var4) {
         if (($i$f$warn & 1) != 0) {
            throwable = null;
         }

         Intrinsics.checkNotNullParameter(messageProducer, "messageProducer");
         $i$f$warn = 0;
         if ($this.isWarnEnabled()) {
            $this.warn((String)messageProducer.invoke(), throwable);
         }

      }

      public final void error(@Nullable Throwable throwable, @NotNull Function0 messageProducer) {
         Intrinsics.checkNotNullParameter(messageProducer, "messageProducer");
         int $i$f$error = 0;
         if (this.isErrorEnabled()) {
            this.error((String)messageProducer.invoke(), throwable);
         }

      }

      // $FF: synthetic method
      public static void error$default(Logger $this, Throwable throwable, Function0 messageProducer, int $i$f$error, Object var4) {
         if (($i$f$error & 1) != 0) {
            throwable = null;
         }

         Intrinsics.checkNotNullParameter(messageProducer, "messageProducer");
         $i$f$error = 0;
         if ($this.isErrorEnabled()) {
            $this.error((String)messageProducer.invoke(), throwable);
         }

      }

      @Nullable
      public LoggingEventBuilder makeLoggingEventBuilder(@Nullable Level level) {
         return this.slf4jLogger.makeLoggingEventBuilder(level);
      }

      @Nullable
      public LoggingEventBuilder atLevel(@Nullable Level level) {
         return this.slf4jLogger.atLevel(level);
      }

      public boolean isEnabledForLevel(@Nullable Level level) {
         return this.slf4jLogger.isEnabledForLevel(level);
      }

      @Nullable
      public LoggingEventBuilder atTrace() {
         return this.slf4jLogger.atTrace();
      }

      @Nullable
      public LoggingEventBuilder atDebug() {
         return this.slf4jLogger.atDebug();
      }

      @Nullable
      public LoggingEventBuilder atInfo() {
         return this.slf4jLogger.atInfo();
      }

      @Nullable
      public LoggingEventBuilder atWarn() {
         return this.slf4jLogger.atWarn();
      }

      @Nullable
      public LoggingEventBuilder atError() {
         return this.slf4jLogger.atError();
      }
   }
}
