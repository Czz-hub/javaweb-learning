package top.soft.class03response.pratice;

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

@WebServlet("/verifyCode")
public class VerifyCode extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 160;
        int height = 45;

        //创建验证码图片对象
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);

        //美化图片，创建画笔对象
        Graphics g = image.getGraphics();

        //设置画笔颜色
        g.setColor(Color.white);
        g.fillRect(0,0,width,height);
        String str = "1234567890ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz";

        //生成随机角标
        Random random = new Random();
        for(int i = 0;i <= 5;i++){
            int index = random.nextInt(str.length());

            char ch = str.charAt(index);
            //设置字体颜色
            Color color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
            g.setColor(color);
            Font font = new Font("Dialog",Font.BOLD,120);
            //填写验证码
            g.drawString(ch + " ", width / 5 * i,height / 3);

        }

        g.setColor(Color.cyan);
        for(int i = 0;i < 10;i ++){
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }

        ImageIO.write(image,"jpg",resp.getOutputStream());
    }
}