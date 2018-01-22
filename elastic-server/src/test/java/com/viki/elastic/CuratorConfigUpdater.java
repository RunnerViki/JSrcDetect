package com.viki.elastic;

import com.alibaba.fastjson.JSONObject;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.EnsurePath;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.Arrays;

/**
 * Function: 更新当前活跃的节点数
 *
 * @author Viki
 * @date 2018/1/22 15:55
 */
public class CuratorConfigUpdater {

    private static final String ZK_ADDRESS = "127.0.0.1:2181";
    private static final String ZK_PATH = "/zktest/data";

    private static final String ZK_PATH_CONFIG = "/zktest/config";

    public static void main(String[] args) throws IOException, InterruptedException {
        // 1.Connect to zk
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();
        // 2.Ensure path
        try {
            new EnsurePath(ZK_PATH).ensure(client.getZookeeperClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
        CuratorConfigUpdater curatorConfigUpdater = new CuratorConfigUpdater();
        curatorConfigUpdater.readFromConsole(client);
    }

    private void readFromConsole(CuratorFramework client) throws IOException, InterruptedException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean done = false;
        while (!done) {
            System.out.print("> ");
            String line = in.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            String command = line.trim();
            String[] parts = command.split("\\s|:");
            if (parts.length == 0) {
                continue;
            }
            String operation = parts[0];
            String args[] = Arrays.copyOfRange(parts, 1, parts.length);
            if("nodes".equalsIgnoreCase(operation)){
                int nodes = 0;
                if((nodes = Integer.parseInt(args[0])) > 0){
                    update(client, nodes);
                }
            }


            Thread.sleep(1000); // just to allow the console output to catch up
        }
    }

    private void update(CuratorFramework client, int nodes){
        try {
            System.out.println("更新节点数为:"+nodes);
            Stat stat = client.checkExists().forPath(ZK_PATH_CONFIG);
            if(stat == null){
                client.create().withMode(CreateMode.EPHEMERAL).forPath(ZK_PATH_CONFIG, getData(nodes));
            }else{
                client.setData().withVersion(stat.getVersion()).forPath(ZK_PATH_CONFIG, getData(nodes));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] getData(int nodes){
        JSONObject data = new JSONObject();
        data.put("appId", ManagementFactory.getRuntimeMXBean().getName());
        data.put("concurrency", nodes);
        return data.toJSONString().getBytes();
    }
}
