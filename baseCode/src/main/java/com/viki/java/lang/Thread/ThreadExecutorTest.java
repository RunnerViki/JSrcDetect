package com.viki.java.lang.Thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/20 18:32
 */
public class ThreadExecutorTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 1 , TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(1000));
        while (true){
            try{
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("开始:"+Thread.currentThread().getName());
                            TimeUnit.SECONDS.sleep(+20);
                            System.out.println("结束:"+Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }finally {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
