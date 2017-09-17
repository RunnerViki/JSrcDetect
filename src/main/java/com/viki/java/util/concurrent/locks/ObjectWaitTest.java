package com.viki.java.util.concurrent.locks;

import java.util.concurrent.TimeUnit;

/**
 * Created by Viki on 2017/9/17.
 * Function: TODO
 */
public class ObjectWaitTest {

    public static void main(String[] args) {
        ThreadA ta = new ThreadA("ThreadA");
        synchronized (ta) { // 通过synchronized(ta)获取“对象ta的同步锁”
            try {
                System.out.println(Thread.currentThread().getName() + " start ThreadA");
                ta.start();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " block");
                ta.wait();    // 等待

                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            synchronized (this) { // 通过synchronized(this)获取“当前对象的同步锁”
                System.out.println(Thread.currentThread().getName() + " wakup others");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notify();    // 唤醒“当前对象上的等待线程”
            }
        }
    }
}