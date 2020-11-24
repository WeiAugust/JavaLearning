package cool.wzg.netty.netty.test;

import cool.wzg.netty.netty.time.TimeServer;
import cool.wzg.netty.netty.time.TimeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @ClassName EchoServer
 * @Description TODO
 * @Author wzg
 * @Date 2020/11/23 22:58
 * @Version 1.0
 **/
public class EchoServer {


    public void init(int port) {

        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup workers = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, workers)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                            socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(20));
                            socketChannel.pipeline().addLast(new StringDecoder());
//                            socketChannel.pipeline().addLast(new StringEncoder());
                            socketChannel.pipeline().addLast(new EchoServerHandler());

                        }
                    });

            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            workers.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new TimeServer().init(8082);
    }
}
