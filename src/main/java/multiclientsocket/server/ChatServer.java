package multiclientsocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: Yang
 * @date: 2019/1/7 02:03
 * @description:
 * 1.一个server与多个client建立连接。
 * 2.每个client与server建立连接server都会打印日志。
 * 3.并且广播通知先连接的client新用户上线。
 * 4.其中任何一个client向server发送的消息，其他client都会收到。
 */
public class ChatServer {

    public static void main(String[] args) throws Exception {
        //1.两个时间循环组，eventLoop可以看作是一个无限循环，一直监听某个事件的发生，
        // 这种机制在服务端编程里面极为常见（其实连接的接受与逻辑处理可以由一个eventLoopGroup来完成，
        // 但是逻辑耦合度较高不推荐）。
        EventLoopGroup bossGroup = new NioEventLoopGroup();  //a.负责获取并接收远程连接，然后将连接分配给具体的workerEventiLoop去处理。
        EventLoopGroup workerGroup = new NioEventLoopGroup();  //b.负责处理bossEventLoop指派的连接。

        try {
            //2.它对服务端启动的相关操作做了一些封装，使服务ServerChanel的启动更容易。
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //3.给启动器配置事件循环组、通道、初始化器。
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChatServerInitializer());

            //4.给启动器绑定监听端口，以此端口对外提供服务
            ChannelFuture channelFuture = serverBootstrap.bind(8082).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            //5.优雅关闭事件循环组。
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
