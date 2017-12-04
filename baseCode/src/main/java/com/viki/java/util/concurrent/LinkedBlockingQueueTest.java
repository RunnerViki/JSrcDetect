package com.viki.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Viki on 2017/9/6.
 * Function: TODO
 */
public class LinkedBlockingQueueTest {

    @Test
    public void test1(){
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.add("a");
        linkedBlockingQueue.add("b");
        linkedBlockingQueue.add("c");
    }
}
