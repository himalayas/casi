package com.casi.demo.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * User: David Guo
 * Date: 12-2-29
 * Time: 下午1:56
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        //监控11111端口，支持10个并发访问 （默认是50）
        ServerSocket serverSocket = new ServerSocket(11111,100);
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();

                StringBuffer sb=new StringBuffer();
                Reader br=new InputStreamReader(inputStream);
                while(true) {
                    char[] msg = new char[10];
                    int size = br.read(msg, 0, 10);
                    if (size<=0)
                        break;
                    System.out.print(msg);
                    System.out.printf(" [size]:%s \n", msg.length);
                    sb.append(msg);
                }
                System.out.println(sb.toString());
                socket.close();
                System.out.println("---------------------");
            } catch (IOException e) {
               e.printStackTrace();
            } finally {
                if (socket != null && !socket.isClosed() && socket.isConnected())
                    socket.close();
            }
        }
    }
}
