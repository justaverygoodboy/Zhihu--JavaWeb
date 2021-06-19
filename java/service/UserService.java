package service;

public interface UserService {
    public boolean verifyUser(String token);
    public String signUser(String username,String password);
    public String getUserByToken(String token) throws Exception;
    public int updateUserAvatar(String userId,String avatar) throws Exception;
    public int existUser(String phone,String email) throws Exception;
    public int registerUSer(String username,String email,String phone,String password,String school) throws Exception;
}