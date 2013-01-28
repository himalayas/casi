package com.casi.dao.index.impl;

import com.casi.dao.CasiBaseDAO;
import com.casi.dao.index.IndexDAO;
import com.casi.dao.dataobject.PersonDO;

import java.sql.SQLException;
import java.util.List;

/**
 * User: hadoop
 * Date: 12-1-11
 * Time: 下午4:37
 */
public class IndexDAOImpl extends CasiBaseDAO implements IndexDAO {

    @Override
    public int add(PersonDO person) throws SQLException {
      return this.getSqlSession().insert("index.INSERT-PERSON",person);
    }

    @Override
    public List<PersonDO> getData(PersonDO person) throws SQLException {
       return this.getSqlSession().selectList("index.SELECT-ALL-PERSON",person);
    }
}
