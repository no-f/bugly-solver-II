package com.bugly.system.vo;

import lombok.Data;

/**
 * @author no_f
 * @since 2020-06-30
 */
@Data
public class BuglyDetailSearchVo {

    private String exceptionTypeId;
    private String currentCluster;
    private String serviceName;
    private String errorMessage;
    private String errorException;
    private String startTime;
    private String endTime;
    private Integer page;
    private Integer pageSize;
}
