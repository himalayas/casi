import com.casi.commons.test.SpockTestCaseBase
import com.casi.jdo.dataobject.StudentDO
import com.casi.jdo.impl.StudentDAO
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: Think
 * Date: 13-10-21
 * Time: 下午9:14
 */
class TestStudentJDO extends SpockTestCaseBase {
    @Autowired
    StudentDAO studentDAO
    def "test add persion"(){
        expect:
            studentDAO.save(id)
        where:
            id<<[new StudentDO("cisco121212",88,"安徽合肥",new Date(),"cisco")]
    }

}
