package com.bugly.system.provider;

import com.bugly.system.dto.GetServerLogDto;
import org.apache.commons.lang3.StringUtils;

/**
 * @author no_f
 * @since 2020-06-24
 */
public class ServiceLogProvider {

    public String findByCondition(GetServerLogDto getServerLogDto) {
        StringBuilder sb = new StringBuilder("SELECT * FROM service_log WHERE deleted=0 ");
        if (StringUtils.isNotBlank(getServerLogDto.getErrorException())) {
            sb.append(" AND locate(#{errorException}, `error_exception`)>0 ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getErrorMessage())) {
            sb.append(" AND locate(#{errorMessage}, `error_message`)>0 ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getServiceName())) {
            sb.append(" AND locate(#{serviceName}, `service_name`)>0 ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getMachinneAddress())) {
            sb.append(" AND locate(#{machinneAddress}, `machinne_address`)>0 ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getCurrentCluster())) {
            sb.append(" AND `current_cluster`=#{currentCluster} ");
        }

        if (getServerLogDto.getStartTime() != null) {
            sb.append(" AND `trigger_time` >=#{startTime} ");
        }

        if (getServerLogDto.getEndTime() != null) {
            sb.append(" AND `trigger_time` <=#{endTime} ");
        }
        sb.append(" order by mtime desc ");
        sb.append(" Limit ").append((getServerLogDto.getPage() -1) * getServerLogDto.getPageSize())
                .append(",").append(getServerLogDto.getPageSize());
        return sb.toString();
    }

    public String countCondition(GetServerLogDto getServerLogDto) {
        StringBuilder sb = new StringBuilder("SELECT count(id) FROM service_log WHERE deleted=0 ");
        if (StringUtils.isNotBlank(getServerLogDto.getErrorException())) {
            sb.append(" AND locate(#{errorException}, `error_exception`)>0 ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getErrorMessage())) {
            sb.append(" AND locate(#{errorMessage}, `error_message`)>0 ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getMachinneAddress())) {
            sb.append(" AND locate(#{machinneAddress}, `machine_address`)>0 ");
        }

        if (getServerLogDto.getStartTime() != null) {
            sb.append(" AND `trigger_time` >=#{startTime} ");
        }

        if (getServerLogDto.getEndTime() != null) {
            sb.append(" AND `trigger_time` <=#{endTime} ");
        }
        return sb.toString();
    }
}
