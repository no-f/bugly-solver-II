
# 平台简介
bugly-boot 服务异常展示，分析，通知用户的平台

# 技术选型
1、系统环境
* JDK1.8
* Servlet 3.0
* Apache Maven 3

2、主框架
* Spring Boot 2.1.5.RELEASE
* Spring Framework 5.0
* Spring Security 5.1

3、持久层
* MyBatis Plus 3.1
* Alibaba Druid 1.1

4、视图层
* Layui 2.X
* Vue 2.0
* Element ui 2.13
* Thymeleaf 3.0
  
# 内置功能
* 用户管理
* 角色管理
* 菜单管理
* 登录日志
* 服务器监控
* 数据源监控
* 菜单图标
* v-charts图表
* 服务异常
* 异常分析
* 异常通知配置

# 环境部署
1. 导入开发工具中(开发工具需要安装lombok插件)
2. 创建数据库 并导入数据脚本/db/bugly.sql 文件
3. 修改application-dev.yml文件中的 mysql的连接信息
4. 打开运行BuglyBootApplication.java
5. 打开浏览器，输入 http://127.0.0.1:8600/bugly/（默认账户 admin/123456）

<p align="center" >
    <img src="https://www.xuxueli.com/doc/static/xxl-job/images/xxl-logo.jpg" width="150">
    <h3 align="center">XXL-JOB</h3>
    <p align="center">
        XXL-JOB, a distributed task scheduling framework.
        <br>
        <a href="https://www.xuxueli.com/xxl-job/"><strong>-- Home Page --</strong></a>
        <br>
        <br>
        <a href="https://github.com/xuxueli/xxl-job/actions">
            <img src="https://github.com/qhysjpw/bugly-solver-II/blob/master/doc/image/%E5%BC%82%E5%B8%B8%E7%B1%BB%E5%9E%8B.png" >
        </a>
        <a href="https://maven-badges.herokuapp.com/maven-central/com.xuxueli/xxl-job/">
            <img src="https://github.com/qhysjpw/bugly-solver-II/blob/master/doc/image/%E5%BC%82%E5%B8%B8%E8%AE%B0%E5%BD%95.png" >
        </a>
        <a href="https://github.com/xuxueli/xxl-job/releases">
         <img src="https://github.com/qhysjpw/bugly-solver-II/blob/master/doc/image/%E5%BC%82%E5%B8%B8%E8%AF%A6%E6%83%85.png" >
        </a>
        <a href="https://github.com/xuxueli/xxl-job/">
            <img src="https://github.com/qhysjpw/bugly-solver-II/blob/master/doc/image/%E5%BC%82%E5%B8%B8%E9%85%8D%E7%BD%AE.png" >
        </a>
        <a href="https://hub.docker.com/r/xuxueli/xxl-job-admin/">
            <img src="https://img.shields.io/docker/pulls/xuxueli/xxl-job-admin" >
        </a>
        <a href="http://www.gnu.org/licenses/gpl-3.0.html">
         <img src="https://img.shields.io/badge/license-GPLv3-blue.svg" >
        </a>
        <a href="https://www.xuxueli.com/page/donate.html">
           <img src="https://img.shields.io/badge/%24-donate-ff69b4.svg?style=flat" >
        </a>
    </p>
</p>



