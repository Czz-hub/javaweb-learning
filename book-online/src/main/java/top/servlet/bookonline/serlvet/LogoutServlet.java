package top.servlet.bookonline.serlvet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取当前会话
        HttpSession session = req.getSession(false);  // false 参数避免创建新的会话
        if (session != null) {
            // 销毁会话
            session.invalidate();
        }

        // 重定向到首页或登录页面
        resp.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用 doPost 方法来处理 GET 请求
        this.doPost(req, resp);
    }
}
