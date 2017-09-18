package com.viki.java.util.concurrent.Volatile;

public class TestVolatile {

private static volatile int MY_INT = 0;

public static void main(String[] args) {
    new ChangeListener().start();
    new ChangeMaker().start();
    new ChangeMaker().start();
    
    /*
     * �ӽ������volatile���������ڶ���̼߳乲��
     * Thread-1Incrementing MY_INT to 1
    Got Change for MY_INT : 1
    --------------Thread-2Incrementing MY_INT to 2
    Got Change for MY_INT : 2
    //��������߳�2���߳�1ͬʱ�õ��ı�����ֵ1��Ȼ���߳�2�ڰѱ�����1����ֵ���˱����������߳�1��ʱ��ֻ�õ��˱����������˶�ԭ����ֵ��+1�ļ��㣬���Լ�ʹ�߳�1������һ�μ�1�Ĳ�����Ҳ
     * ���õ���2���ֵ��
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
