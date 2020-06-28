package com.bugly.system.provider;

import com.bugly.system.dto.DealWithServerLogDto;
import com.bugly.system.dto.GetServerLogDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author no_f
 * @since 2020-06-24
 */
public class ProcessingRecordProvider {

    public String update(final DealWithServerLogDto dealWithServerLogDto) {
        return new SQL() {
            {
                UPDATE("service_log");

                if (null != dealWithServerLogDto.getState()) {
                    SET("state=#{state}");
                }
                if (null != dealWithServerLogDto.getState()) {
                    SET("state=#{state}");
                }
                SET("mtime=#{mtime}");
                WHERE("id=#{id}");
            }
        }.toString();
    }

    public String findByCondition(GetServerLogDto getServerLogDto) {
        StringBuilder sb = new StringBuilder("SELECT * FROM service_log WHERE deleted=0 ");
        if (StringUtils.isNotBlank(getServerLogDto.getCurrentCluster())) {
            sb.append(" AND `current_cluster`=#{currentCluster} ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getServiceName())) {
            sb.append(" AND `service_name`=#{serviceName} ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getTag())) {
            sb.append(" AND `tag`=#{tag} ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getTag())) {
            sb.append(" AND `tag`=#{tag} ");
        }

        if (getServerLogDto.getType() != null) {
            sb.append(" AND `type`=#{type} ");
        }

        if (getServerLogDto.getUserId() != null) {
            sb.append(" AND `user_id`=#{userId} ");
        }

        if (getServerLogDto.getState() != null) {
            sb.append(" AND `state`=#{state} ");
        }

        if (getServerLogDto.getStartTime() != null) {
            sb.append(" AND `trigger_time` >={startTime} ");
        }

        if (getServerLogDto.getEndTime() != null) {
            sb.append(" AND `trigger_time` <={endTime} ");
        }

        sb.append(" GROUP BY `error_location`");
        sb.append(" Limit ").append(getServerLogDto.getPageNo() * getServerLogDto.getPageSize())
                .append(",").append(getServerLogDto.getPageSize());
        return sb.toString();
    }

    public String countCondition(GetServerLogDto getServerLogDto) {
        StringBuilder sb = new StringBuilder("SELECT count(id) FROM service_log WHERE deleted=0 ");
        if (StringUtils.isNotBlank(getServerLogDto.getCurrentCluster())) {
            sb.append(" AND `current_cluster`=#{currentCluster} ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getServiceName())) {
            sb.append(" AND `service_name`=#{serviceName} ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getTag())) {
            sb.append(" AND `tag`=#{tag} ");
        }

        if (StringUtils.isNotBlank(getServerLogDto.getTag())) {
            sb.append(" AND `tag`=#{tag} ");
        }

        if (getServerLogDto.getType() != null) {
            sb.append(" AND `type`=#{type} ");
        }

        if (getServerLogDto.getUserId() != null) {
            sb.append(" AND `user_id`=#{userId} ");
        }

        if (getServerLogDto.getState() != null) {
            sb.append(" AND `state`=#{state} ");
        }

        if (getServerLogDto.getStartTime() != null) {
            sb.append(" AND `trigger_time` >={startTime} ");
        }

        if (getServerLogDto.getEndTime() != null) {
            sb.append(" AND `trigger_time` <={endTime} ");
        }

        sb.append(" GROUP BY `error_location`");
        return sb.toString();
    }

    public String getByFirmId(@Param("devType") Integer devType,
                              @Param("model") String model,
                              @Param("version") String version,
                              @Param("firmId") Long firmId) {
        StringBuilder sb = new StringBuilder("SELECT * FROM device_ability WHERE deleted=0 ");
        if (model != null) {
            sb.append(" AND model=#{model} ");
        }
        if (version != null) {
            sb.append(" AND `version`<=#{version} ");
        }
        if (devType != null) {
            sb.append(" AND dev_type=#{devType} ");
        }
        if (firmId != null) {
            sb.append(" AND `firm_id`=#{firmId} ");
        }

        sb.append(" ORDER BY `version` DESC LIMIT 1");
        return sb.toString();
    }
}
