package com.viki.concurrent;

import com.lmax.disruptor.RingBuffer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Function:
 *  线程池增强:
 *      coresize自适应
 *      支持UncaughtExceptionHandler处理
 *
 *
 * @author Viki
 * @date 2017/11/29 14:19
 */
public class EnhancementThreadPoolExecutor extends ThreadPoolExecutor {

    private volatile boolean allowCoreThreadTimeOut = true;

    private volatile AtomicLong tasksInQueue = new AtomicLong();

    private volatile RingBuffer<Long> recentTaskCount = RingBuffer.createSingleProducer(()->0L, 1000);

    public EnhancementThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new ThreadFactory() {

            private AtomicLong threadNumber = new AtomicLong();

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(new ThreadGroup("enhancement-pool-"), r,
                        "enhancement-pool-" + threadNumber.getAndIncrement(),
                        0);
                if (t.isDaemon()) {
                    t.setDaemon(false);
                }
                if (t.getPriority() < Thread.MAX_PRIORITY) {
                    t.setPriority(Thread.MAX_PRIORITY);
                }
//                t.setUncaughtExceptionHandler(); TODO
                return t;
            }
        });
    }

    public EnhancementThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                threadFactory, true);
    }

    public EnhancementThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, boolean allowCoreThreadTimeOut) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        this.allowCoreThreadTimeOut(allowCoreThreadTimeOut);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        recentTaskCount.publish(this.getQueue().size());
    }


    public static void main(String[] args) {
        RingBuffer<Long> recentTaskCount = RingBuffer.createSingleProducer(()->{
            return new Long(0);
        }, 4 << 2);
        for(int i = 0; i<30; i++){
//            recentTaskCount.get()
            System.out.println(recentTaskCount.remainingCapacity());
        }
    }
}
