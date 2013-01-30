import org.apache.struts2.StrutsSpringTestCase;
public class WebTestCaseBase extends StrutsSpringTestCase {
    @Override
    protected String[] getContextLocations() {
        return new String[] {"classpath*:/springbeans-biz-bo.xml",
                "classpath*:/springbeans-biz-tx.xml",
                "classpath*:/springbeans-dao-ds.xml",
                "classpath*:/springbeans-dao-beans.xml",
                "classpath*:/springbeans-action.xml",
                "classpath*:/struts-index.xml",
                "classpath*:/struts.xml"};
    }
}
