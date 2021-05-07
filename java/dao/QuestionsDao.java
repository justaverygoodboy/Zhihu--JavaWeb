package dao;

import java.io.UnsupportedEncodingException;
import java.sql.*;

import org.json.JSONException;
import utils.Rs2Json;

public class QuestionsDao {
    public String getQuestions(){
        String questions = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://121.5.149.107/zhihu","root","Qbz95000*");
            String sql = "select quesId,title,brief from questions order by quesId desc";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            Rs2Json r2j = new Rs2Json();
            questions = r2j.resultSetToJson(rs);
            statement.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questions;
    }
}

