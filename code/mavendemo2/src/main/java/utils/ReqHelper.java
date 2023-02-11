package utils;

import javax.servlet.http.HttpServletRequest;

public class ReqHelper {
    private HttpServletRequest req;

    public ReqHelper(HttpServletRequest req) {
        this.req = req;
    }

    public String strParam(String s){
        return req.getParameter(s);
    }

    public Integer intParam(String s){
        Integer attr = null;
        String attrStr = req.getParameter(s);
        if(attrStr !=null && !attrStr.isEmpty()){
            attr = Integer.parseInt(attrStr);
        }
        return  attr;
    }
}
