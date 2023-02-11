package book.service;

import book.dao.BookDAO;
import book.model.BookModel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class BookService {
    private final BookDAO bookDAO = new BookDAO();
    private final int pageSize = 20;

    public BookModel selectBookById(int id){
        return bookDAO.selectBookById(id);
    }

    public void delBookById(int id){
        bookDAO.delBookById(id);
    }

    public void updateBook(BookModel book){
        if(book !=null){
            bookDAO.updateBook(book);
        }
    }

    public List<BookModel> selectBooksByWord(String word){
        if(word!=null && !word.isEmpty()){
            word = "%"+word+"%";
            return bookDAO.selectBooksByWord(word);
        }
        return null;
    }

    public List<BookModel> selectBooksByPage(int pageNum) {
        if(pageNum >= 0){
            return bookDAO.selectBooksByPage((pageNum - 1) * pageSize, pageSize);
        }
        return null;
    }

    public void save(BookModel book) {
        if (book != null) {
            bookDAO.insertBook(book);
        }
    }

    public BookModel shotBookTemplate() {
        return new BookModel(null, "这是你的书", "", null, null, null, null, 0, "", "", "", "0", null, "", 0);
    }
}
