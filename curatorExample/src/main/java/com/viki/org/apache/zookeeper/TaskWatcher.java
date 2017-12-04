package com.viki.org.apache.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/14 15:04
 */
public class TaskWatcher implements Watcher {

    private CountDownLatch	connectedSignal;

    public TaskWatcher(CountDownLatch connectedSignal){
        this.connectedSignal = connectedSignal;
    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getState() == Event.KeeperState.SyncConnected){
            connectedSignal.countDown();
        }

    }
}
