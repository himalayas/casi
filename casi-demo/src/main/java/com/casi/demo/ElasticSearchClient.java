package com.casi.demo;

import groovy.json.JsonBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
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
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "webex-cluster")
                .put("number_of_shards",2)
                .put("number_of_replicas",2)
                .build();
        Client client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress("10.224.57.165", 9300))
                .addTransportAddress(new InetSocketTransportAddress("10.224.57.166", 9300));
        BulkRequestBuilder bulkRequest;
        BulkResponse bulkResponse;
        do {
            bulkRequest = client.prepareBulk();
            Random random=new Random();
            String id=String.valueOf(random.nextInt());
            bulkRequest.add(client.prepareIndex("eventlog-index", "eventlog-type", id)
                            .setSource(jsonBuilder()
                                            .startObject()
                                            .field("EVENTTYPE", "Logon")
                                            .field("TIMESTAMP", "2012/2/3 5:19")
                                            .field("UID_", "976234742")
                                            .field("GID", "0")
                                            .field("USERNAME", "wbxadmin")
                                            .field("EMAIL", "admin@webex.com")
                                            .field("WEBSERVERNAME", "hawdtest/admin")
                                            .field("REMOTEHOST", "10.224.65.44")
                                            .field("USERAGENT", "Mozilla/5.0 (Windows NT 6.1; rv:9.0.1) Gecko/20100101 Firefox/9.0.1")
                                            .field("REFERER", "0")
                                            .field("CONFID", "1090723152")
                                            .field("MEETINGTYPE", "0")
                                            .field("REFNUM1", "0")
                                            .field("SITEID", "593968567")
                                            .field("REFSTR1", "LogonFailed")
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
