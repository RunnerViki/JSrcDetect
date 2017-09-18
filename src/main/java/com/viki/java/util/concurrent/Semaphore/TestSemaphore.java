package com.viki.java.util.concurrent.Semaphore;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

	/*
	 * 注意细节：
	 * 1. 通过构造函数设置线程的并发数量N，相同于创建了N个锁，并放在了semaphore对象里
	 * 2. 通过调用semaphore.acquire完成申请锁的动作
	 * 		如果没有获取到锁，将被阻塞；
	 * 		如果获取到了锁，将继续执行；
	 * 3. 务必在finally块中完成释放锁的动作: semaphore.release
	 * 4. 默认采用非公平锁，NonfairSync，因为效率更高
	 * 5. 可以使用new Semaphore(count, true)使用公平锁FairSync
	 * 
	 * 6. acquireUninterruptibly与acquire两个方法的区别：
	 * 		acquire会先判断该线程的状态，如果该线程已经是interrupt状态，则抛出一个异常；
	 * 		acquireUninterruptibly不考虑当前线程的状态
	 * */
	private Semaphore semaphore = new Semaphore(5);
	
	
	public static void main(String[] args){
		TestSemaphore ts = new TestSemaphore();
		for(int i = 0; i< 100; i++){
			new Thread(new TestThread(ts.semaphore)).start();
		}
	}
}
class TestThread implements Runnable{

	private Semaphore semaphore;
	
	public TestThread(Semaphore semaphore){
		this.semaphore = semaphore;
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName()+"正申请信号量");
		try{
			//semaphore.acquire();
			Integer value = (int)(Math.random()*10);
			if(value < 2){
				System.out.println(Thread.currentThread().getName()+"被中断");
				Thread.currentThread().interrupt();
			}
			semaphore.acquireUninterruptibly();
			System.out.println(Thread.currentThread().getName()+"成功获取锁"+Thread.interrupted());
			Thread.sleep(3000);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			semaphore.release();
		}
	}
	
	
}