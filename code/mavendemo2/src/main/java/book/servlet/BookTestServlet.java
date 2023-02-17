package book.servlet;

import book.model.BookModel;
import book.service.BookService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/book/test")
public class BookTestServlet extends HttpServlet {
    BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 允许跨域的主机地址 */
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");//这里设置请求的来源地址,该地址的客户端可以从这个地址获取信息
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        resp.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        resp.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        resp.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        List<BookModel> bookModelList = bookService.selectBooksByPage(1);
        Map<String, Object> map = new HashMap<>();
        //map的key整个项目组都要同一
        map.put("data", bookModelList);
        map.put("code", 200);
        map.put("msg", "传输成功");
        String s = JSON.toJSONString(map, JSONWriter.Feature.WriteMapNullValue);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().println(s);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 允许跨域的主机地址 */
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");//这里设置请求的来源地址,该地址的客户端可以从这个地址获取信息
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        resp.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        resp.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        resp.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        req.setCharacterEncoding("utf-8");
        String book_id = req.getParameter("book_id");
        Map<String, Object> map = new HashMap<>();
        if (book_id == null || book_id.isEmpty()) {
            map.put("data", null);
            map.put("code", 1001);//自定义错误码
            map.put("msg", "参数为空");
        }else {
            int  id = Integer.parseInt(book_id);
            BookModel bookModel = bookService.selectBookById(id);
            if(bookModel == null){
                map.put("data", bookModel);
                map.put("code", 404);
                map.put("msg", "未找到资源");
            }else {
                //map的key整个项目组都要同一
                map.put("data", bookModel);
                map.put("code", 200);
                map.put("msg", "传输成功");
            }
        }
        String s = JSON.toJSONString(map, JSONWriter.Feature.WriteMapNullValue);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().println(s);
    }
}
