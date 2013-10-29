import com.casi.commons.test.JunitTestBase;
import com.casi.jdo.dataobject.StudentDO;
import com.casi.jdo.impl.StudentDAO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * User: Think
 * Date: 13-10-29
 * Time: 下午11:10
 */
public class TestStudentDAO extends JunitTestBase {
    @Autowired
    StudentDAO studentDAO;
    @Test
    public void testSave(){
       studentDAO.save(new StudentDO("cisco121212",88,"安徽合肥",new Date(),"cisco"));
    }
}
