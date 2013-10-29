package com.casi.jdo.impl;

import com.casi.jdo.base.BaseDAO;
import com.casi.jdo.dataobject.StudentDO;

/**
 * User: Think
 * Date: 13-10-21
 * Time: 下午9:03
 */
public class StudentDAO extends BaseDAO<StudentDO> {
    public StudentDAO() {
        super(StudentDO.class);
    }
}
