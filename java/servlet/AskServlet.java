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
@WebServlet("/postAsk")
public class AskServlet extends HttpServlet {
    private String title = "";
    private String brief = "";
    private String token = "";
    private int state = 0;
    private QuestionsService questionsService = new QuestionsServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String param = Req2Json.Req2Json(req);
        token = req.getHeader("Authorization");
        try {
            JSONObject jsonObj = new JSONObject(param);
            title = jsonObj.getString("title");
            brief = jsonObj.getString("brief");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        try {
            state = questionsService.askQuestion(token,title,brief);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (state>0){
            out.write("{\"success\":1}");
        }else {
            out.write("{\"success\":0}");
        }
    }
}


