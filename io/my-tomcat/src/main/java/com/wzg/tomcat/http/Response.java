package com.wzg.tomcat.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName Response
 * @Description TODO
 * @Author wzg
 * @Date 2020/9/24 23:28
 * @Version 1.0
 **/
public class Response {

    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public Response(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }
    public void write(String content) {
        try {
            if (content == null || content.length() == 0) {
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(content.getBytes("UTF-8"))
            );
            response.headers().set("Content-Type", "text/html");
            ctx.write(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            ctx.flush();
            ctx.close();
        }
    }
}
