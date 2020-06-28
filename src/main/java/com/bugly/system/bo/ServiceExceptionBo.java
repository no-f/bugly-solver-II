package com.bugly.system.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author no_f
 * @create 2020-06-18 11:05
 */
@Data
@Accessors(chain = true)
public class ServiceExceptionBo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonProperty("current_cluster")
    private String currentCluster;

    @JsonProperty("machine_address")
    private String machineAddress;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("thread_id")
    private Integer threadId;

    @JsonProperty("error_message")
    private String errorMessage;

    @JsonProperty("error_location")
    private String errorLocation;

    @JsonProperty("error_exception")
    private String errorException;

    private String tag;

    private Integer num;

    private Integer count;

    @JsonProperty("trigger_time")
    private String triggerTime;

    private String ctime;

}
