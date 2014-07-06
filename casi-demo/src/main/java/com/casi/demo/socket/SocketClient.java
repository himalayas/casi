package com.casi.demo.socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * User: David Guo
 * Date: 12-2-29
 * Time: 下午1:55
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
            int count = 100;
            CyclicBarrier cb = new CyclicBarrier(count);
            for (int i = 0; i < count; i++) {
                new Thread(new TaskThread(cb, i)).start();
            }
    }
}
