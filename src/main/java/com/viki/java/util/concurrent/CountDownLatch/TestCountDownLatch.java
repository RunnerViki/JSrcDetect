package com.viki.java.util.concurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * ���󳡾���
 * 		��һ�����͵����񣬿���ͨ�����εķ�ʽ�����ö��̵߳ķ�ʽʵ�֣�
 * 		ÿ���߳�����м��һ���֣�����һ���м�����Ȼ��ȴ�ÿ���̶߳����������֮���ټ������յĽ����
 * 		���������ڣ���ô����֪����һ���߳��е�ÿһ���̶߳���������������أ�
 * 
 * ʵ�ַ�ʽ��
 * 	1. ����һ��CountDownLatch(threadCounts)��������threadCountsָ�ڶ��ٸ��߳���countDown��ȡ���ȴ����൱��һ������
 * 	2. ��������̣߳����Ұ�countDownLatch�������߳��ڲ���
 * 	3. ���߳��ڲ�ִ����ҵ�������ִ��countDown()������
 * 	4. 	a) �ڴ���CountDownLatch������߳��ϣ�ִ��await����()������ö���ļ�������Ϊ�㣬����̻߳�һֱ��������
 * 		b) �ڴ���CountDownLatch������߳��ϣ�ִ��await����(1000,TimeUnit.MILLISECONDS)������ö���ļ�������Ϊ�㣬
 * 		����̻߳ᱻ����,�����ֻ�ᱻ����1000����.
 * 	5. CountDownLath����ļ�����Ϊ0֮�󣬲����ظ�ʹ�á������Ҫ����������newһ������
 */
public class TestCountDownLatch {
	
	private Integer threadCounts = 10;
	
	/**
	 * ����Ϊ�ö����趨һ����ʼ�ķǸ���ֵ
	 * ��ÿһ��С������ɺ�ִ�иö����countDown��
	 * ���getCount����0��������˸ö����await�������̻߳�һֱ������
	 * ��getCount���ص�0ʱ�����̱߳����ѣ��Լ���ִ�к��������
	 */
	private CountDownLatch cdl = new CountDownLatch(threadCounts);
	
	private StringBuffer result = new StringBuffer();
	
	public static void main(String[] args){
		TestCountDownLatch tcdl = new TestCountDownLatch();
		tcdl.awaitInLimitTimes();
	}
	
	@SuppressWarnings("unused")
	private void await4Ever(){
		for(int i = 0; i< threadCounts; i++){
			Thread t = new Thread(new CountDownLatchTestBean(cdl,result));
			t.setName("Thread"+Integer.toString(i));
			t.start();
		}
		try {
			//����Ҫ��һ�������ڵȴ����м�����CDL����Ķ���߳�ִ�����
			//���û��ִ����ϣ���һֱ�ȴ���ȥ
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------"+result);
	}
	
	private void awaitInLimitTimes(){
		for(int i = 0; i< threadCounts; i++){
			Thread t = new Thread(new CountDownLatchTestBean(cdl,result));
			t.setName("Thread"+Integer.toString(i));
			t.start();
		}
		try {
			//����Ҫ��һ�������ڵȴ����м�����CDL����Ķ���߳�ִ�����
			//���������ȴ�1000���룬�����û����ɣ���ֱ���ܵ�������
			cdl.await(1000,TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("1000�����ڣ��������߳��������ҵ��"+result);
	}
}
