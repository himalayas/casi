package com.casi.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: David Guo
 * Date: 12-2-29
 * Time: 下午1:56
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        //监控11111端口，支持10个并发访问 （默认是50）
        ServerSocket serverSocket = new ServerSocket(80,10);
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream=socket.getOutputStream();
                outputStream.write("hello".getBytes());
                byte[] msg = new byte[1024];
                inputStream.read(msg);
                System.out.println(new String(msg));
            } catch (IOException e) {
               //
            } finally {
                if (socket != null && !socket.isClosed() && socket.isConnected())
                    socket.close();
            }
        }
    }
}
