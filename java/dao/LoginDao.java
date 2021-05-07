package dao;

import java.sql.*;

public class LoginDao {
    public String CheckUser(String user, String password){
        int flag = 0;
        String userId = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://121.5.149.107/zhihu","root","Qbz95000*");
            String sql = "select userId from users where (email ='"+user+"' or phone = '"+user+"') and password='"+password+"'";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                flag = 1;
                userId = rs.getString("userId");
            }
            rs.close();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        if(flag==1){
            return userId;
        }
        else {
            return "";
        }
    }
}

