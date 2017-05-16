package com.viki.java.lang.ClassLoader;

public class ClassLoaderSingletonBean {

	private static ClassLoaderSingletonBean classLoaderSingletonBean;

	private ClassLoaderSingletonBean(){}

	/**
	 * 以DoubleCheck+类锁的方式获得一个单例对象
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
