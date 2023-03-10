package login;


import user.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        Object currentUser = req.getSession().getAttribute("currentUser");
        if (currentUser != null) {
            UserLoginModel userModel = (UserLoginModel) currentUser;
            writer.println(userModel.toString());
        }else {
            resp.sendRedirect("login.html");
        }
    }
}
