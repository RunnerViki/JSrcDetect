package sourceCode.java.lang.String;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

	
	/**
	 * 1����public final class String��˵��String��һ��final�࣬���ܱ��̳�
	 * **/
	
	/**
	 * 2��String��ʵ���������ӿڣ�java.io.Serializable, Comparable<String>, CharSequence������ʵ�����л����ɱȽϹ��ܣ�ͬʱ��Ϊһ���ַ����ж���
	 * **/
	
	/*   
	 * 3����ʼ��ʱ��Ĭ�Ϸ���һ���յ�char���� 
	 * public String() {
        this.value = new char[0];
    }*/
	
	/**
	 * 4��
	 * ��ʼ��һ��string��������ַ���������
	 * 	String strA = "abc";  
	 * 	String strB = "abc";  
	 * 	System.out.println(strA == strB);  
	 * 	//true	˵����������ָ�����ͬһ������
	 * 
	 * 	String strAA = new String(strA);  
	 * 	System.out.println(strA == strAA);  
	 * 	//false	˵����ȻstrAA������strA��Ϊ��ʼ��������ʼ��һ��ͬ�����ݵ�String����
	 * 			��strAA��û��ֱ���������String,���Ǹ���������������ݣ�������һ���ڴ�ռ��
	 * 
	 * 	System.out.println(strAA == strBB);  
	 * 	//false	˵����������ֱ��ʹ���������з���һ���µĵ�ַ�Դ����¶���ķ�ʽʵ��
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
	 * 5��
	 * String oldStr = "abcdefghijklmnopqrstuvwxyz";
	 * String newStr = oldStr.substring(oldStr,0,1);
	 * String anotherStr = new String(oldStr.substring(oldStr,0,1));
	 * oldStr = null;
	 * 
	 * ��oldStr����Ϊnull֮��oldStr��������¿��ܻᱻ���գ�������newStr�ڱ���ֵʱ������oldStr�����ݣ����Ե��²��ᱻ���ա�
	 * �����ʹ��anotherStr�ķ�ʽ������oldStr����ΪNull���п��ܻᱻ���յ���
	 * ��Ϊnew String()�ķ�ʽ�Ḵ��һ��ԭ�е�string�������ݣ����ڶ��д�����
	 * ��������ʹ��new String()����string�����Ψһ�ŵ�
	 * **/
	
	
	/**
	 * 6���������캯���ĶԱȣ�
	 *     public String(StringBuffer buffer) {
		        synchronized(buffer) {
		            this.value = Arrays.copyOf(buffer.getValue(), buffer.length());
		        }
		   }
		   �ڸ÷�����ʹ��synchronized����֤��buffer����������������ͬһ��������ա���������������ʱ���������
		   ��ǰ�̻߳��buffer.getValue��(�磺abcd)����һ���߳��޸���buffer��value��������Length��ֵ�����˱仯(�磺xy)��
		   ��ʱ��ǰ�߳����õ���buffer.length����ȡ����һ�������lengthֵ����ʱ������һ�������String����(ab).
		    
		   public String(StringBuilder builder) {
		   		this.value = Arrays.copyOf(builder.getValue(), builder.length());
		   }
		   ����StringBuilder������һ���̰߳�ȫ�Ķ���(��StringBuilder�Ĳ�����û�����������)���������Ｔʹ����������Ҳ�޼����¡�
		   ��Ȼʹ����StringBuilder���ͱ�����֪�ڵ�ǰ�����£���������̰߳�ȫ���⡣
	 * **/
	
	/**
	 * 7���ж�����string����������Ƿ����ʱ��ʹ��equals�������ֱȽ������ִ�Сд�ģ���Ҫʹ��equalsIgnoreCase�жϺ��Դ�Сдʱ�ıȽ�
	 * **/
	public void equalsTest(){
		System.out.println("asdfas".equals("asdfas"));				//true
		System.out.println("asdfas".equals("ASDFAS"));				//false
		System.out.println("asdfas".equalsIgnoreCase("ASDFAS"));	//true
	}
	
	public char[] ch = new char[]{'a','a','a','a','a','a',};
	
	/**
	 * 8�������ĸ��汾��
	 * replace(char oldChar, char newChar)��ֱ��ʹ��ѭ���ַ������ݵķ�ʽ�����滻
	 * replaceFirst��replace(CharSequence target, CharSequence replacement)��replaceAll���ڲ�����Matcher������ʵ���滻
	 * ����replace(CharSequence target, CharSequence replacement)���ȶ�����һ���ı����͵�
	 * pattern����Pattern.compile(target.toString(), Pattern.LITERAL)
	 * �ڰ�pattern������Ϊ�ı���ʱ��Pattern����ı��ʾ��������Ϊ��һ���ı��ͣ���ʹ�м京��һЩ������ʽ�ӣ�
	 * 
	 * TODO
	 * ΪʲôPattern.compile(".*").matcher("asdfadf").replaceAll("new")
	 * ��Pattern.compile(".*").matcher("asdfadf.*").replaceAll("new")���������ͬ?
	 * 
	 * ΪʲôPattern.compile(".*").matcher("asdfadf").replaceAll("new")���������new?
	 * **/
	public void replaceTest(){
		System.out.println("String��\n��������滻��������\n��1".replace('��', '��'));
		System.out.println("String��\n��������滻��������\n��2".replace("��", "��"));
		System.out.println("String��\n��������滻��������\n��3".replaceFirst("��\n", "��"));
		System.out.println("String��\n��������滻��������\n��4".replaceAll("��\n", "��"));
		
		System.out.println(Pattern.compile(".*", Pattern.LITERAL).matcher("asdfadf").replaceAll("new"));
		System.out.println(Pattern.compile(".*", Pattern.LITERAL).matcher("asdfadf.*").replaceAll("new"));
		System.out.println(Pattern.compile(".*").matcher("asdfadf").replaceAll("new"));
		System.out.println(Pattern.compile(".*").matcher("asdfadf.*").replaceAll("new"));
		
		/**
		 * 	xxx->asdfadf
		 *	xxx->
		 *	������۲��һ��ƥ�����������ַ��������ڶ���ȴƥ������һ���մ������Ե����������
		 * **/
		Pattern p = Pattern.compile(".*");
		Matcher m = p.matcher("asdfadf");
		while ( m.find()) {
			System.out.println("xxx->" + m.group());
		}
	}
	
	/**
	 * 9������һ���̶����ı����ָ��ʵӦ�ý���һ����������ʽ��
	 * 	Ϊʲô������һ���ǿ��ַ�������Ŀմ���ֱ��ȥ����
	 * ���ۣ�����ʹ��split���ָ�ʱ������н��ڵ������ָ����������һ���մ���������ɵ������ڸÿմ���û�������ǿմ����ַ�������ᱻ���ԡ���
	 * **/
	public void splitTest(){
		System.out.println(Arrays.toString("a||||||5|||||s".split("\\||\\d")));
		System.out.println(Arrays.toString("a||||||5|||||s||".split("\\||\\d")));
		System.out.println(Arrays.toString("a||||||5|||||".split("\\||\\d")));
	}
	
	
	/**
	 * 10��format����������ܣ�����ƴ�����ʽ��
	 * 	format�ڶ����������ն��object���󣬲�������������һ����������˿���ʵ���ַ���ƴ�ӹ��ܣ�
	 * 	��һ����ʽ���Ĳ���֧���Զ����ʽ�����ͼ���ʽ
	 * 
	 * **/
	public void formatTest(){
		System.out.println(String.format("$%,.2f", 222100.2222f));
	}
	
	/**
	 * 11��intern()�����ж��Ƿ�������ַ���������ͬ���ַ����������ַ����������У�������ڣ���ָ�������е��ַ����ĵ�ַ�����û�������ַ����������д���һ����ָ����ַ�����ַ��
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
