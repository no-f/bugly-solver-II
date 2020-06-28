package com.bugly.system.vo;

import java.io.Serializable;
import java.util.List;


public final class PageResult<T> implements Serializable {


    private List<T> list;

    private Integer total;

    public List<T> getList() {
        return list;
    }

    public PageResult<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public PageResult<T> setTotal(Integer total) {
        this.total = total;
        return this;
    }

}
