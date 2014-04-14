package com.casi.demo;

import groovy.json.JsonBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by xiujguo on 2014/4/11.
 */
public class ElasticSearchClient {
    public static void main(String[] args) throws IOException {
//        http://10.194.245.180/
//        Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("10.194.245.180", 9300));
        Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("10.224.38.191", 9300));
        BulkRequestBuilder bulkRequest;

// either use client#prepare, or use Requests# to directly build index/delete requests
        BulkResponse bulkResponse;
        do {
            bulkRequest = client.prepareBulk();
            Random random=new Random();
            String id=String.valueOf(random.nextInt());
            System.out.println(id);
            bulkRequest.add(client.prepareIndex("google-index", "google", id)
                            .setSource(jsonBuilder()
                                            .startObject()
                                            .field("username", "谷歌日志")
                                            .field("date", new Date())
                                            .field("message", "消息")
                                            .endObject()
                            )
            );
            bulkResponse = bulkRequest.execute().actionGet();
            if (bulkResponse.hasFailures()) {
                System.out.println("failures...........");
                break;
            }
        }while (true);

    }
}
