package com.bugly.system.rest;

import com.bugly.common.annotation.RateLimitAspect;
import com.bugly.common.base.ApiResponse;
import com.bugly.system.dto.DealWithServerLogDto;
import com.bugly.system.dto.UpdateAlarmConfigDto;
import com.bugly.system.service.ExceptionService;
import com.bugly.system.vo.BuglyDetailSearchVo;
import com.bugly.system.vo.BuglySearchVo;
import com.bugly.system.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author no_f
 * rest
 * @create 2020-06-16 17:46
 */
@RestController
@RequestMapping("/exception")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionRestController {

    private final ExceptionService exceptionService;

    @PostMapping("/save")
    @RateLimitAspect
    public CommonResult<Boolean> saveServiceLog(@RequestBody JSONObject jsonParam) {
        return exceptionService.saveServiceLog(jsonParam);
    }

    @GetMapping("/findAll")
    public ApiResponse findAll(int page, int pageSize) {
        return exceptionService.findAll(page, pageSize);
    }

    @PostMapping("/search")
    @ResponseBody
    public ApiResponse search(@RequestBody BuglySearchVo buglySearchVo) {
        return exceptionService.findByCondition(buglySearchVo);
    }

    @PostMapping("/deal_with")
    @ResponseBody
    public ApiResponse dealWith(@RequestBody DealWithServerLogDto dealWithServerLogDto) {
        return exceptionService.dealWith(dealWithServerLogDto);
    }

    @GetMapping("/detail_list")
    public ApiResponse getDetails(String exceptionTypeId, int page, int pageSize) {
        return exceptionService.getDetailsAll(exceptionTypeId, page, pageSize);
    }

    @PostMapping("/detail_search")
    @ResponseBody
    public ApiResponse detailSearch(@RequestBody BuglyDetailSearchVo buglyDetailSearchVo) {
        return exceptionService.getDetailsByCondition(buglyDetailSearchVo);
    }

//    @GetMapping("/detail_search")
//    public ApiResponse dingDingDetailSearch(@RequestBody BuglyDetailSearchVo buglyDetailSearchVo) {
//        return exceptionService.getDetailsByCondition(buglyDetailSearchVo);
//    }

    @GetMapping("/findAllServiceType")
    public ApiResponse findAllServiceType(int page, int pageSize) {
        return exceptionService.findAllServiceType(page, pageSize);
    }

    @PostMapping("/update_alarm_config")
    @ResponseBody
    public ApiResponse updateAlarmConfig(@RequestBody UpdateAlarmConfigDto updateAlarmConfigDto) {
        return exceptionService.updateAlarmConfig(updateAlarmConfigDto);
    }


}
