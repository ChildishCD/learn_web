package login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        // 获取用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 对数据进行校验
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            //留在当前的登录页面为异步请求
            writer.println("<h1 style='color:red'>登录失败</h1>");
            writer.println("<a href='/mavendemo3/login.html'>返回登录页面</a>");
        }else {
            //数据通过了校验,去数据库查询用户名和密码
            UserLoginModel user = new LoginDAO().selectByLogin(username, password);
            if(user == null){
                writer.println("<h1 style='color:red'>登录失败</h1>");
                writer.println("<a href='/mavendemo3/login.html'>返回登录页面</a>");
            }else {
                //将用户信息存入session
                req.getSession().setAttribute("currentUser",user);
                writer.println("<h1 style='color:green'>登录成功, 欢迎 "+user.toString()+"</h1>");
                writer.println("<a href='/mavendemo3/login.html'>返回登录页面</a>");
            }
        }
    }
}
