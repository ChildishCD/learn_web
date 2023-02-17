package cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mycookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取所有的cookie
        Cookie[] cookies = req.getCookies();

        Cookie cookie1 = new Cookie("name", "Jack");//value 不能写中文
        Cookie cookie2 = new Cookie("pass", "jaja123");
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);

        req.getRequestDispatcher("cookie.jsp").forward(req,resp);
    }
}
