package sourceCode.java.lang.ClassLoader;

public class ClassLoaderSingletonBean {

	private static ClassLoaderSingletonBean classLoaderSingletonBean;
	
	private ClassLoaderSingletonBean(){}
	
	/**
	 * ��DoubleCheck+�����ķ�ʽ���һ����������
	 * @return
	 */
	public static ClassLoaderSingletonBean getInstance(){
		if(classLoaderSingletonBean == null){
			synchronized(ClassLoaderSingletonBean.class){
				if(classLoaderSingletonBean == null){
					classLoaderSingletonBean = new ClassLoaderSingletonBean();
				}
			}
		}
		return classLoaderSingletonBean;
	}
}
