package top.slipkinem.admin.service;


import top.slipkinem.admin.po.TableData;
import top.slipkinem.common.beans.PageBean;

/**
 * Created by slipkinem on 2017/4/5.
 */
public interface TableService {
    PageBean<TableData> getTableData(Integer current, Integer pageSize);

    Boolean deleteTable(Integer id);

    Boolean updateTable(TableData tableData);
    Integer addTable(TableData tableData);
}
