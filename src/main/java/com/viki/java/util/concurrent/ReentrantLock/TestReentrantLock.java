package com.viki.java.util.concurrent.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 类名：ReentrantLock
//尝试获取锁,并返回boolean：tryLock
//解锁：unlock
//创建一个公平重入锁：new ReentrantLock(true),为false时不是一个公平锁
//尝试在**单位时间内获取锁，如果**单位时间内没有获取到，则返回False：tryLock(long timeout, TimeUnit unit)
//注：是否获得锁是线程的一种状态标识，因此ReentrantLock需要在一个Thread对象中使用
 * 
 * 
 * ReentrantLock与Synchronized关键字的比较：
 * 	1、synchronized无法取消正在等待的锁
 * 	2、一个ReentrantLock对象对应一个锁，而synchronized要么对应一个类锁，要么对应一个对象锁，无法自定义范围
 * 
 * @author vikiyang
 *
 */

/**
 * 使用场景：
 * 		
 * */
public class TestReentrantLock {

	public static void main(String[] args){
		ReentrantLock rl = new ReentrantLock(false);
		for(int i = 0;i < 10; i++){
			Thread t = new Thread(new ThreadObj(rl));
			t.setName(Double.toString(((int)(Math.random()*10))));
			System.out.println("创建一个锁："+t.getName());
			t.start();
		}
	}
	
}
