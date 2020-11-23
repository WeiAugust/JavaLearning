package cool.wzg.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @ClassName Client
 * @Description TODO
 * @Author wzg
 * @Date 2020/11/19 13:48
 * @Version 1.0
 **/
public class Client {

    private Selector selector;

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.initClient("127.0.0.1", 9000);
        client.connect();
    }

    private void connect() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = this.selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.wrap("Hello server".getBytes());
                    channel.write(buffer);
                    channel.register(this.selector, SelectionKey.OP_READ);
                }else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int len = channel.read(buffer);
        if (len != -1) {
            System.out.println("receive data: " + new String(buffer.array(), 0, len));
        }
    }

    private void initClient(String ip, int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        this.selector = Selector.open();
        socketChannel.connect(new InetSocketAddress(ip, port));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }
}
