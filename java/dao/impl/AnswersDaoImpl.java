package dao.impl;

import dao.AnswersDao;
import utils.JdbcUtils;
import utils.Rs2Json;

import java.sql.Connection;
import java.sql.SQLException;

public class AnswersDaoImpl implements AnswersDao {
    @Override
    public String getAnswerByQuesId(String quesId) {
        return null;
    }
    @Override
    public int addAnswer(String userId, String quesId, String content) throws Exception {
        int rs = 0;
        Connection conn = JdbcUtils.getConn();
        try {
            String sql = "insert into answers(userId,quesId,content) values ("+userId+","+quesId+",'"+content+"')";
            rs = JdbcUtils.doInsert(conn,sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JdbcUtils.close(conn);
        return rs;
    }
}
