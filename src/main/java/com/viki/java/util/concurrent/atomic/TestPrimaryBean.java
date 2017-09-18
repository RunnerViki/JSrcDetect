package com.viki.java.util.concurrent.atomic;

public class TestPrimaryBean implements Runnable {

	private int i;
	
	public TestPrimaryBean(int i){
		this.i = i;
	}

	public void run() {
		System.out.println("当前线程："+Thread.currentThread().getName()+" i:"+i);
		i++;
		System.out.println("当前线程："+Thread.currentThread().getName()+" i:"+i);
	}
}
