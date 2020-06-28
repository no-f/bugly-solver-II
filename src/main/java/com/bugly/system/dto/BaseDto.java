package com.bugly.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author no_f
 * @create 2020-06-18 10:45
 */
@Data
public class BaseDto implements Serializable {

    private static final long serialVersionUID = -6349345170667962883L;

    @JsonProperty("page_no")
    private Integer pageNo = 0;

    @JsonProperty("page_size")
    private Integer pageSize = 20;
}
