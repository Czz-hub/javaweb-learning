package top.servlet.bookonline.service;

import top.servlet.bookonline.entity.Book;

import java.util.List;

public interface BookService {

    /**
     * 查询所有图书
     *
     * @return List<Book>
     */
    List<Book> getBooks();

    /**
     * 根据 id 查询图书
     *
     * @param id id
     * @return 图书信息
     */
    Book getBookById(int id);
}
