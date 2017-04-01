package cn.lvsen.test.service.impl;

import cn.lvsen.test.dao.UserMapper;
import cn.lvsen.test.model.User;
import cn.lvsen.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by slipkinem on 2017/4/1.
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User getUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
