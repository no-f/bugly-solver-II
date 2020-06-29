package com.bugly.system.service.impl;

import com.bugly.common.base.ApiResponse;
import com.bugly.common.logrobot.DingTalkSender;
import com.bugly.common.utils.UUIDUtils;
import com.bugly.system.bo.ExceptionTypeBo;
import com.bugly.system.bo.ServiceExceptionBo;
import com.bugly.system.dao.ExceptionTypeDao;
import com.bugly.system.dao.ProcessingRecordDao;
import com.bugly.system.dao.ServiceLogDao;
import com.bugly.system.dto.DealWithServerLogDto;
import com.bugly.system.dto.GetServerLogDto;
import com.bugly.system.model.ExceptionType;
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
    private ServiceLogDao serviceLogDao;

    @Autowired
    private ExceptionTypeDao exceptionTypeDao;

    @Autowired
    private ProcessingRecordDao processingRecordDao;


    @Override
    public CommonResult<Boolean> saveServiceLog(JSONObject content) {
        ServiceLog serviceLog = getData(JSONObject.fromObject(content));
        serviceLog.setExceptionTypeId(exceptionTypeAction(content));
        serviceLogDao.insert(serviceLog);
        //TODO 传过来的参数格式注意一下
        DingTalkSender.sendDingTalk(content.toString(), webhookUrl);
        return success(true);
    }


    @Override
    public ApiResponse findAll() {
        List<ExceptionType> exceptionTypes = exceptionTypeDao.findAll();
        List<ExceptionTypeBo> exceptionTypeBos = new ArrayList<>();
        exceptionTypes.forEach(e -> {
            ExceptionTypeBo exceptionTypeBo = new ExceptionTypeBo();
            BeanUtils.copyProperties(e, exceptionTypeBo);
            exceptionTypeBo.setMtime(String.valueOf(e.getMtime()));
            exceptionTypeBo.setTag("测试用");
            exceptionTypeBo.setState("未解决");
            exceptionTypeBo.setMachineAddress("load-balance-service-6976ccfbdd-ktfjh/10.244.2.166");
            exceptionTypeBos.add(exceptionTypeBo);
        });

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",exceptionTypes.size());
        jsonObject.put("page",0);
        jsonObject.put("page_size",10);
        jsonObject.put("sysUserList",exceptionTypeBos);
        return ApiResponse.ofSuccess(jsonObject);
    }

    @Override
    public ApiResponse getExceptions(GetServerLogDto getServerLogDto) {
        List<ExceptionType> exceptionTypes = exceptionTypeDao.findByCondition(getServerLogDto);
        List<ExceptionTypeBo> exceptionTypeBos = new ArrayList<>();
        exceptionTypes.forEach(e -> {
            ExceptionTypeBo exceptionTypeBo = new ExceptionTypeBo();
            BeanUtils.copyProperties(e, exceptionTypeBo);
            exceptionTypeBo.setMtime(String.valueOf(e.getMtime()));
            exceptionTypeBo.setState(stateString(e.getState()));
            exceptionTypeBos.add(exceptionTypeBo);
        });

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",exceptionTypeDao.countCondition(getServerLogDto));
        jsonObject.put("page",0);
        jsonObject.put("page_size",10);
        jsonObject.put("sysUserList",exceptionTypeBos);
        return ApiResponse.ofSuccess(jsonObject);
    }


    @Override
    public ApiResponse getDetailsAll() {
        List<ServiceExceptionBo> serviceExceptionBos = new ArrayList<>();

        ServiceExceptionBo serviceExceptionBo = new ServiceExceptionBo();
        serviceExceptionBo.setMachineAddress("facepp-proxy-service-6d4c55f6f-bb4mm/10.244.7.41");
        serviceExceptionBo.setErrorException("123123123");
        serviceExceptionBo.setErrorMessage("zzzxxasdsdqsdasdasdasda");
        serviceExceptionBo.setTriggerTime(String.valueOf(new Date()));
        serviceExceptionBo.setThreadId(111);
        serviceExceptionBo.setErrorLocation("com.bullyun.fpp.util.FaceUploadImage.socketConnect【行号=-2】");
        serviceExceptionBos.add(serviceExceptionBo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",10);
        jsonObject.put("page",0);
        jsonObject.put("page_size",10);
        jsonObject.put("sysUserList",serviceExceptionBos);
        return ApiResponse.ofSuccess(jsonObject);
    }

    @Override
    public ApiResponse getDetails(GetServerLogDto getServerLogDto) {
        List<ServiceLog> serviceLogs = serviceLogDao.findByCondition(getServerLogDto);
        List<ServiceExceptionBo> serviceExceptionBos = new ArrayList<>();
        serviceLogs.forEach(s -> {
            ServiceExceptionBo serviceExceptionBo = new ServiceExceptionBo();
            BeanUtils.copyProperties(s, serviceExceptionBo);
            serviceExceptionBo.setCtime(String.valueOf(s.getCtime().getTime()));
            serviceExceptionBo.setTriggerTime(String.valueOf(s.getTriggerTime().getTime()));
            serviceExceptionBos.add(serviceExceptionBo);
        });

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",serviceLogDao.countCondition(getServerLogDto));
        jsonObject.put("page",0);
        jsonObject.put("page_size",10);
        jsonObject.put("sysUserList",serviceExceptionBos);
        return ApiResponse.ofSuccess(jsonObject);
    }

    @Override
    public CommonResult<Boolean> dealWith(DealWithServerLogDto dealWithServerLogDto) {
        ExceptionType exceptionType = new ExceptionType();
        BeanUtils.copyProperties(dealWithServerLogDto, exceptionType);
        exceptionTypeDao.updateById(exceptionType);
        ProcessingRecord processingRecord = new ProcessingRecord();
        BeanUtils.copyProperties(dealWithServerLogDto, processingRecord);
        processingRecordDao.insert(processingRecord);
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

        if (jsonObject.containsKey("errorMessage")) {
            serviceLog.setErrorMessage((String) jsonObject.get("errorMessage"));
        }
        if (jsonObject.containsKey("errorException")) {
            serviceLog.setErrorException((String) jsonObject.get("errorException"));
        }
        Date day = new Date();
        //todo 目前这个类型不知道填什么东西暂时先放着
        serviceLog.setType(0).setDeleted(0).setCtime(day).setMtime(day);
        return serviceLog;
    }

    /**
     * 不同位置异常保存
     * @param jsonObject
     * @return
     */
    private Long exceptionTypeAction(JSONObject jsonObject) {
        Long id = Long.valueOf(UUIDUtils.getUUID());
        if (jsonObject.containsKey("errorLocation")) {
            String location = (String) jsonObject.get("errorLocation");
            Date day = new Date();
            ExceptionType e = exceptionTypeDao.findByLocal(location);
            if (null == e) {
                e = new ExceptionType();
                e.setId(id).setDeleted(0).setCtime(day).setMtime(day);
                e.setNum(1);
                exceptionTypeDao.insert(e);
            } else {

                e.setNum(e.getNum() + 1).setMtime(day);
                exceptionTypeDao.update(e);
            }

            return e.getId();
        }
        return id;
    }

    private String stateString(int state) {
        switch (state) {
            case 0:
                return "待处理";
            case 1:
                return "已处理";
            case 2:
                return "处理中";
            default: return "待处理";
        }
    }
}
