package service.impl;

import dao.AnswersDao;
import dao.impl.AnswersDaoImpl;
import service.AnswerService;
import utils.TokenSignVery;

public class AnswerServiceImpl implements AnswerService {
    private String userId = "";
    private int state = 0;
    private AnswersDao answersDao = new AnswersDaoImpl();
    @Override
    public int writeAnswer(String token, String quesId, String content) throws Exception {
        userId = TokenSignVery.verify(token);
        if (!userId.isEmpty()){
            state = answersDao.addAnswer(userId,quesId,content);
            if (state>0) state = 1;
        } else state = -1;
        return state;
    }

    @Override
    public String getGoodAnswer(String userId) throws Exception {
        if(userId.equals("")) return "";
        return answersDao.getGoodAnswer(userId);
    }
    @Override
    public int likeAnswer(String answerId, String userId) throws Exception {
        String exist = answersDao.checkLike(answerId,userId);
        if(exist.equals("")) {
            state = answersDao.likeAnswer(answerId, userId);
        }else{
            answersDao.cancelLike(answerId,userId);
            state = -1;
        }
        return state;
    }
}
