package com.bugly.system.model;

import com.bugly.common.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author no_f
 * @create 2020-06-17 10:48
 */
@Data
@Accessors(chain = true)
public class ExceptionTypeUser extends DeletableDO {

    private Long id;
    private Long exceptionTypeId;
    private Long userId;

}
