package com.viki.java.lang.ref.WeakReference;

import java.lang.ref.WeakReference;

/**
 * Created by Viki on 2017/5/13.
 * Feature:
 *      1. 只被弱引用引用的对象无法阻止下一次GC时被回收
 * Function:
 *      1.常用于规范化映射,将不再需要的对象交由JVM自己回收处理.
 *
 * Example:
 *      1. ThreadLocal: 每个Thread都有{@link java.lang.ThreadLocal.ThreadLocalMap}成员，在{@link java.lang.Thread}.exit时被置为null。但ThreadLocalMap中的{@link java.lang.ThreadLocal.ThreadLocalMap.Entry}数组并未置为空
 *      2. 由于Entry继承了WeakReference，所以它将在下一个gc时由jvm回收
 */
public class WeakReferenceTest {

    public String property = "WeakReferenceTest的成员";

    public static void main(String[] args) {
        new WeakReferenceTest().t1();
    }

    public void t1(){
        WeakReferenceTest obj = new WeakReferenceTest();
        WeakReference<WeakReferenceTest> wr = new WeakReference<WeakReferenceTest>(obj);
        obj = null;                                     // 强引用不再引用实例
        System.out.println(wr.get().property);         // 还没有被GC回收
        //通知JVM回收对象
        System.gc();                                     // 显示要求JVM回收，此时实例肯定被回收
        if (wr.get()==null) {
            System.out.println("obj 已经被清除了 ");  // 已被回收
        } else {
            System.out.println("obj 尚未被清除，其信息是 "+obj.property);
        }
    }



}
