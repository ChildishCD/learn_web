package user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    /**
     * ==>测试域对象
     */

    //直接输入链接
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //->1测试request(和服务器的一个响应)
        //每次请求都会建立新的request对象
        //当响应完毕,request自动失效
        HttpSession session = req.getSession();
        resp.setContentType("text/http;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        //向request中添加属性
        req.setAttribute("rname","root");
        req.setAttribute("user",shotUser());
    }

    //表单提交,工具提交
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //->2.1测试session(一个浏览器和服务器的会话)
        //会话是针对浏览器产生的,同一个浏览器会共享session
        //当浏览器关闭,超过默认或设定的session访问间隔
        //但是安卓和ios没有浏览器
        HttpSession session = req.getSession();
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        //向session中添加属性
        session.setAttribute("rname", "admin");
        session.setAttribute("user", shotUser());
        //获取session中的属性
        Object rname = session.getAttribute("rname");
        writer.println(rname);
        Object user = session.getAttribute("user");
        if (user != null) {
            UserModel userModel = (UserModel) user;
            writer.println(userModel.toString());
        }
        //当writer关闭后所有的response操作都不生效了
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private UserModel shotUser(){
        UserModel user = new UserModel(0,"Jack","jack1223");
        return user;
    }
}
