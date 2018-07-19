/*
Navicat MySQL Data Transfer

Source Server         : dev-dever-pub
Source Server Version : 50556
Source Host           : 121.12.252.172:33066
Source Database       : ins_edu_msg

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-03-14 14:30:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `msg_sms`
-- ----------------------------
DROP TABLE IF EXISTS `msg_sms`;
CREATE TABLE `msg_sms` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT 'Id32',
  `day` varchar(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `way` varchar(4) DEFAULT NULL COMMENT '发送渠道(XYC/ALI/...)',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态，0:未处理,1:处理中,2:已成功,3:已失败,4:已取消',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `content` varchar(400) DEFAULT NULL COMMENT '短信内容',
  `time_c` varchar(6) DEFAULT NULL COMMENT '创建时间(HHmmss)',
  `time_s` varchar(19) DEFAULT NULL COMMENT '发送时间yyyy-MM-dd HH:mm:ss',
  `time_f` varchar(19) DEFAULT NULL COMMENT '完成时间yyyy-MM-dd HH:mm:ss',
  `times` tinyint(2) DEFAULT '0' COMMENT '发送次数',
  `result` varchar(64) DEFAULT NULL COMMENT '结果描述',
  `key` varchar(32) DEFAULT NULL COMMENT '源标识',
  `bcd` varchar(32) DEFAULT NULL COMMENT '业务代码',
  `sid` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `sname` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `fid` varchar(32) DEFAULT NULL COMMENT '家长ID',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `hid` varchar(8) DEFAULT NULL COMMENT '学校ID',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信记录';

-- ----------------------------
-- Records of msg_sms
-- ----------------------------
