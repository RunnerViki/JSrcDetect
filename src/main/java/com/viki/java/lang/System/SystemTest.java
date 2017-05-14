package sourceCode.java.lang.System;

import java.util.Date;

public class SystemTest {

	/**
	 * 1��nanoTime()ֻ�����ڼ���߾��ȵ�ʱ�����������ܱ�ʾĳ��ʱ��㡣
	 * ��Ϊ��������ʱ��һ������һ���̶���ʱ����㲢�����ʱ�俪ʼ��ʱ��
	 * ��ʱ�򣬿������Ǵ�ĳһ��δ����ʱ�俪ʼ��ʱ�����Ի�õ�һ����ֵ������ʱ�����������һ��ֵ��
	 * ���Կ϶����ǣ���ͬһ�������ʵ����ε��ø÷���ʱ�����õ�ʱ���������ȫ��ͬ�ġ�
	 * 
	 * currentTimeMillis()��1970��1��1��UTCʱ��Ϊ��㣬�����Ѿ���ȥ�ĺ����������Ա�ʾĳһ��ʱ��㣻
	 * 
	 * ע��һ�����ʮ�����룻һ�����һǧ���룻
	 */
	public void printTime(){
		System.out.println(new Date() + "" + System.nanoTime());
		System.out.println(System.currentTimeMillis());
		System.out.println(new Date() + "" + System.nanoTime());
	}
	
	/**
	 * 2��System.copyArray()���Ժܷ���ظ��������һ����;
	 * �ù������ڡ�ǳ�ȸ��ơ��������޸�����һ�������ᵼ����һ��Ҳ�ܵ�Ӱ��;
	 * ��Ҫע�⸴�Ƶĳ��Ȳ�Ҫ����Դ�����Ŀ������ı߽磬���������±����;
	 * 
	 * ���������������ݸ��ƹ��ܣ�
	 * 	a) Arrays.copyOf()����Arrays.copyOfRange()
	 * 
	 *  b) object.clone()
	 */
	public void copyArrayTest(){
		String[] sourceStrArray = new String[]{"a","b","c","d","e","f","g"};
		String[] destStrArray = new String[8];
		System.arraycopy(sourceStrArray, 0, destStrArray, 0, 6);
		for(String str : destStrArray){
			System.out.println(str);
		}
		System.out.println(sourceStrArray[0] == destStrArray[0]);
	}
	
	public void cloneTest(){
		SystemTest st = new SystemTest();
		try {
			SystemTest st2 = (SystemTest) st.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		SystemTest systemTest = new SystemTest();
		systemTest.copyArrayTest();
	}
}
