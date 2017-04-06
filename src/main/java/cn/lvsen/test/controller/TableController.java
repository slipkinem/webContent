package cn.lvsen.test.controller;

import cn.lvsen.test.model.TableData;
import cn.lvsen.test.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slipkinem on 2017/4/5.
 */
@Controller
@RequestMapping("table")
public class TableController {
    @Autowired
    private TableService tableService;

    @RequestMapping("getTable")
    @ResponseBody
    public Map getTable(Integer  current, Integer size) {
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
}
