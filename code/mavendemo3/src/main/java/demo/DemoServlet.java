package demo;

import novel.NovelServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //->获取路径,在网络项目中读取文件
        String contextPath = getServletContext().getContextPath();
        String realPath = getServletContext().getRealPath("/");
        String classPath = NovelServlet.class.getClassLoader().getResource("/").getPath();
        System.out.println(contextPath);//项目的主路经（相对） 或 服务器中配置的网络根路径
        // /mavendemo3
        System.out.println(realPath);//服务器运行的实际路径（绝对）-->在target文件下
        // C:\Users\Administrator\Desktop\learn_web\code\mavendemo3\out\artifacts\mavendemo3_war_exploded\
        System.out.println(classPath);//编译后存 class 的绝对路径路径 //编译后的properties文件也在这个路径下
        // /C:/Users/Administrator/Desktop/learn_web/code/mavendemo3/out/artifacts/mavendemo3_war_exploded/WEB-INF/classes/

        //->获取路径2
        req.getScheme();//协议
        req.getServerName();//服务器地址
        req.getServerPort();//服务器端口号
        req.getContextPath();//项目入口(根)
        String servletPath = req.getServletPath();//访问的servlet配置的访问地址
        String requestURI = req.getRequestURI();//统一资源标识符
        StringBuffer requestURL = req.getRequestURL();//统一资源定位符
        System.out.println(servletPath);// /demo
        System.out.println(requestURI);//   /mavendemo3/demo
        System.out.println(requestURL);//   http://localhost:8080/mavendemo3/demo


        //->xml变量(一般不用,用配置文件)
        //获取设置的全局变量
        String email = getServletContext().getInitParameter("email");
        System.out.println(email);
    }
}
