package com.viki.java.lang.ThreadLocal;

import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/12/29 15:37
 */
public class ThreadLocalTest1 {



    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){

            @Override
            protected Integer initialValue() {
                return 1;
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000));
        int x = 20;
        while(x-- > 0){
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
//                    Thread.currentThread().setDaemon(true);
                    System.out.println(LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t" + threadLocal.get());
                    threadLocal.set(threadLocal.get()+1);
                }
            });
        }
        threadPoolExecutor.shutdown();
    }
}
