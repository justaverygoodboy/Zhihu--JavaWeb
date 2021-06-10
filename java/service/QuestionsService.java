package service;

public interface QuestionsService {
    public String getQuestions();
    public int askQuestion(String token,String title,String brief) throws Exception;
    public String searchQuestion(String content) throws Exception;
    public String getDetail(String quesId) throws Exception;
}
