package com.viki.org.apache.curator.framework.recipies.atomic;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/10 9:20
 */
public class DistributedAtomicIntegerTest {

    static String distatomicint_path = "/curator_recipes_distatomicint_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("localhost:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

    public static void main(String[] args) throws Exception {
        client.start();
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, distatomicint_path,
                new RetryNTimes(3, 1000));
        AtomicValue<Integer> rc = atomicInteger.add(8);
        System.out.println("Result: " + rc.succeeded() + "\t" + rc.postValue());
    }
}
