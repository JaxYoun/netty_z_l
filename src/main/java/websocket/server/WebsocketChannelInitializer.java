package websocket.server;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author: Yang
 * @date: 2019/4/21 19:58
 * @description:
 */
public class WebsocketChannelInitializer extends io.netty.channel.ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 基于http协议的
        pipeline.addLast(new HttpServerCodec());

        // 块写的处理器
        pipeline.addLast(new ChunkedWriteHandler());

        // http的数据块聚合处理器
        pipeline.addLast(new HttpObjectAggregator(8192));

        // 负责WS有关的连接与消息处理，参数是公开给客户端的接口前缀
        pipeline.addLast(new WebSocketServerProtocolHandler("/websocket"));

        // 自定义的业务逻辑处理器
        pipeline.addLast(new WebsocketHandler());
    }

}