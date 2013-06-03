package synchronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * User: Think
 * Date: 13-6-3
 * Time: 下午7:17
 */
public class MainClass {
    public static void main(String[] args) {
        final People p1=new People();
        final List list=new ArrayList();
        for (int i=0;i<10;i++)
            list.add(i);

        Thread t1=new Thread(new Runnable(){
           public void run(){
              p1.getLast(list);
            }
        });

        final People p2=new People();
        Thread t2=new Thread(new Runnable(){
            public void run(){
                p2.getLast(list);
            }
        });
        t1.start();
        t2.start();
    }
}
