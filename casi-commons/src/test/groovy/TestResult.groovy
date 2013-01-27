import com.casi.commons.Result
import com.casi.commons.test.SpockTestCaseBase

/**
 * User: Think
 * Date: 13-1-25
 * Time: 下午11:14
 */
class TestResult extends SpockTestCaseBase {
    def "init Relult() obj"(){
        Result res=Result.successResult()
        expect:res.isSuccess()
    }
}
