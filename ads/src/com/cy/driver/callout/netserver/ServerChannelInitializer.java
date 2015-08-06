package com.cy.driver.callout.netserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Created by haoy on 2015/2/2.
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast("idleStateHandler", new IdleStateHandler(60,0,0));
        p.addLast("serverHeartBeatHandler", new ServerHeartBeatHandler());
        p.addLast("dataReceivedHandler", new DataReceivedHandler());
    }
}
