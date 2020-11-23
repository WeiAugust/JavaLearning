package cool.wzg.netty.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName TimeServerHandler
 * @Description TODO
 * @Author wzg
 * @Date 2020/11/23 21:04
 * @Version 1.0
 **/
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf) msg;
//        byte[] req = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(req);
//        String body = new String(req, "UTF-8");
        String body = (String) msg;

        System.out.println("The time server receive order: "+ body + "; the count is: "+ (++counter));

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)? LocalDateTime.now().toString()+System.getProperty("line.separator") :"BAD ORDER";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
