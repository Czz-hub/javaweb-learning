package top.servlet.bookonline.serlvet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import top.servlet.bookonline.entity.User;
import top.servlet.bookonline.service.UserService;
import top.servlet.bookonline.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    public void  init(ServletConfig config) throws ServletException {
        userService = new UserServiceImpl();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        System.out.println("remember"+remember);

        if (account == null || password == null || password.trim().isEmpty()) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("<script>alert('注册成功');location.href='/login';</script>");
            return;
        }

        User user =userService.signIn(account, password);
        if(user != null){

            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            if(remember !=null) {
                Cookie usernameCookie = new Cookie("username", account);
                Cookie passwordCookie = new Cookie("password", password);
                usernameCookie.setMaxAge(60*60*24*7);
                passwordCookie.setMaxAge(60*60*24*7);
                resp.addCookie(usernameCookie);
                resp.addCookie(passwordCookie);
            }
            resp.sendRedirect("/index");

        } else {

            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("<script>alert('账号或码错误');location.href='/';</script>");


    }
}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }}
