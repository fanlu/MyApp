package com.mmtzj.guava;

import com.google.common.eventbus.Subscribe;

public class NumberListener {
   
    private Number lastMessage;  
   
    @Subscribe
    public void listen(Number integer) {  
        lastMessage = integer;  
    }  
   
    public Number getLastMessage() {  
        return lastMessage;  
    }  
} 