package top.servlet.bookonline.service.impl;

import top.servlet.bookonline.dao.UserDao;
import top.servlet.bookonline.dao.impl.UserDaoImpl;
import top.servlet.bookonline.entity.User;
import top.servlet.bookonline.service.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    // 用户登录
    @Override
    public User signIn(String account, String password) {
        // 查找用户
        User user = userDao.findByAccount(account);
        if (user != null) {
            // 对用户输入的密码进行加密
            String encryptedPassword = hashPassword(password);

            // 打印调试信息
            System.out.println("输入的加密密码: " + encryptedPassword);
            System.out.println("数据库中的密码: " + user.getPassword());

            // 密码比对
            if (user.getPassword().equals(encryptedPassword)) {
                return user; // 登录成功
            } else {
                System.out.println("密码错误");
            }
        } else {
            System.out.println("用户不存在");
        }
        return null; // 登录失败，用户不存在或密码错误
    }



    // 通过账号查找用户
    @Override
    public User findByAccount(String account) {
        return userDao.findByAccount(account);
    }

    // 用户注册
    @Override
    public void register(User newUser) {
        // 调用 UserDao 插入用户信息
        userDao.insertUser(newUser);
    }

    // 密码加密方法
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5算法不可用", e);
        }
    }
}
