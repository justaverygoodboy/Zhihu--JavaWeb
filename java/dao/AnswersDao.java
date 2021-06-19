package dao;

public interface AnswersDao {
    public int addAnswer(String userId,String quesId,String content) throws Exception;
    public String getGoodAnswer(String userId) throws Exception;
    public int likeAnswer(String answerId,String userId) throws Exception;
    public int cancelLike(String answerId,String userId) throws Exception;
    public String checkLike(String answerId,String userId) throws Exception;
}
