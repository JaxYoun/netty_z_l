package nettyprotobuf.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import protobuf.generated.MyDataInfo;

/**
 * @author: Yang
 * @date: 2019/5/17 00:35
 * @description:
 */
public class ProtoClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());

        //此解码器用于将字节数组反序列化成对象，需要传入需要反序列化类型的实例
        //当有多中类型的实例需要传递时，这里要改为目录类的实例
        pipeline.addLast(new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());

        //编码器，用于将对象序列化为字节数组
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new ProtoClientHandler());
    }

}
