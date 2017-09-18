package com.viki.java.lang.Integer;

import org.junit.Test;

public class IntegerTest {

	/*
	* {@link Integer#valueOf()} 如果参数值在-128到127之间，是从IntegerCache返回的。
	* 但new Integer()并不是
	* */
	@Test
	public void equals(){
		Integer i1 = new Integer(127);
		Integer i2 = new Integer(127);

		Integer i3 = new Integer(227);
		Integer i4 = new Integer(227);
		System.out.println(i1 == i2);
		System.out.println(i3 == i4);

		Integer i5 = Integer.valueOf(127);
		Integer i6 = Integer.valueOf(127);
		System.out.println(i5 == i6);
	}

	@Test
	public void binOr(){
		int x = 54;
		System.out.println("原始值："+x+"二进制："+Integer.toBinaryString(x)+"	后面添加的值："+Integer.toBinaryString(x>>1));
		x |= x>>1;
		System.out.println("原始值："+x+"二进制："+Integer.toBinaryString(x)+"	后面添加的值："+Integer.toBinaryString(x>>2));
		x |= x>>2;
		System.out.println("原始值："+x+"二进制："+Integer.toBinaryString(x)+"	后面添加的值："+Integer.toBinaryString(x>>4));
		x |= x>>4;
		System.out.println("原始值："+x+"二进制："+Integer.toBinaryString(x)+"	后面添加的值："+Integer.toBinaryString(x>>8));
		x |= x>>8;
		System.out.println("原始值："+x+"二进制："+Integer.toBinaryString(x)+"	后面添加的值："+Integer.toBinaryString(x>>16));
		x |= x>>16;
		System.out.println("原始值："+x+"二进制："+Integer.toBinaryString(x));
		System.out.println((x >>> 1)+"最终结果"+Integer.toBinaryString(x >>> 1));
	}


	@Test
	public void roundUpToPowerOf2() {
		int number = 54;
		System.out.println(number >= Integer.MAX_VALUE
				? Integer.MAX_VALUE
				: (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1);
	}


}
