package cn.lvsen.test.controller;

import cn.lvsen.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by slipkinem on 2017/4/1.
 */
public class UserController {
    @Autowired
    private UserService userService;
}
