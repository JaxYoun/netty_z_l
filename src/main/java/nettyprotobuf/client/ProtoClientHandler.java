package nettyprotobuf.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protobuf.generated.MyDataInfo;

import java.util.Random;

/**
 * @author: Yang
 * @date: 2019/5/17 00:38
 * @description:
 */
public class ProtoClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("yang").setAge(30).setAddress("CD").build();
        ctx.writeAndFlush(person);*/

        int type = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage;
        switch (type) {
            case 0: {
                myMessage = MyDataInfo.MyMessage
                        .newBuilder()
                        .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                        .setPerson(MyDataInfo.Person.newBuilder().setName("yang").setAge(30).setAddress("CDCity"))
                        .build();
                break;
            }
            case 1: {
                myMessage = MyDataInfo.MyMessage
                        .newBuilder()
                        .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                        .setDog(MyDataInfo.Dog.newBuilder().setName("wangwang").setLeg(4))
                        .build();
                break;
            }
            default: {
                myMessage = MyDataInfo.MyMessage
                        .newBuilder()
                        .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                        .setCat(MyDataInfo.Cat.newBuilder().setName("mimi").setCity("NY"))
                        .build();
                break;
            }
        }
        ctx.writeAndFlush(myMessage);
    }
}
