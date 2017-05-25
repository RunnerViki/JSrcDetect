package com.viki.java.lang.System;

import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SystemTest {

	/**
	 * 1、nanoTime()只能用于计算高精度的时间间隔，而不能表示某个时间点。
	 * 因为它并不像时钟一样，有一个固定的时间起点并按这个时间开始计时。
	 * 有时候，可能它是从某一个未来的时间开始计时，所以会得到一个负值，而有时候可能是另外一个值；
	 * 可以肯定的是，在同一个虚拟机实例多次调用该方法时，设置的时间起点是完全相同的。
	 *
	 * currentTimeMillis()以1970年1月1日UTC时间为起点，计算已经过去的毫秒数，可以表示某一个时间点；
	 *
	 * 注：一秒等于十亿纳秒；一秒等于一千毫秒；
	 */
	@Test
	public void printTime(){
		System.out.println(new Date() + "" + System.nanoTime());
		System.out.println(System.currentTimeMillis());
		System.out.println(new Date() + "" + System.nanoTime());
	}

	/**
	 * 2、System.copyArray()可以很方便地复制数组的一部分;
	 * 该功能属于【浅度复制】，所以修改其中一方，将会导致另一方也受到影响;
	 * 需要注意复制的长度不要超出源数组和目的数组的边界，避免引起下标溢出;
	 *
	 * 还有另外三种数据复制功能：
	 * 	a) Arrays.copyOf()或者Arrays.copyOfRange()
	 *
	 *  b) object.clone()
	 */
	@Test
	public void copyArrayTest(){
		Object[] sourceStrArray = new Object[]{new String("a"),new Integer(2),new File(""),new Object(),"e","f","g"};
		Object[] destStrArray = new Object[8];
		System.arraycopy(sourceStrArray, 0, destStrArray, 0, 6);
		for(Object str : destStrArray){
			System.out.println(str);
		}
		System.out.println(sourceStrArray[0] == destStrArray[0]);
	}

	@Test
	public void cloneTest(){
		SystemTest st = new SystemTest();
		try {
			SystemTest st2 = (SystemTest) st.clone();
			System.out.println(st2 == st);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	/*
	* JVM找出已经被丢弃但并没有执行finalize的对象，并且执行finalize方法
	* 如何判断已经被丢弃? 已经加入到了finalizer守护线程处理队列，即认为被丢弃。
	* 所以在执行runFinalization前，最好执行一次gc方法
	* */
	@Test
	public void runFinalization(){
		boolean isExecuteGc = false;
		Object s = new Object(){
			protected void finalize() throws Throwable {
				System.out.println(" run finalize");
			}
		};
		s = null;
		if(isExecuteGc){
			System.gc();	 // 加上了gc之后，直接打印run finalize
		}
		System.runFinalization();
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args){
		SystemTest systemTest = new SystemTest();
		systemTest.copyArrayTest();
	}
}
