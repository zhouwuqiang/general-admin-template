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

 Date: 09/06/2019 01:32:31
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
  `MENU_TYPE` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '菜单类型(01-目录 02-菜单 03-按钮)',
  `DISPLAY_INDEX` int(11) UNSIGNED DEFAULT 0 COMMENT '显示顺序',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_basic_face
-- ----------------------------
INSERT INTO `menu_basic_face` VALUES (1, 'MENUCO07500120190314161720782115', '系统设置', 'fa fa-cog', '#', '', '01', 0, '2019-06-04 19:42:55', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (2, 'MENUCO07500120190314161720782116', '用户管理', 'fa fa-user-o', '/view/user/manager', 'MENUCO07500120190314161720782115', '02', 0, '2019-06-04 19:45:42', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (3, 'MENUCO07500120190314161720782117', '菜单管理', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782115', '02', 0, '2019-06-04 19:45:50', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (4, 'MENUCO07500120190314161720782118', '顶级目录', 'fa fa-user-o', '/view/menu/manager', '', '01', 0, '2019-06-04 19:50:14', 'sys', '2019-03-14 16:18:00', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (5, 'MENUCO07500120190314161720782119', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782118', '00', 0, '2019-06-04 19:50:11', 'sys', '2019-03-14 16:18:00', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (6, 'MENUCO07500120190314161720782219', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782118', '00', 0, '2019-06-04 19:50:08', 'sys', '2019-03-14 16:18:00', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (7, 'MENUCO07500120190314161720782319', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782118', '00', 0, '2019-06-04 19:50:06', 'sys', '2019-03-14 16:18:00', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (8, 'MENUCO07500120190314161720782311', '三级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782219', '00', 0, '2019-06-04 19:49:58', 'sys', '2019-03-14 16:18:00', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (9, 'MENUCO07500120190314161720782312', '三级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO07500120190314161720782219', '00', 0, '2019-06-04 19:50:03', 'sys', '2019-03-14 16:18:00', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (10, 'MENUCO07500120190314162720782311', '一级A', 'fa fa-user-o', '/view/menu/manager', '', '00', 0, '2019-06-04 19:49:41', 'sys', '2019-03-14 16:18:00', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (11, 'MENUCO075023120190312162720782311', '一级B', 'fa fa-user-o', '/view/menu/manager', '', '00', 0, '2019-06-04 19:49:55', 'sys', '2019-03-14 16:18:00', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (12, 'MENUCO07500120190312162720782311', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO075023120190312162720782311', '00', 0, '2019-06-04 19:49:53', 'sys', '2019-03-14 16:18:00', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (13, 'MENUCO07500120190312162720782312', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO075023120190312162720782311', '00', 0, '2019-06-04 19:49:50', 'sys', '2019-03-14 16:18:00', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (14, 'MENUCO07500120190312162720782313', '二级', 'fa fa-user-o', '/view/menu/manager', 'MENUCO075023120190312162720782311', '00', 0, '2019-06-04 19:49:47', 'sys', '2019-03-14 16:18:00', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (15, 'MENUCO07500120190314161720782121', '组织管理', 'fa fa-user-o', '/view/organization/manager', 'MENUCO07500120190314161720782115', '02', 0, '2019-06-04 19:45:55', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (16, 'MENUCO07500120190314161720782122', '角色管理', 'fa fa-user-o', '/view/role/manager', 'MENUCO07500120190314161720782115', '02', 0, '2019-06-04 19:46:03', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (17, 'MENUCO07500120190314161720782123', '字典管理', 'fa fa-user-o', '/view/wordbook/manager', 'MENUCO07500120190314161720782115', '02', 0, '2019-06-04 19:46:10', 'sys', '2019-03-14 16:18:00', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (18, 'MENU21100120190604194638872000', '监控管理', 'fa fa-user-o', '#', '', '01', 0, '2019-06-06 23:13:38', 'sys', '2019-06-04 19:46:38', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (19, 'MENU21100120190604194655500000', '登录日志', 'fa fa-user-o', '#', 'MENU21100120190604194638872000', '02', 0, '2019-06-06 23:13:46', 'sys', '2019-06-04 19:46:55', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (20, 'MENU21100120190604194714259000', '在线用户', 'fa fa-user-o', '/view/online/manager', 'MENU21100120190604194638872000', '02', 0, '2019-06-07 22:43:32', 'sys', '2019-06-04 19:47:14', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (21, 'MENU21100120190605185720035000', 'root', '', '#', '', '01', 0, '2019-06-06 23:11:27', 'sys', '2019-06-05 18:57:20', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (22, 'MENU21100120190605185731997000', '一级', '', '#', 'MENU21100120190605185720035000', '02', 0, '2019-06-06 23:11:25', 'sys', '2019-06-05 18:57:31', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (23, 'MENU21100120190605185750981000', '一级', '', '#', 'MENU21100120190605185720035000', '01', 0, '2019-06-06 23:11:22', 'sys', '2019-06-05 18:57:50', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (24, 'MENU21100120190605185804813000', '二级', '', '#', 'MENU21100120190605185750981000', '00', 0, '2019-06-06 23:11:19', 'sys', '2019-06-05 18:58:04', 'sys', '01');
INSERT INTO `menu_basic_face` VALUES (25, 'MENU21100120190607011116860000', '系统监控', '', '#', 'MENU21100120190604194638872000', '02', 0, '2019-06-07 01:11:16', 'sys', '2019-06-07 01:11:16', 'sys', '00');
INSERT INTO `menu_basic_face` VALUES (26, 'MENU21100120190608000144976000', '任务管理', '', '/view/schedule/manager', 'MENUCO07500120190314161720782115', '02', 0, '2019-06-08 00:01:45', 'sys', '2019-06-08 00:01:45', 'sys', '00');

-- ----------------------------
-- Table structure for organization_basic_face
-- ----------------------------
DROP TABLE IF EXISTS `organization_basic_face`;
CREATE TABLE `organization_basic_face`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ORGANIZATION_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '编号',
  `ORGANIZATION_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `PAREN_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '上级编号',
  `ORGANIZATION_STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `ORGANIZATION_TYPE` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '类型(00-部门,01-岗位)',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organization_basic_face
-- ----------------------------
INSERT INTO `organization_basic_face` VALUES (3, 'ORG21100120190604173234419000', '后台科技', '', NULL, '01', '2019-06-05 22:55:08', 'sys', '2019-06-04 17:32:34', 'sys', '00');
INSERT INTO `organization_basic_face` VALUES (4, 'ORG21100120190604173345879000', '财务部', 'ORG21100120190604173234419000', NULL, '01', '2019-06-04 17:51:47', 'sys', '2019-06-04 17:33:45', 'sys', '00');
INSERT INTO `organization_basic_face` VALUES (5, 'ORG21100120190604173721912000', '开发部', 'ORG21100120190604173234419000', NULL, '01', '2019-06-04 17:51:52', 'sys', '2019-06-04 17:37:21', 'sys', '00');
INSERT INTO `organization_basic_face` VALUES (6, 'ORG21100120190604173835471000', '开发经理', 'ORG21100120190604173721912000', NULL, '02', '2019-06-05 19:17:10', 'sys', '2019-06-04 17:38:35', 'sys', '00');
INSERT INTO `organization_basic_face` VALUES (7, 'ORG21100120190604173903286000', '开发岗', 'ORG21100120190604173721912000', NULL, '02', '2019-06-05 19:17:09', 'sys', '2019-06-04 17:39:03', 'sys', '00');
INSERT INTO `organization_basic_face` VALUES (8, 'ORG21100120190604175137661000', '商务部', 'ORG21100120190604173234419000', NULL, '01', '2019-06-04 17:51:37', 'sys', '2019-06-04 17:51:37', 'sys', '00');
INSERT INTO `organization_basic_face` VALUES (9, 'ORG21100120190604175205391000', '人事部', 'ORG21100120190604173234419000', NULL, '01', '2019-06-05 20:57:52', 'sys', '2019-06-04 17:52:05', 'sys', '01');
INSERT INTO `organization_basic_face` VALUES (10, 'ORG21100120190604180441457000', '商务一组', 'ORG21100120190604175137661000', NULL, '01', '2019-06-05 19:17:11', 'sys', '2019-06-04 18:04:41', 'sys', '00');
INSERT INTO `organization_basic_face` VALUES (11, 'ORG21100120190604180452990000', '商务二组', 'ORG21100120190604175137661000', NULL, '01', '2019-06-05 19:17:14', 'sys', '2019-06-04 18:04:52', 'sys', '00');
INSERT INTO `organization_basic_face` VALUES (12, 'ORG21100120190605203005291000', '组长', 'ORG21100120190604180441457000', NULL, '02', '2019-06-05 20:30:05', 'sys', '2019-06-05 20:30:05', 'sys', '00');
INSERT INTO `organization_basic_face` VALUES (13, 'ORG21100120190605203022049000', '组员', 'ORG21100120190604180441457000', NULL, '02', '2019-06-05 20:30:22', 'sys', '2019-06-05 20:30:22', 'sys', '00');

-- ----------------------------
-- Table structure for organization_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `organization_menu_relation`;
CREATE TABLE `organization_menu_relation`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ORGANIZATION_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '编号',
  `MENU_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '菜单编号',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organization_menu_relation
-- ----------------------------
INSERT INTO `organization_menu_relation` VALUES (1, 'ORG21100120190604173345879000', 'MENUCO07500120190314161720782115', '2019-06-05 21:22:18', 'sys', '2019-06-05 21:22:18', 'sys', '01');
INSERT INTO `organization_menu_relation` VALUES (2, 'ORG21100120190604173345879000', 'MENUCO07500120190314161720782116', '2019-06-05 21:22:18', 'sys', '2019-06-05 21:22:18', 'sys', '01');
INSERT INTO `organization_menu_relation` VALUES (3, 'ORG21100120190604173234419000', 'MENUCO07500120190314161720782115', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:22:23', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (4, 'ORG21100120190604173234419000', 'MENUCO07500120190314161720782116', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:22:23', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (5, 'ORG21100120190604173234419000', 'MENUCO07500120190314161720782117', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:27:23', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (6, 'ORG21100120190604173234419000', 'MENUCO07500120190314161720782121', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:29:57', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (7, 'ORG21100120190604173234419000', 'MENUCO07500120190314161720782122', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:29:57', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (8, 'ORG21100120190604173234419000', 'MENUCO07500120190314161720782123', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:29:57', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (9, 'ORG21100120190604173234419000', 'MENU21100120190604194638872000', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:29:57', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (10, 'ORG21100120190604173234419000', 'MENU21100120190604194655500000', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:29:57', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (11, 'ORG21100120190604173234419000', 'MENU21100120190604194714259000', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:29:57', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (12, 'ORG21100120190604173234419000', 'MENU21100120190605185720035000', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:29:57', 'sys', '01');
INSERT INTO `organization_menu_relation` VALUES (13, 'ORG21100120190604173234419000', 'MENU21100120190605185731997000', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:29:57', 'sys', '01');
INSERT INTO `organization_menu_relation` VALUES (14, 'ORG21100120190604173234419000', 'MENU21100120190605185750981000', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:29:57', 'sys', '01');
INSERT INTO `organization_menu_relation` VALUES (15, 'ORG21100120190604173234419000', 'MENU21100120190605185804813000', '2019-06-06 01:00:24', 'sys', '2019-06-05 21:29:57', 'sys', '01');
INSERT INTO `organization_menu_relation` VALUES (16, 'ORG21100120190604173835471000', 'MENUCO07500120190314161720782115', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:13', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (17, 'ORG21100120190604173835471000', 'MENUCO07500120190314161720782116', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:13', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (18, 'ORG21100120190604173835471000', 'MENUCO07500120190314161720782117', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:13', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (19, 'ORG21100120190604173835471000', 'MENUCO07500120190314161720782121', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:13', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (20, 'ORG21100120190604173835471000', 'MENUCO07500120190314161720782122', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:13', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (21, 'ORG21100120190604173835471000', 'MENUCO07500120190314161720782123', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:13', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (22, 'ORG21100120190604173835471000', 'MENU21100120190604194638872000', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:13', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (23, 'ORG21100120190604173835471000', 'MENU21100120190604194655500000', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:13', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (24, 'ORG21100120190604173835471000', 'MENU21100120190604194714259000', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:13', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (25, 'ORG21100120190604173835471000', 'MENU21100120190605185720035000', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:13', 'sys', '01');
INSERT INTO `organization_menu_relation` VALUES (26, 'ORG21100120190604173835471000', 'MENU21100120190605185731997000', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:13', 'sys', '01');
INSERT INTO `organization_menu_relation` VALUES (27, 'ORG21100120190604173835471000', 'MENU21100120190605185750981000', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:14', 'sys', '01');
INSERT INTO `organization_menu_relation` VALUES (28, 'ORG21100120190604173835471000', 'MENU21100120190605185804813000', '2019-06-08 00:01:57', 'sys', '2019-06-05 21:30:14', 'sys', '01');
INSERT INTO `organization_menu_relation` VALUES (29, 'ORG21100120190604173903286000', 'MENU21100120190605185720035000', '2019-06-06 21:46:04', 'sys', '2019-06-06 21:46:04', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (30, 'ORG21100120190604173903286000', 'MENU21100120190605185731997000', '2019-06-06 21:46:04', 'sys', '2019-06-06 21:46:04', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (31, 'ORG21100120190604173903286000', 'MENU21100120190605185750981000', '2019-06-06 21:46:04', 'sys', '2019-06-06 21:46:04', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (32, 'ORG21100120190604173903286000', 'MENU21100120190605185804813000', '2019-06-06 21:46:04', 'sys', '2019-06-06 21:46:04', 'sys', '00');
INSERT INTO `organization_menu_relation` VALUES (33, 'ORG21100120190604173835471000', 'MENU21100120190608000144976000', '2019-06-08 00:01:59', 'sys', '2019-06-08 00:01:59', 'sys', '00');

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_basic_face
-- ----------------------------
INSERT INTO `role_basic_face` VALUES (1, 'ROLECO07500120190314161720782115', 'admin', '管理员', '管理员角色', '2019-06-06 21:56:02', 'sys', '2019-03-07 17:11:40', 'sys', '01');
INSERT INTO `role_basic_face` VALUES (2, 'ROLE21100120190604204042776000', '', '', '', '2019-06-04 20:44:01', 'sys', '2019-06-04 20:40:42', 'sys', '01');
INSERT INTO `role_basic_face` VALUES (3, 'ROLE21100120190604204118515000', '', '', '', '2019-06-04 20:42:19', 'sys', '2019-06-04 20:41:18', 'sys', '01');
INSERT INTO `role_basic_face` VALUES (4, 'ROLE21100120190604204326914000', 'admin', '', '系统管理员角色', '2019-06-06 01:02:42', 'sys', '2019-06-04 20:43:28', 'sys', '00');
INSERT INTO `role_basic_face` VALUES (5, 'ROLE21100120190604204700835000', 'admin', '', 'admin', '2019-06-04 20:47:09', 'sys', '2019-06-04 20:47:03', 'sys', '01');
INSERT INTO `role_basic_face` VALUES (6, 'ROLE21100120190606011116366000', 'manager', '', '管理员', '2019-06-06 01:11:16', 'sys', '2019-06-06 01:11:16', 'sys', '00');

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
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu_relation
-- ----------------------------
INSERT INTO `role_menu_relation` VALUES (1, 'ROLECO07500120190314161720782115', 'MENUCO07500120190314161720782115', '2019-03-14 16:22:35', 'sys', '2019-03-14 16:22:35', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (2, 'ROLECO07500120190314161720782115', 'MENUCO07500120190314161720782116', '2019-03-14 16:22:35', 'sys', '2019-03-14 16:22:35', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (3, 'ROLECO07500120190314161720782115', 'MENUCO07500120190314161720782117', '2019-03-14 16:22:35', 'sys', '2019-03-14 16:22:35', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (4, 'ROLECO07500120190314161720782115', 'MENUCO07500120190314161720782121', '2019-05-27 16:29:03', 'sys', '2019-05-27 16:29:01', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (5, 'ROLECO07500120190314161720782115', 'MENUCO07500120190314161720782122', '2019-05-29 12:28:52', 'sys', '2019-05-27 16:34:48', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (6, 'ROLECO07500120190314161720782115', 'MENUCO07500120190314161720782123', '2019-05-29 12:29:30', 'sys', '2019-05-27 16:34:48', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (7, 'ROLE21100120190604204326914000', 'MENUCO07500120190314161720782115', '2019-06-05 21:30:46', NULL, NULL, NULL, '00');
INSERT INTO `role_menu_relation` VALUES (8, 'ROLE21100120190604204326914000', 'MENUCO07500120190314161720782116', '2019-06-05 21:30:46', NULL, NULL, NULL, '00');
INSERT INTO `role_menu_relation` VALUES (9, 'ROLE21100120190604204326914000', 'MENUCO07500120190314161720782117', '2019-06-05 21:30:46', NULL, NULL, NULL, '00');
INSERT INTO `role_menu_relation` VALUES (10, 'ROLE21100120190604204326914000', 'MENUCO07500120190314161720782121', '2019-06-05 21:30:46', NULL, NULL, NULL, '00');
INSERT INTO `role_menu_relation` VALUES (11, 'ROLE21100120190604204326914000', 'MENUCO07500120190314161720782122', '2019-06-05 21:30:46', NULL, NULL, NULL, '00');
INSERT INTO `role_menu_relation` VALUES (12, 'ROLE21100120190604204326914000', 'MENUCO07500120190314161720782123', '2019-06-05 21:30:46', NULL, NULL, NULL, '00');
INSERT INTO `role_menu_relation` VALUES (13, 'ROLE21100120190604204326914000', 'MENU21100120190604194638872000', '2019-06-05 21:30:46', 'sys', '2019-06-05 20:48:39', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (14, 'ROLE21100120190604204326914000', 'MENU21100120190604194655500000', '2019-06-05 21:30:46', 'sys', '2019-06-05 20:48:40', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (15, 'ROLE21100120190604204326914000', 'MENU21100120190604194714259000', '2019-06-05 21:30:46', 'sys', '2019-06-05 20:48:40', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (16, 'ROLE21100120190604204326914000', 'MENU21100120190605185720035000', '2019-06-05 21:30:46', 'sys', '2019-06-05 21:30:46', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (17, 'ROLE21100120190604204326914000', 'MENU21100120190605185731997000', '2019-06-05 21:30:46', 'sys', '2019-06-05 21:30:46', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (18, 'ROLE21100120190604204326914000', 'MENU21100120190605185750981000', '2019-06-05 21:30:46', 'sys', '2019-06-05 21:30:46', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (19, 'ROLE21100120190604204326914000', 'MENU21100120190605185804813000', '2019-06-05 21:30:46', 'sys', '2019-06-05 21:30:46', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (20, 'ROLE21100120190606011116366000', 'MENU21100120190604194638872000', '2019-06-07 01:11:45', 'sys', '2019-06-06 21:56:14', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (21, 'ROLE21100120190606011116366000', 'MENU21100120190604194655500000', '2019-06-07 01:11:45', 'sys', '2019-06-06 21:56:14', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (22, 'ROLE21100120190606011116366000', 'MENU21100120190604194714259000', '2019-06-07 01:11:45', 'sys', '2019-06-06 21:56:14', 'sys', '00');
INSERT INTO `role_menu_relation` VALUES (23, 'ROLE21100120190606011116366000', 'MENU21100120190607011116860000', '2019-06-07 01:11:46', 'sys', '2019-06-07 01:11:46', 'sys', '00');

-- ----------------------------
-- Table structure for scheduled_task
-- ----------------------------
DROP TABLE IF EXISTS `scheduled_task`;
CREATE TABLE `scheduled_task`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '任务编号',
  `task_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务名称',
  `task_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '任务类型(字典表: task_code)',
  `task_url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '远程任务地址',
  `task_service_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '任务类名',
  `task_service_method` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '任务方法',
  `task_desc` varchar(128) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL COMMENT '任务描述',
  `task_cron` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务表达式',
  `task_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '任务状态',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scheduled_task
-- ----------------------------
INSERT INTO `scheduled_task` VALUES (6, 'TASK21100120190608003845538000', 'demo', '01', '#', '', '', 'demo', '* * * * * *', '01', '2019-06-08 00:38:45', 'sys', '2019-06-08 00:38:45', 'sys', '00');
INSERT INTO `scheduled_task` VALUES (7, 'TASK21100120190609012800595000', 'demo2', '01', '#', '', '', '执行', '* * * * * * ', '02', '2019-06-09 01:28:00', 'sys', '2019-06-09 01:28:00', 'sys', '00');

-- ----------------------------
-- Table structure for scheduled_task_form
-- ----------------------------
DROP TABLE IF EXISTS `scheduled_task_form`;
CREATE TABLE `scheduled_task_form`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '任务编号',
  `input_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '任务表单名称',
  `input_label` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '任务表单label',
  `input_memo` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '任务表单备注',
  `input_not_null` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '任务表单是否必填',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scheduled_task_form
-- ----------------------------
INSERT INTO `scheduled_task_form` VALUES (6, '', 'FORM21100120190609002741824000', '', '', '', '2019-06-09 00:27:41', 'sys', '2019-06-09 00:27:41', 'sys', '01');
INSERT INTO `scheduled_task_form` VALUES (7, 'TASK21100120190608003845538000', 'FORM21100120190609002905077000', '用户名', '1', '', '2019-06-09 00:35:49', 'sys', '2019-06-09 00:29:05', 'sys', '01');
INSERT INTO `scheduled_task_form` VALUES (8, 'TASK21100120190608003845538000', 'FORM21100120190609003530430000', '用户名123', '232', '01', '2019-06-09 00:57:59', 'sys', '2019-06-09 00:35:30', 'sys', '00');
INSERT INTO `scheduled_task_form` VALUES (9, 'TASK21100120190608003845538000', 'FORM21100120190609010027192000', '日期参数', 'yyyy-mm-dd', '01', '2019-06-09 01:00:27', 'sys', '2019-06-09 01:00:27', 'sys', '00');

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
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_basic_face
-- ----------------------------
INSERT INTO `user_basic_face` VALUES (1, 'USER21100120190524180222553000', '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'e10adc3949ba59abbe56e057f20f883e', '00', '00', '01', '2019-06-06 22:04:31', 'admin', '2019-03-07 12:31:02', 'admin', '00');
INSERT INTO `user_basic_face` VALUES (54, 'USER21100120190606214547882000', '测试', 'demo', 'e10adc3949ba59abbe56e057f20f883e', '', '00', '00', '01', '2019-06-07 01:13:20', 'admin', '2019-06-06 21:45:47', 'admin', '00');

-- ----------------------------
-- Table structure for user_organization_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_organization_relation`;
CREATE TABLE `user_organization_relation`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ORGANIZATION_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '编号',
  `USER_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '用户编号',
  `POST_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '岗位名称',
  `UPDATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `UPDATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '更新用户编号',
  `CREATE_TIME` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'sys' COMMENT '创建用户编号',
  `DELETE_FLAG` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_organization_relation
-- ----------------------------
INSERT INTO `user_organization_relation` VALUES (1, 'ORG21100120190604173835471000', 'USER21100120190606182636282000', '开发部\\开发经理', '2019-06-06 19:49:06', 'sys', '2019-06-06 18:26:36', 'sys', '01');
INSERT INTO `user_organization_relation` VALUES (2, 'ORG21100120190604173903286000', 'USER21100120190606182636282000', '开发部\\开发岗', '2019-06-06 19:49:06', 'sys', '2019-06-06 19:49:06', 'sys', '00');
INSERT INTO `user_organization_relation` VALUES (3, 'ORG21100120190604173903286000', 'USER21100120190606214547882000', '开发部\\开发岗', '2019-06-07 01:13:20', 'sys', '2019-06-06 21:45:47', 'sys', '01');
INSERT INTO `user_organization_relation` VALUES (4, 'ORG21100120190604173721912000', 'USER21100120190524180222553000', '开发部', '2019-06-06 21:59:28', 'sys', '2019-06-06 21:55:19', 'sys', '01');
INSERT INTO `user_organization_relation` VALUES (5, 'ORG21100120190604173721912000', 'USER21100120190524180222553000', '开发部', '2019-06-06 22:04:14', 'sys', '2019-06-06 21:59:28', 'sys', '01');
INSERT INTO `user_organization_relation` VALUES (6, 'ORG21100120190604173721912000', 'USER21100120190524180222553000', '开发部', '2019-06-06 22:05:03', 'sys', '2019-06-06 22:04:14', 'sys', '01');
INSERT INTO `user_organization_relation` VALUES (7, 'ORG21100120190604173721912000', 'USER21100120190524180222553000', '开发部', '2019-06-06 22:05:04', 'sys', '2019-06-06 22:05:04', 'sys', '01');
INSERT INTO `user_organization_relation` VALUES (8, 'ORG21100120190604173721912000', 'USER21100120190524180222553000', '开发部', '2019-06-06 22:05:25', 'sys', '2019-06-06 22:05:04', 'sys', '01');
INSERT INTO `user_organization_relation` VALUES (9, 'ORG21100120190604173835471000', 'USER21100120190524180222553000', '开发部\\开发经理', '2019-06-06 22:05:25', 'sys', '2019-06-06 22:05:25', 'sys', '00');
INSERT INTO `user_organization_relation` VALUES (10, 'ORG21100120190604173903286000', 'USER21100120190606214547882000', '开发部\\开发岗', '2019-06-07 01:13:20', 'sys', '2019-06-07 01:13:20', 'sys', '00');

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
INSERT INTO `user_role_relation` VALUES (1, 'ROLE21100120190606011116366000', 'USER21100120190606214547882000', '2019-06-07 01:13:20', 'sys', '2019-06-07 01:13:20', 'sys', '00');

-- ----------------------------
-- Table structure for wordbook
-- ----------------------------
DROP TABLE IF EXISTS `wordbook`;
CREATE TABLE `wordbook`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wordbook_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '字典编码',
  `wordbook_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '字典名称',
  `wordbook_status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '字典状态(00-正常 01-禁用)',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'sys' COMMENT '更新用户编号',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'sys' COMMENT '创建用户编号',
  `delete_flag` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wordbook
-- ----------------------------
INSERT INTO `wordbook` VALUES (4, 'wordbook_status', '字典状态', '01', '字典状态', '2019-06-04 00:53:17', 'sys', '2019-06-04 00:43:24', 'sys', '00');
INSERT INTO `wordbook` VALUES (8, 'delete_flag', '删除标志', '01', '系统删除字段枚举', '2019-06-04 00:51:49', 'sys', '2019-06-04 00:43:24', 'sys', '00');
INSERT INTO `wordbook` VALUES (9, 'organization_type', '组织结构', '01', '系统组织结构', '2019-06-04 16:53:40', 'sys', '2019-06-04 16:53:28', 'sys', '00');
INSERT INTO `wordbook` VALUES (10, 'menu_type', '菜单类型', '01', '', '2019-06-04 19:36:53', 'sys', '2019-06-04 19:36:53', 'sys', '00');
INSERT INTO `wordbook` VALUES (11, 'task_type', '任务类型', '01', NULL, '2019-06-08 00:19:11', 'sys', '2019-06-08 00:19:11', 'sys', '00');
INSERT INTO `wordbook` VALUES (12, 'is_or_not', '是否允许为空', '01', NULL, '2019-06-09 00:51:12', 'sys', '2019-06-09 00:51:12', 'sys', '00');

-- ----------------------------
-- Table structure for wordbook_attribute
-- ----------------------------
DROP TABLE IF EXISTS `wordbook_attribute`;
CREATE TABLE `wordbook_attribute`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wordbook_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '属性编码',
  `attribute_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '属性值',
  `attribute_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '属性名称',
  `attribute_status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '属性状态(00-正常 01-禁用)',
  `attribute_memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '属性备注',
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'sys' COMMENT '更新用户编号',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'sys' COMMENT '创建用户编号',
  `delete_flag` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '00' COMMENT '删除标识(00-正常 01-删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wordbook_attribute
-- ----------------------------
INSERT INTO `wordbook_attribute` VALUES (4, 'wordbook_status', '01', '正常', '01', '测试状态', '2019-06-03 23:27:01', 'sys', '2019-06-03 21:43:21', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (5, 'wordbook_status', '02', '禁用', '01', '测试禁用', '2019-06-03 23:27:05', 'sys', '2019-06-03 21:43:21', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (12, 'delete_flag', '00', '正常', '01', '', '2019-06-04 00:43:24', 'sys', '2019-06-04 00:43:24', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (13, 'delete_flag', '01', '删除', '01', '', '2019-06-04 00:43:24', 'sys', '2019-06-04 00:43:24', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (14, 'organization_type', '01', '部门', '01', '', '2019-06-04 16:53:28', 'sys', '2019-06-04 16:53:28', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (15, 'organization_type', '02', '岗位', '01', '', '2019-06-04 16:53:28', 'sys', '2019-06-04 16:53:28', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (16, 'menu_type', '01', '目录', '01', '', '2019-06-04 19:36:53', 'sys', '2019-06-04 19:36:53', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (17, 'menu_type', '02', '菜单', '01', '', '2019-06-04 19:36:53', 'sys', '2019-06-04 19:36:53', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (18, 'menu_type', '03', '按钮', '01', '', '2019-06-04 19:36:53', 'sys', '2019-06-04 19:36:53', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (19, 'task_type', '01', '远程url调用', '01', NULL, '2019-06-08 00:19:11', 'sys', '2019-06-08 00:19:11', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (20, 'task_type', '02', '本地方法调用', '01', NULL, '2019-06-08 00:19:11', 'sys', '2019-06-08 00:19:11', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (21, 'is_or_not', '01', '是', '01', NULL, '2019-06-09 00:51:12', 'sys', '2019-06-09 00:51:12', 'sys', '00');
INSERT INTO `wordbook_attribute` VALUES (22, 'is_or_not', '02', '否', '01', NULL, '2019-06-09 00:51:12', 'sys', '2019-06-09 00:51:12', 'sys', '00');

SET FOREIGN_KEY_CHECKS = 1;
