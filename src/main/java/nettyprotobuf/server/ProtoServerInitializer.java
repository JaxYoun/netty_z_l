package nettyprotobuf.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.*;
import protobuf.generated.MyDataInfo;

/**
 * @author: Yang
 * @date: 2019/5/17 00:15
 * @description:
 */
public class ProtoServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());

        //此解码器用于将字节数组反序列化成对象，需要传入需要反序列化类型的实例
        pipeline.addLast(new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());

        //编码器，用于将对象序列化为字节数组
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new ProtoServerHandler());
    }

}
