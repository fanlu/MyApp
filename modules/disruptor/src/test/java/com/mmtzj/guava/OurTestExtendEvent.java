package com.mmtzj.guava;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-5-5
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
public class OurTestExtendEvent extends OurTestEvent {
    public OurTestExtendEvent(int message) {
        super(message);
    }

    public int getMessage(){
        return super.getMessage() + 1;
    }
}
