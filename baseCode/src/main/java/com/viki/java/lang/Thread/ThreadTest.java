package com.viki.java.lang.Thread;

import org.junit.Test;

/**
 * Created by Viki on 2017/5/13.
 */
public class ThreadTest {

    /*
    * 线程对象被置为null,并且显示调用gc。按常理，线程应该不再输出is running，但结果并不如此
    * 原因:
    *   1. 当前正在运行的线程被看做是GC roots 对象之一
    *   2. t = null然后gc只会回收分配在堆上的线程对象，无法回收运行在虚拟机栈上的栈帧
    *   3. 举例: main线程也没有任何引用指向它，但不会被GC 回收，就因为第1点.
    * */
    @Test
    public void liveThreadAsGcRoots() {
        // anonymous class extends Thread
        Thread t = new Thread() {
            @Override
            public void run() {
                // infinite loop
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    // as long as this line printed out, you know it is alive.
                    System.out.println("thread is running...");
                }
            }
        };
        t.start(); // Line A
        t = null; // Line B
        // no more references for Thread t
        // another infinite loop
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            System.gc();
            System.out.println("Executed System.gc()");
        } // The program will run forever until you use ^C to stop it
    }

}
