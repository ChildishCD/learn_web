package com.javasm.book;

import com.javasm.utils.DBHelper;
import com.javasm.utils.JavasmDBUtil;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@NoArgsConstructor
public class BookDAO {
    public void save(BookModel book){
//        Connection connection = null;
//        PreparedStatement ps = null;
        try {
//            connection = DBHelper.getConnection();
            String sql = "INSERT INTO chuangbie_book (book_name,author_name,attribution,ftype_id,stype_id,status,is_vip,description,cover_url,keyword,word_count,last_update_chapter_id,last_update_chapter_name,isRecommand) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            ps=connection.prepareStatement(sql);
//            System.out.println(book);
            List<Object> paramList = new ArrayList<>();
            paramList.add(book.getBookName());
            paramList.add(book.getAuthorName());
            paramList.add(book.getAttr());
            paramList.add(book.getFtypeId());
            paramList.add(book.getStypeId());
            paramList.add(book.getStatus());
            paramList.add(book.getVip());
            paramList.add(book.getDescription());
            paramList.add(book.getCoverUrl());
            paramList.add(book.getKeyword());
            paramList.add(book.getWordCount());
            paramList.add(book.getChapterId());
            paramList.add(book.getChapterName());
            paramList.add(book.getIsRecommand());
            DBHelper.getQueryRunner().update(sql,paramList.toArray(new Object[0]));
//            ps.setInt(1,book.getBookId());
//            ps.setString(1,book.getBookName());
//            ps.setString(2,book.getAuthorName());
//            ps.setInt(3,book.getAttr());
//            ps.setInt(4,book.getFtypeId());
//            ps.setInt(5,book.getStypeId());
//            ps.setInt(6,book.getStatus());
//            ps.setInt(7,book.getVip());
//            ps.setString(8,book.getDescription());
//            ps.setString(9,book.getCoverUrl());
//            ps.setString(10,book.getKeyword());
//            ps.setString(11,book.getWordCount());
//            ps.setInt(12,book.getChapterId());
//            ps.setString(13,book.getChapterName());
//            ps.setInt(14,book.getIsRecommand());
//            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
