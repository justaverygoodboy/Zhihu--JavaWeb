package dao;

import java.util.ArrayList;

public interface QuestionsDao {
    public int insertQuestion(String title,String brief,String userId) throws Exception;
    public String getAllQuestion() throws Exception;
    public String searchQuestion(String content) throws Exception;
    public ArrayList getQuestionDetail(String quesId) throws Exception;
}
