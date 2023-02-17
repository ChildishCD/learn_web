package book.service;

import book.dao.BookDAO;
import book.model.BookModel;
import lombok.NoArgsConstructor;
import utils.ParameterUtil;

import java.util.List;

@NoArgsConstructor
public class BookService {

    private final BookDAO bookDAO = new BookDAO();

    public BookModel selectBookById(long id) {
        return bookDAO.selectBookById(id);
    }

    public void delBookById(long id) {
        bookDAO.delBookById(id);
    }

    public void updateBook(BookModel book) {
        if (book != null) {
            bookDAO.updateBook(book);
        }
    }

    public List<BookModel> selectBooks(String word, String pageNumStr, String pageSizeStr) {
        if(ParameterUtil.checkNumbers(pageNumStr,pageSizeStr)){
            return selectBooks(word,Integer.parseInt(pageNumStr),Integer.parseInt(pageSizeStr));
        }
        return null;
    }

    public List<BookModel> selectBooks(String word, int pageNum, int pageSize) {
        if (word != null) {
            word = "%" + word + "%";
            return bookDAO.selectBooks(word, (pageNum - 1) * pageSize, pageSize);
        }
        return null;
    }

    public void saveBook(BookModel book) {
        if (book != null) {
            bookDAO.insertBook(book);
        }
    }

    public Integer selectBooksCount(String word) {
        if (word != null) {
            word = "%" + word + "%";
            return bookDAO.selectBooksCount(word);
        }
        return null;
    }

    public BookModel shotBookTemplate() {
        return new BookModel(null, "这是你的书", "", null, null, null, null, 0, "", "", "", "0", null, "", 0);
    }
}
