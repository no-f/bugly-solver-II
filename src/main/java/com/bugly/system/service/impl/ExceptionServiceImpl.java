package com.bugly.system.service.impl;

import com.bugly.common.base.ApiResponse;
import com.bugly.common.logrobot.DingTalkSender;
import com.bugly.common.utils.UUIDUtils;
import com.bugly.system.bo.ExceptionTypeBo;
import com.bugly.system.bo.ServiceExceptionBo;
import com.bugly.system.dao.*;
import com.bugly.system.dto.DealWithServerLogDto;
import com.bugly.system.dto.GetServerLogDto;
import com.bugly.system.entity.AlarmConfig;
import com.bugly.system.entity.SysUser;
import com.bugly.system.model.ExceptionType;
import com.bugly.system.model.ExceptionTypeUser;
import com.bugly.system.model.ServiceLog;
import com.bugly.system.service.ExceptionService;
import com.bugly.system.service.SysUserService;
import com.bugly.system.vo.BuglyDetailSearchVo;
import com.bugly.system.vo.BuglySearchVo;
import com.bugly.system.vo.CommonResult;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bugly.system.vo.CommonResult.success;


/**
 * @author no_f
 * @create 2020-06-18 11:26
 */
@Service
public class ExceptionServiceImpl implements ExceptionService {

    public static String DATE_Y_M_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ServiceLogDao serviceLogDao;

    @Autowired
    private AlarmConfigDao alarmConfigDao;

    @Autowired
    private ExceptionTypeDao exceptionTypeDao;

    @Autowired
    private ExceptionTypeUserDao exceptionTypeUserDao;


    /**
     * 1.save 不同的异常类型
     * 2.save 每条异常记录
     * 是不是每次都得去数据库查询配置地址？？？优化??
     * @param content
     * @return
     */
    @Override
    public CommonResult<Boolean> saveServiceLog(JSONObject content) {
        ServiceLog serviceLog = getData(JSONObject.fromObject(content));
        serviceLog.setExceptionTypeId(exceptionTypeAction(content));
        serviceLogDao.insert(serviceLog);
        AlarmConfig alarmConfig = alarmConfigDao.findDingDingConfig();
        DingTalkSender.sendDingTalk(content, alarmConfig.getWebhookUrl());
        return success(true);
    }


    @Override
    public ApiResponse findAll(int page, int pageSize) {
        List<ExceptionType> exceptionTypes = exceptionTypeDao.findAll((page-1)*pageSize, pageSize);
        List<ExceptionTypeBo> exceptionTypeBos = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat(DATE_Y_M_DDHHMMSS);
        exceptionTypes.forEach(e -> {
            ExceptionTypeBo exceptionTypeBo = new ExceptionTypeBo();
            BeanUtils.copyProperties(e, exceptionTypeBo);
            exceptionTypeBo.setMtime(sf.format(e.getMtime()));
            exceptionTypeBo.setState(stateString(e.getState()));
            ServiceLog serviceLog = serviceLogDao.findOneByExceptionTypeId(e.getId());
            Optional.ofNullable(serviceLog).ifPresent(s-> exceptionTypeBo.setMachineAddress(s.getMachineAddress()));
            exceptionTypeBos.add(exceptionTypeBo);
        });

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",exceptionTypeDao.findAllNum());
        jsonObject.put("page",page);
        jsonObject.put("page_size",pageSize);
        jsonObject.put("sysUserList",exceptionTypeBos);
        return ApiResponse.ofSuccess(jsonObject);
    }

