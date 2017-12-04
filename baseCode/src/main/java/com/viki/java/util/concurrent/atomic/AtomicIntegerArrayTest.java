package com.viki.java.util.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Viki on 2017/9/17.
 * Function: 一个支持原子化操作的无锁整数数组对象
 */
public class AtomicIntegerArrayTest {

    /*
    * 1、AtomicIntegerArray基于unsafe操作内存中的数组元素
    * 2、利用数组对象的起始元素的地址base + 每个元素占用空间的大小scale位移shift计算每个元素在内存中的位置offset
    * 3、【精】在进行类初始化时，使用(scale & (scale - 1)) != 0判断scale是否是2的指数
    * 4、利用CPU的CAS操作保证操作的原子性
    * 5、设置元素为volatile保证atomicIntegerArray在多线程中的可见性
    * */


    @Test
    public void scaleTest(){
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        atomicIntegerArray.set(8, 1);
        AtomicIntegerArray atomicIntegerArray2 = new AtomicIntegerArray(100);
        atomicIntegerArray2.set(8, 2);
        System.out.println(atomicIntegerArray.get(8));
        System.out.println(atomicIntegerArray2.get(8));
    }

}
