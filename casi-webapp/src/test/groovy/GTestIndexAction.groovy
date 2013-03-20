import com.casi.commons.test.SpockTestCaseBase
import com.casi.dao.dataobject.PersonDO
import com.casi.web.action.index.IndexAction
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: Think
 * Date: 13-3-20
 * Time: 下午9:21
 */
class GTestIndexAction extends SpockTestCaseBase {
    @Autowired
    IndexAction indexAction
    def "test add person"(){
        expect:
            indexAction.setPerson(person)
            indexAction.addPerson()
            code==indexAction.getAjaxResult().resultCode
        where:person |code
            new PersonDO(name: "11",age: 100,address: "香樟大道",birthday:new Date(),school: "ustc")|1
            new PersonDO(name: "22",age: 100,address: "香樟大道",birthday:new Date(),school: "ustc")|1
    }

    def "test get person"(){
        expect:
            indexAction.setQuery(query)
            indexAction.get()
            assert (indexAction.ajaxResult.resultMap) != null
        where:
            query<<["11","薛常"]
    }
}
