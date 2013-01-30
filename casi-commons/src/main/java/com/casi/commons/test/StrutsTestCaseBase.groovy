package com.casi.commons.test

import org.apache.struts2.StrutsSpringTestCase
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(locations = [
"classpath*:/springbeans-biz-bo.xml",
"classpath*:/springbeans-biz-tx.xml",
"classpath*:/springbeans-dao-ds.xml",
"classpath*:/springbeans-dao-beans.xml",
"classpath*:/springbeans-action.xml",
"classpath*:/struts-index.xml",
"classpath*:/struts.xml"])
class StrutsTestCaseBase extends StrutsSpringTestCase {

}
