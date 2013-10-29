package com.casi.commons.test;

import org.junit.runner.RunWith;
import org.spockframework.spring.SpringTestContextManager;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: Think
 * Date: 13-10-29
 * Time: 下午10:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={
"classpath*:/springbeans-biz-bo.xml",
"classpath*:/springbeans-biz-tx.xml",
"classpath*:/springbeans-dao-ds-test.xml",
"classpath*:/springbeans-dao-beans.xml",
"classpath*:/springbeans-jdo-ds.xml",
"classpath*:/springbeans-jdo-beans.xml"})
public class JunitTestBase extends AbstractJUnit4SpringContextTests {
}
