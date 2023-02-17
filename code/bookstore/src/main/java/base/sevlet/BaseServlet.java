package base.sevlet;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class BaseServlet extends HttpServlet {
    //直接通过resp输出流
    protected void printState(HttpServletResponse resp, Map<String, Object> state) throws IOException {
        resp.getWriter().println(JSON.toJSONString(state, JSONWriter.Feature.WriteMapNullValue));
    }

    //设置同一的返回信息
    protected Map<String, Object> state(Integer code, String msg, Object obj) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("obj", obj);
        return map;
    }

    //成功
    protected void success(HttpServletResponse resp, Object obj) throws IOException {
        printState(resp, state(200, "success", obj));
    }

    protected void success(HttpServletResponse resp) throws IOException {
        success(resp, null);
    }

    //出错
    protected void error(HttpServletResponse resp, Integer code, String msg) throws IOException {
        printState(resp, state(code, msg, null));
    }

    //设置rep和resp的头信息
    protected void setHeader(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        /* 允许跨域的主机地址 */
        resp.setHeader("Access-Control-Allow-Origin", "*");//这里表示所有的域都可以接受
//        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");//这里设置请求的来源地址
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        resp.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        resp.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        resp.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
    }
}
