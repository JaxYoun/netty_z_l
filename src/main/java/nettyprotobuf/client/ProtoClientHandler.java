package nettyprotobuf.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protobuf.generated.MyDataInfo;

/**
 * @author: Yang
 * @date: 2019/5/17 00:38
 * @description:
 */
public class ProtoClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("yang").setAge(30).setAddress("CD").build();
        ctx.writeAndFlush(person);
    }
}
