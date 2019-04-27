package heartbeat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import multiclientsocket.client.ChatClientInitializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author: Yang
 * @date: 2019/4/27 11:27
 * @description:
 */
public class HertBeatClient {

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChatClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8082).sync();
            Channel channel = channelFuture.channel();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}
