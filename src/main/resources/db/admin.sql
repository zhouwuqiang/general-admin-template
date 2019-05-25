/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost:3306
 Source Schema         : admin

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 25/05/2019 19:03:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu_basic_face
-- ----------------------------
DROP TABLE IF EXISTS `menu_basic_face`;
CREATE TABLE `menu_basic_face`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MENU_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '菜单编号',
  `MENU_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '菜单名称',
  `MENU_ICON` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '菜单图标',
  `MENU_ACTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '#' COMMENT '菜单地址',
  `PARENT_MENU_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '上级菜单编号',
  `MENU_TYPE` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '菜单类型(00-目录 01-菜单 02-按钮)',
  `DISPLAY_INDEX` int(11) UNSIGNED DEFAULT 0 COMMENT '显示顺序',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_basic_face
-- ----------------------------
INSERT INTO `menu_basic_face` VALUES (1, 'MENUCO07500120190314161720782115', '系统设置', 'fa fa-cog', '#', '', '00', 0, '2019-05-24 18:35:41', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (2, 'MENUCO07500120190314161720782116', '用户管理', 'fa fa-user-o', '/view/user/manager', 'MENUCO07500120190314161720782115', '00', 0, '2019-03-14 16:26:46', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (3, 'MENUCO07500120190314161720782117', '菜单管理', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782115', '00', 0, '2019-03-14 16:26:46', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (4, 'MENUCO07500120190314161720782118', '顶级目录', 'fa fa-user-o', '/view/menu/manager', '', '00', 0, '2019-05-25 18:37:02', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (5, 'MENUCO07500120190314161720782119', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782118', '00', 0, '2019-03-14 16:26:46', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (6, 'MENUCO07500120190314161720782219', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782118', '00', 0, '2019-03-14 16:26:46', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (7, 'MENUCO07500120190314161720782319', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782118', '00', 0, '2019-05-25 18:37:57', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (8, 'MENUCO07500120190314161720782311', '三级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782219', '00', 0, '2019-05-25 18:38:03', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (9, 'MENUCO07500120190314161720782312', '三级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782219', '00', 0, '2019-05-25 18:38:08', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (10, 'MENUCO07500120190314162720782311', '一级A', 'fa fa-user-o', '/view/menu/manager', '', '00', 0, '2019-05-25 18:40:38', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (11, 'MENUCO075023120190312162720782311', '一级B', 'fa fa-user-o', '/view/menu/manager', '', '00', 0, '2019-05-25 18:46:51', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (12, 'MENUCO07500120190312162720782311', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO075023120190312162720782311', '00', 0, '2019-05-25 18:46:52', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (13, 'MENUCO07500120190312162720782312', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO075023120190312162720782311', '00', 0, '2019-05-25 18:46:53', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (14, 'MENUCO07500120190312162720782313', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO075023120190312162720782311', '00', 0, '2019-05-25 18:46:54', 'sys', '2019-03-14 16:18:00', 'sys', '00');

-- ----------------------------
-- Table structure for organization_basic_face
-- ----------------------------
DROP TABLE IF EXISTS `organization_basic_face`;
CREATE TABLE `organization_basic_face`  (
  `ID` int(11) NOT NULL,
  `ORGANIZATION_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '编号',
  `ORGANIZATION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `PAREN_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '上级编号',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role_basic_face
-- ----------------------------
DROP TABLE IF EXISTS `role_basic_face`;
CREATE TABLE `role_basic_face`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '角色编号',
  `ROLE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '角色名称',
  `ROLE_LABEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '角色标签',
  `ROLE_MEMO` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '角色备注',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_basic_face
-- ----------------------------
INSERT INTO `role_basic_face` VALUES (1, 'ROLECO07500120190314161720782115', 'admin', '管理员', '管理员角色', '2019-03-14 16:22:29', 'sys', '2019-03-07 17:11:40', 'sys', '00');

-- ----------------------------
-- Table structure for role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_relation`;
CREATE TABLE `role_menu_relation`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '角色编号',
  `MENU_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '菜单编号',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu_relation
-- ----------------------------
INSERT INTO `role_menu_relation` VALUES (1, 'ROLECO07500120190314161720782115', 'MENUCO07500120190314161720782115', '2019-03-14 16:22:35', 'sys', '2019-03-14 16:22:35', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (2, 'ROLECO07500120190314161720782115', 'MENUCO07500120190314161720782116', '2019-03-14 16:22:35', 'sys', '2019-03-14 16:22:35', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (3, 'ROLECO07500120190314161720782115', 'MENUCO07500120190314161720782117', '2019-03-14 16:22:35', 'sys', '2019-03-14 16:22:35', 'sys', '00');

-- ----------------------------
-- Table structure for user_basic_face
-- ----------------------------
DROP TABLE IF EXISTS `user_basic_face`;
CREATE TABLE `user_basic_face`  (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '用户编号',
  `USER_LABEL` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '用户中文名',
  `USER_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '用户名称',
  `LOGIN_PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '账号登录密码',
  `SECURITY_PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '安全密码',
  `IS_LOCK` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '是否锁定(00-未锁定 01-锁定)',
  `IS_REMEMBER` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '是否记住登录状态(00-记录 01-不记录)',
  `IS_NEED_RESET_PASSWORD` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '01' COMMENT '是否需要重置密码(00-不需要 01-需要)',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_basic_face
-- ----------------------------
INSERT INTO `user_basic_face` VALUES (1, 'USER21100120190524180222553000', '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'e10adc3949ba59abbe56e057f20f883e', '00', '00', '01', '2019-05-25 14:36:38', 'sys', '2019-03-07 12:31:02', 'admin', '00');
INSERT INTO `user_basic_face` VALUES (37, 'USER21100120190524180222558000', '测试用', 'ceshi', '14e1b600b1fd579f47433b88e8d85291', '', '00', '00', '01', '2019-05-24 18:09:44', 'sys', '2019-05-24 18:02:22', 'admin', '01');
INSERT INTO `user_basic_face` VALUES (38, 'USER21100120190525100457224000', '用户', 'user', '', '', '00', '00', '01', '2019-05-25 10:04:57', 'sys', '2019-05-25 10:04:57', 'sys', '00');
INSERT INTO `user_basic_face` VALUES (39, 'USER21100120190525100528043000', '用户A', 'userA', 'd41d8cd98f00b204e9800998ecf8427e', '', '01', '00', '01', '2019-05-25 10:05:36', 'sys', '2019-05-25 10:05:28', 'admin', '00');

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ROLE_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '角色编号',
  `USER_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '用户编号',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
INSERT INTO `user_role_relation` VALUES (1, 'ROLECO07500120190314161720782115', 'USER21100120190524180222553000', '2019-05-25 13:22:19', 'sys', '2019-03-13 14:39:19', 'sys', '00');

SET FOREIGN_KEY_CHECKS = 1;
