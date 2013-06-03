package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * User: Think
 * Date: 13-6-3
 * Time: 下午9:30
 */
public class WebServer {
    private static final int THREAD_NUM=100;
    private static final ExecutorService pool=Executors.newFixedThreadPool(THREAD_NUM);

    public static void main(String[] args) throws IOException {
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(100);
        Executors.newSingleThreadExecutor();
        ThreadPoolExecutor pool=new ThreadPoolExecutor(
                10,
                100,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(100));

            ServerSocket ss=new ServerSocket(80);
            while (true){
                final Socket socket=ss.accept();
                Runnable request=new Runnable() {
                    @Override
                    public void run() {
                       handleRequest(socket);
                    }
                };
                pool.execute(request);
            }
    }
    public static void handleRequest(Socket socket){
      //TODO
    }
}
