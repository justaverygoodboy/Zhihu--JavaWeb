package dao.impl;

import dao.UsersDao;
import org.json.JSONException;
import utils.JdbcUtils;
import utils.Rs2Json;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDaoImpl implements UsersDao {
    private String userId = "";
    private int rs = 0;
    @Override
    public String getUserById(String userId) throws Exception {
        String user = "";
        Connection conn = JdbcUtils.getConn();
        String sql = "select userId,username,school,avatar from users where userId="+userId;
        ResultSet rs = JdbcUtils.getQueryRs(conn,sql);
        user = Rs2Json.resultSetToJson(rs);
        JdbcUtils.close(conn);
        int len = user.length();
        return user.substring(1,len-1);
    }
    @Override
    public String getUserByEmail(String email) throws Exception {
        Connection conn = JdbcUtils.getConn();
        int flag = 0;
        String sql = "select userId from users where email='"+email+"'";
        ResultSet rs = JdbcUtils.getQueryRs(conn,sql);
        if(rs.next()){
            flag = 1;
            userId = rs.getString("userId");
        }
        JdbcUtils.close(conn);
        return flag==1?userId:"";
    }
    @Override
    public String getUserByPhone(String phone) throws Exception {
        Connection conn = JdbcUtils.getConn();
        int flag = 0;
        String sql = "select userId from users where phone='"+phone+"'";
        ResultSet rs = JdbcUtils.getQueryRs(conn,sql);
        if(rs.next()){
            flag = 1;
            userId = rs.getString("userId");
        }
        JdbcUtils.close(conn);
        return flag==1?userId:"";
    }
    @Override
    public String getUserByEmailorPhone(String eop, String password) throws Exception {
        Connection conn = JdbcUtils.getConn();
        int flag = 0;
        String sql = "select userId from users where (email ='"+eop+"' or phone = '"+eop+"') and password='"+password+"'";
        ResultSet rs = JdbcUtils.getQueryRs(conn,sql);
        if(rs.next()){
            flag = 1;
            userId = rs.getString("userId");
        }
        JdbcUtils.close(conn);
        return flag==1?userId:"";
    }
    @Override
    public int updateAvatar(String userId, String avatar) throws Exception {
        Connection conn = JdbcUtils.getConn();
        String sql = "update users set avatar='"+avatar+"' where userId="+userId;
        rs = JdbcUtils.doInsert(conn,sql);
        JdbcUtils.close(conn);
        return rs;
    }
    @Override
    public int insertUser(String username, String email, String phone, String password, String school) throws Exception {
        Connection conn = JdbcUtils.getConn();
        String sql = "insert into users(username,email,phone,password,school) values ('"+username+"','"+email+"','"+phone+"','"+password+"','"+school+"')";
        rs = JdbcUtils.doInsert(conn,sql);
        JdbcUtils.close(conn);
        return rs;
    }
}
