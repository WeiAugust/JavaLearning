package com.wzg.io.demo.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName AIOServer
 * @Description TODO
 * @Author wzg
 * @Date 2020/9/24 22:08
 * @Version 1.0
 **/
public class AIOServer {
    private final int port;

    public AIOServer(int port) {
        this.port = port;
        listen();
    }

    public static void main(String[] args) {
        int port = 8080;
        new AIOServer(port);
    }
    private void listen() {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(threadGroup);
            server.bind(new InetSocketAddress(port));
            System.out.println("listening: " + port);

            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    System.out.println("start to receive data");
                    try {
                        buffer.clear();
                        result.read(buffer).get();
                        buffer.flip();
                        result.write(buffer);
                        buffer.flip();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        try {
                            result.close();
                            server.accept(null, this);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("finished");
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("failed");
                }
            });
            try {
                Thread.sleep(Integer.MAX_VALUE);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
