package com.viki.java.lang.Object;

public class ObjectTest implements Cloneable{



	/**
	 * 从输出可以看出，不 == 的两个元素间，hashCode可以相等。
	 * 另外，由于Hashcode是通过对象的值计算出来的，因此，如果两个对象的hashCode不相等，则两者之间一定不 ==
	 *
	 * **/
	public void hashCodeTest(){
		try {
			ObjectTest obj = new ObjectTest();
			ObjectTest obj2 = (ObjectTest)obj.clone();
			System.out.println(Integer.toHexString(obj.hashCode()));
			System.out.println(Integer.toHexString(obj2.hashCode()));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

	}



	/**
	 * 3、JVM会为每个OBJECT对象分配一个monitor，当该对象的同步方法或者同步块被多个线程调用时，monitor将负责处理多个线程间对该对象的独占性。
	 * 		在获得该对象的锁后，调用wait将导致调用线程被挂起，并放弃对该对象的锁；
	 * 		在获得该对象的锁后，调用notify或notifyAll将导致其他没有获得对象锁的被挂起的线程之一被唤醒；
	 * 		notifyAll与notify的区别在于notify会随机唤醒一个线程，notifyAll会唤醒所有被挂起的线程，并由它们竞争获得锁。获取锁的那个线程继续执行，其他线程继续等待
	 * 	此即为：线程间通信.
	 * **/
	public void waitNofityTest(){
		String name = "testBean";
		for(int i = 0; i<10; i++){
			//new Thread(new ThreadBean(name)).start();
		}
		synchronized(name){
			name.notifyAll();
		}
	}


	public static void main(String[] args){
		ObjectTest objectTest = new ObjectTest();
		objectTest.waitNofityTest();
	}
}
