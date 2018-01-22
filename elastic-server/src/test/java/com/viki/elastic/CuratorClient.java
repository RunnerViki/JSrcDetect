package com.viki.elastic;

import com.alibaba.fastjson.JSONObject;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.EnsurePath;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2018/1/22 15:01
 */
public class CuratorClient {

    private static final String ZK_ADDRESS = "127.0.0.1:2181";
    private static final String ZK_PATH = "/zktest/data";

    private static final String ZK_PATH_CONFIG = "/zktest/config";

    private static final String ZK_PATH_SUB_FORMATTER = "/node_%d";

    private static int nodes = 4;

    public static void main(String[] args) {
        // 1.Connect to zk
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();
        try {
            new EnsurePath(ZK_PATH).ensure(client.getZookeeperClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
        CuratorClient curatorClient = new CuratorClient();
        curatorClient.listen(client);
        curatorClient.listenConfig(client);
        curatorClient.create(client);
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void listen(CuratorFramework client){
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, ZK_PATH, true);
        try {
            pathChildrenCache.start();
            pathChildrenCache.getListenable().addListener((clientx, event) -> {
                switch (event.getType()){
                /*case CHILD_ADDED:
                    break;
                case INITIALIZED:
                    System.out.println(event.getType().name() + "\t" + new String(event.getData().getData()));
                    break;*/
                    case CHILD_REMOVED:
                        if(currentPath==null && create(clientx)){
                            System.out.println("节点"+event.getData().getPath()+"被移除");
                        }
                        break;
                /*case CHILD_UPDATED:

                default:
                    System.out.println(event.getType().name() + "\t" + new String(event.getData().getData()));
                    break;*/
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listenConfig(CuratorFramework client){
        NodeCache nodeCache = new NodeCache(client, ZK_PATH_CONFIG);
        try {
            nodeCache.start(true);
            nodeCache.getListenable().addListener(() -> {
                try{
                    if(nodeCache.getCurrentData() != null){
                        if(currentPath == null){
                            create(client);
                        }else{
                            byte[] newData = freshData(nodeCache.getCurrentData());
                            Stat stat = client.checkExists().forPath(currentPath);
                            client.setData().withVersion(stat.getVersion()).forPath(currentPath, newData);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String currentPath;

    private boolean create(CuratorFramework client){
        for(int x = 0; x < nodes; x++){
            try {
                Object o = client.create().withMode(CreateMode.EPHEMERAL).forPath(ZK_PATH + String.format(ZK_PATH_SUB_FORMATTER, x), getData());
                System.out.println("创建节点:\t"+o);
                currentPath = (String)o;
                break;
            } catch (KeeperException.NodeExistsException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return currentPath == null;
    }

    private byte[] getData(){
        JSONObject data = new JSONObject();
        data.put("appId", ManagementFactory.getRuntimeMXBean().getName());
        data.put("concurrency", nodes);
        return data.toJSONString().getBytes();
    }

    private byte[] freshData(ChildData childData){
        JSONObject data = new JSONObject();
        JSONObject newData = JSONObject.parseObject(new String(childData.getData()));
        data.put("appId", ManagementFactory.getRuntimeMXBean().getName());
        if(null != newData.getString("concurrency")){
            data.put("concurrency", newData.getString("concurrency"));
            nodes = newData.getIntValue("concurrency");
        }
        return data.toJSONString().getBytes();
    }
}
