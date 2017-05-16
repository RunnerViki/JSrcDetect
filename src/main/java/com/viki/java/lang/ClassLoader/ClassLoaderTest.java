package com.viki.java.lang.ClassLoader;

import java.util.ArrayList;

public class ClassLoaderTest {

	/**
	 * 1、ClassLoader功能：加载Java类到JVM中
	 * 	JVM使用java类的方式：
	 * 		a) JVM编译java文件为class文件
	 * 		b) ClassLoader读取class文件，并转换成一个class实例，因此可以通过调用该实例的newInstance()获得该对象的一个实例
	 * **/

	/**
	 * AppClassLoader:
	 * 		用于加载classPath下的所有类,可以通过ClassLoader.getSystemClassLoader()获取
	 *
	 * ExtClassLoader：
	 * 		用于加载%JAVA_HOME%/jre/lib/ext目录下的类
	 *
	 * BootstrapClassLoader:
	 * 		用于加载java核心类库
	 *
	 * AppClassLoader是ExtClassLoader的一个子类
	 * ExtClassLoader是BootstrapClassLoader的一个子类，但由于BootstrapClassLoader是一个native类，所以不会显示
	 */
	public void getLoaderTest(){

		//sun.misc.Launcher$AppClassLoader
		System.out.println(ClassLoader.getSystemClassLoader());

		//sun.misc.Launcher$AppClassLoader
		System.out.println(this.getClass().getClassLoader());

		//null,核心类库由BootstrapClassLoader加载，显示为null
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
