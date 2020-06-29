package com.bugly.system.model;

import com.bugly.common.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author no_f
 * @create 2020-06-17 10:48
 */
@Data
@Accessors(chain = true)
public class ExceptionType extends DeletableDO {

    private Long id;
    private String errorLocation;
    private Integer num;
    private Integer state;

}
