/*
 Navicat Premium Data Transfer

 Source Server         : apollo
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 52.81.60.236:30013
 Source Schema         : bugly

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 06/07/2020 10:48:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alarm_config
-- ----------------------------
DROP TABLE IF EXISTS `alarm_config`;
CREATE TABLE `alarm_config`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '0: 钉钉 2：微信',
  `service_type_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `webhook_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `deleted` tinyint(1) NULL DEFAULT 0,
  `ctime` datetime(0) NULL DEFAULT NULL,
  `mtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ac_id`(`service_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '报警配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of alarm_config
-- ----------------------------
INSERT INTO `alarm_config` VALUES ('1', 0, NULL, 'https://oapi.dingtalk.com/robot/send?access_token=27905f2e725dbf04f6ae1cffb42dcc359c1387d329da81c3dbb94386f519b339', 0, '2020-06-18 09:41:08', '2020-06-18 09:41:10');

-- ----------------------------
-- Table structure for exception_report
-- ----------------------------
DROP TABLE IF EXISTS `exception_report`;
CREATE TABLE `exception_report`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `day` datetime(0) NULL DEFAULT NULL,
  `exception_total` int(11) NULL DEFAULT NULL COMMENT '异常总数',
  `exception_type_num` int(11) NULL DEFAULT NULL COMMENT '异常类型总数',
  `unsolved_exception_num` int(11) NULL DEFAULT NULL COMMENT '未解决异常数',
  `deleted` tinyint(1) NULL DEFAULT NULL,
  `ctime` datetime(0) NULL DEFAULT NULL,
  `mtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exception_report
-- ----------------------------
INSERT INTO `exception_report` VALUES ('1', '2020-01-02 00:00:00', 0, 0, 0, 0, '2020-07-02 16:46:20', '2020-07-02 16:46:23');
INSERT INTO `exception_report` VALUES ('10', '2020-10-02 00:00:00', 0, 0, 0, 0, '2020-07-02 16:46:20', '2020-07-02 16:46:23');
INSERT INTO `exception_report` VALUES ('11', '2020-11-02 00:00:00', 0, 0, 0, 0, '2020-07-02 16:46:20', '2020-07-02 16:46:23');
INSERT INTO `exception_report` VALUES ('12', '2020-12-02 00:00:00', 0, 0, 0, 0, '2020-07-02 16:46:20', '2020-07-02 16:46:23');
INSERT INTO `exception_report` VALUES ('2', '2020-02-02 00:00:00', 56, 3, 5, 0, '2020-07-02 16:46:20', '2020-07-02 16:46:23');
INSERT INTO `exception_report` VALUES ('3', '2020-03-02 00:00:00', 57, 4, 4, 0, '2020-07-02 16:46:20', '2020-07-02 16:46:23');
INSERT INTO `exception_report` VALUES ('4', '2020-04-02 00:00:00', 61, 5, 2, 0, '2020-07-02 16:46:20', '2020-07-02 16:46:23');
INSERT INTO `exception_report` VALUES ('5', '2020-05-02 00:00:00', 58, 6, 7, 0, '2020-07-02 16:46:20', '2020-07-02 16:46:23');
INSERT INTO `exception_report` VALUES ('6', '2020-06-02 00:00:00', 8, 7, 4, 0, '2020-07-02 16:46:20', '2020-07-02 16:46:23');
INSERT INTO `exception_report` VALUES ('7', '2020-07-02 00:00:00', 0, 0, 0, 0, '2020-07-02 16:46:20', '2020-07-06 02:48:00');
INSERT INTO `exception_report` VALUES ('8', '2020-08-02 00:00:00', 0, 0, 0, 0, '2020-07-02 16:46:20', '2020-07-02 16:46:23');
INSERT INTO `exception_report` VALUES ('9', '2020-09-02 00:00:00', 0, 0, 0, 0, '2020-07-02 16:46:20', '2020-07-02 16:46:23');

-- ----------------------------
-- Table structure for exception_type
-- ----------------------------
DROP TABLE IF EXISTS `exception_type`;
CREATE TABLE `exception_type`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `service_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `error_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '错误位置',
  `tag` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `state` int(11) NULL DEFAULT NULL COMMENT '0：待处理 1：已处理 2：处理中',
  `deleted` tinyint(1) NULL DEFAULT 0,
  `ctime` datetime(0) NULL DEFAULT NULL,
  `mtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `et_error_location`(`error_location`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exception_type_user
-- ----------------------------
DROP TABLE IF EXISTS `exception_type_user`;
CREATE TABLE `exception_type_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `exception_type_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NULL DEFAULT 0,
  `ctime` datetime(0) NULL DEFAULT NULL,
  `mtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for service_log
-- ----------------------------
DROP TABLE IF EXISTS `service_log`;
CREATE TABLE `service_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception_type_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '异常类型id',
  `current_cluster` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '集群地址',
  `service_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务名称',
  `machine_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机器地址',
  `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '异常触发时间',
  `thread_id` int(11) NULL DEFAULT NULL COMMENT '线程ID',
  `level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '错误级别',
  `type` int(11) NULL DEFAULT NULL COMMENT '异常类型',
  `error_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '错误信息',
  `error_exception` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '错误异常',
  `tag` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签',
  `deleted` tinyint(1) NULL DEFAULT 0,
  `ctime` datetime(0) NULL DEFAULT NULL,
  `mtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_trigger_time`(`trigger_time`) USING BTREE,
  INDEX `idx_service_name`(`service_name`) USING BTREE,
  INDEX `idx_tag`(`tag`) USING BTREE,
  INDEX `idx_exception_type_id`(`exception_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '服务异常日志信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for service_type
-- ----------------------------
DROP TABLE IF EXISTS `service_type`;
CREATE TABLE `service_type`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `service_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '服务类型名称',
  `deleted` tinyint(1) NULL DEFAULT NULL,
  `ctime` datetime(0) NULL DEFAULT NULL,
  `mtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for service_type_user
-- ----------------------------
DROP TABLE IF EXISTS `service_type_user`;
CREATE TABLE `service_type_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `service_type_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `deleted` tinyint(1) NULL DEFAULT NULL,
  `ctime` datetime(0) NULL DEFAULT NULL,
  `mtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stu_user_id`(`user_id`) USING BTREE,
  INDEX `stu_service_type_id`(`service_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `ip_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'IP地址',
  `ip_source` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'IP来源',
  `message` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `browser_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器名称',
  `system_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统名称',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单主键',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父菜单主键',
  `menu_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `menu_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单别名',
  `menu_href` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单链接',
  `menu_icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `menu_level` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单级别',
  `menu_weight` int(11) NOT NULL COMMENT '菜单权重',
  `is_show` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否显示（1显示 0隐藏）',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('20b017a4010f406eb7951b1a90669926', NULL, '系统异常', 'bugly', '', 'layui-icon-tree', '1', 0, '1', '2020-06-28 16:51:58', 'admin');
INSERT INTO `sys_menu` VALUES ('3bc6a24d1c5d4196b4d7bc3a732d2208', '3e7d54f2bd82464484defcb4709b3142', '登录日志', 'syslog', 'sys_log/list', '', '2', 4, '0', '2020-01-13 11:25:29', 'admin');
INSERT INTO `sys_menu` VALUES ('3c2363839f584216b643e6bd3c05695d', '3e7d54f2bd82464484defcb4709b3142', '用户管理', 'user', 'user/list', '', '2', 1, '1', '2019-12-24 15:04:59', 'admin');
INSERT INTO `sys_menu` VALUES ('3e7d54f2bd82464484defcb4709b3142', NULL, '系统管理', 'system', '', 'layui-icon-home', '1', 1, '1', '2019-12-24 15:02:32', 'admin');
INSERT INTO `sys_menu` VALUES ('3f300e8a94564a79b9a1f8f1f3962e2c', '20b017a4010f406eb7951b1a90669926', '异常分析', 'buglydetail', 'bugly/exception/detail', '', '2', 2, '1', '2020-06-29 14:46:59', 'admin');
INSERT INTO `sys_menu` VALUES ('5b187933d379415689008185d6810d08', '20b017a4010f406eb7951b1a90669926', '异常类型', 'buglyshow', 'bugly/exception/list', '', '2', 1, '1', '2020-06-28 17:05:35', 'admin');
INSERT INTO `sys_menu` VALUES ('5c2f6c5c80084a99a9d7166ba328bfdd', 'e3c575455f1a4af683b26b3f56a27815', '数据源监控', 'druid', 'druid', NULL, '2', 1, '1', '2019-12-29 20:17:10', 'admin');
INSERT INTO `sys_menu` VALUES ('7c3195059e954531909f6b31c91826b9', '3e7d54f2bd82464484defcb4709b3142', '项目介绍', 'systemIntroduce', 'system/introduce', '', '2', 0, '0', '2020-01-19 16:31:48', 'admin');
INSERT INTO `sys_menu` VALUES ('893c49dd5fdb44d79bb2897db9472517', '8f1eb86b09354635b3857222d54991d3', 'v-charts图表', 'vCharts', 'devUtils/vCharts', NULL, '2', 1, '1', '2020-01-16 16:16:48', 'admin');
INSERT INTO `sys_menu` VALUES ('8db930130a1e4b2b9fd479d1f9a4ed45', '3e7d54f2bd82464484defcb4709b3142', '菜单管理', 'menu', 'menu/list', NULL, '2', 2, '1', '2019-12-24 15:07:12', 'admin');
INSERT INTO `sys_menu` VALUES ('8f1eb86b09354635b3857222d54991d3', NULL, '研发工具', 'devUtils', '', 'layui-icon-util', '1', 3, '1', '2020-01-15 16:33:27', 'admin');
INSERT INTO `sys_menu` VALUES ('ba90c64234a44202818e10868ab9da91', '8f1eb86b09354635b3857222d54991d3', '菜单图标', 'menuIcon', 'devUtils/menuIcon', NULL, '2', 0, '1', '2020-01-15 16:34:17', 'admin');
INSERT INTO `sys_menu` VALUES ('be0a8e5ec52c4f0baa2a3edf8194f7f2', 'e3c575455f1a4af683b26b3f56a27815', '服务器监控', 'server', 'system/serverInfo', NULL, '2', 0, '1', '2019-12-27 17:08:56', 'admin');
INSERT INTO `sys_menu` VALUES ('d438e0f133f0480dba03893c367f7493', '20b017a4010f406eb7951b1a90669926', '异常配置', 'alarmConfig', 'bugly/exception/alarmConfig', NULL, '2', 2, '1', '2020-06-30 16:12:30', 'admin');
INSERT INTO `sys_menu` VALUES ('e3c575455f1a4af683b26b3f56a27815', NULL, '系统监控', 'monitor', '', 'layui-icon-engine', '1', 2, '0', '2019-12-24 15:37:04', 'admin');
INSERT INTO `sys_menu` VALUES ('ed8df2ffe77645cdb7a1b2b10f5d89e4', '3e7d54f2bd82464484defcb4709b3142', '角色管理', 'role', 'role/list', NULL, '2', 1, '1', '2019-12-24 15:08:17', 'admin');

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`  (
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单主键',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES ('e3c575455f1a4af683b26b3f56a27815', '811d784a392ad816');
INSERT INTO `sys_menu_role` VALUES ('3e7d54f2bd82464484defcb4709b3142', '811d784a392ad816');
INSERT INTO `sys_menu_role` VALUES ('3c2363839f584216b643e6bd3c05695d', '811d784a392ad816');
INSERT INTO `sys_menu_role` VALUES ('ed8df2ffe77645cdb7a1b2b10f5d89e4', '811d784a392ad816');
INSERT INTO `sys_menu_role` VALUES ('8db930130a1e4b2b9fd479d1f9a4ed45', '811d784a392ad816');
INSERT INTO `sys_menu_role` VALUES ('3bc6a24d1c5d4196b4d7bc3a732d2208', '811d784a392ad816');
INSERT INTO `sys_menu_role` VALUES ('be0a8e5ec52c4f0baa2a3edf8194f7f2', '811d784a392ad816');
INSERT INTO `sys_menu_role` VALUES ('3e7d54f2bd82464484defcb4709b3142', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('3c2363839f584216b643e6bd3c05695d', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('7c3195059e954531909f6b31c91826b9', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('ed8df2ffe77645cdb7a1b2b10f5d89e4', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('8db930130a1e4b2b9fd479d1f9a4ed45', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('3bc6a24d1c5d4196b4d7bc3a732d2208', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('e3c575455f1a4af683b26b3f56a27815', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('be0a8e5ec52c4f0baa2a3edf8194f7f2', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('5c2f6c5c80084a99a9d7166ba328bfdd', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('8f1eb86b09354635b3857222d54991d3', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('ba90c64234a44202818e10868ab9da91', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('893c49dd5fdb44d79bb2897db9472517', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('20b017a4010f406eb7951b1a90669926', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('5b187933d379415689008185d6810d08', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('3f300e8a94564a79b9a1f8f1f3962e2c', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('d438e0f133f0480dba03893c367f7493', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_menu_role` VALUES ('20b017a4010f406eb7951b1a90669926', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('5b187933d379415689008185d6810d08', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('3f300e8a94564a79b9a1f8f1f3962e2c', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('e3c575455f1a4af683b26b3f56a27815', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('be0a8e5ec52c4f0baa2a3edf8194f7f2', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('5c2f6c5c80084a99a9d7166ba328bfdd', '38ab52848a074a3b8845b09eadb3fd71');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `authority` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('38ab52848a074a3b8845b09eadb3fd71', 'ROLE_GENERAL', '普通用户', '2020-01-17 11:01:01');
INSERT INTO `sys_role` VALUES ('811d784a392ad816', 'ROLE_TEST', '测试', '2020-01-11 19:34:21');
INSERT INTO `sys_role` VALUES ('b8174920f33f4b17ad5f415c700aacd2', 'ROLE_ADMIN', '管理员', '2019-12-12 21:42:43');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birthday` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `hobby` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爱好',
  `live_address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现居地',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('64c40c54ef21495da322901107a7ad00', 'admin', '$2a$10$OgVXB6JzNxeGBVd2iAtRP.6JbKL/1WAwu2GuV91OkXfwqVemKwcWa', 'admin', '男', '15757179782', '245175616@qq.com', '1994-02-10', 'zz', 'zz', '2019-12-12 21:41:53', '2020-07-01 14:39:23');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('64c40c54ef21495da322901107a7ad00', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_user_role` VALUES ('7d6f12100e3d4d1ab1c26ad38c243719', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_user_role` VALUES ('f93e9fd7854641dbb3e597ab5772d313', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_user_role` VALUES ('a4a9df8732754f9aad534d896f8f1779', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_user_role` VALUES ('37069fb9c0be4c5d9f8626c4b378bfd4', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_user_role` VALUES ('92379fdd62dd46169b2f569c6f7248e4', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_user_role` VALUES ('4c3372fc21f54e1cad16f757cf987f9d', 'b8174920f33f4b17ad5f415c700aacd2');

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE `bugly`.`service_log`
ADD INDEX `idx_error_message`(`error_message`(10)) USING BTREE,
ADD INDEX `idx_error_exceptionn`(`error_exception`(10)) USING BTREE;