package top.slipkinem.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.slipkinem.admin.po.User;
import top.slipkinem.admin.service.UserService;
import top.slipkinem.admin.util.VerifyCodeUtils;
import top.slipkinem.common.beans.ResultBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static top.slipkinem.common.utils.CheckUtil.check;

/**
 * Created by slipkinem on 2017/4/6.
 */

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录接口，点击登录
     *
     * @param user        user类的数据通过requestBody传过来
     * @param captcha     验证码，通过param传过来的，不想给user添加captcha的model
     * @param httpSession session管理，设置userCode和获取captcha
     * @return 给前端的值
     */
    @PostMapping("login")
    public ResultBean<User> login(@RequestBody User user, String captcha, HttpSession httpSession) {
        check(httpSession.getAttribute("rand").equals(captcha), "captcha.error");
        return new ResultBean<>(userService.login(user));
    }

    /**
     * 注册接口
     *
     * @param user 新增的user
     * @return 返回一个json字符串
     */
    @PostMapping("register")
    public ResultBean<User> registerUser(@RequestBody User user) {
        return new ResultBean<>(userService.register(user));
    }

    @GetMapping("/logout")
    public ResultBean<Boolean> logout () {
        return new ResultBean<>(userService.logout());
    }

    /**
     * 前端获取验证码的接口
     *
     * @param request  获取session
     * @param response 设置返回值
     * @throws IOException response.getOutputStream出错会抛出IO异常
     */
    @RequestMapping(value = "loginVerifyCode", method = RequestMethod.GET)
    public void loginCaptch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 不要缓存
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");

        request.getSession().setAttribute("rand", verifyCode.toLowerCase());
        // 发验证码
        VerifyCodeUtils.outputImage(200, 80, response.getOutputStream(), verifyCode);
    }

}
