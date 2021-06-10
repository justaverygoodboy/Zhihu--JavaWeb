package dao;

public interface AnswersDao {
    public String getAnswerByQuesId(String quesId);
    public int addAnswer(String userId,String quesId,String content) throws Exception;
}
