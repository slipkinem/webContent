package cn.lvsen.test.controller;

import cn.lvsen.test.model.User;
import cn.lvsen.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by slipkinem on 2017/4/6.
 */

@Controller
@RequestMapping("api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody User user, HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User existUser = userService.getUserByUserCode(user.getUserCode());
            String encodeUserPassword = userService.encryptUserPassword(user.getPassword());

            if (null != existUser) {
                if (encodeUserPassword.equals(existUser.getPassword())) {
                    httpSession.setAttribute("userCode", user.getUserCode());
                    map.put("errorCode", 0);
                    map.put("errorMessage", "登录成功");
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
}
