import com.casi.web.action.index.IndexAction;
import com.opensymphony.xwork2.ActionProxy;

import java.util.Map;

public class TestIndexAction extends WebTestCaseBase {

   public void testAddPerson(){
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

    public void testGetPerson(){
        request.setParameter("query","薛 常");
        ActionProxy proxy=getActionProxy("/ajax/query.action");
        try {
            IndexAction ia=(IndexAction)proxy.getAction();
            String result=proxy.execute(); //将返回Action方法 返回的 SUCCESS or ERROR
            assertEquals("success",result);
            Map map=ia.getAjaxResult().getResultMap();//要获取Action 执行后封装的结果数据，这行必须写在excute()方法被调用后
            assertNotNull(map.get("res"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
