package com.casi.jdo.impl;

import com.casi.jdo.base.BaseDAOImpl;
import com.casi.jdo.dataobject.StudentDO;

/**
 * User: Think
 * Date: 13-10-21
 * Time: 下午9:03
 */
public class StudentDAOImpl extends BaseDAOImpl<StudentDO> {
    public StudentDAOImpl() {
        super(StudentDO.class);
    }
}
