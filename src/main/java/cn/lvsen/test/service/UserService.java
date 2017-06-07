package cn.lvsen.test.service;


import cn.lvsen.test.po.User;

/**
 * Created by slipkinem on 2017/4/6.
 */
public interface UserService {
    User getUserByUserCode(String userCode);

    Integer insertUserRecord(User user);

    String encryptUserPassword(String password);

    String getUserNameByUserId(Integer userId);
}
