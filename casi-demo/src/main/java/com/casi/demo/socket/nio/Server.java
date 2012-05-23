package com.casi.demo.socket.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * User: David Guo
 * Date: 12-3-1
 * Time: 下午5:14
 */
public class Server {
    public static int DEFAULT_PORT = 80;


    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(DEFAULT_PORT);
        serverSocket.bind(inetSocketAddress);

        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int i = selector.select();
            System.out.println(1);
            Set readKeys = selector.keys();
            Iterator iterator = readKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);

                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    while (client.read(byteBuffer)!=-1){

                    }
                } else {

                }
            }
        }
    }
}
