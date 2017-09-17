package com.viki.java.lang.Thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Viki on 2017/9/7.
 * Function: TODO
 */
public class ThreadSafeTest {

    Integer x = new Integer(5);
    String stra = "Stra";
    String strb = "Strb";
    InnerClass innerClass1 = new InnerClass("aaa");
    InnerClass innerClass2 = new InnerClass("bbb");

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,60, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(50));

    public void threadSafe(){
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                threadSafeM1(innerClass2, x, strb);
            }
        });
    }

    /*
    * 1、启动线程1，然后马上暂停6秒
    * 2、在线程1结束休眠前，修改innerClass1 innerClass2 与 x的值，其中innerClass1与innerClass2只是改了对象的内容，引用指向的对象并没有改变；而x以New的方式重新指向了一个新的引用。
    *       所以这个时候线程1中形参Integer y指向的还是之前的对象。strb由于是final类，它也重新指向了一个新的对象。
    * 3、线程1输出，innerClass1 innerClass2输出的还是原来的对象，但内容有改变，y输出的是主线程改变之前的引用对象
    * 4、线程2输出皆为变换之后的值
    * */
    private void threadSafeM1(InnerClass x, Integer y, String strb){
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("成员属性访问innerClass1\t"+innerClass1.name);
        System.out.println("成员属性访问String\t"+stra);
        System.out.println("通过方法参数访问Integer\t"+y);
        System.out.println("通过方法参数访问String\t"+strb);
        System.out.println("通过方法参数访问innerClass1\t"+x.name);
        System.out.println("===========");
    }

    public static void main(String[] args) {
        ThreadSafeTest threadSafeTest = new ThreadSafeTest();
        threadSafeTest.threadSafe();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadSafeTest.x = new Integer(6);
        threadSafeTest.stra = "newStra";
        threadSafeTest.strb = "newStrb";
        threadSafeTest.innerClass1.name = "ccc";
        threadSafeTest.innerClass2.name = "ccc";
        threadSafeTest.threadSafe();
        threadSafeTest.threadPoolExecutor.shutdown();       // TODO 5、如果没有这一行，程序不得结束，为什么？为什么不会在main方法执行完之后自行销毁？
    }

    class InnerClass{
        public InnerClass(String name){
            this.name = name;
        }
        public String name = "aaa";
    }
}
