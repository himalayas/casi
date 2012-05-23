package com.casi.demo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * User: David Guo
 * Date: 12-2-29
 * Time: 下午1:55
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 11111);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("This is my message".getBytes());
            outputStream.flush();
        } catch (IOException e) {
            //
        } finally {
            if (socket != null && !socket.isClosed() && socket.isConnected())
                socket.close();
        }

    }
}
