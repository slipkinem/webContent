//package cn.lvsen.test.controller;
//
//import cn.lvsen.test.model.User;
//import cn.lvsen.test.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Logger;
//
///**
// * Created by slipkinem on 2017/4/1.
// */
//@Controller // 声明controller
//@RequestMapping("user") // 映射地址栏
//public class UserController {
//    @Autowired // 自动装载Bean，自动实例化
//    private UserService userService;
//
//    private static Logger logger = Logger.getLogger(String.valueOf(Logger.class));
//
//    @RequestMapping("show_user")
//    public ModelAndView showUser(HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView("showUser");
//        String userId = request.getParameter("id");
//        User user = userService.getUserById("25568cee-1469-4581-99a3-2558b8201e9d");
//        modelAndView.addObject("user", user);
//
//        return modelAndView;
//    }
//
//    @RequestMapping("login")
//    public ModelAndView login(HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView("login");
//        return modelAndView;
//    }
//
//    @RequestMapping("loginUser")
//    @ResponseBody
//    public Map loginUser(String username, String password) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        logger.info("开始验证");
//        User user = userService.getUserByUserName(username);
//
//        if (null != user) {
//            String userPassword = user.getPassword();
//
//            if (userPassword.equals(password)) {
//                map.put("errorCode", 0);
//                map.put("errorMessage", "登录成功");
//            }
//
//            if (!userPassword.equals(password)) {
//                map.put("errorCode", 1);
//                map.put("errorMessage", "密码不对");
//            }
//        } else {
//            map.put("errorCode", 4);
//            map.put("errorMessage", "用户名不存在");
//        }
//
//        return map;
//    }
//
//    @RequestMapping("register")
//    public ModelAndView Register() {
//        ModelAndView modelAndView = new ModelAndView("register");
//        return modelAndView;
//    }
//
//    @RequestMapping("registerUser")
//    @ResponseBody
//    public Map RegisterUser(User user) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        Integer errorCode = userService.insertUser(user);
//        map.put("errorCode", errorCode);
//        map.put("errorMessage", "修改的话");
//        return map;
//    }
//}
