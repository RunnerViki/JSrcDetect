package com.viki.java.util.concurrent.locks;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Viki on 2017/5/15.
 * Function: TODO
 */
public class ReentrantLockTest {


    public static void t1(){
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
}
