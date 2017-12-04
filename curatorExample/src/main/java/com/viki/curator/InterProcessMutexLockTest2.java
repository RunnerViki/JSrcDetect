package com.viki.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/9 13:53
 */
public class InterProcessMutexLockTest2 {



    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.MINUTE, 1);

        Timer timer = new Timer();

        //创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        final CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        client.start();

        //创建分布式锁, 锁空间的根节点路径为/curator/lock
        final InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try{
                    System.out.println("start");
                    mutex.acquire();
                    //获得了锁, 进行业务流程
                    System.out.println(new Date() + Thread.currentThread().getName() + "Enter mutex");
                    TimeUnit.SECONDS.sleep(2);
                    //完成业务流程, 释放锁
                    mutex.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(timerTask, calendar.getTime(), 10000);
        //关闭客户端
//        client.close();
    }


}
