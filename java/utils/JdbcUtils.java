package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    static {
        Properties properties = new Properties();
        try {
            File directory = new File("C:\\Users\\nzq\\IdeaProjects\\bihu\\src\\main\\java\\druid.properties");
            properties.load(new FileReader(directory));
        } catch (Exception e) {
            System.out.println("fail");
            e.printStackTrace();
        }
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection conn) throws SQLException {
        if (conn != null){
            conn.close();
        }
    }
    public static Connection getConn() throws Exception {
        Connection conn = dataSource.getConnection();
        return conn;
    }
    public static ResultSet getQueryRs(Connection conn, String sql) throws Exception {
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        return rs;
    }
    public static int doInsert(Connection conn, String sql) throws Exception {
        PreparedStatement statement = conn.prepareStatement(sql);
        int rs = statement.executeUpdate();
        return rs;
    }
}