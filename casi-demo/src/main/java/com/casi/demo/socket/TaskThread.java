package com.casi.demo.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Kevin on 2014/7/6.
 */
public class TaskThread implements Runnable {
    private static ThreadLocal<Socket> threadLocal=new ThreadLocal<Socket>(){
        @Override
        protected Socket initialValue() {
            Socket  socket=null;
            try {
                socket = new Socket("localhost", 11111);
                socket.setKeepAlive(true);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return socket;
        }
    };
    CyclicBarrier cb;
    int index;
    Socket socket;

    public TaskThread(CyclicBarrier cb,int index) {
        this.cb = cb;
        this.index=index;
    }

    @Override
    public void run() {
        socket=getSocket();

        try {
            cb.await();
            for (int i = 0; i < 100; i++) {
                OutputStream os=getSocket().getOutputStream();
                OutputStreamWriter osw=new OutputStreamWriter(os);
                BufferedWriter out=new BufferedWriter(osw,1024);
                out.write(""+index);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }finally {
            if (socket != null && !socket.isClosed() && socket.isConnected())
                try {
                    socket.close();
                    System.out.printf("%s is down \n",Thread.currentThread().getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }

    public  Socket getSocket() {
        return threadLocal.get();
    }
}
