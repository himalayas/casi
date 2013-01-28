package com.casi.dao.index;

import com.casi.dao.dataobject.PersonDO;

import java.sql.SQLException;
import java.util.List;

/**
 * User: hadoop
 * Date: 12-1-11
 * Time: 下午4:36
 */
public interface IndexDAO {
    public int add(PersonDO person) throws SQLException;
    public List<PersonDO> getData(PersonDO person) throws SQLException;
}
