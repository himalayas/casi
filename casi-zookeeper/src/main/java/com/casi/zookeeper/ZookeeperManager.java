package com.casi.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * User: hadoop
 * Date: 12-2-20
 * Time: 下午6:33
 */
public class ZookeeperManager {

    private ZooKeeper zooKeeper;
    private ConnectionWatcher connectionWatcher;
    private int SESSION_OUT = 9000;
    private static final Charset CHARSET=Charset.forName("UTF-8");

    public ZookeeperManager(String[] hosts) throws IOException, InterruptedException {
        connectionWatcher = new ConnectionWatcher();
        connectionWatcher.connect(hosts, SESSION_OUT);
        zooKeeper = connectionWatcher.getZooKeeper();
    }

    public void createGroup(String groupName) throws IOException, InterruptedException, KeeperException {
        String path = "/" + groupName;
        String createPath = zooKeeper.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

    }

    public void joinGroup(String groupName, String memberName) throws IOException, InterruptedException, KeeperException {
        String path = "/" + groupName + "/" + memberName;
        zooKeeper.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    public void deleteMember(String fullPath) throws IOException, InterruptedException, KeeperException {
        zooKeeper.delete(fullPath, -1);
    }

    public List list(String groupName) throws IOException, InterruptedException, KeeperException {
        String path = "/" + groupName;
        List<String> list = zooKeeper.getChildren(path, false);
        return list;
    }

    public List updateData(String fullPath, String value) throws IOException, InterruptedException, KeeperException {

        Stat stat = zooKeeper.exists(fullPath, false);
        if (stat == null) {
            zooKeeper.create(fullPath, value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } else {
            zooKeeper.setData(fullPath, value.getBytes(CHARSET), -1);
        }
        List<String> list = zooKeeper.getChildren(fullPath, false);
        return list;
    }
    public String getData(String fullPath) throws InterruptedException, KeeperException {
        Stat stat = zooKeeper.exists(fullPath, false);
        if(stat==null){
            System.out.println(fullPath+" is no exists!");
            return null;
        }else
        {
            return new String(zooKeeper.getData(fullPath,connectionWatcher,null),CHARSET);
        }
    }



    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        ZookeeperManager zm = new ZookeeperManager(new String[]{"namenode,s1,s2"});
        String fullPath="/casi-group/casi-group-member-1";
        //zm.deleteMember(fullPath);
        //zm.deleteMember("/casi-group");
        //zm.createGroup("casi-group");
        zm.joinGroup("casi-group","casi-group-member-1");
        zm.joinGroup("casi-group","casi-group-member-2");
        zm.joinGroup("casi-group","casi-group-member-3");


        zm.updateData(fullPath,"himalayas1111");
        //System.out.println(zm.getData(fullPath));
        //Thread.sleep(Long.MAX_VALUE);
        //zm.zooKeeper.close();
    }
}
