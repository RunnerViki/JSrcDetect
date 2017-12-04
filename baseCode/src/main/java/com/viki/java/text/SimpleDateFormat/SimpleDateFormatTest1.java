package com.viki.java.text.SimpleDateFormat;

import java.text.ParseException;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/6 11:08
 */
public class SimpleDateFormatTest1 {
    public static class TestSimpleDateFormatThreadSafe extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    this.join(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    System.out.println(this.getName()+":"+DateUtil.parse("2013-05-24 06:02:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        for(int i = 0; i < 3; i++){
            new TestSimpleDateFormatThreadSafe().start();
        }

    }
}
