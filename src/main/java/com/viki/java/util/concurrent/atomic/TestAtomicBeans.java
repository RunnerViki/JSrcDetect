package com.viki.java.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试结果：循环一百次，原子类型的值增长100，非原子类型的值只增长到57(或其他小于100的值)
 * 1. 原子类型的自增操作，能够保证其的原子性操作，即保证所有线程在操作这个对象时，是串行的；
 * 2. 基本类型的自增操作，由于自增操作的非原子性，无法保证其自增的原子性；
 * 
 * 原子类型的自增操作实现方式：
 * 		调用incrementAndGet()方法
 */
public class TestAtomicBeans {
	
	public AtomicInteger ai = new AtomicInteger(0);
	
	public int i = 0;
	
	public static void main(String[] args){
		TestAtomicBeans tb = new TestAtomicBeans();
		tb.testAtomic();
		tb.testPrimary();
	}
	
	private void testAtomic(){
		for(int i = 0; i< 100 ; i++){
			new Thread(new TestAtomicBean(ai)).start();
		}
		
		System.out.println("最终结果："+ai.get());
	}
	
	private void testPrimary(){
		for(int i = 0; i< 100 ; i++){
			new Thread(new TestPrimaryBean(i)).start();
		}
		System.out.println("最终结果："+i);
	}
	

}
