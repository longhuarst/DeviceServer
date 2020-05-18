package com.lsxs.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

public class NettyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive   "+ Thread.currentThread().getName());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("userEventTriggered");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelWritabilityChanged");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("read data = "+msg);
        super.channelRead(ctx, msg);
    }

//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//        System.out.println("read data = "+msg);
//        super.channelRead0(ctx,msg);
//    }


    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("read0 data = "+msg);

        if (msg.equals("ssl")){
            TrustManagerFactory tmf = null;
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("C:\\Users\\86181\\AppData\\Local\\Packages\\CanonicalGroupLimited.Ubuntu20.04onWindows_79rhkp1fndgsc\\LocalState\\rootfs\\home\\lsxs\\ssl\\d8ca560e-52b2-46b2-a976-c22187c23cc0.jks"), "lsxlsx".toCharArray());
            tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(keyStore);
            SslContext sslContext = SslContextBuilder.forClient().trustManager(tmf).build();
            SSLEngine engine = sslContext.newEngine( ctx.channel().alloc());
            engine.setUseClientMode(true);

            ctx.channel().pipeline().addFirst("ssl", new SslHandler(engine));
            ctx.channel().pipeline().remove(NettyClientHandler.class);
            ctx.channel().pipeline().addLast(new SslNettyClientBusinessHandler());

            System.out.println("ssl channel init ok");

        }else{
            ctx.channel().pipeline().remove(NettyClientHandler.class);
            ctx.channel().pipeline().addLast(new SslNettyClientBusinessHandler());

            System.out.println("null channel init ok");
        }

//        System.out.println();
//
        ctx.writeAndFlush("ok\r\n");
//
//        ctx.channel().eventLoop().scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                ctx.channel().writeAndFlush("test\r\n");
//            }
//        }, 1, 1, TimeUnit.SECONDS);



    }

    @Override
    public boolean acceptInboundMessage(Object msg) throws Exception {
        System.out.println("acceptInboundMessage");
        return super.acceptInboundMessage(msg);
    }
}
