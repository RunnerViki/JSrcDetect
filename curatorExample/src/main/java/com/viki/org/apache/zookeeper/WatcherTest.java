package com.viki.org.apache.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.WatchedEvent;

import java.util.concurrent.TimeUnit;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/10 9:55
 */
public class WatcherTest {

    public static void main(String[] args) {
        String zkConnString = "127.0.0.1:2181";
        final String path = "/test/watcher3";
        CuratorFramework client = null;
        try {
            client = CuratorFrameworkFactory.newClient(zkConnString,
                    new ExponentialBackoffRetry(1000, 3));


            client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
                @Override
                public void stateChanged(CuratorFramework client, ConnectionState newState) {
                    switch (newState){
                        case CONNECTED:
                            System.out.print("connected:");
                            try {
                                DistributedAtomicInteger value = new DistributedAtomicInteger(client, path, new ExponentialBackoffRetry(1000, 3));
                                value.increment();
                                System.out.println(value.get().postValue());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println(newState);
                            break;
                    }
                }
            });

            client.start();
//            client.checkExists().creatingParentsIfNeeded().forPath(path);
            client.getData().usingWatcher(new CuratorWatcher() {
                @Override
                public void process(WatchedEvent event) throws Exception {
                    switch (event.getState()){
                        case SyncConnected:
                            System.out.println("连接创建");
                            break;
                        default:
                            System.out.println(event.getState());
                            break;
                    }
                    switch (event.getType()){
                        case NodeDataChanged:
//                            DistributedAtomicInteger value = new DistributedAtomicInteger(client, path, new ExponentialBackoffRetry(1000, 3));
                            System.out.println("数据改变为"+event.getType());
                            break;
                        default:
                            break;
                    }
                }
            }).forPath(path);
            TimeUnit.MINUTES.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseableUtils.closeQuietly(client);
        }
    }
}
