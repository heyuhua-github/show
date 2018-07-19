/*
Navicat MySQL Data Transfer

Source Server         : dev-dever
Source Server Version : 50556
Source Host           : 121.12.252.172:33066
Source Database       : ins_edu_kq

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-01-11 13:42:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `holidays`
-- ----------------------------
DROP TABLE IF EXISTS `holidays`;
CREATE TABLE `holidays` (
  `hid` varchar(8) NOT NULL DEFAULT '' COMMENT '学校ID',
  `holidays` varchar(200) DEFAULT NULL COMMENT '节假日yyyyMMdd,yyyyMMdd,...',
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of holidays
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_conf`
-- ----------------------------
DROP TABLE IF EXISTS `kq_conf`;
CREATE TABLE `kq_conf` (
  `hid` varchar(8) NOT NULL DEFAULT '' COMMENT '学校ID',
  `skt` varchar(8) DEFAULT NULL COMMENT '学生考勤类型-分段/实时',
  `skinout` tinyint(1) DEFAULT '0' COMMENT '学生考勤-区分进出',
  `sks` tinyint(1) DEFAULT '0' COMMENT '学生考勤统计',
  `sks_walk` tinyint(1) DEFAULT '0' COMMENT '学生考勤统计-走读',
  `sks_dorm` tinyint(1) DEFAULT '0' COMMENT '学生考勤统计-住读',
  `sks_noon` tinyint(1) DEFAULT '0' COMMENT '学生考勤统计-午休',
  `tkt` varchar(8) DEFAULT NULL COMMENT '教师考勤类型-分段/实时',
  `tkinout` tinyint(1) DEFAULT '0' COMMENT '教师考勤-区分进出',
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤配置';

-- ----------------------------
-- Records of kq_conf
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_group`
-- ----------------------------
DROP TABLE IF EXISTS `kq_group`;
CREATE TABLE `kq_group` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '群组ID',
  `hid` varchar(8) DEFAULT NULL COMMENT '学校ID',
  `dau` tinyint(1) DEFAULT '0' COMMENT '默认群组',
  `type` varchar(8) DEFAULT NULL COMMENT '群组类型-STU/TCH',
  `name` varchar(64) DEFAULT NULL COMMENT '群组名称',
  `p0n1` varchar(5) DEFAULT NULL COMMENT '上学-时间1',
  `p0n2` varchar(5) DEFAULT NULL COMMENT '上学-时间2',
  `p0n3` varchar(5) DEFAULT NULL COMMENT '上学-时间3',
  `p0f1` varchar(5) DEFAULT NULL COMMENT '放学-时间1',
  `p0f2` varchar(5) DEFAULT NULL COMMENT '放学-时间2',
  `p0f3` varchar(5) DEFAULT NULL COMMENT '放学-时间3',
  `p1n1` varchar(5) DEFAULT NULL COMMENT '上午上学-时间1',
  `p1n2` varchar(5) DEFAULT NULL COMMENT '上午上学-时间2',
  `p1n3` varchar(5) DEFAULT NULL COMMENT '上午上学-时间3',
  `p1f1` varchar(5) DEFAULT NULL COMMENT '上午放学-时间1',
  `p1f2` varchar(5) DEFAULT NULL COMMENT '上午放学-时间2',
  `p1f3` varchar(5) DEFAULT NULL COMMENT '上午放学-时间3',
  `p2n1` varchar(5) DEFAULT NULL COMMENT '下午上学-时间1',
  `p2n2` varchar(5) DEFAULT NULL COMMENT '下午上学-时间2',
  `p2n3` varchar(5) DEFAULT NULL COMMENT '下午上学-时间3',
  `p2f1` varchar(5) DEFAULT NULL COMMENT '下午放学-时间1',
  `p2f2` varchar(5) DEFAULT NULL COMMENT '下午放学-时间2',
  `p2f3` varchar(5) DEFAULT NULL COMMENT '下午放学-时间3',
  `p3n1` varchar(5) DEFAULT NULL COMMENT '晚上上学-时间1',
  `p3n2` varchar(5) DEFAULT NULL COMMENT '晚上上学-时间2',
  `p3n3` varchar(5) DEFAULT NULL COMMENT '晚上上学-时间3',
  `p3f1` varchar(5) DEFAULT NULL COMMENT '晚上放学-时间1',
  `p3f2` varchar(5) DEFAULT NULL COMMENT '晚上放学-时间2',
  `p3f3` varchar(5) DEFAULT NULL COMMENT '晚上放学-时间3',
  PRIMARY KEY (`id`),
  KEY `inx_hid` (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤群组';

-- ----------------------------
-- Records of kq_group
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_group_dts`
-- ----------------------------
DROP TABLE IF EXISTS `kq_group_dts`;
CREATE TABLE `kq_group_dts` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '群组ID',
  `day` varchar(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `hid` varchar(8) DEFAULT NULL COMMENT '学校ID',
  `workday` tinyint(1) DEFAULT '1' COMMENT '是否工作日(1:是,0:否)',
  `p0n1` varchar(5) DEFAULT NULL COMMENT '上学-时间1',
  `p0n2` varchar(5) DEFAULT NULL COMMENT '上学-时间2',
  `p0n3` varchar(5) DEFAULT NULL COMMENT '上学-时间3',
  `p0f1` varchar(5) DEFAULT NULL COMMENT '放学-时间1',
  `p0f2` varchar(5) DEFAULT NULL COMMENT '放学-时间2',
  `p0f3` varchar(5) DEFAULT NULL COMMENT '放学-时间3',
  `p1n1` varchar(5) DEFAULT NULL COMMENT '上午上学-时间1',
  `p1n2` varchar(5) DEFAULT NULL COMMENT '上午上学-时间2',
  `p1n3` varchar(5) DEFAULT NULL COMMENT '上午上学-时间3',
  `p1f1` varchar(5) DEFAULT NULL COMMENT '上午放学-时间1',
  `p1f2` varchar(5) DEFAULT NULL COMMENT '上午放学-时间2',
  `p1f3` varchar(5) DEFAULT NULL COMMENT '上午放学-时间3',
  `p2n1` varchar(5) DEFAULT NULL COMMENT '下午上学-时间1',
  `p2n2` varchar(5) DEFAULT NULL COMMENT '下午上学-时间2',
  `p2n3` varchar(5) DEFAULT NULL COMMENT '下午上学-时间3',
  `p2f1` varchar(5) DEFAULT NULL COMMENT '下午放学-时间1',
  `p2f2` varchar(5) DEFAULT NULL COMMENT '下午放学-时间2',
  `p2f3` varchar(5) DEFAULT NULL COMMENT '下午放学-时间3',
  `p3n1` varchar(5) DEFAULT NULL COMMENT '晚上上学-时间1',
  `p3n2` varchar(5) DEFAULT NULL COMMENT '晚上上学-时间2',
  `p3n3` varchar(5) DEFAULT NULL COMMENT '晚上上学-时间3',
  `p3f1` varchar(5) DEFAULT NULL COMMENT '晚上放学-时间1',
  `p3f2` varchar(5) DEFAULT NULL COMMENT '晚上放学-时间2',
  `p3f3` varchar(5) DEFAULT NULL COMMENT '晚上放学-时间3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤群组日期自定义配置';

-- ----------------------------
-- Records of kq_group_dts
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_group_stu`
-- ----------------------------
DROP TABLE IF EXISTS `kq_group_stu`;
CREATE TABLE `kq_group_stu` (
  `gid` varchar(32) NOT NULL DEFAULT '' COMMENT '群组ID',
  `cid` varchar(32) NOT NULL DEFAULT '' COMMENT '班级ID',
  `type` varchar(8) NOT NULL DEFAULT '' COMMENT '学生类型(WALK,NOON,DORM)',
  PRIMARY KEY (`gid`,`cid`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kq_group_stu
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_group_tch`
-- ----------------------------
DROP TABLE IF EXISTS `kq_group_tch`;
CREATE TABLE `kq_group_tch` (
  `gid` varchar(32) NOT NULL DEFAULT '' COMMENT '群组ID',
  `rid` varchar(32) NOT NULL DEFAULT '' COMMENT '教师角色ID',
  PRIMARY KEY (`gid`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kq_group_tch
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_record_template_stu`
-- ----------------------------
DROP TABLE IF EXISTS `kq_record_template_stu`;
CREATE TABLE `kq_record_template_stu` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `day` varchar(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `sid` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `sname` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `gid` varchar(32) DEFAULT NULL COMMENT '年级ID',
  `gname` varchar(63) DEFAULT NULL COMMENT '年级名称',
  `ktime` varchar(8) DEFAULT NULL COMMENT '打卡时间HH:mm:ss',
  `kresult` varchar(32) DEFAULT NULL COMMENT '打卡结果',
  `kphoto` varchar(128) DEFAULT NULL COMMENT '打卡照片',
  `dcode` varchar(32) DEFAULT NULL COMMENT '考勤设备编号',
  `dname` varchar(64) DEFAULT NULL COMMENT '考勤设备名称',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`),
  KEY `inx_stu` (`day`,`sid`),
  KEY `inx_cla` (`day`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤记录模版表';

-- ----------------------------
-- Records of kq_record_template_stu
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_record_template_tch`
-- ----------------------------
DROP TABLE IF EXISTS `kq_record_template_tch`;
CREATE TABLE `kq_record_template_tch` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `day` varchar(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `tid` varchar(32) DEFAULT NULL COMMENT '教师ID',
  `tname` varchar(64) DEFAULT NULL COMMENT '教师姓名',
  `rid` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `rname` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `ktime` varchar(8) DEFAULT NULL COMMENT '打卡时间HH:mm:ss',
  `kresult` varchar(32) DEFAULT NULL COMMENT '打卡结果',
  `kphoto` varchar(128) DEFAULT NULL COMMENT '打卡照片',
  `dcode` varchar(32) DEFAULT NULL COMMENT '考勤设备编号',
  `dname` varchar(64) DEFAULT NULL COMMENT '考勤设备名称',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`),
  KEY `inx_stu` (`day`,`tid`),
  KEY `inx_cla` (`day`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤记录模版表';

-- ----------------------------
-- Records of kq_record_template_tch
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_template_cla`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_template_cla`;
CREATE TABLE `kq_statis_template_cla` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `day` varchar(8) DEFAULT NULL COMMENT '日期(yyyyMMdd)',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `gid` varchar(32) DEFAULT NULL COMMENT '年级ID',
  `gname` varchar(64) DEFAULT NULL COMMENT '年级名称',
  `n_toa` int(11) DEFAULT '0' COMMENT '总人数',
  `n_nor` int(11) DEFAULT '0' COMMENT '正常人数',
  `n_exc` int(11) DEFAULT '0' COMMENT '异常人数(早退、迟到)',
  `n_abs` int(11) DEFAULT '0' COMMENT '缺勤人数',
  `n_lev` int(11) DEFAULT '0' COMMENT '请假人数',
  `p_nor` tinyint(2) DEFAULT '100' COMMENT '正常人数百分比',
  `p_exc` tinyint(2) DEFAULT '0' COMMENT '异常人数百分比',
  `p_abs` tinyint(2) DEFAULT '0' COMMENT '缺勤人数百分比',
  `p_lev` tinyint(2) DEFAULT '0' COMMENT '请假人数百分比',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级考勤统计模版表';

-- ----------------------------
-- Records of kq_statis_template_cla
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_template_role`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_template_role`;
CREATE TABLE `kq_statis_template_role` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `rid` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `day` varchar(8) DEFAULT NULL COMMENT '日期(yyyyMMdd)',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `rname` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `n_toa` int(11) DEFAULT '0' COMMENT '总人数',
  `n_nor` int(11) DEFAULT '0' COMMENT '正常人数',
  `n_exc` int(11) DEFAULT '0' COMMENT '异常人数(早退、迟到)',
  `n_abs` int(11) DEFAULT '0' COMMENT '缺勤人数',
  `n_lev` int(11) DEFAULT '0' COMMENT '请假人数',
  `p_nor` tinyint(2) DEFAULT '100' COMMENT '正常人数百分比',
  `p_exc` tinyint(2) DEFAULT '0' COMMENT '异常人数百分比',
  `p_abs` tinyint(2) DEFAULT '0' COMMENT '缺勤人数百分比',
  `p_lev` tinyint(2) DEFAULT '0' COMMENT '请假人数百分比',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师角色考勤统计模版表';

-- ----------------------------
-- Records of kq_statis_template_role
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_template_stu`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_template_stu`;
CREATE TABLE `kq_statis_template_stu` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `mon` varchar(6) DEFAULT NULL COMMENT '月份(yyyyMM)',
  `sid` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `sname` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `gid` varchar(32) DEFAULT NULL COMMENT '年级ID',
  `gname` varchar(64) DEFAULT NULL COMMENT '年级名称',
  `n_nor` int(11) DEFAULT '0' COMMENT '正常次数',
  `n_lat` int(11) DEFAULT '0' COMMENT '迟到次数',
  `n_lea` int(11) DEFAULT NULL COMMENT '早退次数',
  `n_abs` int(11) DEFAULT '0' COMMENT '缺勤次数',
  `n_lev` int(11) DEFAULT '0' COMMENT '请假次数',
  PRIMARY KEY (`id`),
  KEY `inx_sid` (`mon`,`sid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤统计模版表';

-- ----------------------------
-- Records of kq_statis_template_stu
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_template_tch`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_template_tch`;
CREATE TABLE `kq_statis_template_tch` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `mon` varchar(6) DEFAULT NULL COMMENT '月份(yyyyMM)',
  `tid` varchar(32) DEFAULT NULL COMMENT '教师ID',
  `tname` varchar(64) DEFAULT NULL COMMENT '教师姓名',
  `rid` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `rname` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `n_nor` int(11) DEFAULT '0' COMMENT '正常次数',
  `n_lat` int(11) DEFAULT '0' COMMENT '迟到次数',
  `n_lea` int(11) DEFAULT NULL COMMENT '早退次数',
  `n_abs` int(11) DEFAULT '0' COMMENT '缺勤次数',
  `n_lev` int(11) DEFAULT '0' COMMENT '请假次数',
  PRIMARY KEY (`id`),
  KEY `inx_sid` (`mon`,`tid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤统计模版表';

-- ----------------------------
-- Records of kq_statis_template_tch
-- ----------------------------
