package com.bugly.system.vo;

import java.io.Serializable;
import java.util.List;

public class PageObjects<E> implements Serializable {

    private static final long serialVersionUID = -5410104977998201659L;
    /**
     * 查询记录总数
     */
    private int sum;

    /**
     * 查询记录列表
     */
    private List<E> objectList;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public List<E> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<E> objectList) {
        this.objectList = objectList;
    }
}
