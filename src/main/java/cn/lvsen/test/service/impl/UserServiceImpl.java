package cn.lvsen.test.service.impl;

import cn.lvsen.test.dao.UserMapper;
import cn.lvsen.test.model.User;
import cn.lvsen.test.model.UserExample;
import cn.lvsen.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by slipkinem on 2017/4/1.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserCode(String userCode) {
        List<User> userList = new ArrayList<User>();

        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserCodeEqualTo(userCode);
        userList = userMapper.selectByExample(userExample);

        if (userList.size() > 0) {
            return userList.get(0);
        }

        return null;
    }

    @Override
    public Integer insertUserRecord(User user) {
        return userMapper.insert(user);
    }

    @Override
    public String encryptUserPassword(String password) {
        String userPassword = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            userPassword = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return userPassword;
    }
}
