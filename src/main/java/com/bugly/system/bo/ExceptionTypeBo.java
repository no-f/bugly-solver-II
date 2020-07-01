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
public class ExceptionTypeBo {


    private String id;

    @JsonProperty("error_location")
    private String errorLocation;

    @JsonProperty("machine_address")
    private String machineAddress;

    private String state;

    private String tag;

    private Integer num;

    private String mtime;

}
