package com.lsxs.netty;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class ZKPoolConfig extends GenericObjectPoolConfig {

    public ZKPoolConfig(){
        setMinIdle(5);
        setTestOnBorrow(true);

    }
}
