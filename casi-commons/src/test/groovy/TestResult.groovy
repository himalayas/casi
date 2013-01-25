import com.casi.commons.Result
import spock.lang.Specification

/**
 * User: Think
 * Date: 13-1-25
 * Time: 下午11:14
 */
class TestResult extends Specification {
    def "init Relult() obj"(){
        Result res=Result.successResult()
        expect:res.isSuccess()
    }
}
