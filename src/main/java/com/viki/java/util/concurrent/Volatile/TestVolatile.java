package com.viki.java.util.concurrent.Volatile;

public class TestVolatile {

private static volatile int MY_INT = 0;

public static void main(String[] args) {
    new ChangeListener().start();
    new ChangeMaker().start();
    new ChangeMaker().start();
    
    /*
     * 从结果看，volatile变量可以在多个线程间共享。
     * Thread-1Incrementing MY_INT to 1
    Got Change for MY_INT : 1
    --------------Thread-2Incrementing MY_INT to 2
    Got Change for MY_INT : 2
    //这里表明线程2和线程1同时拿到的变量的值1，然后线程2在把变量加1并赋值给了变量本身，但线程1此时还只拿到了变量或者做了对原来的值做+1的计算，所以即使线程1又做了一次加1的操作，也
     * 还得到了2这个值。
    --------------Thread-1Incrementing MY_INT to 2
    Thread-2Incrementing MY_INT to 3
    Got Change for MY_INT : 3
    Thread-1Incrementing MY_INT to 3
    Thread-2Incrementing MY_INT to 4
    Got Change for MY_INT : 4
    Thread-1Incrementing MY_INT to 4
    Thread-2Incrementing MY_INT to 5
    Got Change for MY_INT : 5*/
}

static class ChangeListener extends Thread {
    @Override
    public void run() {
        int local_value = MY_INT;
        while ( local_value < 5){
            if( local_value!= MY_INT){
            	System.out.println("Got Change for MY_INT : "+ MY_INT);
                 local_value= MY_INT;
            }
        }
    }
}

static class ChangeMaker extends Thread{
    @Override
    public void run() {

        int local_value = MY_INT;
        while (MY_INT <5){
            System.out.println(Thread.currentThread().getName()+"Incrementing MY_INT to "+(local_value+1));
            MY_INT = ++local_value;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}}
class PrimitiveWrapper{
	public int i = 0;
}
