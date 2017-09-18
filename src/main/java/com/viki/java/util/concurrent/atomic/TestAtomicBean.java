package com.viki.java.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicBean implements Runnable {

	private AtomicInteger ai;
	
	public TestAtomicBean(AtomicInteger ai){
		this.ai = ai;
	}
	
	public void run() {
		System.out.println("atomicInteger:"+ai.incrementAndGet());
	}

	
	
}
