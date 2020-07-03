package com.bugly.system.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author no_f
 * @since 2020-07-02
 */
@Data
public class ExceptionReport implements Serializable {
    static final long serialVersionUID = 1L;

    private String id;

    private Date day;

    private Integer exceptionTotal;

    private Integer exceptionTypeNum;

    private Integer unsolvedExceptionNum;

    private Integer deleted;

    private Date ctime;

    private Date mtime;
}
