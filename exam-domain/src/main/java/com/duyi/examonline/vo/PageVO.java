package com.duyi.examonline.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/***
 * 记录装载自定义分页展示数据的
 */
public class PageVO implements Serializable {
    //    当前页
    private int curr;
    //    每个页面有多少条信息
    private int rows;
    //
    private Long total;
    //    最大页
    private int max;

    private int start;
    private int end;
    //    查询到的用户信息
    private List<?> data;
    //    查询的时候的过滤信息
    private Map<String,Object> condition;

    public int getCurr() {
        return curr;
    }

    public void setCurr(int curr) {
        this.curr = curr;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public PageVO(int curr, int rows, Long total, int max, int start, int end, List<?> data, Map<String, Object> condition) {
        this.curr = curr;
        this.rows = rows;
        this.total = total;
        this.max = max;
        this.start = start;
        this.end = end;
        this.data = data;
        this.condition = condition;
    }

    public PageVO() {
    }
}
