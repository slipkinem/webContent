import cn.lvsen.test.dao.TableDataMapper;
import cn.lvsen.test.service.TableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by slipkinem on 2017/4/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-mybatis.xml")

public class TestHello {
    private static Logger logger = LoggerFactory.getLogger(TestHello.class);
    @Autowired
    private TableService tableService;

    @Autowired
    private TableDataMapper tableDataMapper;

    @Test
    public void test1() {
        logger.info("打印粗来的");
    }
}
