package com.viki.java.util.concurrent.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadObj implements Runnable{

	ReentrantLock rl;
	
	public ThreadObj(ReentrantLock rl){
		this.rl = rl;
	}
	
	public void run() {
		tryToLock();
	}
	

	
	//没有添加trycatch的tryLock
	private void notrycatchLock(){
		if(rl.tryLock()){
			Thread.currentThread().setName(Double.toString(((int)(Math.random()*10))));
			System.out.println(Thread.currentThread().getName());
			if(Thread.currentThread().getName().equals("800")){
				System.out.println("遇到线程8，就是不放锁");
			}else{
				rl.unlock();
			}
			rl.unlock();
			System.out.println(rl.isLocked());
		}
	}
	
	//unLock一定要放在finally里面
	private void trycatchLock(){
		try{
			if(rl.tryLock()){
				Thread.currentThread().setName(Double.toString(((int)(Math.random()*10))));
				System.out.println(Thread.currentThread().getName());
				if(Thread.currentThread().getName().equals("800")){
					System.out.println("遇到线程8，就是不放锁");
				}else{
					rl.unlock();
				}
				System.out.println(rl.isLocked());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			rl.unlock();
		}
	}
	
	//没有获取到锁时，也要输出日志
	private void tryToLock(){
		try{
			System.out.println(Thread.currentThread().getName());
			if(rl.tryLock()){
				System.out.println("----"+rl.getQueueLength());
			}else{
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//if(rl.isLocked()){
				rl.unlock();
			//}
		}
		
	}
	
}
