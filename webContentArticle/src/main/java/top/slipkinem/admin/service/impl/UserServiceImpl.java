package top.slipkinem.admin.service.impl;

import top.slipkinem.admin.mapper.UserMapper;
import top.slipkinem.admin.po.User;
import top.slipkinem.admin.po.UserExample;
import top.slipkinem.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.slipkinem.common.utils.CheckUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static top.slipkinem.common.utils.CheckUtil.check;
import static top.slipkinem.common.utils.CheckUtil.notNull;

/**
 * Created by slipkinem on 2017/4/1.
 */

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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
        Integer code;
        try {
            code = userMapper.insertSelective(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            code = 9;
        }
        return code;
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

    @Override
    public String getUserNameByUserId(Integer userId) {
        String username = null;
        try {
            User user = userMapper.selectByPrimaryKey(userId);
            username = user.getUsername();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return username;
    }

    @Override
    public User login(User user) {
        notNull(user, "param.is.null");
        // 已有的用户
        User existUser = this.getUserByUserCode(user.getUserCode());
        notNull(existUser, "user.error");
        // 加密密码
        String password = this.encryptUserPassword(user.getPassword());
        // 检查密码是否一样
        check(password.equals(existUser.getPassword()), "user.error");
        return existUser;
    }

    @Override
    public User register(User user) {
        notNull(user, "param.is.null");
        check(this.getUserByUserCode(user.getUserCode()) == null, "user.repeat.error");

        user.setPassword(this.encryptUserPassword(user.getPassword()));
        check(this.insertUserRecord(user) > 0, "user.register.error");
        return user;
    }
}
