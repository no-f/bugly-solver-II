package com.bugly.system.vo;

import lombok.Data;

/**
 * @author no_f
 * @since 2020-06-30
 */
@Data
public class BuglySearchVo {

    private Integer state;
    private String nickName;
    private String serviceName;
    private String errorLocaltion;
    private String machinneAddress;
    private Integer pageSize;
    private Integer page;

}
