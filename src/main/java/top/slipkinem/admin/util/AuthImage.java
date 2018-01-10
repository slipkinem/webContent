package top.slipkinem.admin.util;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by slipkinem on 2017/4/10.
 */
public class AuthImage extends HttpServlet implements Servlet {
    static final long serialVsersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response) {
//        进制图像缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

        HttpSession session = request.getSession(true);
        session.setAttribute("rand", verifyCode.toLowerCase());

        int w = 200, h = 80;
        try {
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
