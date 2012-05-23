package com.casi.demo.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.net.MalformedURLException;
import java.util.List;

/**
 * User: David
 * Date: 12-4-3
 * Time: 下午2:45
 */
public class SolrMgr {
    public static void main(String[] args) throws MalformedURLException, SolrServerException {
        long start=System.currentTimeMillis();
        String urlString = "http://localhost:8081/solr";
        CommonsHttpSolrServer solrServer = new CommonsHttpSolrServer(urlString);
        solrServer.setSoTimeout(1000);  // socket read timeout
        solrServer.setConnectionTimeout(100);
        solrServer.setDefaultMaxConnectionsPerHost(100);
        solrServer.setMaxTotalConnections(100);
        solrServer.setFollowRedirects(false);  // defaults to false
        // allowCompression defaults to false.
        // Server side must support gzip or deflate for this to have any effect.
        solrServer.setAllowCompression(true);
        solrServer.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
        solrServer.setParser(new XMLResponseParser()); // binary parser is used by default




        SolrQuery solrQuery = new SolrQuery();
        //solrQuery.set( "q","name:郭*" );
        solrQuery.setQuery("name:雷 昌"); //This priority higher than  solrQuery.set( "q","name:郭*" );
        solrQuery.addField("age").addField("name").addField("address").addFilterQuery("-age:[0 TO 50]").setSortField("age", SolrQuery.ORDER.desc);
        solrQuery.setStart(0);
        solrQuery.setRows(10);


        QueryResponse rsp = solrServer.query(solrQuery);
        List<Person> personList= rsp.getBeans(Person.class);
        long end=System.currentTimeMillis();
        for (Person p:personList){
            System.out.println(p.name+" "+p.age+" "+p.address);
        }
        System.out.println("hit size:"+personList.size()+" time:"+(end-start)/1000.0);
    }
}
