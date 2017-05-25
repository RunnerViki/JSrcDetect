package com.viki.java.math.BigDecimal;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Viki on 2017/5/16.
 * Function:
 *      1. 提供大数值的精准计算
 */
public class BigDecimalTest {

    @Test
    public void numberScale(){
        System.out.println(BigDecimal.valueOf(Integer.MAX_VALUE).add(new BigDecimal("1")));
        System.out.println(BigInteger.valueOf(Integer.MAX_VALUE).add(new BigInteger("1")));
    }

    /*
    * 在做除法运算时，可能会遇到除不干净的问题，在这种情况下将会发生异常(java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.)
    * 所以建议【在所有的除法运算中，设置好精度和去舍规则】
    * */
    @Test
    public void devideScale(){
//        System.out.println(BigDecimal.valueOf(9).divide(new BigDecimal("7")));
        System.out.println(BigDecimal.valueOf(9).divide(new BigDecimal("7"), 3, BigDecimal.ROUND_HALF_UP));
    }



}
