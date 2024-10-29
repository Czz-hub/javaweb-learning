package com.example.class05ajaxaxios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/check")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.从请求参数中获取用户名
        String username = req.getParameter("username");
        //2.
        boolean flag = !"admin".equals(username);
        //3.响应查询结果
        resp.getWriter().write(String.valueOf(flag));
    }
}
