package com.bugly.system.service.impl;

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
    public CommonResult<PageResult<ExceptionTypeBo>> findAll() {
        List<ExceptionType> exceptionTypes = exceptionTypeDao.findAll();
        List<ExceptionTypeBo> exceptionTypeBos = new ArrayList<>();
        exceptionTypes.forEach(e -> {
            ExceptionTypeBo exceptionTypeBo = new ExceptionTypeBo();
            BeanUtils.copyProperties(e, exceptionTypeBo);
            exceptionTypeBo.setCtime(String.valueOf(e.getCtime().getTime()));
            exceptionTypeBo.setMtime(String.valueOf(e.getMtime().getTime()));
            exceptionTypeBos.add(exceptionTypeBo);
        });
        PageResult<ExceptionTypeBo> listPageResult = new PageResult<>();
        listPageResult.setList(exceptionTypeBos);
        listPageResult.setTotal(exceptionTypes.size());
        return success(listPageResult);
    }

    @Override
    public CommonResult<PageResult<ExceptionTypeBo>> getExceptions(GetServerLogDto getServerLogDto) {
        List<ExceptionType> exceptionTypes = exceptionTypeDao.findByCondition(getServerLogDto);
        List<ExceptionTypeBo> exceptionTypeBos = new ArrayList<>();
        exceptionTypes.forEach(e -> {
            ExceptionTypeBo exceptionTypeBo = new ExceptionTypeBo();
            BeanUtils.copyProperties(e, exceptionTypeBo);
            exceptionTypeBo.setCtime(String.valueOf(e.getCtime().getTime()));
            exceptionTypeBo.setMtime(String.valueOf(e.getMtime().getTime()));
            exceptionTypeBos.add(exceptionTypeBo);
        });
        PageResult<ExceptionTypeBo> listPageResult = new PageResult<>();
        listPageResult.setList(exceptionTypeBos);
        listPageResult.setTotal(exceptionTypeDao.countCondition(getServerLogDto));
        return success(listPageResult);
    }

    @Override
    public CommonResult<PageResult<ServiceExceptionBo>> getServiceLogs(GetServerLogDto getServerLogDto) {

        List<ServiceLog> serviceLogs = serviceLogDao.findByCondition(getServerLogDto);
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
        listPageResult.setTotal(serviceLogDao.countCondition(getServerLogDto));
        return success(listPageResult);
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
}
