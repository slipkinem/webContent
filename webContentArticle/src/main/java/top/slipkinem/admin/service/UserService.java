package top.slipkinem.admin.service;


import top.slipkinem.admin.po.User;

/**
 * Created by slipkinem on 2017/4/6.
 */
public interface UserService {
    User getUserByUserCode(String userCode);

    Integer insertUserRecord(User user);

    String encryptUserPassword(String password);

    String getUserNameByUserId(Integer userId);

    User login(User user);

    User register (User user);

    Boolean logout();
}
