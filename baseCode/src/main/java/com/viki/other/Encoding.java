package com.viki.other;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by Viki on 2017/5/17.
 * Function: TODO
 */
public class Encoding {

    @Test
    public void t1(){
        System.out.println(Arrays.toString(new String("淘".getBytes(), Charset.forName("utf-8")).getBytes()));
        System.out.println(new String(new String(new String("淘".getBytes(), Charset.forName("utf-8")).getBytes(), Charset.forName("gbk")).getBytes(), Charset.forName("iso-8859-1")));


    }
}
