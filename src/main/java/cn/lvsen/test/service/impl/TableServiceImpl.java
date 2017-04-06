package cn.lvsen.test.service.impl;

import cn.lvsen.test.dao.TableDataMapper;
import cn.lvsen.test.model.TableData;
import cn.lvsen.test.model.TableDataExample;
import cn.lvsen.test.service.TableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by slipkinem on 2017/4/5.
 */
@Service
public class TableServiceImpl implements TableService {
    private static Logger logger = Logger.getLogger(String.valueOf(Logger.class));

    @Autowired
    private TableDataMapper tabledataMapper;

    @Override
    public List<TableData> getTableData(Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<TableData> list = tabledataMapper.selectByExample(new TableDataExample());
        PageInfo<TableData> page = new PageInfo<TableData>(list);

        return list;
    }
}
