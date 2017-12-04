package com.viki.java.lang.Instrumentation;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/6 14:00
 */
public class InstrumentationTest {

    public static void premain(String agentArgs){
        System.out.println("premain");
    }

    public static void main(String[] args) {
        System.out.println("main");
    }
}
