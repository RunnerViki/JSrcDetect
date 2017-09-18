package com.viki.java.util.concurrent.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ������ReentrantLock
//���Ի�ȡ��,������boolean��tryLock
//������unlock
//����һ����ƽ��������new ReentrantLock(true),Ϊfalseʱ����һ����ƽ��
//������**��λʱ���ڻ�ȡ�������**��λʱ����û�л�ȡ�����򷵻�False��tryLock(long timeout, TimeUnit unit)
//ע���Ƿ��������̵߳�һ��״̬��ʶ�����ReentrantLock��Ҫ��һ��Thread������ʹ��
 * 
 * 
 * ReentrantLock��Synchronized�ؼ��ֵıȽϣ�
 * 	1��synchronized�޷�ȡ�����ڵȴ�����
 * 	2��һ��ReentrantLock�����Ӧһ��������synchronizedҪô��Ӧһ��������Ҫô��Ӧһ�����������޷��Զ��巶Χ
 * 
 * @author vikiyang
 *
 */

/**
 * ʹ�ó�����
 * 		
 * */
public class TestReentrantLock {

	public static void main(String[] args){
		ReentrantLock rl = new ReentrantLock(false);
		for(int i = 0;i < 10; i++){
			Thread t = new Thread(new ThreadObj(rl));
			t.setName(Double.toString(((int)(Math.random()*10))));
			System.out.println("����һ������"+t.getName());
			t.start();
		}
	}
	
}
