package com.casi.web.action.index;

import com.casi.biz.bo.index.IndexBO;
import com.casi.commons.Result;
import com.casi.dao.dataobject.PersonDO;
import com.casi.web.action.BaseAction;

import java.util.HashMap;

/**
 * User: hadoop
 * Date: 12-1-11
 * Time: 下午5:18
 */
public class IndexAction extends BaseAction{
    private IndexBO indexBO;
    private PersonDO person;
    private Result ajaxResult;
    private String query;
    public String addPerson() throws Exception {
        webLogger.info(person.toString());
        indexBO.addPerson(person);
        return "success";
    }
    
    public String reload() throws Exception {
        indexBO.reloadPerson();
        ajaxResult=Result.successResult();
        return "success";
    }

    
    public String createIndex() throws Exception {
        webLogger.info("开始创建Lucene索引........");
        indexBO.createIndex(1);
        ajaxResult=Result.successResult();
        return "success";
    }
    public String get() throws Exception {
       String res= indexBO.getPerson(query);
        ajaxResult=Result.successResult();
        ajaxResult.setResultMap(new HashMap());
        ajaxResult.getResultMap().put("res",res);
        return "success";
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Result getAjaxResult() {
        return ajaxResult;
    }

    public void setAjaxResult(Result ajaxResult) {
        this.ajaxResult = ajaxResult;
    }

    public IndexBO getIndexBO() {
        return indexBO;
    }

    public void setIndexBO(IndexBO indexBO) {
        this.indexBO = indexBO;
    }

    public PersonDO getPerson() {
        return person;
    }

    public void setPerson(PersonDO person) {
        this.person = person;
    }
}
