package com.bugly.system.model;

import com.bugly.common.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author no_f
 * @create 2020-06-17 10:48
 */
@Data
@Accessors(chain = true)
public class ExceptionType extends DeletableDO implements Serializable {

    static final long serialVersionUID = 1L;

    private String id;
    private String errorLocation;
    private Integer num;
    private Integer state;

}
