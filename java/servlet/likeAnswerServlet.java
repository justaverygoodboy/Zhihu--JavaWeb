package servlet;

import org.json.JSONException;
import org.json.JSONObject;
import service.AnswerService;
import service.impl.AnswerServiceImpl;
import utils.Req2Json;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/like")
public class likeAnswerServlet  extends HttpServlet {
    private String answerId = "";
    private String userId = "";
    private AnswerService answerService = new AnswerServiceImpl();
    private int res = 0;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String param = Req2Json.Req2Json(req);
        try {
            JSONObject jsonObj = new JSONObject(param);
            answerId = jsonObj.getString("answerId");
            userId = jsonObj.getString("userId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            res = answerService.likeAnswer(answerId,userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String s = "";
        if(res==-1){
            s = "{\"success\":-1}"; //赞过，取消点赞
        }else if(res>0){
            s = "{\"success\":1}"; //点赞成功
        }else {
            s = "{\"success\":0}";//点赞失败
        }
        out.write(s);
    }
}
