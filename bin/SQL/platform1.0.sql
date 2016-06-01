/*
Navicat MySQL Data Transfer

Source Server         : localMySql
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : platform1.0

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-10-13 17:33:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for AC_APPLICATION
-- ----------------------------
DROP TABLE IF EXISTS `AC_APPLICATION`;
CREATE TABLE `AC_APPLICATION` (
  `APP_ID` varchar(36) NOT NULL,
  `APP_CODE` varchar(50) DEFAULT NULL,
  `APP_NAME` varchar(50) DEFAULT NULL,
  `APP_TYPE` varchar(255) DEFAULT NULL,
  `IS_OPEN` char(1) DEFAULT NULL,
  `OPEN_DATE` varchar(50) DEFAULT NULL,
  `URL` varchar(256) DEFAULT NULL,
  `APP_DESC` varchar(512) DEFAULT NULL,
  `IP_ADDR` varchar(50) DEFAULT NULL,
  `IP_PORT` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`APP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AC_APPLICATION
-- ----------------------------
INSERT INTO `AC_APPLICATION` VALUES ('a45beeb2-8912-4f24-9556-99d0f87fd621', 'platform', '共通应用', '0', '1', '2015-10-15', '', '', '', '');
INSERT INTO `AC_APPLICATION` VALUES ('root', '-1', '应用功能管理', '0', '1', '', 'http://localhost:8080/platform/', '', '', '');

-- ----------------------------
-- Table structure for AC_FUNC_GROUP
-- ----------------------------
DROP TABLE IF EXISTS `AC_FUNC_GROUP`;
CREATE TABLE `AC_FUNC_GROUP` (
  `FUNC_GROUP_ID` varchar(36) NOT NULL,
  `APP_ID` varchar(36) NOT NULL,
  `FUNC_GROUP_NAME` varchar(40) DEFAULT NULL,
  `PARENT_GROUP` varchar(36) DEFAULT NULL,
  `GROUP_LEVEL` int(11) DEFAULT NULL,
  `FUNC_GROUP_SEQ` varchar(256) DEFAULT NULL,
  `IS_LEAF` char(1) DEFAULT NULL,
  PRIMARY KEY (`FUNC_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AC_FUNC_GROUP
-- ----------------------------
INSERT INTO `AC_FUNC_GROUP` VALUES ('8a0e1172-32bd-48cf-a520-e012d6b0856f', 'a45beeb2-8912-4f24-9556-99d0f87fd621', '权限管理', '', null, null, null);
INSERT INTO `AC_FUNC_GROUP` VALUES ('b73513c7-294c-4e9c-b2c8-446ed8a49efe', 'a45beeb2-8912-4f24-9556-99d0f87fd621', '其它管理', '', null, null, null);
INSERT INTO `AC_FUNC_GROUP` VALUES ('d4a875fd-49a3-4bbf-9015-aa6c26a54403', 'a45beeb2-8912-4f24-9556-99d0f87fd621', '组织管理', '', null, null, null);
INSERT INTO `AC_FUNC_GROUP` VALUES ('f3147256-877c-47f2-afe4-136dbe5558ba', 'a45beeb2-8912-4f24-9556-99d0f87fd621', '字典管理', 'b73513c7-294c-4e9c-b2c8-446ed8a49efe', null, null, null);
INSERT INTO `AC_FUNC_GROUP` VALUES ('fa3aba61-a47b-4d70-a2d6-1d52c6bf5900', 'a45beeb2-8912-4f24-9556-99d0f87fd621', '文件管理', 'b73513c7-294c-4e9c-b2c8-446ed8a49efe', null, null, null);

-- ----------------------------
-- Table structure for AC_FUNCTION
-- ----------------------------
DROP TABLE IF EXISTS `AC_FUNCTION`;
CREATE TABLE `AC_FUNCTION` (
  `FUNC_ID` varchar(36) NOT NULL,
  `FUNC_GROUP_ID` varchar(36) DEFAULT NULL,
  `FUNC_NAME` varchar(128) NOT NULL COMMENT '功能名称',
  `FUNC_DESC` varchar(512) DEFAULT NULL COMMENT '功能描述',
  `FUNC_ACTION` varchar(256) DEFAULT NULL COMMENT '功能调用入口',
  `PARA_INFO` varchar(256) DEFAULT NULL COMMENT '输入参数',
  `IS_CHECK` char(1) DEFAULT NULL COMMENT '是否验证权限',
  `FUNC_TYPE` varchar(255) DEFAULT '0' COMMENT '功能类型',
  `IS_MENU` char(1) DEFAULT NULL COMMENT '可否定义为菜单',
  PRIMARY KEY (`FUNC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AC_FUNCTION
-- ----------------------------
INSERT INTO `AC_FUNCTION` VALUES ('24d3ac34-37b5-4e4b-b417-e3bd8dac2b78', '8a0e1172-32bd-48cf-a520-e012d6b0856f', '应用功能管理', '', 'acApplication/forMain.do', '', null, '0', '1');
INSERT INTO `AC_FUNCTION` VALUES ('92968bf6-c412-498d-91c9-742fec633155', 'd4a875fd-49a3-4bbf-9015-aa6c26a54403', '工作组', '', 'omGroup/forMain.do', '', null, '0', '1');
INSERT INTO `AC_FUNCTION` VALUES ('c4b98a04-61a3-4a82-92bb-603a25e0320f', 'd4a875fd-49a3-4bbf-9015-aa6c26a54403', '机构人员', '', 'omOrganization/forMain.do', '', null, '0', '1');
INSERT INTO `AC_FUNCTION` VALUES ('cd3db4a4-52ca-4b7a-887d-48a18f93bef6', '8a0e1172-32bd-48cf-a520-e012d6b0856f', '角色管理', '', 'acRole/forQueryPage.do', '', null, '0', '1');
INSERT INTO `AC_FUNCTION` VALUES ('d866fb52-da26-4cd0-a6ed-471b583435a3', 'fa3aba61-a47b-4d70-a2d6-1d52c6bf5900', '上传/下载文件示例页面', '', 'sysFile/forDemo.do', '', null, '0', '1');
INSERT INTO `AC_FUNCTION` VALUES ('db548e9c-51fc-41e6-9a7b-42b26c762823', 'f3147256-877c-47f2-afe4-136dbe5558ba', '进入字典管理页面', '', 'sysDict/forQueryPage.do', '', null, '0', '1');
INSERT INTO `AC_FUNCTION` VALUES ('f69e503d-bb68-473f-89c8-051544b74add', '8a0e1172-32bd-48cf-a520-e012d6b0856f', '菜单管理', '', 'acMenu/forMain.do', '', null, '0', '1');

-- ----------------------------
-- Table structure for AC_MENU
-- ----------------------------
DROP TABLE IF EXISTS `AC_MENU`;
CREATE TABLE `AC_MENU` (
  `MENU_ID` varchar(40) NOT NULL,
  `MENU_NAME` varchar(40) NOT NULL COMMENT '菜单名称',
  `MENU_LABEL` varchar(40) NOT NULL COMMENT '菜单显示（中文）',
  `MENU_CODE` varchar(40) DEFAULT NULL,
  `IS_LEAF` char(1) DEFAULT NULL COMMENT '是否叶子菜单',
  `MENU_ACTION` varchar(256) DEFAULT NULL COMMENT '功能调用入口(冗余）',
  `PARAMETER` varchar(256) DEFAULT NULL COMMENT '输入参数（冗余）',
  `UI_ENTRY` varchar(256) DEFAULT NULL COMMENT 'UI入口',
  `MENU_LEVEL` int(11) DEFAULT NULL COMMENT '菜单层次',
  `ROOT_ID` varchar(40) DEFAULT NULL COMMENT '根菜单',
  `PARENT_ID` varchar(40) DEFAULT NULL,
  `DISPLAY_ORDER` int(11) DEFAULT NULL COMMENT '显示顺序',
  `IMAGE_PATH` varchar(100) DEFAULT NULL COMMENT '菜单闭合图片路径',
  `EXPAND_PATH` varchar(100) DEFAULT NULL COMMENT '菜单展开图片路径',
  `MENU_SEQ` varchar(256) DEFAULT NULL COMMENT '菜单路径序列',
  `OPEN_MODE` varchar(255) DEFAULT NULL COMMENT '页面打开方式',
  `APP_ID` varchar(36) DEFAULT NULL COMMENT '应用编号',
  `FUNC_ID` varchar(36) DEFAULT NULL COMMENT '资源编号',
  `MENU_CSS` varchar(50) DEFAULT NULL,
  `ICON_SKIN` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`),
  KEY `AC_MENU_FUNC_ID_INDEX` (`FUNC_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AC_MENU
-- ----------------------------
INSERT INTO `AC_MENU` VALUES ('0e2d8d61-33b3-4cd2-8213-5e4091f1ef46', '工作组', '工作组', 'gzz', '1', 'omGroup/forMain.do', '', null, '3', null, 'ade3b8dd-34b3-461b-b6af-5f9ac6db6cb7', '200', null, null, null, null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', '92968bf6-c412-498d-91c9-742fec633155', '', 'RFicon03');
INSERT INTO `AC_MENU` VALUES ('29ba6504-d5f7-4c79-9ca3-cc5b5dbbc767', '系统设置', '系统设置', 'xtsz', '0', '', '', null, '1', null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', '0', null, null, null, null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', '', 'icon06.png', '');
INSERT INTO `AC_MENU` VALUES ('4021b577-6c7e-4e9d-a068-58778bdf1f3f', '角色管理', '角色管理', 'jsgl', '1', 'acRole/forQueryPage.do', '', null, '3', null, 'dd01ec78-d1ba-4632-893e-64d951936efe', '300', null, null, null, null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', 'cd3db4a4-52ca-4b7a-887d-48a18f93bef6', '', 'RFicon03');
INSERT INTO `AC_MENU` VALUES ('7e124a42-f7b8-4674-b33a-8009e317f339', '机构人员', '机构人员', 'jgry', '1', 'omOrganization/forMain.do', '', null, '3', null, 'ade3b8dd-34b3-461b-b6af-5f9ac6db6cb7', '100', null, null, null, null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', 'c4b98a04-61a3-4a82-92bb-603a25e0320f', '', 'RFicon03');
INSERT INTO `AC_MENU` VALUES ('843c862b-6209-4ab9-85e1-b47b556871d6', '其它管理', '其它管理', 'qtgl', '0', '', '', null, '2', null, '29ba6504-d5f7-4c79-9ca3-cc5b5dbbc767', '3', null, null, null, null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', '', '', '');
INSERT INTO `AC_MENU` VALUES ('98cfc903-aa52-4ac2-99cb-6dd3dbac6c06', '菜单管理', '菜单管理', 'cdgl', '1', 'acMenu/forMain.do', '', null, '3', null, 'dd01ec78-d1ba-4632-893e-64d951936efe', '200', null, null, null, null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', 'f69e503d-bb68-473f-89c8-051544b74add', '', 'RFicon03');
INSERT INTO `AC_MENU` VALUES ('adb471b3-2168-4832-887b-80c7cba412c1', '应用功能管理', '应用功能管理', 'yygngl', '1', 'acApplication/forMain.do', '', null, '3', null, 'dd01ec78-d1ba-4632-893e-64d951936efe', '100', null, null, null, null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', '24d3ac34-37b5-4e4b-b417-e3bd8dac2b78', '', 'RFicon03');
INSERT INTO `AC_MENU` VALUES ('ade3b8dd-34b3-461b-b6af-5f9ac6db6cb7', '组织管理', '组织管理', 'zzgl', '0', '', '', null, '2', null, '29ba6504-d5f7-4c79-9ca3-cc5b5dbbc767', '1', null, null, null, null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', '', '', '');
INSERT INTO `AC_MENU` VALUES ('c1afa1e9-6da3-4af0-8e54-25b4614c638e', '文件上传下载示例', '文件上传下载示例', 'wjscxzsl', '1', 'sysFile/forDemo.do', '', null, '3', null, '843c862b-6209-4ab9-85e1-b47b556871d6', '5', null, null, null, null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', 'd866fb52-da26-4cd0-a6ed-471b583435a3', '', 'RFicon03');
INSERT INTO `AC_MENU` VALUES ('c4894a90-7e91-4139-b43d-58668634da66', '字典管理', '字典管理', 'zdgl', '1', 'sysDict/forQueryPage.do', '', null, '3', null, '843c862b-6209-4ab9-85e1-b47b556871d6', '1', null, null, null, null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', 'db548e9c-51fc-41e6-9a7b-42b26c762823', '', 'RFicon03');
INSERT INTO `AC_MENU` VALUES ('dd01ec78-d1ba-4632-893e-64d951936efe', '权限管理', '权限管理', 'qxgl', '0', '', '', null, '2', null, '29ba6504-d5f7-4c79-9ca3-cc5b5dbbc767', '2', null, null, null, null, 'a45beeb2-8912-4f24-9556-99d0f87fd621', '', '', '');

-- ----------------------------
-- Table structure for AC_OPER_FUNC
-- ----------------------------
DROP TABLE IF EXISTS `AC_OPER_FUNC`;
CREATE TABLE `AC_OPER_FUNC` (
  `OPERATOR_ID` varchar(36) NOT NULL,
  `FUNC_ID` varchar(36) NOT NULL,
  `AUTH_TYPE` varchar(255) NOT NULL,
  `APP_ID` varchar(36) NOT NULL,
  `FUNC_GROUP_ID` varchar(36) NOT NULL,
  `START_DATE` varchar(50) DEFAULT NULL,
  `END_DATE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`OPERATOR_ID`,`FUNC_ID`,`AUTH_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AC_OPER_FUNC
-- ----------------------------

-- ----------------------------
-- Table structure for AC_OPERATOR
-- ----------------------------
DROP TABLE IF EXISTS `AC_OPERATOR`;
CREATE TABLE `AC_OPERATOR` (
  `OPERATOR_ID` varchar(36) NOT NULL,
  `USER_ID` varchar(64) NOT NULL COMMENT '登录用户名',
  `PASSWORD` varchar(100) DEFAULT NULL COMMENT '密码',
  `INVAL_DATE` varchar(50) DEFAULT NULL COMMENT '密码失效日期',
  `OPERATOR_NAME` varchar(64) DEFAULT NULL COMMENT '操作员名称',
  `AUTH_MODE` varchar(255) DEFAULT NULL COMMENT '认证模式',
  `STATUS` varchar(255) DEFAULT NULL COMMENT '操作员状态',
  `UNLOCK_TIME` varchar(50) DEFAULT NULL COMMENT '解锁时间',
  `MENU_TYPE` varchar(255) DEFAULT NULL COMMENT '菜单风格',
  `LAST_LOGIN` varchar(50) DEFAULT NULL COMMENT '最近登录时间',
  `ERR_COUNT` decimal(10,0) DEFAULT NULL COMMENT '最新错误登录次数',
  `START_DATE` varchar(50) DEFAULT NULL COMMENT '有效开始日期',
  `END_DATE` varchar(50) DEFAULT NULL COMMENT '有效截止日期',
  `VALID_TIME` varchar(255) DEFAULT NULL COMMENT '有效时间范围',
  `MAC_CODE` varchar(128) DEFAULT NULL COMMENT 'MAC码',
  `IP_ADDRESS` varchar(128) DEFAULT NULL COMMENT 'IP地址',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`OPERATOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AC_OPERATOR
-- ----------------------------
INSERT INTO `AC_OPERATOR` VALUES ('a53bf54d-c5b3-4615-a6d9-069b3d78319f', 'superadmin', 'c4ca4238a0b923820dcc509a6f75849b', null, '超级管理员', null, '0', null, null, null, null, null, null, null, null, null, '');
INSERT INTO `AC_OPERATOR` VALUES ('c39dc98e-ef3f-4f75-b33f-94b5b910396d', 'liuyx', '1211354de2a10b90eeb9d6c3b9df849f', null, 'liuyx', null, '0', null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for AC_OPERATOR_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `AC_OPERATOR_ROLE`;
CREATE TABLE `AC_OPERATOR_ROLE` (
  `OPERATOR_ID` varchar(36) NOT NULL,
  `ROLE_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`OPERATOR_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AC_OPERATOR_ROLE
-- ----------------------------
INSERT INTO `AC_OPERATOR_ROLE` VALUES ('a53bf54d-c5b3-4615-a6d9-069b3d78319f', '5940c671-fd4a-433e-9479-4a00cbb68292');
INSERT INTO `AC_OPERATOR_ROLE` VALUES ('c39dc98e-ef3f-4f75-b33f-94b5b910396d', '5940c671-fd4a-433e-9479-4a00cbb68292');

-- ----------------------------
-- Table structure for AC_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `AC_ROLE`;
CREATE TABLE `AC_ROLE` (
  `ROLE_ID` varchar(36) NOT NULL,
  `ROLE_NAME` varchar(64) DEFAULT NULL,
  `ROLE_TYPE` varchar(255) DEFAULT NULL,
  `ROLE_DESC` varchar(256) DEFAULT NULL,
  `APP_ID` varchar(36) DEFAULT NULL,
  `CREATE_TIME` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AC_ROLE
-- ----------------------------
INSERT INTO `AC_ROLE` VALUES ('5940c671-fd4a-433e-9479-4a00cbb68292', '管理员', '0', '管理员', 'a45beeb2-8912-4f24-9556-99d0f87fd621', '2015-09-29 09:13:12');

-- ----------------------------
-- Table structure for AC_ROLE_FUNC
-- ----------------------------
DROP TABLE IF EXISTS `AC_ROLE_FUNC`;
CREATE TABLE `AC_ROLE_FUNC` (
  `ROLE_ID` varchar(36) NOT NULL,
  `FUNC_ID` varchar(36) NOT NULL,
  `APP_ID` varchar(36) NOT NULL,
  `FUNC_GROUP_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`FUNC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of AC_ROLE_FUNC
-- ----------------------------
INSERT INTO `AC_ROLE_FUNC` VALUES ('5940c671-fd4a-433e-9479-4a00cbb68292', '24d3ac34-37b5-4e4b-b417-e3bd8dac2b78', 'a45beeb2-8912-4f24-9556-99d0f87fd621', '8a0e1172-32bd-48cf-a520-e012d6b0856f');
INSERT INTO `AC_ROLE_FUNC` VALUES ('5940c671-fd4a-433e-9479-4a00cbb68292', '92968bf6-c412-498d-91c9-742fec633155', 'a45beeb2-8912-4f24-9556-99d0f87fd621', 'd4a875fd-49a3-4bbf-9015-aa6c26a54403');
INSERT INTO `AC_ROLE_FUNC` VALUES ('5940c671-fd4a-433e-9479-4a00cbb68292', 'c4b98a04-61a3-4a82-92bb-603a25e0320f', 'a45beeb2-8912-4f24-9556-99d0f87fd621', 'd4a875fd-49a3-4bbf-9015-aa6c26a54403');
INSERT INTO `AC_ROLE_FUNC` VALUES ('5940c671-fd4a-433e-9479-4a00cbb68292', 'cd3db4a4-52ca-4b7a-887d-48a18f93bef6', 'a45beeb2-8912-4f24-9556-99d0f87fd621', '8a0e1172-32bd-48cf-a520-e012d6b0856f');
INSERT INTO `AC_ROLE_FUNC` VALUES ('5940c671-fd4a-433e-9479-4a00cbb68292', 'f69e503d-bb68-473f-89c8-051544b74add', 'a45beeb2-8912-4f24-9556-99d0f87fd621', '8a0e1172-32bd-48cf-a520-e012d6b0856f');

-- ----------------------------
-- Table structure for DEMO_TEST
-- ----------------------------
DROP TABLE IF EXISTS `DEMO_TEST`;
CREATE TABLE `DEMO_TEST` (
  `TEST_ID` varchar(36) NOT NULL,
  `VALUE` varchar(100) DEFAULT NULL,
  `TEXT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of DEMO_TEST
-- ----------------------------

-- ----------------------------
-- Table structure for OM_EMP_GROUP
-- ----------------------------
DROP TABLE IF EXISTS `OM_EMP_GROUP`;
CREATE TABLE `OM_EMP_GROUP` (
  `GROUP_ID` varchar(36) NOT NULL,
  `EMP_ID` varchar(36) NOT NULL,
  PRIMARY KEY (`GROUP_ID`,`EMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OM_EMP_GROUP
-- ----------------------------

-- ----------------------------
-- Table structure for OM_EMP_ORG
-- ----------------------------
DROP TABLE IF EXISTS `OM_EMP_ORG`;
CREATE TABLE `OM_EMP_ORG` (
  `ORG_ID` varchar(36) NOT NULL,
  `EMP_ID` varchar(36) NOT NULL,
  `IS_MAIN` char(1) DEFAULT NULL COMMENT '1：是  0 ： 非',
  PRIMARY KEY (`ORG_ID`,`EMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OM_EMP_ORG
-- ----------------------------
INSERT INTO `OM_EMP_ORG` VALUES ('5d850690-89b5-4ba8-8634-b1b451efb533', '52342b98-81be-4194-ba47-422355a6b7c9', '1');
INSERT INTO `OM_EMP_ORG` VALUES ('5d850690-89b5-4ba8-8634-b1b451efb533', '715abe96-d34f-4dbc-86df-397a3af8579a', '1');

-- ----------------------------
-- Table structure for OM_EMP_POSITION
-- ----------------------------
DROP TABLE IF EXISTS `OM_EMP_POSITION`;
CREATE TABLE `OM_EMP_POSITION` (
  `POSITION_ID` varchar(36) NOT NULL,
  `EMP_ID` varchar(36) NOT NULL,
  `IS_MAIN` char(1) DEFAULT NULL,
  PRIMARY KEY (`POSITION_ID`,`EMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OM_EMP_POSITION
-- ----------------------------

-- ----------------------------
-- Table structure for OM_EMPLOYEE
-- ----------------------------
DROP TABLE IF EXISTS `OM_EMPLOYEE`;
CREATE TABLE `OM_EMPLOYEE` (
  `EMP_ID` varchar(36) NOT NULL,
  `EMP_CODE` varchar(30) DEFAULT NULL COMMENT '人员代码',
  `OPERATOR_ID` varchar(36) DEFAULT NULL COMMENT '操作员主键',
  `USER_ID` varchar(30) DEFAULT NULL COMMENT '操作员账号（冗余字段）',
  `EMP_NAME` varchar(50) DEFAULT NULL COMMENT '人员姓名',
  `REAL_NAME` varchar(50) DEFAULT NULL COMMENT '人员全名（未使用）',
  `GENDER` varchar(255) DEFAULT NULL COMMENT '性别',
  `BIRTH_DATE` varchar(50) DEFAULT NULL COMMENT '出生日期',
  `POSITION` varchar(36) DEFAULT NULL COMMENT '基本岗位（在哪个岗位上新建的就是基本岗位）',
  `EMP_STATUS` varchar(255) DEFAULT NULL COMMENT '人员状态',
  `CARD_TYPE` varchar(255) DEFAULT NULL COMMENT '证件类型',
  `CARD_NO` varchar(20) DEFAULT NULL COMMENT '证件号码',
  `IN_DATE` varchar(50) DEFAULT NULL COMMENT '入职日期',
  `OUT_DATE` varchar(50) DEFAULT NULL COMMENT '离职日期',
  `O_TEL` varchar(50) DEFAULT NULL COMMENT '办公电话',
  `O_ADDRESS` varchar(255) DEFAULT NULL COMMENT '办公地址',
  `O_ZIP_CODE` varchar(10) DEFAULT NULL COMMENT '办公邮编',
  `O_EMAIL` varchar(128) DEFAULT NULL COMMENT '办公邮件',
  `FAX_NO` varchar(14) DEFAULT NULL COMMENT '传真号码',
  `MOBILE_NO` varchar(14) DEFAULT NULL COMMENT '手机号码',
  `MSN` varchar(16) DEFAULT NULL COMMENT 'MSN号码',
  `H_TEL` varchar(12) DEFAULT NULL COMMENT '家庭电话',
  `H_ADDRESS` varchar(128) DEFAULT NULL COMMENT '家庭地址',
  `H_ZIP_CODE` varchar(10) DEFAULT NULL COMMENT '家庭邮编',
  `P_EMAIL` varchar(128) DEFAULT NULL COMMENT '私人电子邮箱',
  `PARTY` varchar(255) DEFAULT NULL COMMENT '政治面貌',
  `DEGREE` varchar(255) DEFAULT NULL COMMENT '职级',
  `D_SUPERVISOR` varchar(36) DEFAULT NULL COMMENT '直接主管',
  `SPECIALTY` varchar(1024) DEFAULT NULL COMMENT '可授权角色(未使用）',
  `WORK_EXP` varchar(512) DEFAULT NULL COMMENT '工作描述',
  `REG_DATE` varchar(50) DEFAULT NULL COMMENT '注册日期(未使用）',
  `CREATE_TIME` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATE_TIME` varchar(50) DEFAULT NULL COMMENT '最新更新时间',
  `ORG_ID_LIST` varchar(128) DEFAULT NULL COMMENT '可管理机构',
  `ORG_ID` varchar(36) DEFAULT NULL COMMENT '主机构编号（在哪创建就设哪个）',
  `REMARK` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`EMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OM_EMPLOYEE
-- ----------------------------
INSERT INTO `OM_EMPLOYEE` VALUES ('52342b98-81be-4194-ba47-422355a6b7c9', 'liuyx', 'c39dc98e-ef3f-4f75-b33f-94b5b910396d', 'liuyx', 'liuyx', null, '1', '', '', '0', '1', 'liuyx', '', '', '', '', null, '', '', '', '', '', '', '', '', '', '', null, null, '', null, '2015-10-09 15:04:52', null, '', '5d850690-89b5-4ba8-8634-b1b451efb533', '');
INSERT INTO `OM_EMPLOYEE` VALUES ('715abe96-d34f-4dbc-86df-397a3af8579a', 'EMP100001', 'a53bf54d-c5b3-4615-a6d9-069b3d78319f', 'superadmin', '超级管理员', null, '1', '', '', '0', '1', '123456', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null, '', null, '2015-10-03 21:08:29', '2015-10-03 21:39:49', '', '5d850690-89b5-4ba8-8634-b1b451efb533', '');

-- ----------------------------
-- Table structure for OM_GROUP
-- ----------------------------
DROP TABLE IF EXISTS `OM_GROUP`;
CREATE TABLE `OM_GROUP` (
  `GROUP_ID` varchar(36) NOT NULL,
  `ORG_ID` varchar(36) DEFAULT NULL,
  `PARENT_GROUP_ID` varchar(36) DEFAULT NULL,
  `GROUP_LEVEL` int(11) DEFAULT NULL,
  `GROUP_NAME` varchar(50) DEFAULT NULL,
  `GROUP_DESC` varchar(512) DEFAULT NULL,
  `GROUP_TYPE` varchar(255) DEFAULT NULL,
  `GROUP_SEQ` varchar(256) DEFAULT NULL,
  `START_DATE` varchar(50) DEFAULT NULL,
  `END_DATE` varchar(50) DEFAULT NULL,
  `GROUP_STATUS` varchar(255) DEFAULT NULL,
  `MANAGER` varchar(30) DEFAULT NULL,
  `CREATE_TIME` varchar(50) DEFAULT NULL,
  `LASTUP_DATE` varchar(50) DEFAULT NULL,
  `UPDATOR` varchar(36) DEFAULT NULL,
  `IS_LEAF` char(1) DEFAULT NULL,
  PRIMARY KEY (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OM_GROUP
-- ----------------------------
INSERT INTO `OM_GROUP` VALUES ('root', '', '-1', '0', '工作组树', '', '', '', '', '', '', '', '', '', '', '');

-- ----------------------------
-- Table structure for OM_ORGANIZATION
-- ----------------------------
DROP TABLE IF EXISTS `OM_ORGANIZATION`;
CREATE TABLE `OM_ORGANIZATION` (
  `ORG_ID` varchar(36) NOT NULL COMMENT '主键',
  `ORG_CODE` varchar(32) NOT NULL COMMENT '机构代码',
  `ORG_NAME` varchar(64) DEFAULT NULL COMMENT '机构名称',
  `ORG_LEVEL` decimal(2,0) DEFAULT '1' COMMENT '机构层次（暂未使用）',
  `ORG_DEGREE` varchar(255) DEFAULT NULL COMMENT '机构等级（暂未使用）',
  `PARENT_ORG_ID` varchar(36) DEFAULT NULL COMMENT '父机构ID',
  `ORG_SEQ` varchar(512) DEFAULT NULL COMMENT '机构序列（暂未使用）',
  `ORG_TYPE` varchar(12) DEFAULT NULL COMMENT '机构类型',
  `ORG_ADDR` varchar(256) DEFAULT NULL COMMENT '机构地址',
  `ZIP_CODE` varchar(10) DEFAULT NULL COMMENT '邮编',
  `MANA_POSITION` varchar(36) DEFAULT NULL COMMENT '机构主管岗位',
  `MANAGER_ID` varchar(36) DEFAULT NULL COMMENT '机构主管人员',
  `ORG_MANAGER` varchar(128) DEFAULT NULL COMMENT '机构管理员',
  `LINK_MAN` varchar(30) DEFAULT NULL COMMENT '联系人',
  `LINK_TEL` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '电子邮件',
  `WEB_URL` varchar(512) DEFAULT NULL COMMENT '网站地址',
  `START_DATE` varchar(50) DEFAULT NULL COMMENT '生效日期',
  `END_DATE` varchar(50) DEFAULT NULL COMMENT '失效日期',
  `STATUS` varchar(255) DEFAULT NULL COMMENT '机构状态',
  `AREA` varchar(30) DEFAULT NULL COMMENT '所属地域',
  `CREATE_TIME` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATE_TIME` varchar(50) DEFAULT NULL COMMENT '最近更新时间',
  `UPDATOR` varchar(36) DEFAULT NULL COMMENT '最近更新人员',
  `SORT_NO` int(11) DEFAULT NULL COMMENT '排列顺序编号',
  `IS_LEAF` char(1) DEFAULT NULL COMMENT '是否叶子节点',
  `REMARK` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ORG_ID`),
  KEY `OM_ORG_PID_INDEX` (`PARENT_ORG_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OM_ORGANIZATION
-- ----------------------------
INSERT INTO `OM_ORGANIZATION` VALUES ('5d850690-89b5-4ba8-8634-b1b451efb533', 'ORG100001', '开发测试', '0', null, 'root', null, null, '', '', null, null, null, '', '', '', '', '', '', '0', '', '2015-10-03 21:07:26', '2015-10-04 18:20:29', '待完善', '100001', null, '');
INSERT INTO `OM_ORGANIZATION` VALUES ('root', '0', '机构人员树', '0', '', '-1', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', null, '', '');

-- ----------------------------
-- Table structure for OM_PARTY_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `OM_PARTY_ROLE`;
CREATE TABLE `OM_PARTY_ROLE` (
  `ROLE_ID` varchar(36) NOT NULL,
  `PARTY_TYPE` varchar(255) NOT NULL,
  `PARTYID` varchar(36) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`PARTY_TYPE`,`PARTYID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OM_PARTY_ROLE
-- ----------------------------

-- ----------------------------
-- Table structure for OM_POSITION
-- ----------------------------
DROP TABLE IF EXISTS `OM_POSITION`;
CREATE TABLE `OM_POSITION` (
  `POSITION_ID` varchar(36) NOT NULL,
  `POSI_CODE` varchar(20) NOT NULL COMMENT '岗位代码',
  `POSI_NAME` varchar(128) NOT NULL COMMENT '岗位名称',
  `POSI_LEVEL` decimal(2,0) DEFAULT NULL COMMENT '岗位层次',
  `PARENT_POSI_ID` varchar(36) DEFAULT NULL COMMENT '上级岗位',
  `ORG_ID` varchar(36) DEFAULT NULL COMMENT '隶属机构ID',
  `POSITION_SEQ` varchar(512) DEFAULT NULL COMMENT '岗位序列(冗余字段）',
  `POSI_TYPE` varchar(255) DEFAULT NULL COMMENT '岗位类别',
  `CREATE_TIME` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATE_TIME` varchar(50) DEFAULT NULL COMMENT '最近更新时间',
  `UPDATOR` varchar(36) DEFAULT NULL COMMENT '最近更新人员',
  `START_DATE` varchar(50) DEFAULT NULL COMMENT '岗位有效开始日期',
  `END_DATE` varchar(50) DEFAULT NULL COMMENT '岗位有效截止日期',
  `STATUS` varchar(255) DEFAULT NULL COMMENT '岗位状态',
  `IS_LEAF` char(1) DEFAULT NULL COMMENT '是否叶子岗位',
  PRIMARY KEY (`POSITION_ID`),
  KEY `OM_POSI_PPOSI_INDEX` (`PARENT_POSI_ID`) USING BTREE,
  KEY `OM_POSI_PORG_INDEX` (`ORG_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OM_POSITION
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_DICT
-- ----------------------------
DROP TABLE IF EXISTS `SYS_DICT`;
CREATE TABLE `SYS_DICT` (
  `DICT_ID` varchar(36) NOT NULL COMMENT '【字典项】主键',
  `TYPE` varchar(1) DEFAULT NULL COMMENT '【字典】类型（系统字典或者用户字典）',
  `CODE` varchar(50) DEFAULT NULL COMMENT '【字典】编号',
  `NAME` varchar(60) DEFAULT NULL COMMENT '【字典】名称',
  `DICT_VALUE` varchar(10) DEFAULT NULL COMMENT '【字典项】值',
  `DICT_TEXT` varchar(60) DEFAULT NULL COMMENT '【字典项】文本',
  `DICT_PARENT_ID` varchar(50) DEFAULT NULL COMMENT '【字典项】父节点',
  `IS_USED` char(1) DEFAULT NULL COMMENT '【字典项】是否在用',
  `DICT_SORT_NO` int(11) DEFAULT NULL,
  `DICT_REMARK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DICT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of SYS_DICT
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_FILE
-- ----------------------------
DROP TABLE IF EXISTS `SYS_FILE`;
CREATE TABLE `SYS_FILE` (
  `FILE_ID` varchar(36) NOT NULL,
  `FILE_NAME` text,
  `FILE_TYPE` varchar(2) DEFAULT NULL,
  `FILE_LOCATION` text,
  `UPLOAD_TIME` varchar(19) DEFAULT NULL,
  `UPLOADER` varchar(36) DEFAULT NULL,
  `UPDATE_TIME` varchar(19) DEFAULT NULL,
  `UPDATER` varchar(36) DEFAULT NULL,
  `INVALID_TIME` varchar(19) DEFAULT NULL,
  `INVALIDER` varchar(36) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `REMARK` text,
  PRIMARY KEY (`FILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_FILE
-- ----------------------------

-- ----------------------------
-- View structure for V_AC_APP_FUNCG_FUNC_TREE
-- ----------------------------
DROP VIEW IF EXISTS `V_AC_APP_FUNCG_FUNC_TREE`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `V_AC_APP_FUNCG_FUNC_TREE` AS (select `AC_APPLICATION`.`APP_ID` AS `ID`,'-1' AS `PID`,`AC_APPLICATION`.`APP_CODE` AS `CODE`,`AC_APPLICATION`.`APP_NAME` AS `NAME`,'ROOT' AS `TYPE`,'FRoot' AS `ICON_SKIN`,'1' AS `IS_PARENT`,`AC_APPLICATION`.`APP_ID` AS `EXTEND_ATTR`,'' AS `EXTEND_ATTR_1` from `AC_APPLICATION` where (`AC_APPLICATION`.`APP_ID` = 'root')) union all (select `AC_APPLICATION`.`APP_ID` AS `ID`,'root' AS `PID`,`AC_APPLICATION`.`APP_CODE` AS `CODE`,`AC_APPLICATION`.`APP_NAME` AS `NAME`,'APP' AS `TYPE`,'FIcon01' AS `ICON_SKIN`,'1' AS `IS_PARENT`,`AC_APPLICATION`.`APP_ID` AS `EXTEND_ATTR`,'' AS `EXTEND_ATTR_1` from `AC_APPLICATION` where (`AC_APPLICATION`.`APP_ID` <> 'root')) union all (select `AC_FUNC_GROUP`.`FUNC_GROUP_ID` AS `ID`,`AC_FUNC_GROUP`.`APP_ID` AS `PID`,`AC_FUNC_GROUP`.`FUNC_GROUP_SEQ` AS `CODE`,`AC_FUNC_GROUP`.`FUNC_GROUP_NAME` AS `NAME`,'FUNC_GROUP' AS `TYPE`,'FIcon02' AS `ICON_SKIN`,'1' AS `IS_PARENT`,`AC_FUNC_GROUP`.`APP_ID` AS `EXTEND_ATTR`,`AC_FUNC_GROUP`.`FUNC_GROUP_ID` AS `EXTEND_ATTR_1` from `AC_FUNC_GROUP` where (ifnull(`AC_FUNC_GROUP`.`PARENT_GROUP`,'') = '')) union all (select `AC_FUNC_GROUP`.`FUNC_GROUP_ID` AS `ID`,`AC_FUNC_GROUP`.`PARENT_GROUP` AS `PID`,`AC_FUNC_GROUP`.`FUNC_GROUP_SEQ` AS `CODE`,`AC_FUNC_GROUP`.`FUNC_GROUP_NAME` AS `NAME`,'FUNC_GROUP' AS `TYPE`,'FIcon02' AS `ICON_SKIN`,'1' AS `IS_PARENT`,`AC_FUNC_GROUP`.`APP_ID` AS `EXTEND_ATTR`,`AC_FUNC_GROUP`.`FUNC_GROUP_ID` AS `EXTEND_ATTR_1` from `AC_FUNC_GROUP` where (ifnull(`AC_FUNC_GROUP`.`PARENT_GROUP`,'') <> '')) union all (select `AC_FUNCTION`.`FUNC_ID` AS `ID`,`AC_FUNCTION`.`FUNC_GROUP_ID` AS `PID`,`AC_FUNCTION`.`FUNC_DESC` AS `CODE`,`AC_FUNCTION`.`FUNC_NAME` AS `NAME`,'FUNCTION' AS `TYPE`,'' AS `ICON_SKIN`,'0' AS `IS_PARENT`,'' AS `EXTEND_ATTR`,`AC_FUNCTION`.`FUNC_GROUP_ID` AS `EXTEND_ATTR_1` from `AC_FUNCTION`) ;

-- ----------------------------
-- View structure for V_OM_ORG_POSI_EMP_TREE
-- ----------------------------
DROP VIEW IF EXISTS `V_OM_ORG_POSI_EMP_TREE`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `V_OM_ORG_POSI_EMP_TREE` AS (select `OM_ORGANIZATION`.`ORG_ID` AS `ID`,`OM_ORGANIZATION`.`PARENT_ORG_ID` AS `PID`,`OM_ORGANIZATION`.`ORG_CODE` AS `CODE`,`OM_ORGANIZATION`.`ORG_NAME` AS `NAME`,'ORG' AS `TYPE`,'OpIcon02' AS `ICON_SKIN`,'1' AS `IS_PARENT`,`OM_ORGANIZATION`.`ORG_ID` AS `EXTEND_ATTR`,'' AS `EXTEND_ATTR_1` from `OM_ORGANIZATION` where (1 = 1)) union all (select `OM_POSITION`.`POSITION_ID` AS `ID`,`OM_POSITION`.`ORG_ID` AS `PID`,`OM_POSITION`.`POSI_CODE` AS `CODE`,`OM_POSITION`.`POSI_NAME` AS `NAME`,'POSI' AS `TYPE`,'OpIcon01' AS `ICON_SKIN`,'1' AS `IS_PARENT`,`OM_POSITION`.`ORG_ID` AS `EXTEND_ATTR`,`OM_POSITION`.`POSITION_ID` AS `EXTEND_ATTR_1` from `OM_POSITION` where (ifnull(`OM_POSITION`.`PARENT_POSI_ID`,'') = '')) union all (select `OM_POSITION`.`POSITION_ID` AS `ID`,`OM_POSITION`.`PARENT_POSI_ID` AS `PID`,`OM_POSITION`.`POSI_CODE` AS `CODE`,`OM_POSITION`.`POSI_NAME` AS `NAME`,'POSI' AS `TYPE`,'OpIcon01' AS `ICON_SKIN`,'1' AS `IS_PARENT`,`OM_POSITION`.`ORG_ID` AS `EXTEND_ATTR`,`OM_POSITION`.`POSITION_ID` AS `EXTEND_ATTR_1` from `OM_POSITION` where (ifnull(`OM_POSITION`.`PARENT_POSI_ID`,'') <> '')) union all (select `OM_EMPLOYEE`.`EMP_ID` AS `ID`,`OM_EMP_ORG`.`ORG_ID` AS `PID`,`OM_EMPLOYEE`.`EMP_CODE` AS `CODE`,`OM_EMPLOYEE`.`EMP_NAME` AS `NAME`,'EMP' AS `TYPE`,'OpIcon03' AS `ICON_SKIN`,'0' AS `IS_PARENT`,`OM_EMPLOYEE`.`ORG_ID` AS `EXTEND_ATTR`,`OM_EMPLOYEE`.`POSITION` AS `EXTEND_ATTR_1` from (`OM_EMPLOYEE` join `OM_EMP_ORG`) where (`OM_EMPLOYEE`.`EMP_ID` = `OM_EMP_ORG`.`EMP_ID`)) union all (select `OM_EMPLOYEE`.`EMP_ID` AS `ID`,`OM_EMP_POSITION`.`POSITION_ID` AS `PID`,`OM_EMPLOYEE`.`EMP_CODE` AS `CODE`,`OM_EMPLOYEE`.`EMP_NAME` AS `NAME`,'EMP' AS `TYPE`,'OpIcon03' AS `ICON_SKIN`,'0' AS `IS_PARENT`,`OM_EMPLOYEE`.`ORG_ID` AS `EXTEND_ATTR`,`OM_EMPLOYEE`.`POSITION` AS `EXTEND_ATTR_1` from (`OM_EMPLOYEE` join `OM_EMP_POSITION`) where (`OM_EMPLOYEE`.`EMP_ID` = `OM_EMP_POSITION`.`EMP_ID`)) ;

-- ----------------------------
-- Function structure for AC_FUNC_GROUP_QUERY_CHILDREN
-- ----------------------------
DROP FUNCTION IF EXISTS `AC_FUNC_GROUP_QUERY_CHILDREN`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `AC_FUNC_GROUP_QUERY_CHILDREN`(id VARCHAR(50)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp = '$';
SET sTempChd = id;

WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
#execute stml;
SELECT group_concat(FUNC_GROUP_ID) INTO sTempChd FROM AC_FUNC_GROUP  where FIND_IN_SET(PARENT_GROUP,sTempChd)>0;
END WHILE;
return sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for AC_MENU_QUERY_CHILDREN
-- ----------------------------
DROP FUNCTION IF EXISTS `AC_MENU_QUERY_CHILDREN`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `AC_MENU_QUERY_CHILDREN`(id VARCHAR(50)) RETURNS varchar(4000) CHARSET latin1
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp = '$';
SET sTempChd = id;

WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
#execute stml;
SELECT group_concat(MENU_ID) INTO sTempChd FROM AC_MENU where FIND_IN_SET(PARENT_ID,sTempChd)>0;
END WHILE;
return sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for AC_MENU_QUERY_PARENTS
-- ----------------------------
DROP FUNCTION IF EXISTS `AC_MENU_QUERY_PARENTS`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `AC_MENU_QUERY_PARENTS`(id VARCHAR(9999)) RETURNS varchar(9999) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp = '$';
SET sTempChd = id;

WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
#execute stml;
SELECT group_concat(PARENT_ID) INTO sTempChd FROM AC_MENU where FIND_IN_SET(MENU_ID,sTempChd)>0;
END WHILE;
return sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for OM_GROUP_QUERY_CHILDREN
-- ----------------------------
DROP FUNCTION IF EXISTS `OM_GROUP_QUERY_CHILDREN`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `OM_GROUP_QUERY_CHILDREN`(id VARCHAR(50)) RETURNS varchar(4000) CHARSET latin1
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp = '$';
SET sTempChd = id;

WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
#execute stml;
SELECT group_concat(GROUP_ID) INTO sTempChd FROM OM_GROUP where FIND_IN_SET(PARENT_GROUP_ID,sTempChd)>0;
END WHILE;
return sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for OM_ORGANIZATION_QUERY_CHILDREN
-- ----------------------------
DROP FUNCTION IF EXISTS `OM_ORGANIZATION_QUERY_CHILDREN`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `OM_ORGANIZATION_QUERY_CHILDREN`(id VARCHAR(50)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp = '$';
SET sTempChd = id;

WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
#execute stml;
SELECT group_concat(ORG_ID) INTO sTempChd FROM OM_ORGANIZATION where FIND_IN_SET(PARENT_ORG_ID,sTempChd)>0;
END WHILE;
return sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for OM_POSITION_QUERY_CHILDREN
-- ----------------------------
DROP FUNCTION IF EXISTS `OM_POSITION_QUERY_CHILDREN`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `OM_POSITION_QUERY_CHILDREN`(id VARCHAR(50)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp = '$';
SET sTempChd = id;

WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
#execute stml;
SELECT group_concat(POSITION_ID) INTO sTempChd FROM OM_POSITION where FIND_IN_SET(PARENT_POSI_ID,sTempChd)>0;
END WHILE;
return sTemp;
END
;;
DELIMITER ;
