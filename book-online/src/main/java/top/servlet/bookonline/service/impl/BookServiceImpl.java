package top.servlet.bookonline.service.impl;

import top.servlet.bookonline.dao.BookDao;
import top.servlet.bookonline.dao.impl.BookDaoImpl;
import top.servlet.bookonline.entity.Book;
import top.servlet.bookonline.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
private final BookDao bookDao = new BookDaoImpl();

public List<Book> getBooks(){
    return bookDao.selectAll();
}
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }
}