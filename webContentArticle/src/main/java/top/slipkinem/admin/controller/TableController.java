package top.slipkinem.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.slipkinem.admin.po.TableData;
import top.slipkinem.admin.service.TableService;
import top.slipkinem.common.beans.PageBean;
import top.slipkinem.common.beans.ResultBean;

/**
 * Created by slipkinem on 2017/4/5.
 */
@RequestMapping("/table")
@RestController
public class TableController {
    @Autowired
    private TableService tableService;

    /**
     * 获取数据
     * @param current 页码
     * @param size 页量
     * @return map
     */
    @GetMapping
    public ResultBean<PageBean<TableData>> getTable(Integer current, Integer size) {
        return new ResultBean<>(tableService.getTableData(current, size));
    }


    /**
     * 根据ID删除一条数据
     * @param tableId 需要删除数据的ID
     * @return map
     */
    @DeleteMapping("/{tableId}")
    public ResultBean<Boolean> deleteTable(@PathVariable Integer tableId) {
        return new ResultBean<>(tableService.deleteTable(tableId));
    }

    /**
     * 更新数据
     * @param tableData 更新的数据
     * @return map
     */
    @PostMapping("/update")
    public ResultBean<Boolean> updateTable(@RequestBody TableData tableData) {
        return new ResultBean<>(tableService.updateTable(tableData));
    }

    /**
     * 插入一条数据
     * @param tableData 数据
     * @return result
     */
    @PostMapping
    public ResultBean<Integer> addDataTable(@RequestBody TableData tableData) {
        return new ResultBean<>(tableService.addTable(tableData));
    }
}