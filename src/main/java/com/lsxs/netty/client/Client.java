package com.lsxs.netty.client;

import com.lsxs.netty.NettyServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

public class Client {


    void start(String ip, int port){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        b.handler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel ch) throws Exception {

                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new StringEncoder());

                ch.pipeline().addLast(new NettyClientHandler());
            }
        });

        try {
            System.out.println("try connect to server");
            final ChannelFuture f =  b.connect(ip, port).sync();

            System.out.println("connect ok");
//            Thread.sleep(1*1000);
//            Thread.sleep(1*1000);




            f.channel().writeAndFlush("d8ca560e-52b2-46b2-a976-c22187c23cc0\r\n");



//            while(true){
//
//                channel.writeAndFlush("d8ca560e-52b2-46b2-a976-c22187c23cc0\r\n");
//                Thread.sleep(1000);
//            }

//            f.channel().eventLoop().scheduleAtFixedRate(new Runnable() {
//                public void run() {
//                    System.out.println("----"+Thread.currentThread().getName());
//
//                    f.channel().writeAndFlush("1111111111\r\n");
//                }
//            }, 1, 1, TimeUnit.SECONDS);
            System.out.println("sync()    " + Thread.currentThread().getName());

//            f.channel().writeAndFlush("d8ca560e-52b2-46b2-a976-c22187c23cc0\r\n");


            f.channel().closeFuture().sync();
            System.out.println("sync end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }


//
//        try {

//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }


    }


    public static void main(String[] args) {
        Client client = new Client();
        client.start("127.0.0.1", 18888);
    }
}
