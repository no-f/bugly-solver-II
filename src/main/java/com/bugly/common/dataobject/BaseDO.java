package com.bugly.common.dataobject;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体对象
 */
public class BaseDO implements Serializable {

    /**
     * 创建时间
     */
    private Date ctime;
    /**
     * 最后更新时间
     */
    private Date mtime;

    @Override
    public String toString() {
        return "BaseDO{" +
                "ctime=" + ctime +
                ", mtime=" + mtime +
                '}';
    }

    public Date getMtime() {
        return mtime;
    }

    public BaseDO setMtime(Date mtime) {
        this.mtime = mtime;
        return this;
    }

    public Date getCtime() {
        return ctime;
    }

    public BaseDO setCtime(Date ctime) {
        this.ctime = ctime;
        return this;
    }
}
