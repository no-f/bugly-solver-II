package com.bugly.system.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author no_f
 * @since 2020-07-03
 */
@Data
@Accessors(chain = true)
public class ServiceType implements Serializable {
    private String id;
    private String serviceName;
    private Integer deleted;
    /**
     * 创建时间
     */
    private Date ctime;
    /**
     * 最后更新时间
     */
    private Date mtime;
}
