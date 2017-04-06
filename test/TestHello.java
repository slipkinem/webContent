import cn.lvsen.test.dao.TableDataMapper;
import cn.lvsen.test.model.TableData;
import cn.lvsen.test.service.TableService;
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
@ContextConfiguration(locations = "classpath:spring/spring-mybatis.xml")

public class TestHello {
    private static Logger logger = Logger.getLogger(String.valueOf(TestHello.class));
    @Autowired
    private TableService tableService;

    @Autowired
    private TableDataMapper tableDataMapper;

    @Test
    public void test1() {
        TableData tableData = new TableData();
        tableData.setAddress("南京市");
        tableData.setName("lvsen");
        tableData.setProvince("山西");
        tableData.setZip(1234556);
        tableData.setCity("先西安");
        for (int i = 0; i < 500; i++) {
            tableDataMapper.insert(tableData);
        }
    }
}
