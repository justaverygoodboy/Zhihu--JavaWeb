package dao;

public interface UsersDao {
    public String getUserById(String userId) throws Exception;
    public String getUserByEmail(String email) throws Exception;
    public String getUserByPhone(String phone) throws Exception;
    public String getUserByEmailorPhone(String eop,String password) throws Exception;
    public int updateAvatar(String userId,String avatar) throws Exception;
    public int insertUser(String username, String email, String phone, String password, String school) throws Exception;
}
