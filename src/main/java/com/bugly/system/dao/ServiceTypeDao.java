package com.bugly.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugly.system.model.ServiceType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author no_f
 * @since 2020-07-01
 */
@Repository
public interface ServiceTypeDao extends BaseMapper<ServiceType> {

    @Select("SELECT * FROM `service_type` where service_name =#{serviceName} limit 1")
    ServiceType findByName(String serviceName);

    @Select("SELECT * FROM `service_type` order by mtime desc limit #{no}, #{pageSize}")
    List<ServiceType> findAll(Integer no, Integer pageSize);

    @Select("SELECT st.* FROM `service_type` st, `service_type_user` stu where st.id=stu.`service_type_id` and "
            +" stu.user_id=#{userId} GROUP BY st.id" )
    List<ServiceType> findByUserId(String userId);

    @Select("SELECT count(id) FROM `service_type`")
    int findAllNum();

}
