package com.casi.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;


/**
 * User: hadoop
 * Date: 12-1-11
 * Time: 下午4:34
 */
public class CasiBaseDAO extends SqlSessionDaoSupport {
    protected Logger daoLogger = LoggerFactory.getLogger(this.getClass());
}
