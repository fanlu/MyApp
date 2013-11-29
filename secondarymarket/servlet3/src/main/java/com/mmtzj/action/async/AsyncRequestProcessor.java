package com.mmtzj.action.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;

public class AsyncRequestProcessor implements Runnable {

    private AsyncContext asyncContext;
    private int secs;

    public AsyncRequestProcessor() {
    }

    public AsyncRequestProcessor(AsyncContext asyncCtx, int secs) {
        this.asyncContext = asyncCtx;
        this.secs = secs;
    }

    @Override
    public void run() {
        System.out.println("Async Supported? "
                + asyncContext.getRequest().isAsyncSupported());
        longProcessing(secs);
        try {
            PrintWriter out = asyncContext.getResponse().getWriter();
            out.write("Processing done for " + secs + " milliseconds!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //完成异步线程处理 
        asyncContext.complete();
    }

    private void longProcessing(int secs) {
        // 休眠指定的时间 
        try {
            Thread.sleep(secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
} 