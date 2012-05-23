/*
package com.casi.demo.memcached;

import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * User: hadoop
 * Date: 11-12-31
 * Time: 下午3:42
 *//*

public class MemCacheDemo {

    private MemcachedClient memClient;
    private InetSocketAddress server;
    private ArrayList<InetSocketAddress> serverList;

    public MemCacheDemo() throws IOException {

        this.server = new InetSocketAddress("localhost", 11211);
        this.serverList = new ArrayList<InetSocketAddress>();
        this.serverList.add(server);
        this.memClient = new MemcachedClient(serverList);

    }

    public static void main(String[] args) throws IOException {
        MemCacheDemo memCacheRW=new MemCacheDemo();
        memCacheRW.add(5,null);
        memCacheRW.memClient.shutdown();

    }

    */
/**
     * add data
     *//*

    private void add(int size, List list) {
        for (int i = 0; i < size; i++) {
            memClient.set("test" + i, 1000, i + "_ttttt");

        }
        memClient.flush();
        for(int i=0;i<size;i++){
            System.out.println(this.get("test" + i));
        }
    }

    */
/**
     * get data
     *//*

    private Object get(String key) {
        return memClient.get(key);
    }

    */
/**
     * del data
     *//*

    private void del(String key) {
        memClient.delete(key);
    }

    */
/**
     * update data
     *//*

    private void update(String key, Object val) {
        memClient.replace(key, 60, val);
    }
}
*/
