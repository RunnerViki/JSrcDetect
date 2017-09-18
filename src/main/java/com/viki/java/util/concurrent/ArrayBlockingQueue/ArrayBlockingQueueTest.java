package com.viki.java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 内部基于一个数组来实现的队列结构
 * @author vikiyang
 *
 */
public class ArrayBlockingQueueTest {

	/**
	 * 1、共有三种方式实现往队列中添加元素
	 * 		a) add(e)往队列添加元素时，如果队列已经满了，则抛出异常;如果添加成功，则返回true
	 * 		b) offer(e)往队列添加元素时，如果队列已经满了，则返回False;成功时则返回true
	 * 		c) put(e)往队列添加元素时，如果队列已经满了，则操作被阻塞；没有返回值
	 */
	public void insertIntoQueue(){
		ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(10);
		for(int i = 0;i<20;i++){
			//boolean add = arrayBlockingQueue.add("StringObj");
			//System.out.println(add+"add:"+i);
			boolean offer = arrayBlockingQueue.offer("StringObj");
			System.out.println(offer+"offer:"+i);
			/*try {
				arrayBlockingQueue.put("StringObj");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			//System.out.println("put:"+i);
		}
	}
	
	public static void main(String[] args){
		ArrayBlockingQueueTest arrayBlockingQueueTest = new ArrayBlockingQueueTest();
		arrayBlockingQueueTest.insertIntoQueue();
	}
}
