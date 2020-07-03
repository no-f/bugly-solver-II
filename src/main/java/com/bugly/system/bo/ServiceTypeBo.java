package com.bugly.system.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author no_f
 * @since 2020-07-03
 */
@Data
@Accessors(chain = true)
public class ServiceTypeBo {

    private String id;
    private String serviceName;
    private String nickNames;
    private String webhookUrl;
}
