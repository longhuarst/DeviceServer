package com.lsxs.netty;

public class NettyWorkerThread extends Thread{

    private String workerThreadName;

    NettyWorkerThread(Runnable target, String name){
        super(target, name);
        workerThreadName = name;
    }
}
