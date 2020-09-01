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
            sb.append(" AND `error_exception` LIKE  '" + getServerLogDto.getErrorException() + "%'");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getErrorMessage())) {
            sb.append(" AND `error_message` LIKE  '" + getServerLogDto.getErrorMessage() + "%'");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getServiceName())) {
            sb.append(" AND `service_name` LIKE  '" + getServerLogDto.getServiceName() + "%'");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getMachinneAddress())) {
            sb.append(" AND `machine_address` LIKE  '" + getServerLogDto.getMachinneAddress() + "%'");
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
            sb.append(" AND `error_exception` LIKE  '" + getServerLogDto.getErrorException() + "%'");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getErrorMessage())) {
            sb.append(" AND `error_message` LIKE  '" + getServerLogDto.getErrorMessage() + "%'");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getMachinneAddress())) {
            sb.append(" AND `machine_address` LIKE  '" + getServerLogDto.getMachinneAddress() + "%'");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getServiceName())) {
            sb.append(" AND `service_name` LIKE  '" + getServerLogDto.getServiceName() + "%'");
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
