package org.roboquant.common;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0003J\u0006\u0010\u0014\u001a\u00020\rJ\b\u0010\u0015\u001a\u00020\u000fH\u0016J\u0006\u0010\u0016\u001a\u00020\bJ\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"},
   d2 = {"Lorg/roboquant/common/PriceSeries;", "", "capacity", "", "(I)V", "counter", "", "data", "", "size", "getSize", "()I", "add", "", "price", "", "clear", "", "increaseCapacity", "newCapacity", "isFull", "last", "toDoubleArray", "update", "roboquant"}
)
@SourceDebugExtension({"SMAP\nPriceSeries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PriceSeries.kt\norg/roboquant/common/PriceSeries\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,137:1\n1#2:138\n*E\n"})
public class PriceSeries {
   private int capacity;
   @NotNull
   private double[] data;
   private long counter;

   public PriceSeries(int capacity) {
      this.capacity = capacity;
      this.data = new double[this.capacity];
   }

   public boolean add(double price) {
      int index = (int)(this.counter % (long)this.capacity);
      this.data[index] = price;
      int var4 = this.counter++;
      return this.isFull();
   }

   public boolean update(double price) {
      if (this.counter == 0L) {
         throw new NoSuchElementException();
      } else {
         int index = (int)((this.counter - 1L) % (long)this.capacity);
         this.data[index] = price;
         return this.isFull();
      }
   }

   public double last() {
      if (this.counter == 0L) {
         throw new NoSuchElementException();
      } else {
         int index = (int)((this.counter - 1L) % (long)this.capacity);
         return this.data[index];
      }
   }

   public final boolean isFull() {
      return this.counter >= (long)this.capacity;
   }

   public final int getSize() {
      return this.counter > (long)this.capacity ? this.capacity : (int)this.counter;
   }

   @NotNull
   public final double[] toDoubleArray() {
      double[] result = new double[this.getSize()];
      double[] var10000;
      if (this.counter > (long)this.capacity) {
         int offset = (int)(this.counter % (long)this.capacity);
         System.arraycopy(this.data, offset, result, 0, this.capacity - offset);
         System.arraycopy(this.data, 0, result, this.capacity - offset, offset);
         var10000 = result;
      } else {
         System.arraycopy(this.data, 0, result, 0, result.length);
         var10000 = result;
      }

      return var10000;
   }

   public final void increaseCapacity(int newCapacity) {
      if (newCapacity <= this.capacity) {
         int var3 = 0;
         String var4 = "new capcity should be larger than old one, new=" + newCapacity + " old=" + this.capacity;
         throw new IllegalArgumentException(var4.toString());
      } else {
         double[] oldData = this.toDoubleArray();
         this.data = new double[newCapacity];
         System.arraycopy(oldData, 0, this.data, 0, this.getSize());
         this.capacity = newCapacity;
      }
   }

   public void clear() {
      this.counter = 0L;
      this.data = new double[this.capacity];
   }
}
