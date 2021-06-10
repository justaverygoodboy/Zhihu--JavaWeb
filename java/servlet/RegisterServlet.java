package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.impl.UserServiceImpl;
import org.json.*;
import utils.Req2Json;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private String res = "";
    private int state = 0;
    private String username = "";
    private String password = "";
    private String email = "";
    private String phone = "";
    private String school = "";
    private UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String param = Req2Json.Req2Json(req);
        try {
            JSONObject jsonObj = new JSONObject(param);
            username = jsonObj.getString("username");
            password = jsonObj.getString("password");
            email = jsonObj.getString("email");
            phone = jsonObj.getString("phone");
            school = jsonObj.getString("school");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        try {
            state = userService.registerUSer(username,email,phone,password,school);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(state==1)
            res = "{\"state\":\"success\"}";
        if(state==0)
            res = "{\"state\":\"p\"}";
        if(state==-1)
            res = "{\"state\":\"e\"}";
        out.write(res);
    }
}


