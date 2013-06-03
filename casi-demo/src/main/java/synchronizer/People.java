package synchronizer;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Think
 * Date: 13-6-3
 * Time: 下午7:13
 */
public class People {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int ages) {
        this.age = ages;
    }
    public Object getLast(List list) {
        synchronized (list){
            int lastIndex=list.size()-1;
            return list.get(lastIndex);
        }
    }


    public void deleteLast(List list){
        synchronized (this){
            System.out.println("del...."+Thread.currentThread().getName());
            int lastIndex=list.size()-1;
            list.remove(lastIndex);
        }
    }
}
