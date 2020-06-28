
# 平台简介
thyme-boot是一款基于SpringBoot+Layui+Vue的快速后台开发框架

thyme-boot 是一个 Java EE 企业级快速开发平台，基于（Spring Boot、Spring Security、MyBatisPlus、Thymeleaf、Layui、Vue）技术组合，内置模块如：角色用户、菜单权限、登录日志、系统监控等。支持集群，支持多数据源。

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

# 环境部署
1. 前往Gitee下载页面(https://gitee.com/thyme-boot/thyme-boot)下载解压到工作目录
2. 导入开发工具中(开发工具需要安装lombok插件)
3. 创建数据库 并导入数据脚本/db/init.sql 文件
4. 修改application-dev.yml文件中的 mysql和redis的连接信息
5. 打开运行ThymeBootApplication.java
6. 打开浏览器，输入 http://127.0.0.1:8600/thyme/（默认账户 admin/123456）

后续随着功能完善将技术文档单独拎出来

# 演示图
<table>
    <tr>
        <td><img src="https://thymefree.oss-cn-hangzhou.aliyuncs.com/1.png"/></td>
        <td><img src="https://thymefree.oss-cn-hangzhou.aliyuncs.com/2.png"/></td>
    </tr>
    <tr>
        <td><img src="https://thymefree.oss-cn-hangzhou.aliyuncs.com/3.png"/></td>
        <td><img src="https://thymefree.oss-cn-hangzhou.aliyuncs.com/4.png"/></td>
    </tr>
    <tr>
         <td><img src="https://thymefree.oss-cn-hangzhou.aliyuncs.com/5.png"/></td>
         <td><img src="https://thymefree.oss-cn-hangzhou.aliyuncs.com/6.png"/></td>
    </tr>
    <tr>
         <td><img src="https://thymefree.oss-cn-hangzhou.aliyuncs.com/7.png"/></td>
         <td><img src="https://thymefree.oss-cn-hangzhou.aliyuncs.com/8.png"/></td>
    </tr>
</table>

