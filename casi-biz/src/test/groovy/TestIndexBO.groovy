import com.casi.biz.bo.index.IndexBO
import org.springframework.beans.factory.annotation.Autowired
import util.SpockTestCaseBase
/**
 * User: Think
 * Date: 13-1-26
 * Time: 下午12:53
 */
class TestIndexBO extends SpockTestCaseBase {
    @Autowired
    IndexBO indexBO;
    def "test indexBO"(){
        print  indexBO.getPerson("和 陈")
        expect:true
    }
    def "test createIndex"(){
        expect:indexBO.createIndex()
    }
}
