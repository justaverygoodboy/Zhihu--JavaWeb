package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Req2Json;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    private String userId = "";
    private String avatar = "";
    private String res = "";
    private int state = 0;
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String param = Req2Json.Req2Json(req);
        try {
            JSONObject jsonObj = new JSONObject(param);
            userId = jsonObj.getString("userId");
            avatar = jsonObj.getString("avatar");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        try {
           state = userService.updateUserAvatar(userId,avatar);
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (state == 1)
            res = "{\"success\":1}";
        else
            res = "{\"success\":0}";
        out.write(res);
    }
}