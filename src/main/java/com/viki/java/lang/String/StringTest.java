package sourceCode.java.lang.String;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {


	/**
	 * 1、“public final class String”说明String是一个final类，不能被继承
	 * **/

	/**
	 * 2、String类实现了三个接口：java.io.Serializable, Comparable<String>, CharSequence，可以实现序列化，可比较功能，同时做为一个字符序列对象
	 * **/

	/*
	 * 3、初始化时，默认分配一个空的char数组
	 * public String() {
        this.value = new char[0];
    }*/

	/**
	 * 4、
	 * 初始化一个string对象的两种方法及区别：
	 * 	String strA = "abc";
	 * 	String strB = "abc";
	 * 	System.out.println(strA == strB);
	 * 	//true	说明两个引用指向的是同一个对象
	 *
	 * 	String strAA = new String(strA);
	 * 	System.out.println(strA == strAA);
	 * 	//false	说明虽然strAA命名用strA做为初始化参数初始化一个同样内容的String对象，
	 * 			但strAA并没有直接引用这个String,而是复制了这个对象内容，放在另一块内存空间里。
	 *
	 * 	System.out.println(strAA == strBB);
	 * 	//false	说明两个对象都直接使用在主存中分配一个新的地址以创建新对象的方式实现
	 * **/
	public void initDifference(){
		String strA = "abc";
		String strB = "abc";
		String strAA = new String(strA);
		String strBB = new String(strB);
		System.out.println(strA == strB);  	//true
		System.out.println(strA == strAA);  //false
		System.out.println(strAA == strBB); //false
	}

	/**
	 * 5、
	 * String oldStr = "abcdefghijklmnopqrstuvwxyz";
	 * String newStr = oldStr.substring(oldStr,0,1);
	 * String anotherStr = new String(oldStr.substring(oldStr,0,1));
	 * oldStr = null;
	 *
	 * 在oldStr被置为null之后，oldStr正常情况下可能会被回收，但由于newStr在被赋值时共享了oldStr的内容，所以导致不会被回收。
	 * 但如果使用anotherStr的方式，则在oldStr被置为Null后，有可能会被回收掉。
	 * 因为new String()的方式会复制一份原有的string参数内容，并在堆中创建。
	 * 可能这是使用new String()创建string对象的唯一优点
	 * **/


	/**
	 * 6、两个构造函数的对比：
	 *     public String(StringBuffer buffer) {
	 synchronized(buffer) {
	 this.value = Arrays.copyOf(buffer.getValue(), buffer.length());
	 }
	 }
	 在该方法中使用synchronized，保证了buffer的两个方法操作是同一个对象快照。否则在如下情形时，将会出错：
	 当前线程获得buffer.getValue后(如：abcd)，另一个线程修改了buffer的value，并导致Length的值发生了变化(如：xy)。
	 这时当前线程又拿到了buffer.length，获取到了一个意外的length值，这时将产生一个意外的String对象(ab).

	 public String(StringBuilder builder) {
	 this.value = Arrays.copyOf(builder.getValue(), builder.length());
	 }
	 由于StringBuilder并不是一个线程安全的对象(对StringBuilder的操作都没有添加排他锁)，所以这里即使加上了锁，也无济于事。
	 既然使用了StringBuilder，就必须明知在当前环境下，不会产生线程安全问题。
	 * **/

	/**
	 * 7、判断两个string对象的内容是否相等时，使用equals，但这种比较是区分大小写的，需要使用equalsIgnoreCase判断忽略大小写时的比较
	 * **/
	public void equalsTest(){
		System.out.println("asdfas".equals("asdfas"));				//true
		System.out.println("asdfas".equals("ASDFAS"));				//false
		System.out.println("asdfas".equalsIgnoreCase("ASDFAS"));	//true
	}

	public char[] ch = new char[]{'a','a','a','a','a','a',};

	/**
	 * 8、共有四个版本：
	 * replace(char oldChar, char newChar)，直接使用循环字符串内容的方式进行替换
	 * replaceFirst、replace(CharSequence target, CharSequence replacement)、replaceAll在内部采用Matcher对象来实现替换
	 * 其中replace(CharSequence target, CharSequence replacement)是先定义了一个文本类型的
	 * pattern对象Pattern.compile(target.toString(), Pattern.LITERAL)
	 * 在把pattern对象定义为文本型时，Pattern对象的表达示都将被认为是一个文本型，即使中间含有一些正则表达式子；
	 *
	 * TODO
	 * 为什么Pattern.compile(".*").matcher("asdfadf").replaceAll("new")
	 * 与Pattern.compile(".*").matcher("asdfadf.*").replaceAll("new")的输出不相同?
	 *
	 * 为什么Pattern.compile(".*").matcher("asdfadf").replaceAll("new")会输出两个new?
	 * **/
	public void replaceTest(){
		System.out.println("String对\n象的四种替换方法，对\n象1".replace('对', '假'));
		System.out.println("String对\n象的四种替换方法，对\n象2".replace("对", "假"));
		System.out.println("String对\n象的四种替换方法，对\n象3".replaceFirst("对\n", "假"));
		System.out.println("String对\n象的四种替换方法，对\n象4".replaceAll("对\n", "假"));

		System.out.println(Pattern.compile(".*", Pattern.LITERAL).matcher("asdfadf").replaceAll("new"));
		System.out.println(Pattern.compile(".*", Pattern.LITERAL).matcher("asdfadf.*").replaceAll("new"));
		System.out.println(Pattern.compile(".*").matcher("asdfadf").replaceAll("new"));
		System.out.println(Pattern.compile(".*").matcher("asdfadf.*").replaceAll("new"));

		/**
		 * 	xxx->asdfadf
		 *	xxx->
		 *	从输出观察第一次匹配上了所有字符串，但第二次却匹配上了一个空串，所以导致输出两次
		 * **/
		Pattern p = Pattern.compile(".*");
		Matcher m = p.matcher("asdfadf");
		while ( m.find()) {
			System.out.println("xxx->" + m.group());
		}
	}

	/**
	 * 9、常用一个固定的文本做分割，其实应该接收一个【正则表达式】
	 * 	为什么会把最后一个非空字符串后面的空串给直接去掉？
	 * 结论：【在使用split做分割时，如果有紧邻的两个分割符，则会产生一个空串。如果生成的数组在该空串后没有其他非空串的字符串，则会被忽略。】
	 * **/
	public void splitTest(){
		System.out.println(Arrays.toString("a||||||5|||||s".split("\\||\\d")));
		System.out.println(Arrays.toString("a||||||5|||||s||".split("\\||\\d")));
		System.out.println(Arrays.toString("a||||||5|||||".split("\\||\\d")));
	}


	/**
	 * 10、format完成两个功能：对象拼接与格式化
	 * 	format第二个参数接收多个object对象，并按次序填充进第一个参数，因此可以实现字符串拼接功能；
	 * 	第一个格式化的参数支持自定义格式化类型及样式
	 *
	 * **/
	public void formatTest(){
		System.out.println(String.format("$%,.2f", 222100.2222f));
	}

	/**
	 * 11、intern()用于判断是否有与该字符串内容相同的字符串存在于字符串常量池中，如果存在，则指向常量池中的字符串的地址，如果没有则在字符串常量池中创建一个并指向该字符串地址；
	 * **/
	public void internTest(){
		String s0= "testString";
		String s1=new String("testString");
		String s2=new String("testString");
		System.out.println( s0==s1 );
		s1.intern();
		s2=s2.intern();
		System.out.println( s0==s1);
		System.out.println( s0==s1.intern() );
		System.out.println( s0==s2 );
	}


	public static void main(String[] args){
		StringTest stringTest = new StringTest();
		//stringTest.initDifference();

		//String te = null;
		//System.out.println(te.isEmpty());


		stringTest.formatTest();
	}
}
