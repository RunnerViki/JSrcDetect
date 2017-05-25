package com.viki.other;

/**
 * Created by Viki on 2017/5/02.
 * Function: TODO
 */
public class BinaryRepresentation {

    public static void main(String[] args) {
        因吹斯挺();
    }

    /*
    * x % (x & -x) equals 0, whatever x should be.
    * */
    public static void 因吹斯挺(){
        int x = (int)(Math.random() * 100000);
        System.out.println("x = "+x + "x & -x = " + (x & -x));
        System.out.println(String.format("%32s",Integer.toBinaryString(x)).replace(" ", "0"));
        System.out.println(String.format("%32s",Integer.toBinaryString(-x)).replace(" ", "0"));
        System.out.println(x % (x & -x));
    }

    /*
    * 在java中，数字采用补码形式存储
    * 原因:
    *   1. 计算机中只有加法器，没有减少器，采用补码可以实现用加法器计算减法(减去一个数等于加上模减去这个数的差)
    *   2. 没有歧义地表示0
    *   3. 二进制补码在数字运算时对计算机最方便不需要考虑符号位的问题，符号位参与计算
    *   4. 与原码相比，更多表示一位最小数
    * */
    public static void positiveAndNegative(){
        int x = 679;
        System.out.println(String.format("%32s",Integer.toBinaryString(x)).replace(" ", "0"));
        x = -679;
        System.out.println(String.format("%32s",Integer.toBinaryString(x)).replace(" ", "0"));
    }
}


