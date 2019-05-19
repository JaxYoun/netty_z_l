package nettyprotobuf.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protobuf.generated.MyDataInfo;

/**
 * @author: Yang
 * @date: 2019/5/17 00:25
 * @description:
 */
public class ProtoServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        /*System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());*/

        MyDataInfo.MyMessage.DataType type = msg.getDataType();
        switch (type) {
            case PersonType: {
                MyDataInfo.Person person = msg.getPerson();
                System.out.println(person.getName());
                System.out.println(person.getAge());
                System.out.println(person.getAddress());
                break;
            }
            case DogType: {
                MyDataInfo.Dog dog = msg.getDog();
                System.out.println(dog.getName());
                System.out.println(dog.getLeg());
                break;
            }
            case CatType: {
                MyDataInfo.Cat cat = msg.getCat();
                System.out.println(cat.getName());
                System.out.println(cat.getCity());
                break;
            }
            default: {
                break;
            }
        }
    }

}
