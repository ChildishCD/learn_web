package login;


import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DBHelper;

public class LoginDAO {
    public UserLoginModel selectByLogin(String username, String password) {
        QueryRunner queryRunner = DBHelper.getQueryRunner();
        String sql = "select * from sm_user where username=? and password=? and isvalid=1";
        BeanHandler<UserLoginModel> beanHandler = new BeanHandler<>(UserLoginModel.class, new BasicRowProcessor(new GenerousBeanProcessor()));
        try {
            return queryRunner.query(sql, beanHandler, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
