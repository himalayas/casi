package com.casi.biz.bo.index.impl;

import com.casi.biz.bo.CasiBaseBO;
import com.casi.biz.bo.index.IndexBO;
import com.casi.dao.index.IndexDAO;
import com.casi.dao.dataobject.PersonDO;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.*;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
//import org.apache.solr.client.solrj.SolrServer;
//import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
//import org.apache.solr.common.SolrInputDocument;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * User: hadoop
 * Date: 12-1-11
 * Time: 下午4:39
 */
public class IndexBOImpl extends CasiBaseBO implements IndexBO {
    private IndexDAO indexDAO;
    private String solrURL;
    private String indexDir;


    /**
     * 向数据库添加数据
     */
    @Override
    public void addPerson(PersonDO person) throws SQLException {
        indexDAO.add(person);
    }


    /**
     * 重建Solr索引
     */
    @Override
    public void reloadPerson() throws Exception {
        PersonDO person = new PersonDO();
        while (true) {
            List<PersonDO> personDOs = indexDAO.getData(person);
            if (personDOs == null || personDOs.size() == 0)
                return;
            Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
            for (PersonDO personDO : personDOs) {
                SolrInputDocument doc = new SolrInputDocument();
                doc.addField("id", personDO.getId());
                doc.addField("casi_id", "casi-" + personDO.getId());
                doc.addField("casi_name", personDO.getName());
                doc.addField("casi_age", personDO.getAge());
                doc.addField("casi_birthday", personDO.getBirthday());
                doc.addField("casi_address", personDO.getAddress());
                doc.addField("casi_school", personDO.getSchool());
                docs.add(doc);
            }
            SolrServer solrCore = new CommonsHttpSolrServer(solrURL);
            solrCore.add(docs);
            solrCore.optimize();
            solrCore.commit();
            person.setStart(person.getStart() + person.getPageSize());
        }
    }


    /**
     * 创建lucene索引
     */
    @Override
    public void createIndex() throws Exception {
        long start = System.currentTimeMillis();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
        Directory directory = new SimpleFSDirectory(new File(indexDir));
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        PersonDO person = new PersonDO();
        person.setPageSize(100000);
        System.out.println("pageSize is:" + person.getPageSize());
        int s = 0;
        try {
            Collection<Document> docs = new ArrayList<Document>();
            while (true) {
                person.setStart(s);
                s = s + person.getPageSize();
                System.out.println("start is:" + person.getStart());
                List<PersonDO> personDOs = indexDAO.getData(person);
                if (personDOs == null || personDOs.size() == 0)
                    break;
                System.out.println("load data size is:" + personDOs.size());
                for (PersonDO personDO : personDOs) {
                    Document doc = new Document();
                    doc.add(new Field("casi_id", String.valueOf(personDO.getId()), Field.Store.YES, Field.Index.ANALYZED));
                    doc.add(new Field("casi_name", personDO.getName(), Field.Store.YES, Field.Index.ANALYZED));
                    doc.add(new Field("casi_address", personDO.getAddress(), Field.Store.YES, Field.Index.ANALYZED));
                    doc.add(new Field("casi_school", personDO.getSchool(), Field.Store.YES, Field.Index.ANALYZED));
                    docs.add(doc);
                }
                indexWriter.addDocuments(docs);
                docs.clear();
            }
        } catch (Exception e) {
            boLogger.error(e.getLocalizedMessage());
        } finally {
            indexWriter.commit();
            indexWriter.close();
        }

        boLogger.info("更新索引耗时:" + (System.currentTimeMillis() - start) / 1000.0);
    }


    /**
     * 从lucene查询数据
     */
    @Override
    public String getPerson(String q) throws Exception {
        //数据存放路径
        Directory directory = new NIOFSDirectory(new File(indexDir));
        //创建搜索对象
        IndexSearcher is = new IndexSearcher(IndexReader.open(directory));
        QueryParser queryParser = new QueryParser(Version.LUCENE_35, "casi_name", new StandardAnalyzer(Version.LUCENE_35));
        Query query = queryParser.parse(q);
        TopDocs hits = is.search(query, 10);
        StringBuilder builder = new StringBuilder();
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            builder.append(" [");
            builder.append(" id:" + doc.get("casi_id"));
            builder.append(" name:" + doc.get("casi_name"));
            builder.append(" address:" + doc.get("casi_address"));
            builder.append(" school:" + doc.get("casi_school"));
            builder.append("] ");
        }
        return builder.toString();
    }

    public String getIndexDir() {
        return indexDir;
    }

    public void setIndexDir(String indexDir) {
        this.indexDir = indexDir;
    }

    public String getSolrURL() {
        return solrURL;
    }

    public void setSolrURL(String solrURL) {
        this.solrURL = solrURL;
    }

    public IndexDAO getIndexDAO() {
        return indexDAO;
    }

    public void setIndexDAO(IndexDAO indexDAO) {
        this.indexDAO = indexDAO;
    }
}
