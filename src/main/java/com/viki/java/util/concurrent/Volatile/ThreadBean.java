package com.viki.java.util.concurrent.Volatile;

public class ThreadBean implements Runnable {

	private StringBuilder str;
	
	private int x;
	
	private PrimitiveWrapper pw;
	
	public ThreadBean(PrimitiveWrapper pw){
		this.pw = pw;
	}
	
	public ThreadBean(StringBuilder str){
		this.str = str;
	}
	
	public ThreadBean(int x){
		this.x = x;
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName()+"读之前"+pw.i);
		//str = str.append(Integer.toString((int)(Math.random()*10)));
		//x++;
		pw.i++;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"读之后"+pw.i);
	}

}
