package com.viki.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by Viki on 2017/9/6.
 * Function: TODO
 */
public class ArrayBlockingQueueTest {



    /*
    * 1、ArrayBlockingQueue基于数组形式存储，由于数组无法动态扩容，需要在创建对象时即设定内置数组的大小。该大小需要由使用者根据需求自行指定
    * */
    @Test
    public void defaultCapacity(){
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(10);
    }

    /*
    * 2、它默认采用非公平锁 TODO 有什么区别?
    * */
    @Test
    public void isFairLoc(){
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(10);
        ArrayBlockingQueue<String> arrayBlockingQueue2 = new ArrayBlockingQueue(10, false);
    }

    /*
    * 3、
    *   add: 要么输出true,要么返回异常
    *   offer: 输入true或者false false表示添加失败
    *   put: 阻塞操作，在满了时继续添加将会被阻塞
    * */
    @Test
    public void add_offer_put(){
        int i = 0;
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(3);
        try{
            arrayBlockingQueue.clear();
            i = 0;
            while(i++ < 4){
                System.out.println(arrayBlockingQueue.add("a"));
            }
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("add end");
        }

        try{
            arrayBlockingQueue.clear();
            i = 0;
            while(i++ < 4){
                System.out.println(arrayBlockingQueue.offer("a"));
            }
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("offer end");
        }

        try{
            arrayBlockingQueue.clear();
            i = 0;
            while(i++ < 4){
                arrayBlockingQueue.put("a");
                System.out.println(" put a");
            }
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("put end");
        }
    }

    /*
    *   public boolean add(E e) {
    *    if (offer(e))
    *        return true;
    *    else
    *        throw new IllegalStateException("Queue full");
    *    }
    *   1、add只是对offer做了一个简单包装，如果不是true，则抛出一个unchecked异常
    *
    *   2、ArrayBlockingQueue 内部使用三个整数分别记录下一个插入的位置，下一个取值的位置，当前共有的item的数量
    *
    *   3、每个插入操作在执行前都会判断count是否等于数组的总capacity，如果相等，则认为队列已满，不会执行插入操作
    *
    *   4、arrayBlockingQueue并没有使用读写锁分离的方式，原因在于在读或者写时，都会影响count的计数，会导致count计数错误。因此它与其他采用读写锁分离的集合对象相比，性能可能会低 {@link ArrayBlockingQueueTest.#performanceDiffer}
    *
    *   5、
    * */
    public void add_offer(){
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(3);

    }

    /*
    * 单线程下，arrayBlockingQueue 性能比linkedBlockingQueue 高近三倍
    * */
    @Test
    public void performanceDifferWithSingleThread(){
        int size = 10000000;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,60, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(50));
        final ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(size);
        final LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(size);
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                long nanos = System.nanoTime();
                while (arrayBlockingQueue.offer("a")) {
                }
                System.out.println("arrayBlockingQueue\t"+(System.nanoTime() - nanos));
            }
        });

        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                long nanos = System.nanoTime();
                while (linkedBlockingQueue.offer("a")) {
                }
                System.out.println("linkedBlockingQueue\t"+(System.nanoTime() - nanos));
            }
        });
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void performanceDifferWithMultiThread(){
        int size = 10000000;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,60, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(50));
        final ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(size);
        final LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(size);
        int blockingQueueThreadSize = 10, linkedBlockingQueueThreadSize = blockingQueueThreadSize;
        while(blockingQueueThreadSize-- >0){
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    long nanos = System.nanoTime();
                    while (arrayBlockingQueue.offer("a")) {
                    }
                    System.out.println("arrayBlockingQueue\t"+(System.nanoTime() - nanos));
                }
            });
        }

        while(linkedBlockingQueueThreadSize-- >0){
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    long nanos = System.nanoTime();
                    while (linkedBlockingQueue.offer("a")) {
                    }
                    System.out.println("linkedBlockingQueue\t"+(System.nanoTime() - nanos));
                }
            });
        }
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
