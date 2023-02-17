package book.servlet;

import base.sevlet.BaseServlet;
import book.model.BookModel;
import book.service.BookService;
import com.alibaba.fastjson2.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/book")
public class BookServlet extends BaseServlet {
    BookService bookService = new BookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求和返回的属性
        setHeader(req, resp);
        //根据method参数执行对应的方法
        String method = req.getParameter("method");
        if (method == null || method.isEmpty()) {
            error(resp, 1001, "缺少method参数");
        } else {
            try {
                Method dataMethod = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
                dataMethod.setAccessible(true);
                dataMethod.invoke(this, req, resp);
            } catch (Exception e) {
                error(resp, 1002, "未找到'" + method + "'方法");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String keyword = req.getParameter("keyword");
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");
        try {
            Integer total = bookService.selectBooksCount(keyword);
            List<BookModel> list = bookService.selectBooks(keyword, pageNum, pageSize);
            Map<String,Object> data = new HashMap<>();
            data.put("total",total);
            data.put("list",list);
            success(resp,data);
        }catch (Exception e){
            error(resp,1003,"查询出错");
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String book = req.getParameter("book");
        try {
            BookModel bookOne = JSON.parseObject(book, BookModel.class);
            bookService.saveBook(bookOne);
            success(resp);
        }catch (Exception e){
            error(resp,1004,"添加出错");
        }
    }

    private void del(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String book = req.getParameter("book");
        try {
            BookModel bookOne = JSON.parseObject(book, BookModel.class);
            bookService.delBookById(bookOne.getBookId());
            success(resp);
        }catch (Exception e){
            error(resp,1005,"删除出错");
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String book = req.getParameter("book");
        try {
            BookModel bookOne = JSON.parseObject(book, BookModel.class);
            bookService.updateBook(bookOne);
            success(resp);
        }catch (Exception e){
            error(resp,1003,"查询出错");
        }
    }

    private void bookById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String book = req.getParameter("book");
        try {
            BookModel bookOne = JSON.parseObject(book, BookModel.class);
            BookModel bookModel = bookService.selectBookById(bookOne.getBookId());
            success(resp,bookModel);
        }catch (Exception e){
            error(resp,1003,"查询出错");
        }
    }
}
