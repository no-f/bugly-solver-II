package com.bugly.system.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author no_f
 * @create 2020-06-18 11:05
 */
@Data
@Accessors(chain = true)
public class ServiceExceptionBo {

    private String id;

    private String currentCluster;

    private String machineAddress;

    private String serviceName;

    private Integer threadId;

    private String errorMessage;

    private String errorLocation;

    private String errorException;

    private String triggerTime;

}
