package com.viki.java.utils.JavaPImplements;

import java.util.regex.Pattern;

/**
 * Created by Viki on 2017/5/16.
 * Function: TODO
 */
public class Test {
    public String s1 = "Hello";

    Test t1 = new Test();

    static Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{8,15}$");

    public static void main(String[] args) {
        /*String s2 = "world";
        System.out.println(new StringBuilder(new Test().s1).append(" ").append(s2));*/

        System.out.println(pattern.matcher("tesA_aki2").find());

    }
}
