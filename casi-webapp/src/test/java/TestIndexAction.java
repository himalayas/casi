import com.opensymphony.xwork2.ActionProxy;

public class TestIndexAction extends WebTestCaseBase {

   public void testGetPerson(){
        request.setParameter("person.name","xiaoqingwa");
        request.setParameter("person.age", "10");
        ActionProxy proxy=getActionProxy("/addPerson.action");
        try {
            assertNotNull(proxy);
            assertNotNull(proxy.execute());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
