package com.bugly.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author no_f
 * @create 2020-06-18 10:38
 */
@Data
@Accessors(chain = true)
public class GetServerLogDto extends BaseDto {

    private static final long serialVersionUID = 1391340588728829310L;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("current_cluster")
    private String currentCluster;

    private Integer state;

    @JsonProperty("user_id")
    private Long userId;

    private Integer type;

    private String key;

    private String tag;

    @JsonProperty("start_time")
    private Long startTime;

    @JsonProperty("end_time")
    private Long endTime;

}
