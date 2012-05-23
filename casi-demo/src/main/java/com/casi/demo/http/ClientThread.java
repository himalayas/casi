package com.casi.demo.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


/**
 * User: hadoop
 * Date: 12-1-18
 * Time: 上午9:18
 */
public class ClientThread implements Runnable{

    private final HttpClient httpClient;
    private final HttpContext context;
    private final HttpGet httpGet;

    public ClientThread() {
        this.httpClient = new DefaultHttpClient();
        this.context = new BasicHttpContext();
        this.httpGet=new HttpGet("http://localhost:8080/casi/index.action");
    }

    @Override
    public void run() {
        try {
            for(int i=0;i<10;i++){
                HttpResponse response = this.httpClient.execute(this.httpGet, this.context);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println(response.getStatusLine());
                }
                EntityUtils.consume(entity);
            }
        } catch (Exception ex) {
            this.httpGet.abort();
        }
    }
}
