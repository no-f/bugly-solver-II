package com.bugly.system.model;

import com.bugly.common.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author no_f
 * @create 2020-06-17 10:48
 */
@Data
@Accessors(chain = true)
public class ExceptionTypeUser extends DeletableDO implements Serializable {
    static final long serialVersionUID = 1L;

    private String id;
    private String exceptionTypeId;
    private String userId;
    private String remark;

}
