/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1本地库
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : no_f

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 20/06/2020 17:38:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `menu_weight` int(100) NOT NULL COMMENT '菜单权重',
  `is_show` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否显示（1显示 0隐藏）',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('3bc6a24d1c5d4196b4d7bc3a732d2208', '3e7d54f2bd82464484defcb4709b3142', '登录日志', 'syslog', 'sys_log/list', NULL, '2', '4', '1', '2020-01-13 11:25:29', 'admin');
INSERT INTO `sys_menu` VALUES ('3c2363839f584216b643e6bd3c05695d', '3e7d54f2bd82464484defcb4709b3142', '用户管理', 'user', 'user/list', '', '2', '1', '1', '2019-12-24 15:04:59', 'admin');
INSERT INTO `sys_menu` VALUES ('3e7d54f2bd82464484defcb4709b3142', NULL, '系统管理', 'system', NULL, 'layui-icon-home', '1', '0', '1', '2019-12-24 15:02:32', 'admin');
INSERT INTO `sys_menu` VALUES ('5c2f6c5c80084a99a9d7166ba328bfdd', 'e3c575455f1a4af683b26b3f56a27815', '数据源监控', 'druid', 'druid', NULL, '2', '1', '1', '2019-12-29 20:17:10', 'admin');
INSERT INTO `sys_menu` VALUES ('7c3195059e954531909f6b31c91826b9', '3e7d54f2bd82464484defcb4709b3142', '项目介绍', 'systemIntroduce', 'system/introduce', NULL, '2', '0', '1', '2020-01-19 16:31:48', 'admin');
INSERT INTO `sys_menu` VALUES ('893c49dd5fdb44d79bb2897db9472517', '8f1eb86b09354635b3857222d54991d3', 'v-charts图表', 'vCharts', 'devUtils/vCharts', NULL, '2', '1', '1', '2020-01-16 16:16:48', 'admin');
INSERT INTO `sys_menu` VALUES ('8db930130a1e4b2b9fd479d1f9a4ed45', '3e7d54f2bd82464484defcb4709b3142', '菜单管理', 'menu', 'menu/list', NULL, '2', '2', '1', '2019-12-24 15:07:12', 'admin');
INSERT INTO `sys_menu` VALUES ('8f1eb86b09354635b3857222d54991d3', NULL, '研发工具', 'devUtils', '', 'layui-icon-util', '1', '3', '1', '2020-01-15 16:33:27', 'admin');
INSERT INTO `sys_menu` VALUES ('ba90c64234a44202818e10868ab9da91', '8f1eb86b09354635b3857222d54991d3', '菜单图标', 'menuIcon', 'devUtils/menuIcon', NULL, '2', '0', '1', '2020-01-15 16:34:17', 'admin');
INSERT INTO `sys_menu` VALUES ('be0a8e5ec52c4f0baa2a3edf8194f7f2', 'e3c575455f1a4af683b26b3f56a27815', '服务器监控', 'server', 'system/serverInfo', NULL, '2', '0', '1', '2019-12-27 17:08:56', 'admin');
INSERT INTO `sys_menu` VALUES ('e3c575455f1a4af683b26b3f56a27815', NULL, '系统监控', 'monitor', NULL, 'layui-icon-engine', '1', '1', '1', '2019-12-24 15:37:04', 'admin');
INSERT INTO `sys_menu` VALUES ('ed8df2ffe77645cdb7a1b2b10f5d89e4', '3e7d54f2bd82464484defcb4709b3142', '角色管理', 'role', 'role/list', NULL, '2', '1', '1', '2019-12-24 15:08:17', 'admin');

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
INSERT INTO `sys_menu_role` VALUES ('3e7d54f2bd82464484defcb4709b3142', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('3c2363839f584216b643e6bd3c05695d', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('ed8df2ffe77645cdb7a1b2b10f5d89e4', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('8db930130a1e4b2b9fd479d1f9a4ed45', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('3bc6a24d1c5d4196b4d7bc3a732d2208', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('e3c575455f1a4af683b26b3f56a27815', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('be0a8e5ec52c4f0baa2a3edf8194f7f2', '38ab52848a074a3b8845b09eadb3fd71');
INSERT INTO `sys_menu_role` VALUES ('5c2f6c5c80084a99a9d7166ba328bfdd', '38ab52848a074a3b8845b09eadb3fd71');
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
INSERT INTO `sys_user` VALUES ('64c40c54ef21495da322901107a7ad00', 'admin', '$2a$10$OgVXB6JzNxeGBVd2iAtRP.6JbKL/1WAwu2GuV91OkXfwqVemKwcWa', 'admin', '男', '177', '111111@qq.com', '2020-01-06', '111', '22', '2019-12-12 21:41:53', '2020-01-17 10:56:02');
INSERT INTO `sys_user` VALUES ('86fdffc82e2dcfdf', 'test', '$2a$10$wGIuvNDCU0hZFndHBGmjcOIWviuEPQJzApyZ1h4oFUrgdL/Uo4XA6', 'test', '女', '17788886666', '111@qq.com', '2020-01-15', 'dfsf', 'ff', '2020-01-11 19:31:41', '2020-01-14 15:20:20');
INSERT INTO `sys_user` VALUES ('8cd77cb345e94c31a5b884ed6e3616a2', 'no_f', '$2a$10$WF0tz62CbtwiBNGb6YPu9.o0ojKHZx7iQXn.ZzAlPnAQ.IppcT3AG', 'no_f', '男', '17788886666', '111@qq.com', '2020-01-06', 'f', 'ff', '2019-12-12 21:42:15', '2020-01-14 15:20:49');
INSERT INTO `sys_user` VALUES ('e1941a92b1c74c8893e29b6ad95cf0ba', 'cxd', '$2a$10$CzRDbWQGQgAiDB1cg8ut.O7XBYh2EFdQg0xLOiNcffLYjUhCW7FQq', 'caixiaodong', '男', '17788886666', '111@qq.com', '2020-01-20', 'aa', 'aa', '2020-01-17 11:01:44', NULL);

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
INSERT INTO `sys_user_role` VALUES ('86fdffc82e2dcfdf', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_user_role` VALUES ('8cd77cb345e94c31a5b884ed6e3616a2', 'b8174920f33f4b17ad5f415c700aacd2');
INSERT INTO `sys_user_role` VALUES ('e1941a92b1c74c8893e29b6ad95cf0ba', '38ab52848a074a3b8845b09eadb3fd71');

SET FOREIGN_KEY_CHECKS = 1;
