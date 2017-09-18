package com.viki.java.util.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 需求场景：
 * 		有一个大型的任务，可以通过分治的方式，采用多线程的方式实现；
 * 		每个线程完成中间的一部分，生成一个中间结果，然后等待每个线程都完成其任务之后，再计算最终的结果；
 * 		但问题在于，怎么才能知道这一批线程中的每一个线程都完成了他的任务呢？
 * 
 * 实现方式：
 * 	1. 创建一个CountDownLatch(threadCounts)对象，其中threadCounts指在多少个线程中countDown后取消等待，相当于一个计数
 * 	2. 创建多个线程，并且把countDownLatch对象传入线程内部。
 * 	3. 在线程内部执行完业务操作后，执行countDown()方法；
 * 	4. 	a) 在创建CountDownLatch对象的线程上，执行await方法()，如果该对象的计数器不为零，则该线程会一直被阻塞。
 * 		b) 在创建CountDownLatch对象的线程上，执行await方法(1000,TimeUnit.MILLISECONDS)，如果该对象的计数器不为零，
 * 		则该线程会被阻塞,但最多只会被阻塞1000毫秒.
 * 	5. CountDownLath对象的计数变为0之后，不可重复使用。如果需要，必须重新new一个对象
 */
public class TestCountDownLatch {
	
	private Integer threadCounts = 10;
	
	/**
	 * 首先为该对象设定一个初始的非负数值
	 * 在每一个小任务完成后，执行该对象的countDown。
	 * 如果getCount大于0，则调用了该对象的await方法的线程会一直被堵塞
	 * 当getCount返回到0时，该线程被唤醒，以继续执行后面的内容
	 */
	private CountDownLatch cdl = new CountDownLatch(threadCounts);
	
	private StringBuffer result = new StringBuffer();
	
	public static void main(String[] args){
		TestCountDownLatch tcdl = new TestCountDownLatch();
		tcdl.awaitInLimitTimes();
	}
	
	@SuppressWarnings("unused")
	private void await4Ever(){
		for(int i = 0; i< threadCounts; i++){
			Thread t = new Thread(new CountDownLatchTestBean(cdl,result));
			t.setName("Thread"+Integer.toString(i));
			t.start();
		}
		try {
			//最重要的一步，用于等待所有加载了CDL对象的多个线程执行完毕
			//如果没有执行完毕，会一直等待下去
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------"+result);
	}
	
	private void awaitInLimitTimes(){
		for(int i = 0; i< threadCounts; i++){
			Thread t = new Thread(new CountDownLatchTestBean(cdl,result));
			t.setName("Thread"+Integer.toString(i));
			t.start();
		}
		try {
			//最重要的一步，用于等待所有加载了CDL对象的多个线程执行完毕
			//但这里最多等待1000毫秒，如果还没有完成，就直接跑掉啦。。
			cdl.await(1000,TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("1000毫秒内，有如下线程完成了作业："+result);
	}
}
