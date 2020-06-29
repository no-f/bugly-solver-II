package com.bugly.system.controller;

import com.bugly.system.bo.ExceptionTypeBo;
import com.bugly.system.bo.ServiceExceptionBo;
import com.bugly.system.dto.DealWithServerLogDto;
import com.bugly.system.dto.GetServerLogDto;
import com.bugly.system.service.ExceptionService;
import com.bugly.system.vo.CommonResult;
import com.bugly.system.vo.PageResult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author no_f
 * @create 2020-06-16 17:46
 */
@RestController
@RequestMapping("/bugly/exception")
public class ExceptionController {

    @Autowired
    private ExceptionService exceptionService;

    @PostMapping("/save")
    public CommonResult<Boolean> saveServiceLog(@RequestBody JSONObject jsonParam) {
        return exceptionService.saveServiceLog(jsonParam);
    }

    @PostMapping("/findAll")
    public CommonResult<PageResult<ExceptionTypeBo>> findAll() {
        return exceptionService.findAll();
    }

    @PostMapping("/exception_list")
    public CommonResult<PageResult<ExceptionTypeBo>> getExceptions(@RequestBody @Validated GetServerLogDto getServerLogDto) {
        return exceptionService.getExceptions(getServerLogDto);
    }

    @PostMapping("/deal_with")
    public CommonResult<Boolean> dealWith(@Validated DealWithServerLogDto dealWithServerLogDto) {
        return exceptionService.dealWith(dealWithServerLogDto);
    }

    @PostMapping("/list")
    public CommonResult<PageResult<ServiceExceptionBo>> getServiceLogs(@RequestBody @Validated GetServerLogDto getServerLogDto) {
        return exceptionService.getServiceLogs(getServerLogDto);
    }
}
