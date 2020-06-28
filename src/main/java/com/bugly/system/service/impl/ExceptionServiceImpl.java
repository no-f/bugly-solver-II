package com.bugly.system.service.impl;

import com.bugly.common.logrobot.DingTalkSender;
import com.bugly.system.bo.ServiceExceptionBo;
import com.bugly.system.dao.ProcessingRecordMapper;
import com.bugly.system.dao.ServiceLogMapper;
import com.bugly.system.dto.DealWithServerLogDto;
import com.bugly.system.dto.GetServerLogDto;
import com.bugly.system.model.ProcessingRecord;
import com.bugly.system.model.ServiceLog;
import com.bugly.system.service.ExceptionService;
import com.bugly.system.vo.CommonResult;
import com.bugly.system.vo.PageResult;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bugly.system.vo.CommonResult.success;


/**
 * @author no_f
 * @create 2020-06-18 11:26
 */
@Service
public class ExceptionServiceImpl implements ExceptionService {
    private static final String webhookUrl = "";

    @Autowired
    private ServiceLogMapper serviceLogMapper;

    @Autowired
    private ProcessingRecordMapper processingRecordMapper;


    @Override
    public CommonResult<Boolean> saveServiceLog(JSONObject content) {
        ServiceLog serviceLog = getData(JSONObject.fromObject(content));
        serviceLogMapper.insert(serviceLog);
        //TODO 传过来的参数格式注意一下
        DingTalkSender.sendDingTalk(content.toString(), webhookUrl);
        return success(true);
    }

    @Override
    public CommonResult<PageResult<ServiceExceptionBo>> getServiceLogs(GetServerLogDto getServerLogDto) {

        List<ServiceLog> serviceLogs = serviceLogMapper.findByCondition(getServerLogDto);
        List<ServiceExceptionBo> serviceExceptionBos = new ArrayList<>();
        serviceLogs.forEach(s -> {
            ServiceExceptionBo serviceExceptionBo = new ServiceExceptionBo();
            BeanUtils.copyProperties(s, serviceExceptionBo);
            serviceExceptionBo.setCtime(String.valueOf(s.getCtime().getTime()));
            serviceExceptionBo.setTriggerTime(String.valueOf(s.getTriggerTime().getTime()));
            serviceExceptionBos.add(serviceExceptionBo);
        });
        PageResult<ServiceExceptionBo> listPageResult = new PageResult<>();
        listPageResult.setList(serviceExceptionBos);
        listPageResult.setTotal(serviceLogMapper.countCondition(getServerLogDto));
        return success(listPageResult);
    }

    @Override
    public CommonResult<Boolean> dealWith(DealWithServerLogDto dealWithServerLogDto) {
        ServiceLog serviceLog = new ServiceLog();
        BeanUtils.copyProperties(dealWithServerLogDto, serviceLog);
        serviceLogMapper.updateById(serviceLog);
        ProcessingRecord processingRecord = new ProcessingRecord();
        BeanUtils.copyProperties(dealWithServerLogDto, processingRecord);
        processingRecordMapper.insert(processingRecord);
        return success(true);
    }

    private ServiceLog getData(JSONObject jsonObject) {
        ServiceLog serviceLog = new ServiceLog();

        if (jsonObject.containsKey("currentCluster")) {
            serviceLog.setCurrentCluster((String) jsonObject.get("currentCluster"));
        }
        if (jsonObject.containsKey("serviceName")) {
            serviceLog.setServiceName((String) jsonObject.get("serviceName"));
        }
        if (jsonObject.containsKey("machineAddress")) {
            serviceLog.setMachineAddress((String) jsonObject.get("machineAddress"));
        }
        if (jsonObject.containsKey("triggerTime")) {
            Long triggerTime = (Long) jsonObject.get("triggerTime");
            serviceLog.setTriggerTime(new Date(triggerTime));
        }

        if (jsonObject.containsKey("threadId")) {
            serviceLog.setThreadId((int) jsonObject.get("threadId"));
        }
        if (jsonObject.containsKey("level")) {
            serviceLog.setLevel((String) jsonObject.get("level"));
        }

        if (jsonObject.containsKey("errorLocation")) {
            String location = (String) jsonObject.get("errorLocation");
            serviceLog.setErrorLocation(location);
            serviceLog.setNum(serviceLogMapper.findSameExceptionNum(location) + 1);
        }

        if (jsonObject.containsKey("errorMessage")) {
            serviceLog.setErrorMessage((String) jsonObject.get("errorMessage"));
        }
        if (jsonObject.containsKey("errorException")) {
            serviceLog.setErrorException((String) jsonObject.get("errorException"));
        }
        Date day = new Date();
        //todo 目前这个类型不知道填什么东西暂时先放着
        serviceLog.setType(0).setState(0).setDeleted(0).setCtime(day).setMtime(day);
        return serviceLog;
    }
}
