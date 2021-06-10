package service.impl;

import dao.impl.AnswersDaoImpl;
import service.AnswerService;
import utils.TokenSignVery;

public class AnswerServiceImpl implements AnswerService {
    private String userId = "";
    private int state = 0;
    private AnswersDaoImpl answersDao = new AnswersDaoImpl();
    @Override
    public int writeAnswer(String token, String quesId, String content) throws Exception {
        userId = TokenSignVery.verify(token);
        if (!userId.isEmpty()){
            AnswersDaoImpl answersDao = new AnswersDaoImpl();
            state = answersDao.addAnswer(userId,quesId,content);
            if (state>0){
                state = 1;
            }
        }else{
            state = -1;
        }
        return state;
    }
}
