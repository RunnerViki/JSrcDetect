package com.viki.java.util.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by Viki on 2017/9/17.
 * Function: TODO
 */
public class AtomicIntegerFieldUpdaterTest {



    /*
    * 1、volatile只能保存多线程环境下的可见性，无法保证原子性操作，AtomicIntegerFieldUpdater利用CAS完成原子操作, 同理AtomicMarkableReference
    * */
    @Test
    public void atomicUpdate(){
        IntegerClass integerClass = new IntegerClass();
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(IntegerClass.class, "x");
        int x = atomicIntegerFieldUpdater.addAndGet(integerClass, 2);
        System.out.println(x);
        atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(IntegerClass.class, "y");
        int y = atomicIntegerFieldUpdater.addAndGet(integerClass, 2);
        System.out.println(y);
    }


    class IntegerClass{
        volatile int x = 2;
        int y = 3;
    }
}
