package com.casi.biz.bo.test.impl;

import com.casi.biz.bo.test.TestBO;
import com.casi.commons.Result;

/**
 * Created with IntelliJ IDEA.
 * User: himalayas
 * Date: 12-11-20
 * Time: 下午10:02
 * To change this template use File | Settings | File Templates.
 */
public class TestBOImpl implements TestBO {

    @Override
    public String getJson() {
        return "{'name':'xiujguo','age':30}";
    }
}
