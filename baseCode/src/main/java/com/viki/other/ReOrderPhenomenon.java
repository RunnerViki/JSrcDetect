package com.viki.other;

/**
 * Function: 指令重排序现象
 * 运行期行为
 *
 * @author Viki
 * @date 2017/12/29 15:19
 */
public class ReOrderPhenomenon {

    private static boolean ready = false;
    private static int number = 0;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield(); //交出CPU让其它线程工作
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

}
