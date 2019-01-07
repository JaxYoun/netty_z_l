package socket.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author: Yang
 * @date: 2019/1/7 02:13
 * @description:
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 服务端收到客户端发来消息后会回调此函数，向客户端做出响应
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + msg);
        ctx.channel().writeAndFlush("from server" + UUID.randomUUID());
    }

    /**
     * 遇到异常就关闭连接
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
