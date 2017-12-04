package com.viki.java.lang.Runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RuntimeTest {

	/**
	 * 1、Runtime的用途：
	 * 		a) 使用freeMemory、maxMemory、totalMemory等方法获得当前JVM占用内存的情况
	 * 		b) 执行GC,runFinalization执行回收内存操作
	 * 		c) 通过exec方法执行外部命令
	 * 		d) 为JVM在正常shutdown时执行指定的动作
	 * **/

	Runtime runtime = Runtime.getRuntime();

	/**
	 * 在JVM开始运行时，设置runtime的属性
	 */
	public void runtimeSetting(){
		runtime.addShutdownHook(new Thread(new Runnable(){
			public void run(){
				System.out.println("系统终于跑完啦！");
			}
		}));
		runtime.traceMethodCalls(true);
		runtime.traceInstructions(true);
	}

	/**
	 * 监控内存使用情况
	 */
	public void memoryMonitor(){
		System.out.println(String.format("当前可用内存:%dM", runtime.freeMemory() >> 20));
		System.out.println(String.format("当前总内存:%dM", runtime.maxMemory() >> 20));
		System.out.println(String.format("当前最大可分配内存:%dM", runtime.totalMemory() >> 20));
	}

	/**
	 * 执行外部命令，并且输出返回数据
	 */
	public void executeExternalCommand(String command){
		Process process = null;
		try {
			process = runtime.exec(command);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = bufferedReader.readLine())!= null){
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				Thread.currentThread().sleep(1000);
				runtime.exec("pkill "+command);
			} catch (InterruptedException  e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args){
		RuntimeTest runtimeTest = new RuntimeTest();
		runtimeTest.runtimeSetting();
		runtimeTest.memoryMonitor();
		runtimeTest.executeExternalCommand("NOTEPAD");
	}
}
