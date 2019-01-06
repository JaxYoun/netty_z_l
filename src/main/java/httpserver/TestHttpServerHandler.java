package httpserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author: Yang
 * @date: 2019/1/7 00:25
 * @description: 自定义逻辑处理器
 * 由于http1.1的keepAlive机制，一次请求之后可以保持一定时间的连接以便复用，而不是立即断开。
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取客户端请求，并处理，然后做出响应（但是和Servelet规范不同）
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.printf(">>>>>>>>>%s%n", msg.getClass().getName());
        System.out.printf(">>---%s%n", ctx.channel().remoteAddress());
        if(msg instanceof HttpRequest) {
            System.out.println("received request!");

            //0.请求信息获取
            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.printf("请求方法名：%s%n", httpRequest.method().name());
            URI uri = new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())) {
                System.out.println(uri.getPath());
                return;
            }

            //1.字节化返回字符
            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello World!", CharsetUtil.UTF_8);

            //2.实例化响应对象
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

            //3.设置响应头
            fullHttpResponse.headers()
                    .set(HttpHeaderNames.CONTENT_TYPE, "text/plain")
                    .set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            //4.将响应返回（单独的write只会将数据写入缓存，不会真正发送，所以必须要fush）
            ctx.writeAndFlush(fullHttpResponse);

            //5.手动关闭连接，避开http1.1的keepAlive机制
            ctx.channel().close();
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Registereds-----");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Active----");
        super.channelActive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler Added--------");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Inactive-------");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Unregistered------");
        super.channelUnregistered(ctx);
    }
}
