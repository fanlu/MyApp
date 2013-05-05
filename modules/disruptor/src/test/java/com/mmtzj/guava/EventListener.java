package com.mmtzj.guava;

import com.google.common.eventbus.Subscribe;

public class EventListener {
 
    public int lastMessage = 0;
 
    @Subscribe
    public void listen(OurTestEvent event) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        lastMessage = event.getMessage();
    }
 
    public int getLastMessage() {
        return lastMessage;
    }
}