package com.viki.java.lang.Class;

/**
 * SyntheticClass是一个合成类，JVM在编译类时，也会编译该类内部定义的类(如，内部类，方法内部定义的类)，这些类称之为合成类
 * 因此，当前类文件在编译后，会有如下几个文件：
 * 	SyntheticClassTest$1.class
 * 	SyntheticClassTest$MyInner.class
 * 	SyntheticClassTest.class
 * **/
public class SyntheticClassTest {


	public void SyntheticClass() {
		for (int x = 0; x < 4; x++) {
			new Thread(new Runnable() {
				public void run() {
					System.out.println("当前线程是"
							+ Thread.currentThread().getName());
				}
			}).start();
		}
	}

	private MyInner inner;

	void createInner() {
		inner = new MyInner();

		inner.doSomething();
	}

	public class MyInner {
		private MyInner() {
		}

		private void doSomething() {
		}
	}

}
