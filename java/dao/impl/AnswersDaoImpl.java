package dao.impl;

import dao.AnswersDao;
import utils.JdbcUtils;
import utils.Rs2Json;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswersDaoImpl implements AnswersDao {
    private int flag = 0;
    @Override
    public int addAnswer(String userId, String quesId, String content) throws Exception {
        int rs = 0;
        Connection conn = JdbcUtils.getConn();
        String sql = "insert into answers(userId,quesId,content) values ("+userId+","+quesId+",'"+content+"')";
        rs = JdbcUtils.doInsert(conn,sql);
        JdbcUtils.close(conn);
        return rs;
    }
    @Override
    public String getGoodAnswer(String userId) throws Exception {
        String res = "";
        Connection conn = JdbcUtils.getConn();
        String sql = "select U.userId,U.username,U.school,U.avatar,P.good,P.answerId,P.content,Q.quesId,Q.title from users U,questions Q,(select answerId,quesId,userId,good,content from answers where answerId in (select answerId from favorite where userId="+userId+")) as P where U.userId=P.userId and P.quesId=Q.quesId";
        ResultSet rs = JdbcUtils.getQueryRs(conn,sql);
        res = Rs2Json.resultSetToJson(rs);
        JdbcUtils.close(conn);
        return res;
    }
    @Override
    public int likeAnswer(String answerId, String userId) throws Exception {
        Connection conn = JdbcUtils.getConn();
        String sql = "insert into favorite values("+answerId+","+userId+")";
        int rs = JdbcUtils.doInsert(conn,sql);
        JdbcUtils.close(conn);
        return rs;
    }
    @Override
    public int cancelLike(String answerId, String userId) throws Exception {
        Connection conn = JdbcUtils.getConn();
        String sql = "delete from favorite where answerId="+answerId+" and userId="+userId;
        int rs = JdbcUtils.doInsert(conn,sql);
        return rs;
    }
    @Override
    public String checkLike(String answerId, String userId) throws Exception {
        Connection conn = JdbcUtils.getConn();
        String sql = "select answerId from favorite where userId="+userId+" and answerId="+answerId;
        ResultSet rs = JdbcUtils.getQueryRs(conn,sql);
        if(rs.next()){
            flag = 1;
            answerId = rs.getString("answerId");
        }
        JdbcUtils.close(conn);
        if(flag==1){
            flag = 0;
            return answerId;
        }else{
            return "";
        }
    }
}
