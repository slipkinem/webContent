package top.slipkinem.admin.controller;

import top.slipkinem.admin.po.User;
import top.slipkinem.admin.service.UserService;
import top.slipkinem.admin.util.VerifyCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by slipkinem on 2017/4/6.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录接口，点击登录
     * @param user user类的数据通过requestBody传过来
     * @param captcha 验证码，通过param传过来的，不想给user添加captcha的model
     * @param httpSession session管理，设置userCode和获取captcha
     * @return 给前端的值
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody User user, String captcha, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User existUser = userService.getUserByUserCode(user.getUserCode());
            String encodeUserPassword = userService.encryptUserPassword(user.getPassword());

            if (null != existUser) {
                if (encodeUserPassword.equals(existUser.getPassword())) {
                    if (httpSession.getAttribute("rand").equals(captcha)) {
                       httpSession.setAttribute("user", existUser);
                        map.put("errorCode", 0);
                        map.put("errorMessage", "登录成功");

                    } else {
                        map.put("errorCode", 1);
                        map.put("errorMessage", "验证码错误");
                    }

                } else {
                    map.put("errorCode", 3);
                    map.put("errorMessage", "密码错误");
                }

            } else {
                map.put("errorCode", 7);
                map.put("errorMessage", "用户不存在");
            }

        } catch (Exception e) {
            map.put("errorCode", 1);
            map.put("errorMessage", "未知错误");
        }
        return map;
    }

    /**
     * 注册接口
     * @param user 新增的user
     * @return 返回一个json字符串
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public Map registerUser(@RequestBody User user) {
        logger.info(user.getUsername());
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            User user1 = userService.getUserByUserCode(user.getUserCode());
            if (null != user1) {
                map.put("errorCode", 1);
                map.put("errorMessage", "用户已存在");
            } else {
                user.setPassword(userService.encryptUserPassword(user.getPassword()));
                Integer code = userService.insertUserRecord(user);
                if (code == 1) {
                    map.put("errorCode", 0);
                    map.put("errorMessage", "用户验证成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 前端获取验证码的接口
     * @param request 获取session
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
