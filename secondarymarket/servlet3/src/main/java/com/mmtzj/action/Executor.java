package com.mmtzj.action;

import javax.servlet.AsyncContext;
import java.io.PrintWriter;
import java.util.Date;

public class Executor implements Runnable {
        private AsyncContext ctx = null;

        public Executor(AsyncContext ctx) {
            this.ctx = ctx;
        }

        public void run() {
            try {
                //等待十秒钟，以模拟业务方法的执行
                Thread.sleep(10000);
                PrintWriter out = ctx.getResponse().getWriter();
                System.out.println(Thread.currentThread().getName() + new Date());
                out.println("业务处理完毕的时间：" + new Date() + ".");
                out.flush();
                ctx.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }