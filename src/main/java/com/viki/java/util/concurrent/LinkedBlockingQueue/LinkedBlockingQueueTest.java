package com.viki.java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Viki on 2017/9/4.
 */
public class LinkedBlockingQueueTest {


    @Test
    public void deQueue() throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        System.out.println(queue.take());
    }
}
