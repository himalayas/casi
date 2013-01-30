package com.casi.commons.test

import org.apache.struts2.StrutsSpringTestCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * User: Think
 * Date: 13-1-26
 * Time: 上午10:52
 */
@ContextConfiguration(locations = [
"classpath*:/springbeans-biz-bo.xml",
"classpath*:/springbeans-biz-tx.xml",
"classpath*:/springbeans-dao-ds.xml",
"classpath*:/springbeans-dao-beans.xml",
"classpath*:/springbeans-action.xml",
"classpath*:/struts-index.xml",
"classpath*:/struts.xml"])
class SpockTestCaseBase extends Specification {

}

