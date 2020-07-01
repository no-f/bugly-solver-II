package com.bugly.system.service;

import com.bugly.common.base.ApiResponse;
import com.bugly.system.dto.DealWithServerLogDto;
import com.bugly.system.dto.GetServerLogDto;
import com.bugly.system.vo.CommonResult;
import net.sf.json.JSONObject;

/**
 * @author no_f
 * @create 2020-06-18 11:25
 */
public interface ExceptionService {

    /**
     * 第三方 报警异常存储
     * @param content
     * @return
     */
    CommonResult<Boolean> saveServiceLog(JSONObject content);

    ApiResponse findAll();

    ApiResponse getExceptions(GetServerLogDto getServerLogDto);

    ApiResponse getDetails(GetServerLogDto getServerLogDto);

    ApiResponse getDetailsAll(String exceptionTypeId);

    /**
     * 异常处理
     * @param dealWithServerLogDto
     * @return
     */
    ApiResponse dealWith(DealWithServerLogDto dealWithServerLogDto);



}
