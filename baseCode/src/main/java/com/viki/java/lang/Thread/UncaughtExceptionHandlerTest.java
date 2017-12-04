package com.viki.java.lang.Thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/1 17:20
 */
public class UncaughtExceptionHandlerTest {

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        System.out.println(simpleDateFormat1.format(new Date()));

        SimpleDateFormat simpleDateFormal2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        System.out.println(simpleDateFormal2.format(new Date()));

        try {
            Thread thread = new Thread(new Task());
            thread.setUncaughtExceptionHandler(new UncaughtExceptionHandlerT1());
            thread.start();
        } catch (Exception e) {
            System.out.println("==Exception: " + e.getMessage());
        }
    }
}
class Task implements Runnable {
    @Override
    public void run() {
        System.out.println(3 / 2);
        System.out.println(3 / 0);
        System.out.println(3 / 1);
    }
}

class UncaughtExceptionHandlerT1 implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("抛出异常了:"+e.getMessage());
    }
}