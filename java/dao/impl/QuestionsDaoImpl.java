package dao.impl;

import dao.QuestionsDao;
import org.json.JSONException;
import utils.JdbcUtils;
import utils.Rs2Json;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionsDaoImpl implements QuestionsDao {
    private int rs = 0;
    private String questions = "";
    private String quesInfo = "";
    @Override
    public int insertQuestion(String title, String brief, String userId) throws Exception {
        Connection conn = JdbcUtils.getConn();
        try {
            String sql = "insert into questions(title,brief,userId) values ('"+title+"','"+brief+"',"+userId+")";
            rs = JdbcUtils.doInsert(conn,sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JdbcUtils.close(conn);
        return rs;
    }
    @Override
    public String getAllQuestion() throws Exception {
        Connection conn = JdbcUtils.getConn();
        try {
            String sql = "select quesId,title,brief from questions order by quesId desc";
            ResultSet rs = JdbcUtils.getQueryRs(conn,sql);
            questions = Rs2Json.resultSetToJson(rs);
        } catch (ClassNotFoundException | SQLException | UnsupportedEncodingException | JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JdbcUtils.close(conn);
        return questions;
    }
    @Override
    public String searchQuestion(String content) throws Exception {
        Connection conn = JdbcUtils.getConn();
        try {
            String sql = "select quesId,title,brief from questions where title like '%"+content+"%' order by quesId desc";
            ResultSet rs = JdbcUtils.getQueryRs(conn,sql);
            questions = Rs2Json.resultSetToJson(rs);
        } catch (ClassNotFoundException | SQLException | UnsupportedEncodingException | JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JdbcUtils.close(conn);
        return questions;
    }
    @Override
    public ArrayList getQuestionDetail(String quesId) throws Exception {
        Connection conn = JdbcUtils.getConn();
        String sql1 = "select username,school,avatar,content,good,timestamp from answers a join users u on a.userId=u.userId and a.quesId = "+quesId+" order by good,timestamp desc";
        String sql2 = "select title,brief from questions where quesId = "+quesId;
        PreparedStatement statement = conn.prepareStatement(sql1);
        ResultSet rs = statement.executeQuery();
        questions = Rs2Json.resultSetToJson(rs);
        statement = conn.prepareStatement(sql2);
        rs = statement.executeQuery();
        quesInfo = Rs2Json.resultSetToJson(rs);
        JdbcUtils.close(conn);
        ArrayList resList = new ArrayList(2);
        resList.add(questions);
        resList.add(quesInfo);
        return resList;
    }
}
