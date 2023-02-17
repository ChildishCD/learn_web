package china.servlet;

import china.dao.ChinaDAO;
import china.model.ChinaModel;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ChinaServlet", value = "/China")
public class ChinaServlet extends HttpServlet {
    ChinaDAO dao = new ChinaDAO();
    Map<String, Object> data = new HashMap<>();

    //设置其他客户端的访问许可
    private void setHeader(HttpServletResponse resp) {
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
    }

    private void success(List<ChinaModel> list){
        data.put("code", 200);
        data.put("msg", "查询成功");
        data.put("data", list);
    }

    private void getProvinces(HttpServletRequest req, HttpServletResponse resp) {
        data.clear();
        List<ChinaModel> list = dao.selectProvince();
        success(list);
    }

    private void getCities(HttpServletRequest req, HttpServletResponse resp) {
        data.clear();
        String proIdStr = req.getParameter("proId");
        if(proIdStr ==null || proIdStr.isEmpty()){
            data.put("code", 1003);
            data.put("msg", "省份id不正确");
            data.put("data", null);
        }else {
            int proId = Integer.parseInt(proIdStr);
            List<ChinaModel> list = dao.selectCityByPid(proId);
            success(list);
        }
    }

    private void getAreas(HttpServletRequest req, HttpServletResponse resp) {
        data.clear();
        String cityIdStr = req.getParameter("cityId");
        if(cityIdStr ==null || cityIdStr.isEmpty()){
            data.put("code", 1004);
            data.put("msg", "区市id不正确");
            data.put("data", null);
        }else {
            int cityId = Integer.parseInt(cityIdStr);
            List<ChinaModel> list = dao.selectCityByPid(cityId);;
            success(list);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        setHeader(resp);

        String method = req.getParameter("method");
        if (method == null || method.isEmpty()) {
            data.put("code", 1001);
            data.put("msg", "缺少method参数");
            data.put("data", null);
        } else {
            try {
                Method dataMethod = this.getClass().getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
                dataMethod.setAccessible(true);
                dataMethod.invoke(this, req, resp);
            } catch (Exception e) {
                data.put("code", 1002);
                data.put("msg", "未找到'" + method + "'方法");
                data.put("data", null);
            }
        }

        resp.getWriter().println(JSON.toJSONString(data, JSONWriter.Feature.WriteMapNullValue));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
