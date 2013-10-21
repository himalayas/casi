import com.casi.commons.test.SpockTestCaseBase
import com.casi.jdo.dataobject.StudentDO
import com.casi.jdo.impl.StudentDAOImpl
import org.springframework.beans.factory.annotation.Autowired

/**
 * User: Think
 * Date: 13-10-21
 * Time: 下午9:14
 */
class GTestStudentJDO extends SpockTestCaseBase {
    @Autowired
    StudentDAOImpl studentDAO
    def "test add persion"(){
        expect:
            studentDAO.save(student)
        where:
            student<<[new StudentDO(name: "11",age: 100,address: "香樟大道12",birthday:new Date(),school: "ustc"),
                new StudentDO(name: "22",age: 100,address: "香樟大道12",birthday:new Date(),school: "ustc")]
    }

}
