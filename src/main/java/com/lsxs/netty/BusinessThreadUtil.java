package com.lsxs.netty;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.*;

public class BusinessThreadUtil {

    private static ExecutorService excutor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(100000));

    public static void doBusiness(ChannelHandlerContext ctx, String msg, Runnable runnable){
        //异步线程处理
        excutor.submit(runnable);
    }
}
