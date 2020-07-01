package com.bugly.system.dto;

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

    private String id;

    private String nickName;

    private Integer state;

    private String remark;

}
