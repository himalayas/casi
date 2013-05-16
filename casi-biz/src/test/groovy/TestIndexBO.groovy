import com.casi.biz.bo.index.IndexBO
import com.casi.dao.dataobject.PersonDO
import org.springframework.beans.factory.annotation.Autowired
import com.casi.commons.test.SpockTestCaseBase

/**
 * User: Think
 * Date: 13-1-26
 * Time: 下午12:53
 */
class TestIndexBO extends SpockTestCaseBase {

    @Autowired
    IndexBO indexBO;
    def "test getPerson from search engine"(){
        expect:
            indexBO.getPerson(name).length() >= size
            println name+":"+  indexBO.getPerson(name)
        where:
            name|size
            "薛 常"|1
            "1231231"|22
    }

    def "test createIndex"(){
        expect:indexBO.createIndex(1)
    }

    def "insert into person"(){
         expect: code==indexBO.addPerson(p).resultCode
         where:p|code
            new PersonDO("cisco",88,"安徽合肥",new Date(),"cisco")|1
            new PersonDO("xiujguo",18,"安徽合肥",new Date(),"cisco")|1
    }
    def "test resource txt file"(){
        InputStream is= System.getResourceAsStream("/data/test.txt")
        expect: print is.readLines()
    }

    def "test resource csv file"(){
        when:
            InputStream is= System.getResourceAsStream("/data/test.csv")
        then:
            is.readLines().size()>0

    }
}
