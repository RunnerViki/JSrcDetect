package com.viki.java.lang;

/**
 * Created by Viki on 2017/5/13.
 */
public class ThreadTest {

    /*
    * �̶߳�����Ϊnull,������ʾ����gc���������߳�Ӧ�ò������is running��������������
    * ԭ��:
    *   1. ��ǰ�������е��̱߳�������GC roots ����֮һ
    *   2. t = nullȻ��gcֻ����շ����ڶ��ϵ��̶߳����޷����������������ջ�ϵ�ջ֡
    *   3. ����: main�߳�Ҳû���κ�����ָ�����������ᱻGC ���գ�����Ϊ��1��.
    * */
    public static void liveThreadAsGcRoots() {
        // anonymous class extends Thread
        Thread t = new Thread() {
            public void run() {
                // infinite loop
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    // as long as this line printed out, you know it is alive.
                    System.out.println("thread is running...");
                }
            }
        };
        t.start(); // Line A
        t = null; // Line B
        // no more references for Thread t
        // another infinite loop
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            System.gc();
            System.out.println("Executed System.gc()");
        } // The program will run forever until you use ^C to stop it
    }
}
