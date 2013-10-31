package com.casi.service;

import com.casi.biz.bo.index.IndexBO;

/**
 * User: Think
 * Date: 13-10-31
 * Time: 下午10:45
 */
public class PersonServiceImpl implements PersonService {
    IndexBO indexBO;
    @Override
    public String getPersion(String name) throws Exception {
        return indexBO.getPerson(name);
    }

    public IndexBO getIndexBO() {
        return indexBO;
    }

    public void setIndexBO(IndexBO indexBO) {
        this.indexBO = indexBO;
    }
}
