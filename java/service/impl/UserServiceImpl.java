package service.impl;

import dao.impl.UsersDaoImpl;
import service.UserService;
import utils.Md5Util;
import utils.TokenSignVery;
import java.security.NoSuchAlgorithmException;

public class UserServiceImpl implements UserService {
    private String userId="";
    private int state = 0;
    private String rs = "";
    private UsersDaoImpl usersDao = new UsersDaoImpl();
    @Override
    public boolean verifyUser(String token) {
        String userVerify = TokenSignVery.verify(token);
        return (userVerify == "")?false:true;
    }
    @Override
    public String signUser(String user,String password) {
        try {
            rs = usersDao.getUserByEmailorPhone(user,Md5Util.crypt(password));
        } catch (Exception e) {e.printStackTrace();}
        return (rs != "")?TokenSignVery.sign(rs):"error";
    }
    @Override
    public String getUserByToken(String token) throws Exception {
        if (token != null)
            userId = TokenSignVery.verify(token);
        if (!userId.isEmpty()){
            return usersDao.getUserById(userId);
        } else {
            return "{\"valid\":\"fail\"}";
        }
    }
    @Override
    public int updateUserAvatar(String userId, String avatar) throws Exception {
        state = usersDao.updateAvatar(userId,avatar);
        return state;
    }
    @Override
    public int existUser(String phone,String email) throws Exception {
        int state = 0;
        String phoneState = usersDao.getUserByPhone(phone);
        String emailState = usersDao.getUserByEmail(email);
        if(phoneState!="")
            state = 0;
        else if (emailState!="")
            state = -1;
        else
            state = 1;
        return state;
    }
    @Override
    public int registerUSer(String username, String email, String phone, String password, String school) throws Exception {
        int state = this.existUser(phone,email);
        if(state==1) {
            usersDao.insertUser(username,email,phone,Md5Util.crypt(password),school);
        }
        return state;
    }
}
