package sourceCode.java.lang.ClassLoader;

import java.util.ArrayList;

public class ClassLoaderTest {

	/**
	 * 1��ClassLoader���ܣ�����Java�ൽJVM��
	 * 	JVMʹ��java��ķ�ʽ��
	 * 		a) JVM����java�ļ�Ϊclass�ļ�
	 * 		b) ClassLoader��ȡclass�ļ�����ת����һ��classʵ������˿���ͨ�����ø�ʵ����newInstance()��øö����һ��ʵ��
	 * **/
	
	/**
	 * AppClassLoader:
	 * 		���ڼ���classPath�µ�������,����ͨ��ClassLoader.getSystemClassLoader()��ȡ
	 * 
	 * ExtClassLoader��
	 * 		���ڼ���%JAVA_HOME%/jre/lib/extĿ¼�µ���
	 * 
	 * BootstrapClassLoader:
	 * 		���ڼ���java�������
	 * 
	 * AppClassLoader��ExtClassLoader��һ������
	 * ExtClassLoader��BootstrapClassLoader��һ�����࣬������BootstrapClassLoader��һ��native�࣬���Բ�����ʾ
	 */
	public void getLoaderTest(){
		
		//sun.misc.Launcher$AppClassLoader
		System.out.println(ClassLoader.getSystemClassLoader());
		
		//sun.misc.Launcher$AppClassLoader
		System.out.println(this.getClass().getClassLoader());
		
		//null,���������BootstrapClassLoader���أ���ʾΪnull
		System.out.println("StringObj".getClass().getClassLoader());
		
		//sun.misc.Launcher$ExtClassLoader@55264c84
		System.out.println(this.getClass().getClassLoader().getParent());
		
		//null
		System.out.println(this.getClass().getClassLoader().getParent().getParent());
	}
	
	/**
	 * TODO
	 */
	public void isReallySingleton(){
		NewClassLoader classLoaderA = new NewClassLoader();
		NewClassLoader classLoaderB = new NewClassLoader();
		try {
			ClassLoaderSingletonBean classLoaderSingletonBeanA  = 
					(ClassLoaderSingletonBean)classLoaderA.loadClass("sourceCode.java.lang.ClassLoader.ClassLoaderSingletonBean").newInstance();
			ClassLoaderSingletonBean classLoaderSingletonBeanB  = 
					(ClassLoaderSingletonBean)classLoaderB.loadClass("sourceCode.java.lang.ClassLoader.ClassLoaderSingletonBean").newInstance();
			System.out.println(classLoaderSingletonBeanA == classLoaderSingletonBeanB);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args){
		ClassLoaderTest classLoaderTest = new ClassLoaderTest();
		classLoaderTest.isReallySingleton();
	}
}
