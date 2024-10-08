package top.servlet.bookonline.serlvet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/captcha")
public class CaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 100;
        int height = 50;

        // 创建图片对象
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();

        // 填充背景颜色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 生成随机验证码
        String captchaText = generateCaptchaText(5);
        req.getSession().setAttribute("captcha", captchaText); // 存储验证码到 session

        // 绘制验证码
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 28));
        g.drawString(captchaText, 15, 35);

        // 输出图片
        resp.setContentType("image/jpeg");
        ImageIO.write(bufferedImage, "JPEG", resp.getOutputStream());
    }

    private String generateCaptchaText(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captchaText = new StringBuilder();

        for (int i = 0; i < length; i++) {
            captchaText.append(chars.charAt(random.nextInt(chars.length())));
        }

        return captchaText.toString();
    }
}
