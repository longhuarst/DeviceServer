package com.lsxs.netty;

import io.netty.channel.ChannelHandlerContext;

public class NettyWorkerRunnable implements Runnable{
    protected ChannelHandlerContext ctx;
    protected String msg;
    NettyWorkerRunnable(ChannelHandlerContext ctx, String msg){
        this.ctx = ctx;
        this.msg = msg;
    }

    public void run() {

    }
}
