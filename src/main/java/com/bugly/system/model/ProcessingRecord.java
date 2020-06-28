package com.bugly.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bugly.common.dataobject.DeletableDO;
import lombok.Data;

/**
 * @author no_f
 * @create 2020-06-17 10:48
 */
@Data
public class ProcessingRecord extends DeletableDO {

    private Long id;
    private Long serviceLogId;
    private Long userId;
    private String remarks;

}
