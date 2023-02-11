package Demo;

import book.model.BookModel;
import book.service.BookService;

public class Demo {
    public static void main(String[] args) {
        BookModel book = new BookService().shotBookTemplate();

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
}
