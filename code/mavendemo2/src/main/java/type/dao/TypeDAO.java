package type.dao;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import type.model.FtypeModel;
import type.model.StypeModel;
import utils.DBHelper;

import java.util.List;

public class TypeDAO {
    public List<FtypeModel> selectAllFtype(){
        String sql = "SELECT * FROM chuangbie_book_ftype";
        QueryRunner queryRunner = DBHelper.getQueryRunner();
        BeanListHandler<FtypeModel> beanListHandler = new BeanListHandler<>(FtypeModel.class,new BasicRowProcessor(new GenerousBeanProcessor()));
        try {
            return queryRunner.query(sql,beanListHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<StypeModel> selectAllStype(){
        String sql = "SELECT * FROM chuangbie_book_stype";
        QueryRunner queryRunner = DBHelper.getQueryRunner();
        BeanListHandler<StypeModel> beanListHandler = new BeanListHandler<>(StypeModel.class,new BasicRowProcessor(new GenerousBeanProcessor()));
        try {
            return queryRunner.query(sql,beanListHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
