package book.servlet;

import book.model.BookModel;
import book.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int pageNum = getPageNum(req);
        List<BookModel> list = new ArrayList<>();
        req.setAttribute("normal",true);

        Object queryObj = req.getParameter("q");
        if(queryObj !=null){
            //如果查书就不分页
            String query = (String) queryObj;
            list=bookService.selectBooksByWord(query);
            req.setAttribute("normal",false);
        }else {
            Object methodObj = req.getParameter("method");
            if(methodObj !=null){
                String method = (String) methodObj;
                switch (method){
                    case "forePage":
                        if(pageNum--<=1){
                            pageNum = 1;
                        }
                        req.getSession().setAttribute("pageNum", pageNum);
                        break;
                    case "nextPage":
                        pageNum++;
                        req.getSession().setAttribute("pageNum", pageNum);
                        break;
                }
            }else {
                pageNum = 1;
                req.getSession().setAttribute("pageNum", pageNum);
            }
            //取得当前页面的数据
            list = bookService.selectBooksByPage(pageNum);
            System.out.println(pageNum);
            if (list == null || list.isEmpty()) {
                pageNum--;
                list = bookService.selectBooksByPage(pageNum);
                req.getSession().setAttribute("pageNum", pageNum);
            }
        }
        req.setAttribute("count",list.size());
        req.setAttribute("bookList", list);
        //完成获取数据的操作后，跳转到列表页面
        //这是服务器的行为，可以访问到受保护的 WEB-INF下的文件，同时不会改变浏览器的url地址，浏览器不会知道此次的跳转行为
        //response响应的结果也是jsp的结果，已经交给另一个servlet，即jsp文件处理
        req.getRequestDispatcher("book_list.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //提交
//        System.out.println("====开始====");
//        //设置字符集
//        req.setCharacterEncoding("UTF-8");
//        ReqUtil reqUtil = new ReqUtil(req);
//        resp.setContentType("text/http;charset=UTF-8");
//
//        PrintWriter writer = resp.getWriter();
//        writer.println("请稍等.............");
//
//        BookModel book = new BookModel();
//        book.setBookId(null);
//        book.setBookName(reqUtil.strParam("book_name"));
//        book.setAuthorName(reqUtil.strParam("author_name"));
//        book.setAttribution(reqUtil.intParam("attribution"));
//        book.setFtypeId(reqUtil.intParam("ftype_id"));
//        book.setStypeId(reqUtil.intParam("stype_id"));
//        book.setStatus(reqUtil.intParam("status"));
//        book.setIsVip(reqUtil.intParam("is_vip"));
//        book.setDescription(reqUtil.strParam("description"));
//        book.setCoverUrl(reqUtil.strParam("cover_url"));
//        book.setKeyword(reqUtil.strParam("keyword"));
//        book.setWordCount(reqUtil.strParam("word_count"));
//        book.setLastUpdateChapterId(null);
//        book.setLastUpdateChapterName("");
//        book.setIsRecommand(reqUtil.intParam("isRecommand"));
//
//        try {
//            bookService.save(book);
//            System.out.println("上传成功!");
//            writer.println("上传成功!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("上传失败!!!!!!!!!!!");
//            writer.println("上传失败!!!!!!!!!!!");
////            throw new RuntimeException(e);
//        }
//        System.out.println("====结束====");
    }

    public Integer getPageNum(HttpServletRequest req){
        Object pageNumObj = req.getSession().getAttribute("pageNum");
        int pageNum = 1;
        if (pageNumObj != null) {
            pageNum = (Integer) pageNumObj;
            if(pageNum <= 1){
                pageNum =1;
                req.getSession().setAttribute("pageNum", 1);
            }
        } else {
            req.getSession().setAttribute("pageNum", 1);
        }
        return pageNum;
    }
}
