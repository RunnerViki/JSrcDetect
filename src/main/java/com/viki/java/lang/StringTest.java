package viki.java.lang.String;

public class StringTest {


	/**
	 * 【1】
	 * 	String s = "0123456789012345678901234567890123456789";
	 String s2 = s.substring(0, 1);
	 s = null;
	 即使s置为null,但s无法被垃圾回收器回收，因为s2共享了s在常量池中创建的string常量

	 String s2 = new String(s.substring(0, 1));
	 构造函数会复制原来的string对象值为一个新的字符数组，这样原来的字符串将会被垃圾回收器回收；
	 * */
	public String Str1 = "Test1211111111";
	public String Str2 = new String("Test");





	public static void main(String[] args){
		StringTest st = new StringTest();
		st.new2();
		st.new1();
	}

	public void new1(){
		long time1 = System.currentTimeMillis();
		for(long i = 0; i< 10000000000L; i++){
			String str = new String("Test");
		}
		System.out.println(System.currentTimeMillis() - time1);
	}

	public void new2(){
		long time1 = System.currentTimeMillis();
		for(long i = 0; i< 10000000000L; i++){
			String str = "Test";
		}
		System.out.println(System.currentTimeMillis() - time1);
	}
}
