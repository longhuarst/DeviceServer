package com.lsxs.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {



    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);

        System.out.println("收到数据"+new Date() + Thread.currentThread().getId());
//        ctx.channel().eventLoop().execute(new Runnable() {
//            public void run() {
//                System.out.println(Thread.currentThread().getId());
//                try {
//                    Thread.sleep(100*1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

//        ctx.channel().eventLoop().schedule(new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(1*1000);
//                    System.out.println("complete" + Thread.currentThread().getId());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 0, TimeUnit.SECONDS);
        //        ctx.channel().eventLoop().execute(new NettyWorkerRunnable(ctx, msg){
//            @Override
//            public void run() {
//                try {
//
//                    System.out.println("线程工作"+new Date() + Thread.currentThread().getId());
//                    Thread.sleep(1000);
//                    System.out.println("数据发送"+new Date());
//                    this.ctx.writeAndFlush(this.msg);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
}
