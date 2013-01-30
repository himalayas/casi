import com.casi.biz.bo.index.IndexBO
import com.casi.dao.dataobject.PersonDO
import org.springframework.beans.factory.annotation.Autowired
import com.casi.commons.test.SpockTestCaseBase
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource

/**
 * User: Think
 * Date: 13-1-26
 * Time: 下午12:53
 */
class TestIndexBO extends SpockTestCaseBase {

    @Autowired
    IndexBO indexBO;
    def "test indexBO"(){
        expect:
            indexBO.getPerson(name).length() >= size
            print name+":"+  indexBO.getPerson(name)
        where:
            name|size
            "xiujguo"|1
    }

    def "test createIndex"(){
        expect:indexBO.createIndex()
    }

    def "insert into person"(){
         expect: code==indexBO.addPerson(p).resultCode
         where:p|code
            new PersonDO("xiujguo",18,"安徽合肥",new Date(),"cisco")|1
            new PersonDO("xiujguo",18,"安徽合肥",new Date(),"cisco")|1
    }

    def "test get test.txt"(){
        expect:
        print Thread.currentThread().getContextClassLoader().getResource("").openStream()
    }
}
