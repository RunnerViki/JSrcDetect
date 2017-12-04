package com.viki.java.lang.Object;

import org.junit.Test;

/**
 * Created by Viki on 2017/5/16.
 * Function:
 *  1. {@link Object#clone()}方法是protected方法
 *  2. 调用clone()会检查是否实现的cloneable接口，如果没有，则抛异常CloneNotSupportedException
 *  3. clone()只是新复制了对象本身，但对象含有的属性，都是引用复制
 */
public class ObjectCloneTest {

    static class CloneableClass implements Cloneable{
        public Object obj1 = new Object();

        public void cloneTest(){
            CloneableClass unCloneableClass = new CloneableClass();
            try {
                CloneableClass unCloneableClass2 = (CloneableClass) unCloneableClass.clone();
                System.out.println(unCloneableClass2 == unCloneableClass);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            new CloneableClass().cloneTest();
        }
    }


    static class UnCloneableClass extends Object{
        public Object obj1 = new Object();

        public void cloneTest(){
            UnCloneableClass unCloneableClass = new UnCloneableClass();
            try {
                UnCloneableClass unCloneableClass2 = (UnCloneableClass) unCloneableClass.clone();
                System.out.println(unCloneableClass2 == unCloneableClass);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            new UnCloneableClass().cloneTest();
        }
    }


    private String x = new String("a");

    private Object object = new Object();

    private CloneableClass objectTest = new CloneableClass();

    /**
     * 2、 两个都输出为false,
     * 第一个说明t1和t2根本是两个不相同的对象，说明clone()方法是一个属于深度复制的方法，但调用此方法必须保证该类实现了cloneable接口
     * 第二个说明hashCode的值不仅与引用指向的对象的值相关，也与引用指向的对象的地址相关
     * 后面三个说明，对象的属性只是复制了引用，并不是产生了一个新的对象
     */
    @Test
    public void cloneTest(){
        try {
            ObjectCloneTest t1 = new ObjectCloneTest();
            ObjectCloneTest t2 = (ObjectCloneTest)t1.clone();
            System.out.println(t1 == t2);
            System.out.println(t1.equals(t2));
            System.out.println(t1.hashCode() == t2.hashCode());
            System.out.println(t1.x == t2.x);
            System.out.println(t1.object == t2.object);
            System.out.println(t1.objectTest == t2.objectTest);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


}
