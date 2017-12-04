package com.viki.org.apache.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/14 15:02
 */
public class DistributedTaskAdjusting {

    private String namespace = "testing";

    private String taskName = "refreshTaskStatus";

    private CountDownLatch	connectedSignal	=	new CountDownLatch(1);

    ZooKeeper zooKeeper;

    public static void main(String[] args) {
        try {
            DistributedTaskAdjusting distributedTaskAdjusting = new DistributedTaskAdjusting();
            distributedTaskAdjusting.create();
            distributedTaskAdjusting.addTaskNode();
            while (true){
                distributedTaskAdjusting.updateData(String.format("/%s/task/%s", distributedTaskAdjusting.namespace, distributedTaskAdjusting.taskName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void create() throws Exception {
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 6000, new TaskWatcher(connectedSignal));
        connectedSignal.await();
    }

    private String addTaskNode() throws KeeperException, InterruptedException {
        String path = String.format("/%s/task/%s", namespace, taskName);
        String[] nodes = path.split("\\/");
        String temp="";
        int i = 0, length;
        length = nodes.length;
        for(String n : nodes){
            i++;
            if(n.isEmpty()){
                continue;
            }
            if(i == length - 1){
                continue;
            }
            temp = temp + "/"+ n;
            Stat stat = zooKeeper.exists(temp, false);
            if(stat == null){
                zooKeeper.create(temp, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        }
        return zooKeeper.create(path, "adfadf".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    private void updateData(String path)  {
        try{
            Stat stat = zooKeeper.exists(path, false);
            if(stat == null){
                return;
            }
            TimeUnit.SECONDS.sleep((int)(Math.random() * 3));
            String newData = new Integer((int)(Math.random() * 1000)).toString();
            Stat newStat = zooKeeper.setData(path, newData.getBytes(), stat.getVersion());
            System.out.print("尝试更新为:"+newData);
            if(newStat.getVersion() == stat.getVersion() + 1){
                System.out.println("更新成功,当前版本号为:"+newStat.getVersion());
            }else{
                System.out.println("更新失败,当前版本号为:"+stat.getVersion());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                TimeUnit.SECONDS.sleep((int)(Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

