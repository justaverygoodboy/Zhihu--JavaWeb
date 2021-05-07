package dao;

import org.json.JSONException;
import utils.Rs2Json;

import java.io.UnsupportedEncodingException;
import java.sql.*;

public class AskDao {
    public int insQuestion(String title,String brief,String userId){
        int rs = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://121.5.149.107/zhihu?characterEncoding=utf8","root","Qbz95000*");
            String sql = "insert into questions(title,brief,userId) values ('"+title+"','"+brief+"',"+userId+")";
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


