package com.viki.java.util.concurrent.atomic;

public class TestPrimaryBean implements Runnable {

	private int i;
	
	public TestPrimaryBean(int i){
		this.i = i;
	}

	public void run() {
		System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+" i:"+i);
		i++;
		System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName()+" i:"+i);
	}
}
