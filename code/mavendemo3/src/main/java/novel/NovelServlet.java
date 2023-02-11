package novel;

import user.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/novel")
public class NovelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //->3测试ServletContext(一个服务器)
        //上下文对象,服务器不关闭会一直存在,也叫application对象
        //不建议频繁读写,不安全 且 服务器的内存大小有限
        //不同浏览器可以共享
//        ServletContext servletContext = req.getServletContext();
        //也可以直接调用方法
//        ServletContext servletContext = getServletContext();
        //访问计数
        Object countObj = req.getServletContext().getAttribute("count");
        Integer count = 0;
        if (countObj != null) {
            count = (Integer) countObj;
        }
        req.getServletContext().setAttribute("count", ++count);
        //打印计数器
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>" + count + "</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //->2.2测试session对象跨servlet
        HttpSession session = req.getSession();
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        //从session对象中获取属性并输出
        Object user = session.getAttribute("user");
        if (user != null) {
            UserModel userModel = (UserModel) user;
            writer.write(userModel.toString());
        }
    }
}
