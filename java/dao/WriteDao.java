package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteDao {
    public int writeAns(String userId,String quesId,String content){
        int rs = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://121.5.149.107/zhihu?characterEncoding=utf8","root","Qbz95000*");
            String sql = "insert into answers(userId,quesId,content) values ("+userId+","+quesId+",'"+content+"')";
            PreparedStatement statement = conn.prepareStatement(sql);
            rs = statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
