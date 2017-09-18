package com.viki.java.util.concurrent.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*
 * CountDownLatch与CyclicBarrier的区别：
 * 
 * CountDownLatch 是计数器, 线程完成一个就记一个, 就像 报数一样, 只不过是递减的.
而CyclicBarrier更像一个水闸, 线程执行就想水流, 在水闸处都会堵住, 等到水满(线程到齐)了, 才开始泄流.*/


/**
 * 1. 创建一个对象CyclicBarrier(10)，其中10指需要一起等待的线程的个数；
 * 
 * 2. 创建多个线程，并把CyclicBarrier对象传入线程对象中，启动多个线程；
 * 
 * 3. 	a)在线程内部调用CyclicBarrier对象的await()方法，
 * 			以便无限制地等待最后一个线程(最后一根稻草)调用await()方法时，唤醒这批次中的所有线程；
 * 		b)在线程内部调用CyclicBarrier对象的await(10, TimeUnit.SECONDS)方法，
 * 			以便等待最后一个线程(最后一根稻草)调用await(10, TimeUnit.SECONDS)时，唤醒这批次中的所有线程，
 * 			但如果在指定的时间段内(这里是10秒)没有被唤醒，那么该线程将被抛出一个TimeoutException并取消接下来的业务操作；
 * 
 * 4. 注意：该CyclicBarrier是可以被循环利用的，所以如果有17个线程，且有共享一个CyclicBarrier(10)对象，那么
 * 		最后7个线程将一直被阻塞，无法执行；
 * */

public class TestCyclicBarrier {

	private CyclicBarrier cb;
	/*Thread-1正在等待
	Thread-0正在等待
	Thread-19正在等待
	Thread-14正在等待
	Thread-18正在等待
	Thread-15正在等待
	Thread-10正在等待
	Thread-11正在等待
	Thread-6正在等待
	Thread-2正在等待
	Thread-7正在等待
	Thread-7正在执行------------
	Thread-3正在等待
	Thread-0正在执行------------
	Thread-19正在执行------------
	Thread-14正在执行------------
	Thread-18正在执行------------
	Thread-10正在执行------------
	Thread-15正在执行------------
	Thread-6正在执行------------
	Thread-11正在执行------------
	Thread-2正在执行------------
	Thread-4正在等待
	Thread-5正在等待
	Thread-8正在等待
	Thread-9正在等待
	Thread-12正在等待
	Thread-13正在等待
	Thread-16正在等待
	Thread-17正在等待
	Thread-17正在执行------------
	Thread-3正在执行------------
	Thread-1正在执行------------
	Thread-4正在执行------------
	Thread-5正在执行------------
	Thread-8正在执行------------
	Thread-12正在执行------------
	Thread-16正在执行------------
	Thread-9正在执行------------
	Thread-13正在执行------------
	从测试结果看，说明在创建一个CyclicBarrier(10)的对象时，
	如果在线程类中添加该对象的await方法，那么将会导致每10个线程会一起执行await()之后的操作，如果还未满10个，则将被阻塞。
	因此，如果这里有21个线程，则在执行完前面20个线程之后，第21个线程将会一直被阻塞，不能被执行。
	此时可以采用添加时限的await(**,**)方法设定最长等待时间,如果在最长等待时间内没有被执行，则将抛出TimeoutException并且取消执行下面的操作步骤
	*/
	public static void main(String[] args){
		TestCyclicBarrier tcb = new TestCyclicBarrier();
		tcb.test();
	}
	
	public void test(){
		this.cb = new CyclicBarrier(10);
		for(int x = 1; x< 22 ; x++){
			new Thread(new TestThread(cb)).start();
		}
	}
}

class TestThread implements Runnable{
	private CyclicBarrier cb;

	public TestThread(CyclicBarrier cb){
		this.cb = cb;
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName()+"正在等待");
		try {
			//cb.await();阻塞线程数没有达到预定个数则将被阻塞，直到满足时才被唤醒
			cb.await(10, TimeUnit.SECONDS);//阻塞线程数没有达到预定个数则将被阻塞，但最多等待10秒
			System.out.println(Thread.currentThread().getName()+"还会执行么");
		} catch (InterruptedException  e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"正在执行------------");
	}
	
	
}
