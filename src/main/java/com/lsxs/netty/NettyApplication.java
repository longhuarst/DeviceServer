package com.lsxs.netty;

public class NettyApplication {







    public static void main(String[] args) throws Exception {
        NettyServer server = new NettyServer();

        server.start(18888);


    }
}
