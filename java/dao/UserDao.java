package dao;

import java.io.UnsupportedEncodingException;
import java.sql.*;

import org.json.JSONException;
import utils.Rs2Json;

public class UserDao {
    public String getUser(String userId){
        String user = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://121.5.149.107/zhihu","root","Qbz95000*");
            String sql = "select userId,username,school,avatar from users where userId="+userId;
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            Rs2Json r2j = new Rs2Json();
            user = r2j.resultSetToJson(rs);
            statement.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int len = user.length();
        return user.substring(1,len-1);
    }
}

