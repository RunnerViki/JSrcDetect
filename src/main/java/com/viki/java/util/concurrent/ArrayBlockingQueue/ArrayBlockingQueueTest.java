package com.viki.java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * �ڲ�����һ��������ʵ�ֵĶ��нṹ
 * @author vikiyang
 *
 */
public class ArrayBlockingQueueTest {

	/**
	 * 1���������ַ�ʽʵ�������������Ԫ��
	 * 		a) add(e)���������Ԫ��ʱ����������Ѿ����ˣ����׳��쳣;�����ӳɹ����򷵻�true
	 * 		b) offer(e)���������Ԫ��ʱ����������Ѿ����ˣ��򷵻�False;�ɹ�ʱ�򷵻�true
	 * 		c) put(e)���������Ԫ��ʱ����������Ѿ����ˣ��������������û�з���ֵ
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
