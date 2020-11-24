package cool.wzg.netty.netty.serial;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @ClassName SubReqClientHandler
 * @Description TODO
 * @Author wzg
 * @Date 2020/11/24 9:52
 * @Version 1.0
 **/
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(subReq(1));
    }

    private SubscribeReqProto.SubscribeReq subReq(int i) {
        SubscribeReqProto.SubscribeReq.Builder req = SubscribeReqProto.SubscribeReq.newBuilder();
        req.setAddress("北京");
        req.setPhoneNumber("12345");
        req.setProductName("netty");
        req.setSubReqID(i);
        req.setUserName("wzg");
        return req.build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response: [" + msg + "]");
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
