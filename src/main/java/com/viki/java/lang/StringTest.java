package viki.java.lang.String;

public class StringTest {

	
	/**
	 * ��1��
	 * 	String s = "0123456789012345678901234567890123456789";
		String s2 = s.substring(0, 1);
		s = null;
		��ʹs��Ϊnull,��s�޷����������������գ���Ϊs2������s�ڳ������д�����string����
		
		String s2 = new String(s.substring(0, 1));
		���캯���Ḵ��ԭ����string����ֵΪһ���µ��ַ����飬����ԭ�����ַ������ᱻ�������������գ�
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
