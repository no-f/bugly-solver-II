package com.bugly.system.service;

import com.bugly.common.base.ApiResponse;
import com.bugly.system.dto.DealWithServerLogDto;
import com.bugly.system.dto.UpdateAlarmConfigDto;
import com.bugly.system.vo.BuglyDetailSearchVo;
import com.bugly.system.vo.BuglySearchVo;
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

    /**
     * 获取所有异常类型列表
     * @return
     */
    ApiResponse findAll(int page, int pageSize);

    /**
     * 根据获取所有异常类型列表
     * @return
     */
    ApiResponse findByCondition(BuglySearchVo buglySearchVo);

    /**
     * 获取所有异常信息列表
     * @param exceptionTypeId
     * @return
     */
    ApiResponse getDetailsAll(String exceptionTypeId, Integer page, Integer pageSize);

    /**
     * 根据条件查询异常信息列表
     * @param buglyDetailSearchVo
     * @return
     */
    ApiResponse getDetailsByCondition(BuglyDetailSearchVo buglyDetailSearchVo);

    /**
     * 异常处理
     * @param dealWithServerLogDto
     * @return
     */
    ApiResponse dealWith(DealWithServerLogDto dealWithServerLogDto);


    /**
     * 获取所有服务类型列表
     * @return
     */
    ApiResponse findAllServiceType(int page, int pageSize);

    /**
     * 配置更新
     * @param updateAlarmConfigDto
     * @return
     */
    ApiResponse updateAlarmConfig(UpdateAlarmConfigDto updateAlarmConfigDto);



}
