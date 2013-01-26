package util

import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * User: Think
 * Date: 13-1-26
 * Time: 上午10:52
 */
@ContextConfiguration(locations = "classpath*:springbeans-*.xml")
class SpockTestCaseBase extends Specification {

}
