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
	

	
	//û�����trycatch��tryLock
	private void notrycatchLock(){
		if(rl.tryLock()){
			Thread.currentThread().setName(Double.toString(((int)(Math.random()*10))));
			System.out.println(Thread.currentThread().getName());
			if(Thread.currentThread().getName().equals("800")){
				System.out.println("�����߳�8�����ǲ�����");
			}else{
				rl.unlock();
			}
			rl.unlock();
			System.out.println(rl.isLocked());
		}
	}
	
	//unLockһ��Ҫ����finally����
	private void trycatchLock(){
		try{
			if(rl.tryLock()){
				Thread.currentThread().setName(Double.toString(((int)(Math.random()*10))));
				System.out.println(Thread.currentThread().getName());
				if(Thread.currentThread().getName().equals("800")){
					System.out.println("�����߳�8�����ǲ�����");
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
	
	//û�л�ȡ����ʱ��ҲҪ�����־
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
