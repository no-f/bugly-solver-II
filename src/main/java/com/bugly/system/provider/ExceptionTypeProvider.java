package com.bugly.system.provider;

import com.bugly.system.vo.BuglySearchVo;
import org.apache.commons.lang3.StringUtils;

/**
 * @author no_f
 * @since 2020-07-01
 */
public class ExceptionTypeProvider {

    public String findByCondition(BuglySearchVo buglySearchVo) {
        StringBuilder sb = new StringBuilder("SELECT * FROM `exception_type` WHERE deleted=0 ");
        if (null != buglySearchVo.getState()) {
            sb.append(" AND `state`=#{state} ");
        }

        if (StringUtils.isNotBlank(buglySearchVo.getErrorLocaltion())) {
            sb.append(" AND `error_location` LIKE  '" + buglySearchVo.getErrorLocaltion() + "%'");
        }
        sb.append(" order by mtime desc ");
        sb.append(" Limit ").append((buglySearchVo.getPage()-1) * buglySearchVo.getPageSize())
                .append(",").append(buglySearchVo.getPageSize());
        return sb.toString();
    }

    public String countCondition(BuglySearchVo buglySearchVo) {
        StringBuilder sb = new StringBuilder("SELECT count(id) FROM `exception_type` WHERE deleted=0 ");
        if (null != buglySearchVo.getState()) {
            sb.append(" AND `state`=#{state} ");
        }

        if (StringUtils.isNotBlank(buglySearchVo.getErrorLocaltion())) {
            sb.append(" AND `error_location` LIKE  '" + buglySearchVo.getErrorLocaltion() + "%'");
        }
        return sb.toString();
    }
}
