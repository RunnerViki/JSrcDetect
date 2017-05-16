package com.viki.java.other;

/**
 * Created by Viki on 2017/5/15.
 * Function: difference between & and &&
 */
public class BitwiseOperator {

    public static void main(String[] args) {
        t2();
    }

    /*
    * & 与 &&相比，&&具有短路功能，而&并不会。实现了"逻辑非短路与"功能
    * */
    public static void t1(){
        int x = 0;
        System.out.println(true & false & (x = 2) == 2);
        System.out.println(x);

        int y = 0;
        System.out.println(true && false && (y = 2) == 2);
        System.out.println(y);
    }


    /*
    * &位运算符实现了"位与"功能
    * */
    public static void t2(){
        int x = 37;
        int y = 84;
        System.out.println("x = "+ x + "\t y = " + y + "x & y = " + (x & y));
//        System.out.println(StringUtils.leftPad(yourString, 8, '0'));
        System.out.println(String.format("%32s",Integer.toBinaryString(x)).replace(" ", "0") +"\n"+ String.format("%32s",Integer.toBinaryString(y)).replace(" ", "0"));
        System.out.println(String.format("%32s",Integer.toBinaryString(x & y)).replace(" ", "0"));
    }
}
