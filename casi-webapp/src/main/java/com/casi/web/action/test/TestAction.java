package com.casi.web.action.test;

import com.casi.biz.bo.test.TestBO;
import com.casi.commons.Result;
import com.casi.web.action.BaseAction;

/**
 * Created with IntelliJ IDEA.
 * User: himalayas
 * Date: 12-11-20
 * Time: 下午10:00
 * To change this template use File | Settings | File Templates.
 */
public class TestAction extends BaseAction {
    private Result ajaxResult;
    private TestBO testBO;
    public String test(){
        ajaxResult=Result.successResult();
        ajaxResult.setMessage(testBO.getJson());
        webLogger.info(testBO.getJson());
        return SUCCESS;
    }

    public Result getAjaxResult() {
        return ajaxResult;
    }

    public void setAjaxResult(Result ajaxResult) {
        this.ajaxResult = ajaxResult;
    }

    public TestBO getTestBO() {
        return testBO;
    }

    public void setTestBO(TestBO testBO) {
        this.testBO = testBO;
    }
}
