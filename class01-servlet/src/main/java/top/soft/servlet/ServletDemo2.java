package top.soft.servlet;


import jakarta.servlet.*;

import java.io.IOException;


public class ServletDemo2 implements Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("ServletDemo2 初始化");

    }


    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().write("ServletDemo2 Service");

    }


    @Override
    public String getServletInfo() {
        return "";
    }


    @Override
    public void destroy() {
        System.out.println("ServletDemo2 销毁");
    }
}
