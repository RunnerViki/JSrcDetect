package com.viki.java.util.concurrent.Exchanger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Exchanger;

import viki.java.lang.String.StringTest;

public class TestThread implements Runnable{

	private Exchanger<TestExchangerBean> exchanger;
	
	public TestExchangerBean teb;
	
	public TestThread(Exchanger<TestExchangerBean> exchanger){
		this.exchanger = exchanger;
	}
	
	public void run() {
		TestExchangerBean teb2 = new TestExchangerBean();
		teb2.setName(Thread.currentThread().getName()+"");
		setTeb(teb2);
		try {
			exchanger.exchange(teb);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public TestExchangerBean getTeb() {
		return teb;
	}

	public void setTeb(TestExchangerBean teb) {
		this.teb = teb;
	}
	
	
	public static void main(String[] args) {
		try {
			
			//ͨ��������Ƶõ�ʵ��������һ������
			StringTest obj = (StringTest) Class.forName("viki.java.lang.String.StringTest").newInstance();
			
			//������
			System.out.println(obj.Str1);
			
			
			Class c = Class.forName("viki.java.lang.String.StringTest");
			//�鿴����������еķ���
			Method[] m = c.getMethods();
			for(Method m_ : m){
				System.out.println(m_.getName()+"\t" + m_.getReturnType().getSimpleName()+"\t"+m_.getModifiers());
			}
			
			//������Щ����
			Field[] fs = c.getFields();
			for(Field m_ : fs){
				System.out.println(m_.getName()+"\t" + m_.getType().getSimpleName());
			}
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}