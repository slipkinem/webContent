import cn.lvsen.test.model.User;
import cn.lvsen.test.service.UserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

/**
 * Created by slipkinem on 2017/4/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")

public class TestHello {
    private static Logger logger = Logger.getLogger(String.valueOf(TestHello.class));
    @Autowired
    private UserService helloWorldService;

    @Test
    public void test1() {
        User user = helloWorldService.getUserById("25568cee-1469-4581-99a3-2558b8201e9d");

        logger.info(JSON.toJSONString(user));
    }
}
