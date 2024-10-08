package top.soft.servlet;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;


@WebServlet(name = "servletDemo3", value = "/servlet3",loadOnStartup = 2)
public class ServletDemo3 implements Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("ServletDemo3 初始化");

    }


    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().write("ServletDemo3 Service");

    }


    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {
        System.out.println("ServletDemo3 销毁");
    }
}
