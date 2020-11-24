package cool.wzg.netty.netty.serial;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @ClassName SubReqServerHandler
 * @Description TODO
 * @Author wzg
 * @Date 2020/11/24 9:41
 * @Version 1.0
 **/
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        if ("wzg".equalsIgnoreCase(req.getUserName())) {
            System.out.println("Service accept client subscribe req: [" + req.toString()+"]");
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }

    }

    private SubscribeRespProto.SubscribeResp resp(int subReqID) {
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();

        builder.setSubReqID(subReqID);
        builder.setRespCode(0);
        builder.setDesc("netty book order succeed, 3 days later, sent to the designated address");
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
