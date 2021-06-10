package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.impl.UserServiceImpl;
import org.json.*;
import utils.Req2Json;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private String res = "";
    private String user = "";
    private String password = "";
    private UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String token = req.getHeader("Authorization");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        res=(userService.verifyUser(token))?"{\"success\":1}":"{\"success\":0}";
        out.write(res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String param = Req2Json.Req2Json(req);
        try {
            JSONObject jsonObj = new JSONObject(param);
            user = jsonObj.getString("user");
            password = jsonObj.getString("password");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        res = "{\"token\":\""+userService.signUser(user,password)+"\"}";
        out.write(res);
    }
}


