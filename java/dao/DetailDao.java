package dao;

import java.io.UnsupportedEncodingException;
import java.sql.*;

import org.json.JSONException;
import utils.Rs2Json;

public class DetailDao {
    public String getDetails(String quesId){
        String questions = "";
        String quesInfo = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://121.5.149.107/zhihu","root","Qbz95000*");
            String sql1 = "select username,school,avatar,content,good,timestamp from answers a join users u on a.userId=u.userId and a.quesId = "+quesId+" order by good,timestamp desc";
            String sql2 = "select title,brief from questions where quesId = "+quesId;
            PreparedStatement statement = conn.prepareStatement(sql1);
            ResultSet rs = statement.executeQuery();
            Rs2Json r2j = new Rs2Json();
            questions = r2j.resultSetToJson(rs);
            statement = conn.prepareStatement(sql2);
            rs = statement.executeQuery();
            quesInfo = r2j.resultSetToJson(rs);
            statement.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int len = quesInfo.length();
        return "{\"answers\":"+questions+","+quesInfo.substring(2,len-2)+"}";
    }
}

