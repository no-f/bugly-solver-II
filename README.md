
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

# 效果展示

1. 异常类型页面
2. 异常列表页面
3. 异常详情页面
4. 异常配置页面
5. 钉钉群消息通知页面
6. 钉钉群异常详情展示页面
7. 钉钉群异常处理页面





