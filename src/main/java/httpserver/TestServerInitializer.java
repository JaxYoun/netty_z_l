package httpserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author: Yang
 * @date: 2019/1/7 00:17
 * @description: 初始化器
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 连接一旦被注册，这个初始化器对象就会被创建，本方法就会被调用
     *
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //1.这里的pipeline代表的是一系列处理步骤所构成的处理链。
        ChannelPipeline pipeline = ch.pipeline();

        //2.将一个Netty内置处理器追加到pipeline的最后。
        pipeline.addLast("httpServerCodec", new HttpServerCodec());

        //3.将自定义的请求处理器追加到pipeline的最后
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());

        System.out.println("server start success and listen----->");
    }
}
