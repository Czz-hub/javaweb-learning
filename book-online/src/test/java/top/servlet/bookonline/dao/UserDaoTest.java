package top.servlet.bookonline.dao;

import org.junit.jupiter.api.Test;
import top.servlet.bookonline.dao.impl.UserDaoImpl;
import top.servlet.bookonline.entity.Book;
import top.servlet.bookonline.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    void insertUser() {
        UserDao userDao = new UserDaoImpl();
        User user = User.builder().nickname("sst").password("123456").address("江苏南京")
                .avatar("https://www.forestpolice.net/_upload/article/images/c9/b5/e4e4f1cc480e889cadd76ee59e23/7ede84bc-a80e-423f-8c1b-98034a5947ba.jpg").account("sst").build();
        userDao.insertUser(user);
    }}


//    void insertBook() {
//        UserDao userDao = new UserDaoImpl();
//        Book book = Book.builder().id("sys").password("123456").address("江苏南京")
//                .avatar("https://www.forestpolice.net/2024/0924/c1697a97577/page.htm").account("sys").build();
//
//
//