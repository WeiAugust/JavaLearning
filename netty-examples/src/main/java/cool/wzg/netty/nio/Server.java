package cool.wzg.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Server
 * @Description TODO
 * @Author wzg
 * @Date 2020/11/19 13:32
 * @Version 1.0
 **/
public class Server {


    static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            System.out.println("waiting for connection...");
            selector.select();
            System.out.println("received");
            Iterator<SelectionKey> selectKeys = selector.selectedKeys().iterator();
            while (selectKeys.hasNext()) {
                SelectionKey key = selectKeys.next();
                selectKeys.remove();;
//                pool.submit(new Thread(() -> handle(key)));
                handle(key);
            }
        }
    }

    private static void handle(SelectionKey key) {
        if (key.isAcceptable()) {
            System.out.println("Event: connection");
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel;
            try {
                socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(key.selector(), SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (key.isReadable()) {
            System.out.println("Event: read");
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            try {
                int len = socketChannel.read(buffer);
                if (len != -1) {
                    System.out.println("receive data: " + new String(buffer.array(), 0, len));
                }
                ByteBuffer bufferToWrite = ByteBuffer.wrap("Hello Client".getBytes());
                socketChannel.write(bufferToWrite);
                key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (key.isWritable()) {
            SocketChannel sc = (SocketChannel) key.channel();
            System.out.println("write事件");
            key.interestOps(SelectionKey.OP_READ);
            try {
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
