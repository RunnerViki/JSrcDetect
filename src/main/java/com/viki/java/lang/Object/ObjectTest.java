package sourceCode.java.lang.Object;

public class ObjectTest implements Cloneable{

	
	
	/**
	 * ��������Կ������� == ������Ԫ�ؼ䣬hashCode������ȡ�
	 * ���⣬����Hashcode��ͨ�������ֵ��������ģ���ˣ�������������hashCode����ȣ�������֮��һ���� ==
	 * 
	 * **/
	public void hashCodeTest(){
		try {
			ObjectTest obj = new ObjectTest();
			ObjectTest obj2 = (ObjectTest)obj.clone();
			System.out.println(Integer.toHexString(obj.hashCode()));
			System.out.println(Integer.toHexString(obj2.hashCode()));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 2�� ���������Ϊfalse,
	 * ��һ��˵��t1��t2��������������ͬ�Ķ���˵��clone()������һ��������ȸ��Ƶķ����������ô˷������뱣֤����ʵ����cloneable�ӿ�
	 * �ڶ���˵��hashCode��ֵ����������ָ��Ķ����ֵ��أ�Ҳ������ָ��Ķ���ĵ�ַ���
	 */
	public void cloneTest(){
		try {
			ObjectTest t1 = new ObjectTest();
			ObjectTest t2 = (ObjectTest)t1.clone();
			System.out.println(t1 == t2);
			System.out.println(t1.equals(t2));
			System.out.println(t1.hashCode() == t2.hashCode());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 3��JVM��Ϊÿ��OBJECT�������һ��monitor�����ö����ͬ����������ͬ���鱻����̵߳���ʱ��monitor�����������̼߳�Ըö���Ķ�ռ�ԡ�
	 * 		�ڻ�øö�������󣬵���wait�����µ����̱߳����𣬲������Ըö��������
	 * 		�ڻ�øö�������󣬵���notify��notifyAll����������û�л�ö������ı�������߳�֮һ�����ѣ�
	 * 		notifyAll��notify����������notify���������һ���̣߳�notifyAll�ỽ�����б�������̣߳��������Ǿ������������ȡ�����Ǹ��̼߳���ִ�У������̼߳����ȴ�
	 * 	�˼�Ϊ���̼߳�ͨ��.
	 * **/
	public void waitNofityTest(){
		String name = "testBean";
		for(int i = 0; i<10; i++){
			//new Thread(new ThreadBean(name)).start();
		}
		synchronized(name){
			name.notifyAll();
		}
	}
	
	
	public static void main(String[] args){
		ObjectTest objectTest = new ObjectTest();
		objectTest.waitNofityTest();
	}
}
