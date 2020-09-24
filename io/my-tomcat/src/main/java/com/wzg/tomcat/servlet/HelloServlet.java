package com.wzg.tomcat.servlet;

import com.wzg.tomcat.http.Request;
import com.wzg.tomcat.http.Response;
import com.wzg.tomcat.http.Servlet;

/**
 * @ClassName HelloServlet
 * @Description TODO
 * @Author wzg
 * @Date 2020/9/24 23:36
 * @Version 1.0
 **/
public class HelloServlet extends Servlet {
    @Override
    public void doPost(Request request, Response response) {
        response.write("hello world");
    }

    @Override
    public void doGet(Request request, Response response) {
        this.doPost(request, response);
    }
}
