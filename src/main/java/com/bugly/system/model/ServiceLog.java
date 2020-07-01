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
public class ServiceLog extends DeletableDO implements Serializable {

    private String id;
    private String exceptionTypeId;
    private String currentCluster;
    private String serviceName;
    private String machineAddress;
    private Date triggerTime;
    private int threadId;
    private String level;
    private int type;
    private String errorMessage;
    private String errorException;
    private String tag;

}
