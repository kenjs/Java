package com.cy.driver.callout.netclient;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Created by haoy on 2015/1/31.
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();

        p.addLast("idleTimeoutHandler", new IdleStateHandler(0,10,0));
        p.addLast("heartbeatHandler", new HeartbeatHandler());
        p.addLast("dataSendHandler", new DataSendHandler());
    }
}
