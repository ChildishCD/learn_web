package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterUtil {
    private ParameterUtil(){

    }
    public static boolean checkNumbers(String... numStr){
        String pattern = "^\\d+(,\\d+)*$";
        Pattern r = Pattern.compile(pattern);
        for(String s : numStr){
            Matcher m = r.matcher(s);
            if(!m.matches()){
                return false;
            }
        }
        return true;
//        String str = "123,54458";
    }

    public static boolean checkLegal(Object... objects){
        for (Object o :objects){
            if(o == null){
                return false;
            }
            //判断字符合法
            if(o instanceof String){
                if (((String) o).isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }
}
