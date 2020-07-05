package com.bugly.system.rest;

import com.bugly.common.base.ApiResponse;
import com.bugly.common.base.Constants;
import com.bugly.system.entity.SysUser;
import com.bugly.system.service.SysUserService;
import com.bugly.common.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author no_f
 * @ClassName IndexRestController
 * @Description TODO
 * @Date 2020/06/17 20:18
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexRestController {

//    private final RedisService redisService;

//    private final RedisUtils redisUtils;

    private final SysUserService sysUserService;

    /**
     * 验证码 宽度
     */
    @Value("${loginCode.width}")
    private Integer width;

    /**
     * 验证码 高度
     */
    @Value("${loginCode.height}")
    private Integer height;

    /**
     * 验证码 运算位数
     */
    @Value("${loginCode.digit}")
    private Integer digit;

    /**
     * 获取验证码
     */
//    @GetMapping("/code")
//    public ImgResult getCode() {
//        // 算术类型 https://gitee.com/whvse/EasyCaptcha
//        ArithmeticCaptcha captcha = new ArithmeticCaptcha(width, height,digit);
//        // 获取运算的结果
//        String result = captcha.text();
//        String uuid = IdUtil.simpleUUID();
//        redisService.saveCode(uuid,result);
//        return new ImgResult(captcha.toBase64(),uuid);
//    }

    @PostMapping("/updatePassword")
    public ApiResponse updatePassword(@RequestParam("oldPass") String oldPass,
                                      @RequestParam("pass") String pass){
        //获取用户
        Authentication authentication = SecurityUtils.getCurrentUserAuthentication();
        String username = (String)authentication.getPrincipal();
//        String usernameRedisKey = Constants.PASSWORD_UPDATE + username;
        // 校验用户是否被锁定
        // 考虑到目前先不用redis
//        if (redisUtils.exists(usernameRedisKey)) {
//            if (redisUtils.sGetSetSize(usernameRedisKey) >= 3L){
//                return ApiResponse.fail("旧密码错误次数太多了，请稍后重试");
//            }
//        }
        // 判断旧密码是否正确
        SysUser sysUser = sysUserService.findByName(username);
        if (sysUser != null){
            if (!new BCryptPasswordEncoder().matches(oldPass,sysUser.getPassword())){
//                redisUtils.sSetAndTime(usernameRedisKey,Constants.PASSWORD_UPDATE_MINUTE, new Date());
//                return ApiResponse.fail("旧密码不匹配,还有"+ (3-redisUtils.sGetSetSize(usernameRedisKey)) +"次机会");
                return ApiResponse.fail("旧密码不匹配");
            } else {
                //更新密码
                sysUserService.updatePasswordById(new BCryptPasswordEncoder().encode(pass), sysUser.getId());
//                if (redisUtils.exists(usernameRedisKey)) {
//                   redisUtils.remove(usernameRedisKey);
//                }
                return ApiResponse.success("更新成功");
            }
        } else {
            return ApiResponse.fail("获取用户信息出错，请稍后重试");
        }
    }




    
    /*@GetMapping("/getUserInfo")
    public ApiResponse getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getName()
    }*/
}
