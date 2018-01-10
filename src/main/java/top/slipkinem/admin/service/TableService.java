package top.slipkinem.admin.service;


import top.slipkinem.admin.po.TableData;

import java.util.List;

/**
 * Created by slipkinem on 2017/4/5.
 */
public interface TableService {
    List<TableData> getTableData(Integer current, Integer pageSize);

    Integer deleteTable(Integer id);

    Integer updateTable(TableData tableData);
    Integer addTable(TableData tableData);
}
