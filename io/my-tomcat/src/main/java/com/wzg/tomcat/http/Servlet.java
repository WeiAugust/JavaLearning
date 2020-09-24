package com.wzg.tomcat.http;/**
 * @Title:
 * @Package
 * @Description:
 * @author ceshi
 * @date 2020/9/2423:33
 */

/**
 * @ClassName Servlet
 * @Description TODO
 * @Author wzg
 * @Date 2020/9/24 23:33
 * @Version 1.0
 **/
public abstract class Servlet {

    public void service(Request request, Response response) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        }else {
            doPost(request, response);
        }


    }

    public abstract void doPost(Request request, Response response);

    public abstract void doGet(Request request, Response response);
}
