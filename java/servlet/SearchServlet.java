package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONException;
import org.json.JSONObject;
import service.impl.QuestionsServiceImpl;
import utils.Req2Json;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private String content = "";
    private String res = "";
    private QuestionsServiceImpl questionsService = new QuestionsServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String param = Req2Json.Req2Json(req);
        try {
            JSONObject jsonObj = new JSONObject(param);
            content = jsonObj.getString("search");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        try {
            res = questionsService.searchQuestion(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.write(res);
    }
}

