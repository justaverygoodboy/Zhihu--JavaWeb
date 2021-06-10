package service.impl;

import dao.impl.QuestionsDaoImpl;
import service.QuestionsService;
import utils.TokenSignVery;

import java.util.ArrayList;

public class QuestionsServiceImpl implements QuestionsService {
    private String res = "";
    private String questions = "";
    private String quesInfo = "";
    private int state = 0;
    private QuestionsDaoImpl quesDao = new QuestionsDaoImpl();
    @Override
    public String getQuestions() {
        try {
            res = quesDao.getAllQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    @Override
    public int askQuestion(String token,String title,String brief) throws Exception {
        String userId = TokenSignVery.verify(token);
        if (!userId.isEmpty()) {
            state = quesDao.insertQuestion(title,brief,userId);
        }
        return state;
    }
    @Override
    public String searchQuestion(String content) throws Exception {
        String res = quesDao.searchQuestion(content);
        if (res.length()==2){
            res = "{\"results\":\"null\"}";
        }
        return res;
    }
    @Override
    public String getDetail(String quesId) throws Exception {
        ArrayList resDetail = new ArrayList(2);
        resDetail = quesDao.getQuestionDetail(quesId);
        questions = (String) resDetail.get(0);
        quesInfo = (String) resDetail.get(1);
        int len = quesInfo.length();
        return "{\"answers\":"+questions+","+quesInfo.substring(2,len-2)+"}";
    }
}
