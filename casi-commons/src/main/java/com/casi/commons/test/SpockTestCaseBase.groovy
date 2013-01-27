package com.casi.commons.test

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
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
                                    "classpath*:/springbeans-dao-beans.xml"])
class SpockTestCaseBase extends Specification {
}
