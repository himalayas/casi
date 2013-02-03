import org.apache.struts2.StrutsSpringTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebTestCaseBase extends StrutsSpringTestCase {
    @Override
    protected String[] getContextLocations() {
        return new String[] {"classpath*:/springbeans-biz-bo.xml",
                "classpath*:/springbeans-biz-tx.xml",
                "classpath*:/springbeans-dao-ds-test.xml",
                "classpath*:/springbeans-dao-beans.xml",
                "classpath*:/springbeans-action.xml",
                "classpath*:/struts-index.xml",
                "classpath*:/struts.xml"};
    }
}
