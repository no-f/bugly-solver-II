package com.bugly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * @author no_f
 * @ClassName SecurityMvcConfigurer
 * @Description 启动类
 * @Date
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.bugly.*.dao")
public class BuglyBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuglyBootApplication.class, args);
    }
}
