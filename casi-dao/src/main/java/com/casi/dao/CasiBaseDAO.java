package com.casi.dao;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;


/**
 * User: hadoop
 * Date: 12-1-11
 * Time: 下午4:34
 */
public class CasiBaseDAO extends SqlSessionDaoSupport {
    protected final Log daoLogger = LogFactory.getLog(getClass());
}
