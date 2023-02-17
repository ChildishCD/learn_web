package china.dao;

import china.model.ChinaModel;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class ChinaDAO {
    public List<ChinaModel> selectProvince() {
        QueryRunner queryRunner = DBHelper.getQueryRunner();
        String sql = "SELECT * FROM china WHERE pid = 0 and id!=0";
        BeanListHandler<ChinaModel> handler = new BeanListHandler<>(ChinaModel.class, new BasicRowProcessor(new GenerousBeanProcessor()));
        try {
            return queryRunner.query(sql, handler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ChinaModel> selectCityByPid(int id){
        QueryRunner queryRunner = DBHelper.getQueryRunner();
        String sql = "SELECT * FROM china WHERE pid =?";
        BeanListHandler<ChinaModel> handler = new BeanListHandler<>(ChinaModel.class, new BasicRowProcessor(new GenerousBeanProcessor()));
        try {
            return queryRunner.query(sql, handler,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
