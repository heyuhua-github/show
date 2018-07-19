/*
Navicat MySQL Data Transfer

Source Server         : dev-dever-pub
Source Server Version : 50556
Source Host           : 121.12.252.172:33066
Source Database       : ins_edu_mj

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-03-14 14:29:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mj_holidays`
-- ----------------------------
DROP TABLE IF EXISTS `mj_holidays`;
CREATE TABLE `mj_holidays` (
  `hid` varchar(8) NOT NULL DEFAULT '' COMMENT '学校ID',
  `days` varchar(400) DEFAULT NULL COMMENT '日期(yyyyMMdd,yyyyMMdd,..)',
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mj_holidays
-- ----------------------------
INSERT INTO `mj_holidays` VALUES ('00000001', '20180501,20181001,20181002,20181003,20181004,20181005,20181006,20181007');
