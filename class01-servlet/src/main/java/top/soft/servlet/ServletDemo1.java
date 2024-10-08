package top.soft.servlet;


import jakarta.servlet.*;

import java.io.IOException;


public class ServletDemo1 implements Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("ServletDemo1 初始化");

    }


    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.getWriter().write("ServletDemo1 Service 方法");

    }


    @Override
    public String getServletInfo() {
        return "";
    }


    @Override
    public void destroy() {
        System.out.println("Servlet 销毁");
    }
}
