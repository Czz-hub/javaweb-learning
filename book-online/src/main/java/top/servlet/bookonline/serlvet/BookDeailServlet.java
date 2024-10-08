package top.servlet.bookonline.serlvet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.servlet.bookonline.entity.Book;
import top.servlet.bookonline.service.BookService;
import top.servlet.bookonline.service.impl.BookServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/detail/*")
public class BookDeailServlet extends HttpServlet {
    private BookService bookService;

    public void init(ServletConfig config) throws ServletException {
        bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI().trim();
        int position = requestPath.lastIndexOf("/");
        String id = requestPath.substring(position + 1);
        Book book = bookService.getBookById(Integer.parseInt(id));
        req.setAttribute("book", book);
        req.getRequestDispatcher("/book_detail.jsp").forward(req, resp);
    }
}
