package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DBHelper {

    private DBHelper() {}

    private static Properties properties;

    private static DataSource dataSource;

    static {
        properties = new Properties();
        try {
            String propertiesPath = DBHelper.class.getClassLoader().getResource("/").getPath() + "application.properties";
            properties.load(new FileInputStream(propertiesPath));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDateSource(){
        return dataSource;
    }

    //获取 Connection对象
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static QueryRunner getQueryRunner(){
        return new QueryRunner(dataSource);
    }

}
