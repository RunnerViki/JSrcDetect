package com.viki.java.util.concurrent.Exchanger;

import java.util.concurrent.Exchanger;

/**
 * Test fail
 * TODO
 * @author vikiyang
 *
 */
public class TestExchanger {
	
	private Exchanger exchanger;
	
	public static void main(String[] args){
		TestExchanger te = new TestExchanger();
		te.exchanger = new Exchanger();
		TestThread t1 = new TestThread(te.exchanger);
		
		TestThread t2 = new TestThread(te.exchanger);
		new Thread(t1).start();
		new Thread(t2).start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t1.getTeb().getName());
		System.out.println(t2.getTeb().getName());
	}

	
}
