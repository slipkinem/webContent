package top.slipkinem.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.slipkinem.admin.mapper.TableDataMapper;
import top.slipkinem.admin.po.TableData;
import top.slipkinem.admin.po.TableDataExample;
import top.slipkinem.admin.service.TableService;
import top.slipkinem.common.beans.PageBean;

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
    public PageBean<TableData> getTableData(Integer current, Integer size) {
        PageHelper.startPage(current, size);
        List<TableData> list = tabledataMapper.selectByExample(new TableDataExample());
        PageInfo<TableData> page = new PageInfo<>(list);
        return new PageBean<>(page);
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

    @Override
    public Integer addTable(TableData tableData) {
        Integer code = tabledataMapper.insertSelective(tableData);
        logger.info("insertTableCode=> " + code);
        return code;
    }
}
