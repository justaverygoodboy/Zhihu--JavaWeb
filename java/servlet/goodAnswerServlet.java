package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import service.AnswerService;
import service.impl.AnswerServiceImpl;

import utils.Req2Json;
@WebServlet("/goodAnswers")
public class goodAnswerServlet extends HttpServlet {
    private String userId = "";
    private String res = "";
    private AnswerService answerService = new AnswerServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String param = Req2Json.Req2Json(req);
        try {
            JSONObject jsonObj = new JSONObject(param);
            userId = jsonObj.getString("userId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        try {
            res = answerService.getGoodAnswer(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        res = (res.equals(""))?"{\"data\":0}":"{\"data\":"+res+"}";
        out.write(res);
    }
}


