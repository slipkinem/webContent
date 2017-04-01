package cn.lvsen.test.controller;

import cn.lvsen.test.model.User;
import cn.lvsen.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by slipkinem on 2017/4/1.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/showUser")
    public String showUser (HttpServletRequest request, Model model) {
        String userId = request.getParameter("id");
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);

        return "showUser";
    }
}
