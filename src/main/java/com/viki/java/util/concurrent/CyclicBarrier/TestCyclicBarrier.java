package com.viki.java.util.concurrent.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*
 * CountDownLatch��CyclicBarrier������
 * 
 * CountDownLatch �Ǽ�����, �߳����һ���ͼ�һ��, ���� ����һ��, ֻ�����ǵݼ���.
��CyclicBarrier����һ��ˮբ, �߳�ִ�о���ˮ��, ��ˮբ�������ס, �ȵ�ˮ��(�̵߳���)��, �ſ�ʼй��.*/


/**
 * 1. ����һ������CyclicBarrier(10)������10ָ��Ҫһ��ȴ����̵߳ĸ�����
 * 
 * 2. ��������̣߳�����CyclicBarrier�������̶߳����У���������̣߳�
 * 
 * 3. 	a)���߳��ڲ�����CyclicBarrier�����await()������
 * 			�Ա������Ƶصȴ����һ���߳�(���һ������)����await()����ʱ�������������е������̣߳�
 * 		b)���߳��ڲ�����CyclicBarrier�����await(10, TimeUnit.SECONDS)������
 * 			�Ա�ȴ����һ���߳�(���һ������)����await(10, TimeUnit.SECONDS)ʱ�������������е������̣߳�
 * 			�������ָ����ʱ�����(������10��)û�б����ѣ���ô���߳̽����׳�һ��TimeoutException��ȡ����������ҵ�������
 * 
 * 4. ע�⣺��CyclicBarrier�ǿ��Ա�ѭ�����õģ����������17���̣߳����й���һ��CyclicBarrier(10)������ô
 * 		���7���߳̽�һֱ���������޷�ִ�У�
 * */

public class TestCyclicBarrier {

	private CyclicBarrier cb;
	/*Thread-1���ڵȴ�
	Thread-0���ڵȴ�
	Thread-19���ڵȴ�
	Thread-14���ڵȴ�
	Thread-18���ڵȴ�
	Thread-15���ڵȴ�
	Thread-10���ڵȴ�
	Thread-11���ڵȴ�
	Thread-6���ڵȴ�
	Thread-2���ڵȴ�
	Thread-7���ڵȴ�
	Thread-7����ִ��------------
	Thread-3���ڵȴ�
	Thread-0����ִ��------------
	Thread-19����ִ��------------
	Thread-14����ִ��------------
	Thread-18����ִ��------------
	Thread-10����ִ��------------
	Thread-15����ִ��------------
	Thread-6����ִ��------------
	Thread-11����ִ��------------
	Thread-2����ִ��------------
	Thread-4���ڵȴ�
	Thread-5���ڵȴ�
	Thread-8���ڵȴ�
	Thread-9���ڵȴ�
	Thread-12���ڵȴ�
	Thread-13���ڵȴ�
	Thread-16���ڵȴ�
	Thread-17���ڵȴ�
	Thread-17����ִ��------------
	Thread-3����ִ��------------
	Thread-1����ִ��------------
	Thread-4����ִ��------------
	Thread-5����ִ��------------
	Thread-8����ִ��------------
	Thread-12����ִ��------------
	Thread-16����ִ��------------
	Thread-9����ִ��------------
	Thread-13����ִ��------------
	�Ӳ��Խ������˵���ڴ���һ��CyclicBarrier(10)�Ķ���ʱ��
	������߳�������Ӹö����await��������ô���ᵼ��ÿ10���̻߳�һ��ִ��await()֮��Ĳ����������δ��10�����򽫱�������
	��ˣ����������21���̣߳�����ִ����ǰ��20���߳�֮�󣬵�21���߳̽���һֱ�����������ܱ�ִ�С�
	��ʱ���Բ������ʱ�޵�await(**,**)�����趨��ȴ�ʱ��,�������ȴ�ʱ����û�б�ִ�У����׳�TimeoutException����ȡ��ִ������Ĳ�������
	*/
	public static void main(String[] args){
		TestCyclicBarrier tcb = new TestCyclicBarrier();
		tcb.test();
	}
	
	public void test(){
		this.cb = new CyclicBarrier(10);
		for(int x = 1; x< 22 ; x++){
			new Thread(new TestThread(cb)).start();
		}
	}
}

class TestThread implements Runnable{
	private CyclicBarrier cb;

	public TestThread(CyclicBarrier cb){
		this.cb = cb;
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName()+"���ڵȴ�");
		try {
			//cb.await();�����߳���û�дﵽԤ�������򽫱�������ֱ������ʱ�ű�����
			cb.await(10, TimeUnit.SECONDS);//�����߳���û�дﵽԤ�������򽫱������������ȴ�10��
			System.out.println(Thread.currentThread().getName()+"����ִ��ô");
		} catch (InterruptedException  e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"����ִ��------------");
	}
	
	
}
