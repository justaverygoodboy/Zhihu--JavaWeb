package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.AnswersDaoImpl;
import org.json.JSONException;
import org.json.JSONObject;
import service.impl.AnswerServiceImpl;
import utils.TokenSignVery;
import utils.Req2Json;
@WebServlet("/write")
public class WriteServlet extends HttpServlet {
    private int state = 0;
    private AnswerServiceImpl answerService = new AnswerServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String param = Req2Json.Req2Json(req);
        String quesId = "";
        String content = "";
        String token = req.getHeader("Authorization");
        try {
            JSONObject jsonObj = new JSONObject(param);
            quesId = jsonObj.getString("quesId");
            content = jsonObj.getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        try {
            state = answerService.writeAnswer(token,quesId,content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.write("{\"success\":"+state+"}");
    }
}


