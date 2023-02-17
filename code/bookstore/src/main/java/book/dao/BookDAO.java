package book.dao;

import book.model.BookModel;
import lombok.NoArgsConstructor;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBHelper;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class BookDAO {

    public Integer selectBooksCount(String word){
        String sql = "SELECT COUNT(b.book_id) count FROM chuangbie_book b,(SELECT book_id bid,CONCAT(book_name, IFNULL(author_name,''),IFNULL(description,''),IFNULL(keyword,'')) word FROM chuangbie_book) w WHERE b.book_id=w.bid AND w.word LIKE ? ";
        QueryRunner queryRunner = DBHelper.getQueryRunner();
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
        try {
            return DBHelper.getQueryRunner().query(sql,scalarHandler,word).intValue();
        } catch (SQLException e) {
            return null;
//            throw new RuntimeException(e);
        }
    }

    public void delBookById(long id){
        String sql = "DELETE FROM chuangbie_book WHERE book_id = ?";
        try {
            DBHelper.getQueryRunner().update(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(BookModel book){
        String sql = "UPDATE chuangbie_book SET book_name=?,author_name=?,attribution=?,ftype_id=?,stype_id=?,`status`=?,is_vip=?,description=?,cover_url=?,keyword=?,word_count=?,last_update_chapter_id=?,last_update_chapter_name=?,isRecommand=? WHERE book_id = ?";
        List<Object> paramList = new ArrayList<>();
        try {
            Field[] fields = book.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                paramList.add(field.get(book));
            }
            paramList.add(paramList.get(0));
            paramList.remove(0);
            DBHelper.getQueryRunner().update(sql, paramList.toArray(new Object[0]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BookModel selectBookById(long id){
        String sql = "SELECT * FROM chuangbie_book b where b.book_id = ?";
        QueryRunner queryRunner = DBHelper.getQueryRunner();
        BeanHandler<BookModel> beanHandler = new BeanHandler<>(BookModel.class,new BasicRowProcessor(new GenerousBeanProcessor()));
        try {
            return queryRunner.query(sql, beanHandler, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookModel> selectBooks(String word, int start, int size) {
        String sql = "SELECT b.* FROM chuangbie_book b,(SELECT book_id bid,CONCAT(book_name, IFNULL(author_name,''),IFNULL(description,''),IFNULL(keyword,'')) word FROM chuangbie_book) w WHERE b.book_id=w.bid AND w.word LIKE ? ORDER BY b.book_id DESC LIMIT ?,?";
        QueryRunner queryRunner = DBHelper.getQueryRunner();
        BeanListHandler<BookModel> beanListHandler = new BeanListHandler<>(BookModel.class,new BasicRowProcessor(new GenerousBeanProcessor()));
        try {

            List<BookModel> query = queryRunner.query(sql, beanListHandler, word, start, size);
            return query;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void insertBook(BookModel book) {
        String sql = "INSERT INTO chuangbie_book (book_name,author_name,attribution,ftype_id,stype_id,status,is_vip,description,cover_url,keyword,word_count,last_update_chapter_id,last_update_chapter_name,isRecommand) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        List<Object> paramList = new ArrayList<>();
        try {
            Field[] fields = book.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                paramList.add(field.get(book));
            }
            paramList.remove(0);
            DBHelper.getQueryRunner().update(sql, paramList.toArray(new Object[0]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
