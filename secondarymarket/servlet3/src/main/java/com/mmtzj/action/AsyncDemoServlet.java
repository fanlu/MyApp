package com.mmtzj.action;

import com.mmtzj.action.async.AppAsyncListener;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-6-1
 * Time: 下午6:02
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(urlPatterns = "/demo", asyncSupported = true)
public class AsyncDemoServlet extends HttpServlet {

    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("进入Servlet的时间：" + new Date() + ".");
        out.flush();

        //在子线程中执行业务调用，并由其负责输出响应，主线程退出
        AsyncContext ctx = req.startAsync();
        executor.submit(new Executor(ctx));
        ctx.addListener(new AppAsyncListener());
        out.println("结束Servlet的时间：" + new Date() + ".");
        out.flush();
//        out.close();
    }

}
