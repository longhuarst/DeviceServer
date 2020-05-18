package com.lsxs.netty.RabbitMQ;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class RMQPoolConfig extends GenericObjectPoolConfig {

    public RMQPoolConfig(){
        setMinIdle(5);
        setTestOnBorrow(true);

    }
}
