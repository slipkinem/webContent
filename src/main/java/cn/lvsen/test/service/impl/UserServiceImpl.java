//package cn.lvsen.test.service.impl;
//
//import cn.lvsen.test.dao.UserMapper;
//import cn.lvsen.test.model.User;
//import cn.lvsen.test.model.UserExample;
//import cn.lvsen.test.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.logging.Logger;
//
///**
// * Created by slipkinem on 2017/4/1.
// */
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    private static Logger logger = Logger.getLogger(String.valueOf(Logger.class));
//
//    @Resource
//    private UserMapper userMapper;
//
//    @Override
//    public User getUserById(String userId) {
//        return userMapper.selectByPrimaryKey(userId);
//    }
//
//    @Override
//    public User getUserByUserName(String userName) {
//        UserExample userExample = new UserExample();
//        userExample.createCriteria().andUsernameEqualTo(userName);
//
//        logger.info(String.valueOf(userExample));
//
//        List<User> list = userMapper.selectByExample(userExample);
//
//        logger.info(String.valueOf(list));
//        if (list.size() > 0) {
//            return list.get(0);
//        }
//        return null;
//    }
//
//    @Override
//    public Integer insertUser(User user) {
//        Integer integer = userMapper.insert(user);
//        return integer;
//    }
//}
