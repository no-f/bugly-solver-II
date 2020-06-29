package com.bugly.system.rest;

import com.bugly.common.base.ApiResponse;
import com.bugly.system.dto.DealWithServerLogDto;
import com.bugly.system.dto.GetServerLogDto;
import com.bugly.system.service.ExceptionService;
import com.bugly.system.vo.CommonResult;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author no_f
 * @create 2020-06-16 17:46
 */
@RestController
@RequestMapping("/exception")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionRestController {

    private final ExceptionService exceptionService;

    @PostMapping("/save")
    public CommonResult<Boolean> saveServiceLog(@RequestBody JSONObject jsonParam) {
        return exceptionService.saveServiceLog(jsonParam);
    }

    @GetMapping("/findAll")
    public ApiResponse findAll() {
        //需要删除的
        return exceptionService.findAll();
    }

    @GetMapping("/list")
    public ApiResponse getExceptions(@RequestParam("page") int page,
                                     @RequestParam("page_size") int pageSize) {
        GetServerLogDto dto = new GetServerLogDto();
        dto.setPageNo(page);
        dto.setPageSize(pageSize);
        return exceptionService.getExceptions(dto);
    }

    @PostMapping("/deal_with")
    public CommonResult<Boolean> dealWith(@Validated DealWithServerLogDto dealWithServerLogDto) {
        return exceptionService.dealWith(dealWithServerLogDto);
    }

    @GetMapping("/detail_list")
//    public ApiResponse getDetails(@RequestParam("page") int page,
//                                  @RequestParam("page_size") int pageSize) {
//        GetServerLogDto dto = new GetServerLogDto();
//        dto.setPageNo(page);
//        dto.setPageSize(pageSize);
//        return exceptionService.getDetails(dto);
//    }
    public ApiResponse getDetails() {
        return exceptionService.getDetailsAll();
    }
}
