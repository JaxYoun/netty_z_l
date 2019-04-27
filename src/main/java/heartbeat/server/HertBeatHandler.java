package heartbeat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author: Yang
 * @date: 2019/4/27 11:15
 * @description:
 */
public class HertBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            String eventType = null;
            switch (idleStateEvent.state()) {
                case READER_IDLE: {
                    eventType = "读空闲";
                    break;
                }
                case WRITER_IDLE: {
                    eventType = "写空闲";
                    break;
                }
                case ALL_IDLE: {
                    eventType = "读或写空闲";
                    break;
                }
                default: {
                    break;
                }
            }

            Channel channel = ctx.channel();
            System.out.println(channel.remoteAddress() + "---->" + eventType);
            channel.close();
        }
    }
}
