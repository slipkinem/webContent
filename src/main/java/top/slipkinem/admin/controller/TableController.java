package top.slipkinem.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.slipkinem.admin.po.TableData;
import top.slipkinem.admin.service.TableService;
import top.slipkinem.common.beans.PageBean;
import top.slipkinem.common.beans.ResultBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by slipkinem on 2017/4/5.
 */
@RestController
@RequestMapping("/table")
public class TableController {
    private final static Logger logger = LoggerFactory.getLogger(TableController.class);

    @Autowired
    private TableService tableService;

    /**
     * 获取数据
     * @param current 页码
     * @param size 页量
     * @return map
     */
    @GetMapping("/getTable")
    public ResultBean<PageBean<TableData>> getTable(Integer current, Integer size) {
        return new ResultBean<>(tableService.getTableData(current, size));
    }


    /**
     * 根据ID删除一条数据
     * @param id 需要删除数据的ID
     * @return map
     */
    @RequestMapping(value = "deleteTable", method = RequestMethod.DELETE)
    @ResponseBody
    public Map deleteTable(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer code = tableService.deleteTable(id);
        map.put("errorCode", code);
        return map;
    }

    /**
     * 更新数据
     * @param tableData 更新的数据
     * @return map
     */
    @RequestMapping(value = "updateTable", method = RequestMethod.POST)
    @ResponseBody
    public Map updateTable(@RequestBody TableData tableData) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Integer code = tableService.updateTable(tableData);
            map.put("errorCode", code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 插入一条数据
     * @param tableData 数据
     * @return result
     */
    @RequestMapping("insertTableData")
    @ResponseBody
    public Map<String, Object> addDataTable(@RequestBody TableData tableData) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Integer code = tableService.addTable(tableData);
            map.put("errorCode", code);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("errorCode", 1);
            map.put("errorMessage", "服务器有误");
        }
        return map;
    }
}