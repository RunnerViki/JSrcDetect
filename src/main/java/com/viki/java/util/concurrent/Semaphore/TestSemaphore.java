package com.viki.java.util.concurrent.Semaphore;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

	/*
	 * ע��ϸ�ڣ�
	 * 1. ͨ�����캯�������̵߳Ĳ�������N����ͬ�ڴ�����N��������������semaphore������
	 * 2. ͨ������semaphore.acquire����������Ķ���
	 * 		���û�л�ȡ����������������
	 * 		�����ȡ��������������ִ�У�
	 * 3. �����finally��������ͷ����Ķ���: semaphore.release
	 * 4. Ĭ�ϲ��÷ǹ�ƽ����NonfairSync����ΪЧ�ʸ���
	 * 5. ����ʹ��new Semaphore(count, true)ʹ�ù�ƽ��FairSync
	 * 
	 * 6. acquireUninterruptibly��acquire��������������
	 * 		acquire�����жϸ��̵߳�״̬��������߳��Ѿ���interrupt״̬�����׳�һ���쳣��
	 * 		acquireUninterruptibly�����ǵ�ǰ�̵߳�״̬
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
		System.out.println(Thread.currentThread().getName()+"�������ź���");
		try{
			//semaphore.acquire();
			Integer value = (int)(Math.random()*10);
			if(value < 2){
				System.out.println(Thread.currentThread().getName()+"���ж�");
				Thread.currentThread().interrupt();
			}
			semaphore.acquireUninterruptibly();
			System.out.println(Thread.currentThread().getName()+"�ɹ���ȡ��"+Thread.interrupted());
			Thread.sleep(3000);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			semaphore.release();
		}
	}
	
	
}