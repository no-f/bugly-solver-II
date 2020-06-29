package com.bugly.system.service;

import com.bugly.common.base.ApiResponse;
import com.bugly.system.bo.ServiceExceptionBo;
import com.bugly.system.dto.DealWithServerLogDto;
import com.bugly.system.dto.GetServerLogDto;
import com.bugly.system.vo.CommonResult;
import com.bugly.system.vo.PageResult;
import net.sf.json.JSONObject;

/**
 * @author no_f
 * @create 2020-06-18 11:25
 */
public interface ExceptionService {

    CommonResult<Boolean> saveServiceLog(JSONObject content);

    ApiResponse findAll();

    ApiResponse getExceptions(GetServerLogDto getServerLogDto);

    CommonResult<PageResult<ServiceExceptionBo>> getServiceLogs(GetServerLogDto getServerLogDto);

    CommonResult<Boolean> dealWith(DealWithServerLogDto dealWithServerLogDto);



}
