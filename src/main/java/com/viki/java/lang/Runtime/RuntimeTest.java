package sourceCode.java.lang.Runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RuntimeTest {

	/**
	 * 1��Runtime����;��
	 * 		a) ʹ��freeMemory��maxMemory��totalMemory�ȷ�����õ�ǰJVMռ���ڴ�����
	 * 		b) ִ��GC,runFinalizationִ�л����ڴ����
	 * 		c) ͨ��exec����ִ���ⲿ����
	 * 		d) ΪJVM������shutdownʱִ��ָ���Ķ���
	 * **/
	
	Runtime runtime = Runtime.getRuntime();
	
	/**
	 * ��JVM��ʼ����ʱ������runtime������
	 */
	public void runtimeSetting(){
		runtime.addShutdownHook(new Thread(new Runnable(){
			public void run(){
				System.out.println("ϵͳ������������");
			}
		}));
		runtime.traceMethodCalls(true);
		runtime.traceInstructions(true);
	}
	
	/**
	 * ����ڴ�ʹ�����
	 */
	public void memoryMonitor(){
		System.out.println(String.format("��ǰ�����ڴ�:%dM", runtime.freeMemory() >> 20));
		System.out.println(String.format("��ǰ���ڴ�:%dM", runtime.maxMemory() >> 20));
		System.out.println(String.format("��ǰ���ɷ����ڴ�:%dM", runtime.totalMemory() >> 20));
	}
	
	/**
	 * ִ���ⲿ������������������
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
