package com.mmtzj.disruptor;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-4-30
 * Time: 下午10:21
 * To change this template use File | Settings | File Templates.
 */
public enum MySingleton {
    INSTANCE;
    public static MySingleton getInstance(){
        return INSTANCE;
    }
}
