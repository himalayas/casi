import com.casi.commons.test.SpockTestCaseBase
import com.casi.service.consumer.PersonServiceConsumer
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: Think
 * Date: 13-10-31
 * Time: 下午11:28
 */
class TestPersonServiceConsumer extends SpockTestCaseBase {
    @Autowired
    PersonServiceConsumer personServiceConsumer
    def "get person"(){
        expect:
            print personServiceConsumer.getPerson(name)
        where:
            name<<["常"]
    }
}
