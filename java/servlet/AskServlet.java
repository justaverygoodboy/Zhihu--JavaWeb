package servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AskDao;
import org.json.JSONException;
import org.json.JSONObject;
import utils.TokenSignVery;

@WebServlet("/postAsk")
public class AskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8"));
        StringBuffer sb = new StringBuffer("");
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        String param = sb.toString();
        String title = "";
        String brief = "";
        String token = req.getHeader("Authorization");
        try {
            JSONObject jsonObj = new JSONObject(param);
            title = jsonObj.getString("title");
            brief = jsonObj.getString("brief");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TokenSignVery verify = new TokenSignVery();
        String userId = verify.verify(token);
        if (!userId.isEmpty()) {
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            AskDao ask = new AskDao();
            int state = ask.insQuestion(title,brief,userId);
            if (state>0){
                out.write("{\"success\":1}");
            }else {
                out.write("{\"success\":0}");
            }
        }
    }
}


