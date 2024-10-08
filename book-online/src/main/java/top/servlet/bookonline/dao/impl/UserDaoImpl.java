package top.servlet.bookonline.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.servlet.bookonline.dao.UserDao;
import top.servlet.bookonline.entity.User;
import top.servlet.bookonline.util.JdbcUtil;
import top.servlet.bookonline.util.Md5Util;

public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtil.getDataSource());

    // 插入用户
    @Override
    public int insertUser(User user) {
        String sql = "INSERT INTO t_user(account, password, nickname, avatar) VALUES (?, ?, ?, ?)";
        // 对密码进行MD5加密
        String encryptedPassword = Md5Util.crypt(user.getPassword());
        return jdbcTemplate.update(sql, user.getAccount(), encryptedPassword, user.getNickname(), user.getAvatar());
    }

    // 查找用户（登录时使用账号和密码）
    @Override
    public User findUser(User userDto) {
        try {
            String sql = "SELECT * FROM t_user WHERE account = ? AND password = ?";
            // 对用户输入的密码进行MD5加密再进行查询
            String encryptedPassword = Md5Util.crypt(userDto.getPassword());
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
                    userDto.getAccount(), encryptedPassword);
        } catch (DataAccessException e) {
            // 查找不到用户时返回null，不打印堆栈信息，避免过多日志
            return null;
        }
    }

    // 通过账号查找用户（用于注册时判断用户是否已存在）
    @Override
    public User findByAccount(String account) {
        try {
            String sql = "SELECT * FROM t_user WHERE account = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), account);
        } catch (DataAccessException e) {
            // 查找不到用户时返回null
            return null;
        }
    }
}