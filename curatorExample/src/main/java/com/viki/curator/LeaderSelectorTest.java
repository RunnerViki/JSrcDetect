package com.viki.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/9 14:47
 */
public class LeaderSelectorTest {

    public static void main(String[] args) throws Exception {
        //创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        final CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        client.start();

        String leaderPath = "/my/path2";
        client.delete().forPath(leaderPath);
        client.create().creatingParentsIfNeeded().forPath(leaderPath);
        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter()
        {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception
            {
                System.out.println("I'm leader");
            }
        };

        LeaderSelector selector = new LeaderSelector(client, leaderPath, listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();
        try {
            System.out.println("isLeader:\t"+selector.getLeader().getId().equalsIgnoreCase(selector.getId()));
            System.out.println("selector.getId():\t"+selector.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        TimeUnit.SECONDS.sleep(4);
    }
}
