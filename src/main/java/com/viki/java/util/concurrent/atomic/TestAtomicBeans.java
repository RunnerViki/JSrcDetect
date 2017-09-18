package com.viki.java.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ���Խ����ѭ��һ�ٴΣ�ԭ�����͵�ֵ����100����ԭ�����͵�ֵֻ������57(������С��100��ֵ)
 * 1. ԭ�����͵������������ܹ���֤���ԭ���Բ���������֤�����߳��ڲ����������ʱ���Ǵ��еģ�
 * 2. �������͵������������������������ķ�ԭ���ԣ��޷���֤��������ԭ���ԣ�
 * 
 * ԭ�����͵���������ʵ�ַ�ʽ��
 * 		����incrementAndGet()����
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
		
		System.out.println("���ս����"+ai.get());
	}
	
	private void testPrimary(){
		for(int i = 0; i< 100 ; i++){
			new Thread(new TestPrimaryBean(i)).start();
		}
		System.out.println("���ս����"+i);
	}
	

}
