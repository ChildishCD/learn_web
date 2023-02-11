package book.servlet;

import book.model.BookModel;
import book.service.BookService;
import com.alibaba.fastjson.JSON;
import type.service.TypeService;
import utils.ReqHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/detail")
public class BookDetailServlet extends HttpServlet {
    private final BookService bookService = new BookService();
    private final TypeService typeService = new TypeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/http;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        //初始化类别
        String ftypeJSON = (String) req.getSession().getAttribute("ftypeJSON");
        if (ftypeJSON == null || ftypeJSON.isEmpty()) {
            ftypeJSON = JSON.toJSONString(typeService.selectAllFtype());
            req.getSession().setAttribute("ftypeJSON", ftypeJSON);
        }
        String stypeJSON = (String) req.getSession().getAttribute("stypeJSON");
        if (stypeJSON == null || stypeJSON.isEmpty()) {
            stypeJSON = JSON.toJSONString(typeService.selectAllStype());
            req.getSession().setAttribute("stypeJSON", stypeJSON);
        }

        //获取当前图书
        Integer id = new ReqHelper(req).intParam("id");
        BookModel bookModel = new BookModel();
        if (id != null) {
            bookModel = bookService.selectBookById(id);
        } else {
            bookModel = bookService.shotBookTemplate();
        }
        req.setAttribute("currentBookJSON", JSON.toJSONString(bookModel));
        //转到各自的页面
        String method = req.getParameter("method");
        if (method != null && !method.isEmpty()) {
            switch (method) {
                case "detail":
                    req.setAttribute("submit", false);
                    req.getRequestDispatcher("book_detail.jsp").forward(req, resp);
                    break;
                case "update":
                case "add":
                    req.setAttribute("submit", true);
                    req.getRequestDispatcher("book_detail.jsp").forward(req, resp);
                    break;
                case "del":
                    try {
//                        bookService.delBookById(id);
                        System.out.println("删除成功!");
                        writer.println("删除成功!");
                    }catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("删除失败!!!!!!!!!!!");
                        writer.println("删除失败!!!!!!!!!!!");
                    }
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/http;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String bookOne = req.getParameter("bookOne");
        BookModel updateBook = JSON.parseObject(bookOne, BookModel.class);
        if (updateBook.getBookId() == null) {
            System.out.println("===== start =====");
            try {
                bookService.save(updateBook);
                System.out.println("提交成功!");
                writer.println("提交成功!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("提交失败!!!!!!!!!!!");
                writer.println("提交失败!!!!!!!!!!!");
            }
            System.out.println("===== end =====");
        } else {
            System.out.println("===== start =====");
            try {
                bookService.updateBook(updateBook);
                System.out.println("修改成功!");
                writer.println("修改成功!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("修改失败!!!!!!!!!!!");
                writer.println("修改失败!!!!!!!!!!!");
            }
            System.out.println("===== end =====");
        }
    }
}
