package cn.lvsen.test.controller;

import cn.lvsen.test.model.TableData;
import cn.lvsen.test.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slipkinem on 2017/4/5.
 */
@Controller
@RequestMapping("api/table")
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
    @RequestMapping(value = "getTable", method = RequestMethod.GET)
    @ResponseBody
    public Map getTable(Integer current, Integer size) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<TableData> page = tableService.getTableData(current, size);

        if (null != page) {
            map.put("errorCode", 0);
            map.put("data", page);
        } else {
            map.put("errorCode", 1);
            map.put("errorMessage", "数据为空");
        }
        return map;
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