package Demo;

import book.model.BookModel;
import book.service.BookService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        //fastjson2 test
        JSONTest();

        compareTest();


//        List<Object> paramList = new ArrayList<>();
//        Field[] fields = book.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            try {
//                paramList.add(field.get(book));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        paramList.remove(0);
//        paramList.forEach(System.out::println);
    }

    private static void compareTest() {
        TreeSet<Integer> treeSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
                return -1;
            }
        });
        Collections.addAll(treeSet,2,2,5,4,6,3);
        System.out.println(treeSet);
    }

    private static void JSONTest() {
        BookService bookService = new BookService();

        /*
          FastJSON，没有构造方法、Setter，Getter会报错
         */

        //---
        BookModel book = new BookService().shotBookTemplate();
        //转为JSON格式
        String temp = JSON.toJSONString(book);
        //将JSON转为对象实例
        BookModel bookModel = JSON.parseObject(temp, BookModel.class);
        //将JSON转为JSON实例
        JSONObject jsonObject = JSON.parseObject(temp);
        BookModel bookModel1 = jsonObject.toJavaObject(BookModel.class);


        //---
        List<BookModel> bookModelList = Arrays.asList(book,book,book);
        //变为map
        Map<String,Object> map = new HashMap<>();
        map.put("list",bookModelList);
        map.put("code",200);
        //转为JSON格式
        String s = JSON.toJSONString(map);
        String ss = JSON.toJSONString(bookModelList);
        //将JSON转为对象实例
        var map1 = JSON.parseObject(s, Map.class);
        var bookModelList1 = JSON.parseArray(ss, BookModel.class);

        System.out.println( "end");

    }
}
