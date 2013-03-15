package com.mmtzj.thrift.pool;

import org.apache.thrift.transport.TSocket;

public interface ConnectionProvider {
    /**
     * 取链接池中的一个链接
     *
     * @return
     */
    public TSocket getConnection();

    /**
     * 返回链接
     *
     * @param socket
     */
    public void returnCon(TSocket socket);
}