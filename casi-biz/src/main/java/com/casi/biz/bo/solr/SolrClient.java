package com.casi.biz.bo.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * User: hadoop
 * Date: 12-2-3
 * Time: 下午4:38*/


public class SolrClient {
    public static void main(String[] args) throws Exception{

        SolrClient solrClient=new SolrClient();
        solrClient.getDataFromDB();
        String url = "http://localhost:8080/solr";
        CommonsHttpSolrServer server = new CommonsHttpSolrServer( url );
        server.setSoTimeout(1000);  // socket read timeout
        server.setConnectionTimeout(100);
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false);  // defaults to false
        // allowCompression defaults to false.
        // Server side must support gzip or deflate for this to have any effect.
        server.setAllowCompression(true);
        server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
        server.setParser(new XMLResponseParser()); // binary parser is used by default


        SolrInputDocument doc1 = new SolrInputDocument();
        doc1.addField( "id", "id1", 1.0f );
        doc1.addField( "name", "doc1", 1.0f );
        doc1.addField( "price", 10 );

        SolrInputDocument doc2 = new SolrInputDocument();
        doc2.addField( "id", "id2", 1.0f );
        doc2.addField( "name", "doc2", 1.0f );
        doc2.addField( "price", 20 );


        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        docs.add( doc1 );
        docs.add( doc2 );


        server.add( docs );

        server.commit();

    }


//-------------------------------------------------------------------------
        private static int fetchSize = 1000;
        private static String url = "http://localhost:8080/solr";
        private static CommonsHttpSolrServer solrCore;

        public SolrClient() throws MalformedURLException
        {
            solrCore = new CommonsHttpSolrServer(url);
        }

    public void getDataFromDB() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/php_db", "root", "root");
        PreparedStatement stmt = conn.prepareStatement("select * from person");
        ResultSet rs=stmt.executeQuery();
        long count=  addResultSet(rs);
        System.out.println("count is: "+count);

    }

/**
         * Takes an SQL ResultSet and adds the documents to solr. Does it in batches
         * of fetchSize.
         *
         * @param rs
         *            A ResultSet from the database.
         * @return The number of documents added to solr.
         * @throws SQLException
         * @throws SolrServerException
         * @throws IOException*/


        public long addResultSet(ResultSet rs) throws SQLException,
                SolrServerException, IOException
        {
            long count = 0;
            int innerCount = 0;
            Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
            ResultSetMetaData rsm = rs.getMetaData();
            int numColumns = rsm.getColumnCount();
            String[] colNames = new String[numColumns + 1];

/**
             * JDBC numbers the columns starting at 1, so the normal java convention
             * of starting at zero won't work.*/


            for (int i = 1; i < (numColumns + 1); i++)
            {
                colNames[i] = rsm.getColumnName(i);
/**
                 * If there are fields that you want to handle manually, check for
                 * them here and change that entry in colNames to null. This will
                 * cause the loop in the next section to skip that database column.*/


                // //Example:
                // if (rsm.getColumnName(i) == "db_id")
                // {
                // colNames[i] = null;
                // }
            }

            while (rs.next())
            {
                count++;
                innerCount++;

                SolrInputDocument doc = new SolrInputDocument();

/**
                 * At this point, take care of manual document field assignments for
                 * which you previously assigned the colNames entry to null.*/


                // //Example:
                // doc.addField("solr_db_id", rs.getLong("db_id"));

                for (int j = 1; j < (numColumns + 1); j++)
                {
                    if (colNames[j] != null)
                    {
                        Object f;
                        switch (rsm.getColumnType(j))
                        {
                            case Types.BIGINT:
                            {
                                f = rs.getLong(j);
                                break;
                            }
                            case Types.INTEGER:
                            {
                                f = rs.getInt(j);
                                break;
                            }
                            case Types.DATE:
                            {
                                f = rs.getDate(j);
                                break;
                            }
                            case Types.FLOAT:
                            {
                                f = rs.getFloat(j);
                                break;
                            }
                            case Types.DOUBLE:
                            {
                                f = rs.getDouble(j);
                                break;
                            }
                            case Types.TIME:
                            {
                                f = rs.getDate(j);
                                break;
                            }
                            case Types.BOOLEAN:
                            {
                                f = rs.getBoolean(j);
                                break;
                            }
                            default:
                            {
                                f = rs.getString(j);
                            }
                        }
                        doc.addField(colNames[j], f);
                    }
                }
                docs.add(doc);

/**
                 * When we reach fetchSize, index the documents and reset the inner
                 * counter.*/


                if (innerCount == fetchSize)
                {
                    solrCore.add(docs);
                    docs.clear();
                    innerCount = 0;
                }
            }

/**
             * If the outer loop ended before the inner loop reset, index the
             * remaining documents.*/


            if (innerCount != 0)
            {
                solrCore.add(docs);
            }
            return count;
        }

}
