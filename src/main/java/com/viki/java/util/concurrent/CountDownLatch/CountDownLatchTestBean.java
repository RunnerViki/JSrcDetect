package com.viki.java.util.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTestBean implements Runnable{

	/**
	 * 手动控制多线程的执行顺序
	 */
	private CountDownLatch cdl;
	
	private StringBuffer result;
	
	public CountDownLatchTestBean(CountDownLatch cdl,StringBuffer result){
		this.cdl = cdl;
		this.result = result;
	}
	
	public void run() {
		result = result.append("["+Thread.currentThread().getName()+"]");
		cdl.countDown();
		System.out.println(Thread.currentThread().getName()+"			"+cdl.getCount());
		try {
			Thread.sleep((int)(Math.random()*10000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"完成			");
	}

}
