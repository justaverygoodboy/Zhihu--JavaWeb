package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import service.QuestionsService;
import service.impl.QuestionsServiceImpl;
import utils.Req2Json;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
    private String quesId = "";
    private QuestionsService questionsService = new QuestionsServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String param = Req2Json.Req2Json(req);
        try {
            JSONObject jsonObj = new JSONObject(param);
            quesId = jsonObj.getString("quesId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String res = null;
        try {
            res = questionsService.getDetail(quesId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.write(res);
    }
}


