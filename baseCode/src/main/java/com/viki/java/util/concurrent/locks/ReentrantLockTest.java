package com.viki.java.util.concurrent.locks;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Viki on 2017/5/15.
 * Function: TODO
 */
public class ReentrantLockTest {


    public static void t0(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,60, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(50));
        ReentrantLock reentrantLock = new ReentrantLock();


        /*threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try{

                }catch (Exception e){

                }finally {
                    reentrantLock.newCondition().
                }
            }
        });*/

    }

    public static void main(String[] args) throws InterruptedException {
        t2();
    }

    private static void t1(){
        final ExecutorService exec = Executors.newFixedThreadPool(4);
        final ReentrantLock lock = new ReentrantLock();
        final Condition con = lock.newCondition();
        final int time = 5;
        final Runnable add = new Runnable() {
            @Override
            public void run() {
                System.out.println("Pre " + lock);
                lock.lock();
                try {
                    con.await(time, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "Post " + lock.toString());
                    lock.unlock();
                }
            }
        };
        for(int index = 0; index < 4; index++){
            exec.submit(add);
        }
        exec.shutdown();
    }

    private static void t2(){
        final ExecutorService exec = Executors.newFixedThreadPool(4);
        final Object o = new Object();
        final int time = 5;
        final Runnable add = new Runnable() {
            @Override
            public void run() {
                System.out.println("Pre "+Thread.currentThread().getName());
                synchronized (o){
                    try {
                        o.wait(time * 1000);
//                        con.await(time, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("Post "+Thread.currentThread().getName());
                    }
                }
            }
        };
        for(int index = 0; index < 4; index++){
            exec.submit(add);
        }
        exec.shutdown();
    }
}
