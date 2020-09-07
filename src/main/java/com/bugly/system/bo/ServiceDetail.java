package com.bugly.system.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author no_f
 * @since 2020-09-07
 */
@Data
@Accessors(chain = true)
public class ServiceDetail {
    private Integer num;
    private String name;
}
