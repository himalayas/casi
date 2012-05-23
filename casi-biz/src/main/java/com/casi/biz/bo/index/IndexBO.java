package com.casi.biz.bo.index;

import com.casi.dao.dataobject.PersonDO;

import java.sql.SQLException;
import java.util.List;

/**
 * User: hadoop
 * Date: 12-1-11
 * Time: 下午4:38
 */
public interface IndexBO {
    void addPerson(PersonDO person) throws SQLException;
    void reloadPerson() throws Exception;
    void createIndex() throws Exception;
    String getPerson(String q) throws Exception;
}
