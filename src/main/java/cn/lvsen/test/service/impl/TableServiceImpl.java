package cn.lvsen.test.service.impl;

import cn.lvsen.test.dao.TableDataMapper;
import cn.lvsen.test.model.TableData;
import cn.lvsen.test.model.TableDataExample;
import cn.lvsen.test.service.TableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by slipkinem on 2017/4/5.
 */

@Service
public class TableServiceImpl implements TableService {
    private static final Logger logger = LoggerFactory.getLogger(TableServiceImpl.class);

    @Autowired
    private TableDataMapper tabledataMapper;

    @Override
    public List<TableData> getTableData(Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<TableData> list = tabledataMapper.selectByExample(new TableDataExample());
        PageInfo<TableData> page = new PageInfo<TableData>(list);
        logger.info("total=> " + page.getTotal());
        return list;
    }

    @Override
    public Integer deleteTable(Integer id) {
        Integer code = tabledataMapper.deleteByPrimaryKey(id);
        logger.info("code=> " + code);
        return code;
    }

    @Override
    public Integer updateTable(TableData tableData) {
        Integer code = tabledataMapper.updateByPrimaryKey(tableData);
        logger.info("updateCode=> " + code);
        return code;
    }
}
