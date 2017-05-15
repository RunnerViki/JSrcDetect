package sourceCode.java.lang.Class;

public class ClassTest {

	public static void main(String[] args){
		ClassTest ct = new ClassTest();
	}

	/**
	 * TODO	还没有弄懂
	 * **/
	public void isSyntheticTest(){
		try {
			System.out.println(SyntheticClassTest.class.isSynthetic());
			System.out.println(SyntheticClassTest.MyInner.class.isSynthetic());
			System.out.println(Class.forName("sourceCode.java.lang.Class.SyntheticClassTest$MyInner").isSynthetic());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}



}
