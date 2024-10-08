package top.soft.class04cookiesession.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
@WebServlet("/cookieDemo04")
public class CookieDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie:cookies) {
            if("username".equals(cookie.getName())){
                String value = cookie.getValue();
                String username = URLDecoder.decode(value, StandardCharsets.UTF_8);
                System.out.println("获取的用户名是" + username);
            }
        }
    }
}
