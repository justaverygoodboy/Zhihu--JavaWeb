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

import dao.UserDao;
import utils.TokenSignVery;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String token = req.getHeader("Authorization");
        TokenSignVery verify = new TokenSignVery();
        String userId = verify.verify(token);
        if (!userId.isEmpty()){
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            UserDao user = new UserDao();
            String res = user.getUser(userId);
            out.write(res);
        }
    }
}


