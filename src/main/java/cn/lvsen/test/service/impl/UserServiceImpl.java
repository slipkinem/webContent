package cn.lvsen.test.service.impl;

import cn.lvsen.test.dao.UserMapper;
import cn.lvsen.test.model.User;
import cn.lvsen.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by slipkinem on 2017/4/1.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
