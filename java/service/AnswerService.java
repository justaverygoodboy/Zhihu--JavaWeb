package service;

public interface AnswerService {
    public int writeAnswer(String token,String quesId,String content) throws Exception;
    public String getGoodAnswer(String userId) throws Exception;
    public int likeAnswer(String answerId,String userId) throws Exception;
}
