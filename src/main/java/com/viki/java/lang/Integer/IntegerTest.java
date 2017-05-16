package com.viki.java.lang.Integer;

public class IntegerTest {

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

	public static void main(String[] args){
		//new IntegerTest().binOr();
		System.out.println(roundUpToPowerOf2(54));
	}

	private static int roundUpToPowerOf2(int number) {
		// assert number >= 0 : "number must be non-negative";
		return number >= Integer.MAX_VALUE
				? Integer.MAX_VALUE
				: (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
	}
}
