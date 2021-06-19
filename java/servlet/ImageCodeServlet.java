package servlet;

import utils.VerifyCodeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/imageCode")
public class ImageCodeServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("image/jpeg");
        res.setDateHeader("expires",-1);
        res.setHeader("Cache-Control","no-cache");
        res.setHeader("Pragma","no-cache");
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        req.getSession().setAttribute("verifyCode",verifyCode);
        VerifyCodeUtils.outputImage(100,35,res.getOutputStream(),verifyCode);
    }
}
