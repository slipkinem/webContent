package top.slipkinem.common.beans;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by slipkinem on 1/10/2018.
 */

@Data
public class PageBean<T> {
    private List<T> rows;

    private int pageNum;

    private int pageSize;

    private long total;

    public PageBean (PageInfo<T> pageInfo) {
        this.rows = pageInfo.getList();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.total = pageInfo.getTotal();
    }

}
