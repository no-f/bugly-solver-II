package com.bugly.system.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author no_f
 * @create 2020-06-18 10:38
 */
@Data
@Accessors(chain = true)
public class GetServerLogDto extends BaseDto {

    private static final long serialVersionUID = 1391340588728829310L;

    private String errorMessage;

    private String errorException;

    private String exceptionTypeId;

    private String serviceName;

    private String machinneAddress;

    private String currentCluster;

    private Date startTime;

    private Date endTime;

}
