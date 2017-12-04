package com.viki.sun.misc;

import org.junit.Test;
import sun.misc.Unsafe;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/3 17:29
 */
public class UnsafeTest {



    /*
    * 看源码得知，该类只允许根加载器加载
    * */
    @Test
    public void getInstance(){
        Unsafe unsafe = Unsafe.getUnsafe();
        System.out.println(unsafe.addressSize());
    }

    @Test
    public void getInstance2(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader.getParent().getParent().getParent().toString());
    }
}
