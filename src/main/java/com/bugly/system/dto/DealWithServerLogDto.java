package com.bugly.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author no_f
 * @create 2020-06-18 10:38
 */
@Data
@Accessors(chain = true)
public class DealWithServerLogDto implements Serializable {

    private static final long serialVersionUID = 1391340588728829310L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Integer state;

    @JsonProperty("user_id")
    private Long userId;

    private String remarks;

}
