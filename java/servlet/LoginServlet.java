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

import utils.TokenSignVery;
import dao.LoginDao;
import org.json.*;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String token = req.getHeader("Authorization");
        TokenSignVery verify = new TokenSignVery();
        String userVerify = verify.verify(token);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String res;
        if(userVerify == ""){
            res = "{\"success\":0}";
        }else {
            res = "{\"success\":1}";
        }
        out.write(res);
    }
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
        String user = "";
        String password = "";
        try {
            JSONObject jsonObj = new JSONObject(param);
            user = jsonObj.getString("user");
            password = jsonObj.getString("password");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LoginDao check = new LoginDao();
        String data = check.CheckUser(user,password);
        String res = "";
        if (data != ""){
            TokenSignVery signer = new TokenSignVery();
            res = "{\"token\":\""+signer.sign(data)+"\"}";
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(res);
    }
}