    @Override
    public ApiResponse findByCondition(BuglySearchVo buglySearchVo) {
        List<ExceptionType> exceptionTypes = exceptionTypeDao.findByCondition(buglySearchVo);
        List<ExceptionTypeBo> exceptionTypeBos = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat(DATE_Y_M_DDHHMMSS);
        exceptionTypes.forEach(e -> {
            ExceptionTypeBo exceptionTypeBo = new ExceptionTypeBo();
            BeanUtils.copyProperties(e, exceptionTypeBo);
            exceptionTypeBo.setMtime(sf.format(e.getMtime()));
            exceptionTypeBo.setState(stateString(e.getState()));
            ServiceLog serviceLog = serviceLogDao.findOneByExceptionTypeId(e.getId());
            Optional.ofNullable(serviceLog).ifPresent(s-> exceptionTypeBo.setMachineAddress(s.getMachineAddress()));
            exceptionTypeBos.add(exceptionTypeBo);
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",exceptionTypeDao.countCondition(buglySearchVo));
        jsonObject.put("page",buglySearchVo.getPage());
        jsonObject.put("page_size",buglySearchVo.getPageSize());
        jsonObject.put("sysUserList",exceptionTypeBos);
        return ApiResponse.ofSuccess(jsonObject);
    }


    @Override
    public ApiResponse getDetailsAll(String exceptionTypeId, Integer page, Integer pageSize) {
        List<ServiceLog> serviceLogs = null;
        int count = 0;
        if (null == exceptionTypeId || exceptionTypeId.isEmpty()) {
            serviceLogs = serviceLogDao.findAll((page-1) * pageSize , pageSize);
            count = serviceLogDao.findAllNum();
        } else {
            serviceLogs = serviceLogDao.findByExceptionTypeId(exceptionTypeId, (page-1) * pageSize , pageSize);
            count = serviceLogDao.findCountByExceptionTypeId(exceptionTypeId);

        }

        JSONObject jsonObject = new JSONObject();
        if (null == serviceLogs || serviceLogs.isEmpty()) {
            jsonObject.put("total",count);
            jsonObject.put("page",page);
            jsonObject.put("page_size",pageSize);
            jsonObject.put("sysUserList", Collections.emptyList());
            return ApiResponse.ofSuccess(jsonObject);
        }
        SimpleDateFormat sf = new SimpleDateFormat(DATE_Y_M_DDHHMMSS);
        List<ServiceExceptionBo> serviceExceptionBos = new ArrayList<>();
        serviceLogs.forEach(serviceLog -> {
            ServiceExceptionBo serviceExceptionBo = new ServiceExceptionBo();
            BeanUtils.copyProperties(serviceLog, serviceExceptionBo);
            serviceExceptionBo.setTriggerTime(sf.format(serviceLog.getTriggerTime()));
            if (null != serviceLog.getErrorException()) {
                serviceExceptionBo.setErrorException(serviceLog.getErrorException().substring(0,100) + "......");
            }
            if (null != serviceLog.getErrorMessage()) {
                serviceExceptionBo.setErrorMessage(serviceLog.getErrorMessage().substring(0,100) + "......");
            }
            serviceExceptionBos.add(serviceExceptionBo);

        });

        jsonObject.put("total",count);
        jsonObject.put("page",page);
        jsonObject.put("page_size",pageSize);
        jsonObject.put("sysUserList",serviceExceptionBos);
        return ApiResponse.ofSuccess(jsonObject);
    }


    @Override
    public ApiResponse getDetailsByCondition(BuglyDetailSearchVo buglyDetailSearchVo) {
        GetServerLogDto getServerLogDto = new GetServerLogDto();
        BeanUtils.copyProperties(buglyDetailSearchVo, getServerLogDto);
        try {

            SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS",Locale.ENGLISH);
            if (null != buglyDetailSearchVo.getStartTime()) {
                getServerLogDto.setStartTime(dff.parse(buglyDetailSearchVo.getStartTime()));
            }
            if (null != buglyDetailSearchVo.getEndTime()) {
                getServerLogDto.setEndTime(dff.parse(buglyDetailSearchVo.getEndTime()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<ServiceLog> serviceLogs = serviceLogDao.findByCondition(getServerLogDto);
        JSONObject jsonObject = new JSONObject();
        if (null == serviceLogs || serviceLogs.isEmpty()) {
            jsonObject.put("total",0);
            jsonObject.put("page",buglyDetailSearchVo.getPage());
            jsonObject.put("page_size",buglyDetailSearchVo.getPageSize());
            jsonObject.put("sysUserList", Collections.emptyList());
            return ApiResponse.ofSuccess(jsonObject);
        }
        SimpleDateFormat sf = new SimpleDateFormat(DATE_Y_M_DDHHMMSS);
        List<ServiceExceptionBo> serviceExceptionBos = new ArrayList<>();
        serviceLogs.forEach(serviceLog -> {
            ServiceExceptionBo serviceExceptionBo = new ServiceExceptionBo();
            BeanUtils.copyProperties(serviceLog, serviceExceptionBo);
            serviceExceptionBo.setTriggerTime(sf.format(serviceLog.getTriggerTime()));
            if (null != serviceLog.getErrorException()) {
                serviceExceptionBo.setErrorException(serviceLog.getErrorException().substring(0,100) + "......");
            }
            if (null != serviceLog.getErrorMessage()) {
                serviceExceptionBo.setErrorMessage(serviceLog.getErrorMessage().substring(0,100) + "......");
            }
            serviceExceptionBos.add(serviceExceptionBo);
        });

        jsonObject.put("total",serviceLogDao.countCondition(getServerLogDto));
        jsonObject.put("page",buglyDetailSearchVo.getPage());
        jsonObject.put("page_size",buglyDetailSearchVo.getPageSize());
        jsonObject.put("sysUserList",serviceExceptionBos);
        return ApiResponse.ofSuccess(jsonObject);
    }

    @Override
    public ApiResponse dealWith(DealWithServerLogDto dto) {
        ExceptionType exceptionType = new ExceptionType();
        BeanUtils.copyProperties(dto, exceptionType);
        int num = exceptionTypeDao.updateById(exceptionType);

        JSONObject jsonObject = new JSONObject();
        if (num == 0) {
            jsonObject.put("code",500);
            return ApiResponse.ofSuccess(jsonObject);
        }
        Date day = new Date();
        ExceptionTypeUser exceptionTypeUser = new ExceptionTypeUser();
        exceptionTypeUser.setId(UUIDUtils.getUUID()).setExceptionTypeId(dto.getId());
        exceptionTypeUser.setDeleted(0).setMtime(day).setCtime(day);
        exceptionTypeUser.setRemark(dto.getRemark());
        SysUser sysUser = sysUserService.findByName(dto.getNickName());
        Optional.ofNullable(sysUser).ifPresent(u -> exceptionTypeUser.setUserId(u.getId()));
        exceptionTypeUserDao.insert(exceptionTypeUser);

        jsonObject.put("code",200);
        return ApiResponse.ofSuccess(jsonObject);
    }

    private ServiceLog getData(JSONObject jsonObject) {
        ServiceLog serviceLog = new ServiceLog();
        try {
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
                String triggerTime = (String) jsonObject.get("triggerTime");
                serviceLog.setTriggerTime(new Date(Long.valueOf(triggerTime)));
            }

            if (jsonObject.containsKey("threadId")) {
                serviceLog.setThreadId(Integer.parseInt(jsonObject.get("threadId").toString()));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date day = new Date();
        //todo 目前这个type类型不知道填什么东西暂时先放着
        serviceLog.setId(UUIDUtils.getUUID()).setType(0).setDeleted(0).setCtime(day).setMtime(day);
        return serviceLog;
    }

    /**
     * 不同位置异常保存
     * @param jsonObject
     * @return
     */
    private String exceptionTypeAction(JSONObject jsonObject) {
        if (jsonObject.containsKey("errorLocation")) {
            String location = (String) jsonObject.get("errorLocation");
            Date day = new Date();
            ExceptionType e = exceptionTypeDao.findByLocal(location);
            if (null == e) {
                e = new ExceptionType();
                e.setId(UUIDUtils.getUUID()).setErrorLocation(location).setState(0).setDeleted(0).setCtime(day).setMtime(day);
                e.setNum(1);
                exceptionTypeDao.insert(e);
            } else {
                e.setNum(e.getNum() + 1).setMtime(day);
                exceptionTypeDao.updateById(e);
            }
            return e.getId();
        }
        return "0";
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
