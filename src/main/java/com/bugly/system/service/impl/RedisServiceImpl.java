//package com.bugly.system.service.impl;
//
//import com.bugly.common.utils.RedisUtils;
//import com.bugly.system.service.RedisService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author no_f
// * @ClassName RedisServiceImpl
// * @Description TODO
// * @Date 2020/62/17 20:09
// */
//@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@SuppressWarnings({"unchecked","all"})
//public class RedisServiceImpl implements RedisService {
//
//    private final RedisUtils redisUtils;
//
//    @Value("${loginCode.expiration}")
//    private Long expiration;
//
//    @Override
//    public String getCodeVal(String key) {
//        try {
//            return Objects.requireNonNull(redisUtils.get(key)).toString();
//        }catch (Exception e){
//            return "";
//        }
//    }
//
//    @Override
//    public void saveCode(String key, Object val) {
//        redisUtils.set(key,val,expiration,TimeUnit.MINUTES);
//        /*redisTemplate.opsForValue().set(key,val);
//        redisTemplate.expire(key,expiration, TimeUnit.MINUTES);*/
//    }
//
//    @Override
//    public void delete(String key) {
//        redisUtils.remove(key);
//    }
//}
