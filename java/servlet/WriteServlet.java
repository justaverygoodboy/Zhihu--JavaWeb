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

import dao.WriteDao;
import org.json.JSONException;
import org.json.JSONObject;
import utils.TokenSignVery;

@WebServlet("/write")
public class WriteServlet extends HttpServlet {
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
        TokenSignVery verify = new TokenSignVery();
        String userId = verify.verify(token);
        System.out.println(userId);
        if (!userId.isEmpty()){
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            WriteDao wt = new WriteDao();
            int state = wt.writeAns(userId,quesId,content);
            System.out.println(state);
            if (state>0){
                out.write("{\"success\":1}");
            }else {
                out.write("{\"success\":0}");
            }
        }
    }
}


