package com.viki.java.lang.ref.SoftReference;

import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/21 10:32
 */
public class SoftReferenceTest {

    @Test
    public void teee() throws InterruptedException {
        //测试环境，内存充足
        Object referent = new Object();
        SoftReference<Object> softRerference = new SoftReference<Object>(referent);
//        assertSame(referent, softRerference.get());//referent '强可及'
        System.out.println(softRerference.get());
        System.gc();
        System.out.println(softRerference.get());
        referent = null;//referent '软可及'
        System.gc();
        System.out.println(softRerference.get()); //referent '软可及'|内存充足，不会被被回收
        TimeUnit.SECONDS.sleep(20);
    }
}