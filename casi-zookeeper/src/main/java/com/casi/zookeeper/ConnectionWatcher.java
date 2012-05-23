package com.casi.zookeeper;

import org.apache.commons.lang3.ArrayUtils;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;

/**
 * User: hadoop
 * Date: 12-2-20
 * Time: 下午5:35
 */
public class ConnectionWatcher implements Watcher {

    private ZooKeeper zooKeeper;
    private CountDownLatch countDownLatch;
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public void connect(String[] hosts, int sessionOut) throws IOException, InterruptedException {
        this.countDownLatch = new CountDownLatch(hosts.length);
        String hosts_str = ArrayUtils.toString(hosts);
        hosts_str = hosts_str.replaceFirst("\\{", "");
        hosts_str = hosts_str.replaceFirst("\\}", "");
        zooKeeper = new ZooKeeper(hosts_str, sessionOut, this);
        countDownLatch.await();
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) { //表示连接已经建立 状态为connected
            countDownLatch.countDown();
        }
        if (event.getType() == Event.EventType.NodeDataChanged) { //节点数据改变会触发
            String path = event.getPath();
            try {
                byte[] data = zooKeeper.getData(path, this, null);
                System.out.println("new data is:" + new String(data, CHARSET));
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (event.getType() == Event.EventType.NodeChildrenChanged) {
            System.out.println("Event.EventType.NodeChildrenChanged");
        }
        if (event.getType() == Event.EventType.NodeCreated) {
            System.out.println("Event.EventType.NodeCreated");
        }
        if (event.getType() == Event.EventType.NodeDeleted) {
            System.out.println("Event.EventType.NodeDeleted");
        }
    }
}
