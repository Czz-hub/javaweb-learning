package top.servlet.bookonline.service;

import top.servlet.bookonline.entity.User;

public interface UserService {
    /**
     * 用户登录功能
     *
     * @param account  账号
     * @param password 密码
     * @return user
     */
    User signIn(String account, String password);

    User findByAccount(String account);

    void register(User newUser);

    String hashPassword(String password);
}
