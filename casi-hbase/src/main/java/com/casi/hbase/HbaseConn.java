package com.casi.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * User: hadoop
 * Date: 12-2-23
 * Time: 上午11:45
 */
public class HbaseConn {

    public static void main(String[] args) throws IOException {
        HbaseConn hbaseConn = new HbaseConn();
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "10.13.120.154,10.13.120.153,10.13.120.155");
        //HTable不是线程安全的。建议使用同一个HBaseConfiguration实例来创建HTable实例

/*        HBaseAdmin hBaseAdmin = new HBaseAdmin(config);
        HTableDescriptor tableDescriptor = new HTableDescriptor();
        tableDescriptor.setName(Bytes.toBytes("blog"));
        tableDescriptor.addFamily(new HColumnDescriptor("author"));
        tableDescriptor.addFamily(new HColumnDescriptor("article"));
        hbaseConn.createTable(hBaseAdmin, tableDescriptor);*/

        HTable table = new HTable(config, "blog");
        table.setAutoFlush(false);
        //hbaseConn.putData(table);
       // hbaseConn.list(table);
        hbaseConn.getData(table,"1");
    }

    void createTable(HBaseAdmin hBaseAdmin, HTableDescriptor tableDescriptor) throws IOException {
        hBaseAdmin.createTable(tableDescriptor);
    }

    void putData(HTable table) throws IOException {
        /**=========插入数据=========*/
        Put put = new Put(Bytes.toBytes("1"));
        put.add(Bytes.toBytes("article"), Bytes.toBytes("title"), Bytes.toBytes("Head First HBase"));
        put.add(Bytes.toBytes("article"), Bytes.toBytes("content"), Bytes.toBytes("HBase is the Hadoop database. Use it when you need random, realtime read/write access to your Big Data."));
        put.add(Bytes.toBytes("article"), Bytes.toBytes("tags"), Bytes.toBytes("Hadoop,HBase,NoSQL"));
        put.add(Bytes.toBytes("author"), Bytes.toBytes("name"), Bytes.toBytes("hujinjun"));
        put.add(Bytes.toBytes("author"), Bytes.toBytes("nickname"), Bytes.toBytes("一叶渡江"));
        table.put(put);
        table.flushCommits();
    }

    void getData(HTable table, String rowKey) throws IOException {
        /**=========根据rowkey查询数据=========*/
        Get get = new Get(Bytes.toBytes(rowKey));
        //get.addFamily(Bytes.toBytes("article"));
        get.addColumn(Bytes.toBytes("article"),Bytes.toBytes("content"));
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
            System.out.print("family:" + Bytes.toString(kv.getFamily()));
            System.out.print("  qualifier:" + Bytes.toString(kv.getQualifier()));
            System.out.print("  value:" + Bytes.toString(kv.getValue()));
            System.out.println("  Timestamp:" + kv.getTimestamp());
        }

    }

    void list(HTable table) throws IOException {
        /**=========遍历查询=========*/
        Scan scan = new Scan();
        ResultScanner rs = null;
        try {
            rs = table.getScanner(scan);
            for (Result r : rs) {
                for (KeyValue kv : r.list()) {
                    System.out.println("family:" + Bytes.toString(kv.getFamily()));
                    System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
                    System.out.println("value:" + Bytes.toString(kv.getValue()));
                }
            }
        } finally {
            rs.close();
        }

    }
}
