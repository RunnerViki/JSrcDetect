package sourceCode.java.lang.Class;

/**
 * SyntheticClass��һ���ϳ��࣬JVM�ڱ�����ʱ��Ҳ���������ڲ��������(�磬�ڲ��࣬�����ڲ��������)����Щ���֮Ϊ�ϳ���
 * ��ˣ���ǰ���ļ��ڱ���󣬻������¼����ļ���
 * 	SyntheticClassTest$1.class
 * 	SyntheticClassTest$MyInner.class
 * 	SyntheticClassTest.class
 * **/
public class SyntheticClassTest {


	public void SyntheticClass() {
		for (int x = 0; x < 4; x++) {
			new Thread(new Runnable() {
				public void run() {
					System.out.println("��ǰ�߳���"
							+ Thread.currentThread().getName());
				}
			}).start();
		}
	}

	private MyInner inner;

	void createInner() {
		inner = new MyInner();

		inner.doSomething();
	}

	public class MyInner {
		private MyInner() {
		}

		private void doSomething() {
		}
	}

}
