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

import dao.DetailDao;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
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
        try {
            JSONObject jsonObj = new JSONObject(param);
            quesId = jsonObj.getString("quesId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(quesId);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        DetailDao dt = new DetailDao();
        String res = dt.getDetails(quesId);
        out.write(res);
    }
}


