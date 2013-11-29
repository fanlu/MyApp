package com.mmtzj.action.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppAsyncListener implements AsyncListener {

    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        System.out.println("AppAsyncListener onComplete");
        // 在这里可以做一些资源清理工作 
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        System.out.println("AppAsyncListener onError");
        //这里可以抛出错误信息 
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        System.out.println("AppAsyncListener onStartAsync");
        //可以记录相关日志 
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        System.out.println("AppAsyncListener onTimeout");
        ServletResponse response = asyncEvent.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.write("TimeOut Error in Processing");
    }

}