package com.viki.java.utils.JavaPImplements;

/**
 * Created by Viki on 2017/5/16.
 * Function: TODO
 */
public class Test {
    public String s1 = "Hello";

    Test t1 = new Test();

    public static void main(String[] args) {
        String s2 = "world";
        System.out.println(new StringBuilder(new Test().s1).append(" ").append(s2));
    }
}
