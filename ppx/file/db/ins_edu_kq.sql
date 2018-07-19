/*
Navicat MySQL Data Transfer

Source Server         : dev-dever-pub
Source Server Version : 50556
Source Host           : 121.12.252.172:33066
Source Database       : ins_edu_kq

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-03-19 18:37:39
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `sks_youer` tinyint(1) DEFAULT NULL COMMENT '学生考勤统计-幼儿园',
  `tkt` varchar(8) DEFAULT NULL COMMENT '教师考勤类型-分段/实时',
  `tkinout` tinyint(1) DEFAULT '0' COMMENT '教师考勤-区分进出',
  `tks` tinyint(1) DEFAULT '0' COMMENT '教师考勤统计',
  `ut` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤配置';

-- ----------------------------
-- Records of kq_conf
-- ----------------------------
INSERT INTO `kq_conf` VALUES ('00000001', 'PERIOD', '1', '0', '0', '0', '0', null, 'PERIOD', '0', '0', '2018-02-27 15:15:40');

-- ----------------------------
-- Table structure for `kq_conf_youer`
-- ----------------------------
DROP TABLE IF EXISTS `kq_conf_youer`;
CREATE TABLE `kq_conf_youer` (
  `hid` varchar(8) NOT NULL DEFAULT '' COMMENT '学校ID',
  `pnum` tinyint(1) DEFAULT NULL COMMENT '1:一天两次卡；2：一天四次卡；',
  `p1n1` varchar(5) DEFAULT NULL COMMENT '上午入园-时间1',
  `p1n2` varchar(5) DEFAULT NULL COMMENT '上午入园-时间2',
  `vbn1` varchar(50) DEFAULT NULL COMMENT '语音播报内容-上午入园',
  `twn1` tinyint(1) DEFAULT NULL COMMENT '是否测量体温-上午入园',
  `p1f1` varchar(5) DEFAULT NULL COMMENT '上午离园-时间1',
  `p1f2` varchar(5) DEFAULT NULL COMMENT '上午离园-时间2',
  `vbf1` varchar(50) DEFAULT NULL COMMENT '语音播报内容-上午离园',
  `twf1` tinyint(1) DEFAULT NULL COMMENT '是否测量体温-上午离园',
  `p2n1` varchar(5) DEFAULT NULL COMMENT '下午入园-时间1',
  `p2n2` varchar(5) DEFAULT NULL COMMENT '下午入园-时间2',
  `vbn2` varchar(50) DEFAULT NULL COMMENT '语音播报内容-下午入园',
  `twn2` tinyint(1) DEFAULT NULL COMMENT '是否测量体温-下午入园',
  `p2f1` varchar(5) DEFAULT NULL COMMENT '下午离园-时间1',
  `p2f2` varchar(5) DEFAULT NULL COMMENT '下午离园-时间2',
  `vbf2` varchar(50) DEFAULT NULL COMMENT '语音播报内容-下午离园',
  `twf2` tinyint(1) DEFAULT NULL COMMENT '是否测量体温-下午离园',
  `vbother` varchar(50) DEFAULT NULL COMMENT '语音播报-其它时段',
  `vbtch` varchar(50) DEFAULT NULL COMMENT '语音播报-教师',
  `ut` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='幼儿园考勤配置表';

-- ----------------------------
-- Records of kq_conf_youer
-- ----------------------------
INSERT INTO `kq_conf_youer` VALUES ('00000028', '2', '08:00', '10:00', '上午好', '1', '12:00', '14:00', '中午好', '1', '13:00', '15:00', '下午好', '1', '16:00', '18:00', '下午好', '1', '其它好', '老师好', '2018-03-17 14:14:02');

-- ----------------------------
-- Table structure for `kq_detail_stu_00000001`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_stu_00000001`;
CREATE TABLE `kq_detail_stu_00000001` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
  `sid` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `sname` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `stype` varchar(16) DEFAULT NULL COMMENT '学生类型',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `gid` varchar(32) DEFAULT NULL COMMENT '年级ID',
  `gname` varchar(64) DEFAULT NULL COMMENT '年级名称',
  `fid` varchar(32) DEFAULT NULL COMMENT '家长ID',
  `fname` varchar(64) DEFAULT NULL COMMENT '家长姓名',
  `tw` varchar(10) DEFAULT NULL COMMENT '体温',
  `ktime` varchar(8) DEFAULT NULL COMMENT '打卡时间HH:mm:ss',
  `kresult` varchar(32) DEFAULT NULL COMMENT '打卡结果',
  `kphoto` varchar(128) DEFAULT NULL COMMENT '打卡照片',
  `dcode` varchar(32) DEFAULT NULL COMMENT '考勤设备编号',
  `dname` varchar(64) DEFAULT NULL COMMENT '考勤设备名称',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`),
  KEY `inx_stu` (`day`,`sid`),
  KEY `inx_cla` (`day`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤明细表';

-- ----------------------------
-- Records of kq_detail_stu_00000001
-- ----------------------------
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803121718073066152760', '0123456789', '20180312', 'PERIOD', 'P1N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803121718073459443892', '0123456789', '20180312', 'PERIOD', 'P1F', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803121718073611634022', '0123456789', '20180312', 'PERIOD', 'P2N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803121718073798734364', '0123456789', '20180312', 'PERIOD', 'P2F', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803121718073950223577', '0123456789', '20180312', 'PERIOD', 'P3N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803131018456055539045', '0123456789', '20180313', 'PERIOD', 'P1N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, '08:00:00', 'NORMAL', 'photo', null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803131018456543377587', '0123456789', '20180313', 'PERIOD', 'P1F', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, '11:34:00', 'NORMAL', 'photo', 'testdev', null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803131042323933231858', '0123456789', '20180313', 'PERIOD', 'P2N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803131401316146072574', '0123456789', '20180313', 'PERIOD', 'P2F', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803141628029002036021', null, '20180314', 'PERIOD', 'P1N', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803141628029019100286', '0123456789', '20180314', 'PERIOD', 'P1N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803141628029415425796', '0123456789', '20180314', 'PERIOD', 'P1F', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803141628029418943750', null, '20180314', 'PERIOD', 'P1F', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803141628029662550812', '0123456789', '20180314', 'PERIOD', 'P2N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803141628029668987989', null, '20180314', 'PERIOD', 'P2N', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803141628029825898608', null, '20180314', 'PERIOD', 'P2F', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803141628029827480802', '0123456789', '20180314', 'PERIOD', 'P2F', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, '16:38:00', 'NORMAL', 'photo', 'devCode', null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803141628030013708328', '0123456789', '20180314', 'PERIOD', 'P3N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803141628030017425486', null, '20180314', 'PERIOD', 'P3N', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803141636455609312433', '0123456789', '20180314', 'PERIOD', null, '0', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', null, null, null, null, null, '16:36:00', null, 'photo', 'devCode', null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161230002143188775', '1313131313', '20180316', 'PERIOD', 'P1N', '1', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161230002143742499', '0123456789', '20180316', 'PERIOD', 'P1N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161230002147796311', null, '20180316', 'PERIOD', 'P1N', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161230002621333771', '1313131313', '20180316', 'PERIOD', 'P1F', '1', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161230002622874480', null, '20180316', 'PERIOD', 'P1F', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161230002623982972', '0123456789', '20180316', 'PERIOD', 'P1F', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161230002932670239', '0123456789', '20180316', 'PERIOD', 'P2N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161230002936957638', null, '20180316', 'PERIOD', 'P2N', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161230002939400490', '1313131313', '20180316', 'PERIOD', 'P2N', '1', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161400008591002288', null, '20180316', 'PERIOD', 'P2F', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161400008604127916', '0123456789', '20180316', 'PERIOD', 'P2F', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161400008605296312', '1313131313', '20180316', 'PERIOD', 'P2F', '1', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161730011186014179', null, '20180316', 'PERIOD', 'P3N', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161730011189311964', '1313131313', '20180316', 'PERIOD', 'P3N', '1', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803161730011189997489', '0123456789', '20180316', 'PERIOD', 'P3N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803170940026897497374', '0123456789', '20180316', 'PERIOD', null, '0', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', null, null, null, null, null, '16:54:00', null, '00000001/kq/20180316/20180317094002586860.jpg', 'CPAD0001', null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803190930001503432973', null, '20180319', 'PERIOD', 'P1N', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803190930001505672625', '0123456789', '20180319', 'PERIOD', 'P1N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803190930001506365759', '1313131313', '20180319', 'PERIOD', 'P1N', '1', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, '08:23:00', 'LATE', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803190930001509804577', null, '20180319', 'PERIOD', 'P1N', '1', '00000001201803171357336263966366', '刘武钊', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803190930001810763770', null, '20180319', 'PERIOD', 'P1F', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803190930001811895881', '1313131313', '20180319', 'PERIOD', 'P1F', '1', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803190930001816811292', null, '20180319', 'PERIOD', 'P1F', '1', '00000001201803171357336263966366', '刘武钊', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803190930001819897358', '0123456789', '20180319', 'PERIOD', 'P1F', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191030010061686827', '0123456789', '20180319', 'PERIOD', 'P2N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191030010064332370', '1313131313', '20180319', 'PERIOD', 'P2N', '1', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191030010067618269', null, '20180319', 'PERIOD', 'P2N', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191030010067992627', null, '20180319', 'PERIOD', 'P2N', '1', '00000001201803171357336263966366', '刘武钊', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191033533531298557', '1313131313', '20180319', 'PERIOD', null, '0', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', null, null, null, null, null, '10:33:00', null, null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191034534116883576', '1313131313', '20180319', 'PERIOD', null, '0', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', null, null, null, null, null, '08:33:00', null, null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191400001001789608', null, '20180319', 'PERIOD', 'P2F', '1', '00000001201803171357336263966366', '刘武钊', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191400001006267538', null, '20180319', 'PERIOD', 'P2F', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191400001008130281', '0123456789', '20180319', 'PERIOD', 'P2F', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191400001009506450', '1313131313', '20180319', 'PERIOD', 'P2F', '1', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191600000355083091', '0123456789', '20180319', 'PERIOD', 'P3N', '1', '00000001201803121619137060949464', '考勤测试（切勿删）', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191600000356344156', null, '20180319', 'PERIOD', 'P3N', '1', '00000001201803171357336263966366', '刘武钊', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191600000357226918', null, '20180319', 'PERIOD', 'P3N', '1', '00000001201803131657469530181965', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191600000358113530', '1313131313', '20180319', 'PERIOD', 'P3N', '1', '00000001201803161154543483169895', '呵呵哒', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000001` VALUES ('00000001201803191600000589458281', null, '20180319', 'PERIOD', 'P3N', '1', '00000001201803191432423983435077', '1', null, '00000001201803121547079170506088', '小一（1）班', '00000001201803121143504510146393', null, null, null, null, null, 'UNBRUSH', null, null, null);

-- ----------------------------
-- Table structure for `kq_detail_stu_00000026`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_stu_00000026`;
CREATE TABLE `kq_detail_stu_00000026` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
  `sid` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `sname` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `stype` varchar(16) DEFAULT NULL COMMENT '学生类型',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `gid` varchar(32) DEFAULT NULL COMMENT '年级ID',
  `gname` varchar(63) DEFAULT NULL COMMENT '年级名称',
  `fid` varchar(32) DEFAULT NULL COMMENT '家长ID',
  `fname` varchar(64) DEFAULT NULL COMMENT '家长姓名',
  `tw` varchar(10) DEFAULT NULL COMMENT '体温',
  `ktime` varchar(8) DEFAULT NULL COMMENT '打卡时间HH:mm:ss',
  `kresult` varchar(32) DEFAULT NULL COMMENT '打卡结果',
  `kphoto` varchar(128) DEFAULT NULL COMMENT '打卡照片',
  `dcode` varchar(32) DEFAULT NULL COMMENT '考勤设备编号',
  `dname` varchar(64) DEFAULT NULL COMMENT '考勤设备名称',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`),
  KEY `inx_stu` (`day`,`sid`),
  KEY `inx_cla` (`day`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤明细表';

-- ----------------------------
-- Records of kq_detail_stu_00000026
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_stu_00000028`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_stu_00000028`;
CREATE TABLE `kq_detail_stu_00000028` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
  `sid` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `sname` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `stype` varchar(16) DEFAULT NULL COMMENT '学生类型',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `gid` varchar(32) DEFAULT NULL COMMENT '年级ID',
  `gname` varchar(63) DEFAULT NULL COMMENT '年级名称',
  `fid` varchar(32) DEFAULT NULL COMMENT '家长ID',
  `fname` varchar(64) DEFAULT NULL COMMENT '家长姓名',
  `tw` varchar(10) DEFAULT NULL COMMENT '体温',
  `ktime` varchar(8) DEFAULT NULL COMMENT '打卡时间HH:mm:ss',
  `kresult` varchar(32) DEFAULT NULL COMMENT '打卡结果',
  `kphoto` varchar(128) DEFAULT NULL COMMENT '打卡照片',
  `dcode` varchar(32) DEFAULT NULL COMMENT '考勤设备编号',
  `dname` varchar(64) DEFAULT NULL COMMENT '考勤设备名称',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`),
  KEY `inx_stu` (`day`,`sid`),
  KEY `inx_cla` (`day`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤明细表';

-- ----------------------------
-- Records of kq_detail_stu_00000028
-- ----------------------------
INSERT INTO `kq_detail_stu_00000028` VALUES ('00000028201803190927193429902545', '1234567890', '20180319', 'PERIOD', 'P1N', '1', '00000028201803171454166386085340', '考勤测试', null, '00000028201803171452183730885547', '幼儿考勤测试班级', '00000028201803171449463213645259', null, '00000028201803190839316774896372', '考勤测试家长', '39.8', '09:29:00', 'NORMAL', '123', null, null);
INSERT INTO `kq_detail_stu_00000028` VALUES ('00000028201803191030017602720871', '1234567890', '20180319', 'PERIOD', 'P1F', '1', '00000028201803171454166386085340', '考勤测试', null, '00000028201803171452183730885547', '幼儿考勤测试班级', '00000028201803171449463213645259', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000028` VALUES ('00000028201803191100004557428752', '1234567890', '20180319', 'PERIOD', 'P2N', '1', '00000028201803171454166386085340', '考勤测试', null, '00000028201803171452183730885547', '幼儿考勤测试班级', '00000028201803171449463213645259', null, null, null, null, null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_stu_00000028` VALUES ('00000028201803191400000221359266', '1234567890', '20180319', 'PERIOD', 'P2F', '1', '00000028201803171454166386085340', '考勤测试', null, '00000028201803171452183730885547', '幼儿考勤测试班级', '00000028201803171449463213645259', null, null, null, null, null, 'UNBRUSH', null, null, null);

-- ----------------------------
-- Table structure for `kq_detail_stu_00000029`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_stu_00000029`;
CREATE TABLE `kq_detail_stu_00000029` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
  `sid` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `sname` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `stype` varchar(16) DEFAULT NULL COMMENT '学生类型',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `gid` varchar(32) DEFAULT NULL COMMENT '年级ID',
  `gname` varchar(63) DEFAULT NULL COMMENT '年级名称',
  `fid` varchar(32) DEFAULT NULL COMMENT '家长ID',
  `fname` varchar(64) DEFAULT NULL COMMENT '家长姓名',
  `tw` varchar(10) DEFAULT NULL COMMENT '体温',
  `ktime` varchar(8) DEFAULT NULL COMMENT '打卡时间HH:mm:ss',
  `kresult` varchar(32) DEFAULT NULL COMMENT '打卡结果',
  `kphoto` varchar(128) DEFAULT NULL COMMENT '打卡照片',
  `dcode` varchar(32) DEFAULT NULL COMMENT '考勤设备编号',
  `dname` varchar(64) DEFAULT NULL COMMENT '考勤设备名称',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`),
  KEY `inx_stu` (`day`,`sid`),
  KEY `inx_cla` (`day`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤明细表';

-- ----------------------------
-- Records of kq_detail_stu_00000029
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_stu_his_00000001`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_stu_his_00000001`;
CREATE TABLE `kq_detail_stu_his_00000001` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
  `sid` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `sname` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `stype` varchar(16) DEFAULT NULL COMMENT '学生类型',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `gid` varchar(32) DEFAULT NULL COMMENT '年级ID',
  `gname` varchar(63) DEFAULT NULL COMMENT '年级名称',
  `fid` varchar(32) DEFAULT NULL COMMENT '家长ID',
  `fname` varchar(64) DEFAULT NULL COMMENT '家长姓名',
  `tw` varchar(10) DEFAULT NULL COMMENT '体温',
  `ktime` varchar(8) DEFAULT NULL COMMENT '打卡时间HH:mm:ss',
  `kresult` varchar(32) DEFAULT NULL COMMENT '打卡结果',
  `kphoto` varchar(128) DEFAULT NULL COMMENT '打卡照片',
  `dcode` varchar(32) DEFAULT NULL COMMENT '考勤设备编号',
  `dname` varchar(64) DEFAULT NULL COMMENT '考勤设备名称',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`),
  KEY `inx_stu` (`day`,`sid`),
  KEY `inx_cla` (`day`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤明细历史表';

-- ----------------------------
-- Records of kq_detail_stu_his_00000001
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_stu_his_00000026`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_stu_his_00000026`;
CREATE TABLE `kq_detail_stu_his_00000026` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
  `sid` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `sname` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `stype` varchar(16) DEFAULT NULL COMMENT '学生类型',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `gid` varchar(32) DEFAULT NULL COMMENT '年级ID',
  `gname` varchar(63) DEFAULT NULL COMMENT '年级名称',
  `fid` varchar(32) DEFAULT NULL COMMENT '家长ID',
  `fname` varchar(64) DEFAULT NULL COMMENT '家长姓名',
  `tw` varchar(10) DEFAULT NULL COMMENT '体温',
  `ktime` varchar(8) DEFAULT NULL COMMENT '打卡时间HH:mm:ss',
  `kresult` varchar(32) DEFAULT NULL COMMENT '打卡结果',
  `kphoto` varchar(128) DEFAULT NULL COMMENT '打卡照片',
  `dcode` varchar(32) DEFAULT NULL COMMENT '考勤设备编号',
  `dname` varchar(64) DEFAULT NULL COMMENT '考勤设备名称',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`),
  KEY `inx_stu` (`day`,`sid`),
  KEY `inx_cla` (`day`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤明细历史表';

-- ----------------------------
-- Records of kq_detail_stu_his_00000026
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_stu_his_00000028`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_stu_his_00000028`;
CREATE TABLE `kq_detail_stu_his_00000028` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
  `sid` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `sname` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `stype` varchar(16) DEFAULT NULL COMMENT '学生类型',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `gid` varchar(32) DEFAULT NULL COMMENT '年级ID',
  `gname` varchar(63) DEFAULT NULL COMMENT '年级名称',
  `fid` varchar(32) DEFAULT NULL COMMENT '家长ID',
  `fname` varchar(64) DEFAULT NULL COMMENT '家长姓名',
  `tw` varchar(10) DEFAULT NULL COMMENT '体温',
  `ktime` varchar(8) DEFAULT NULL COMMENT '打卡时间HH:mm:ss',
  `kresult` varchar(32) DEFAULT NULL COMMENT '打卡结果',
  `kphoto` varchar(128) DEFAULT NULL COMMENT '打卡照片',
  `dcode` varchar(32) DEFAULT NULL COMMENT '考勤设备编号',
  `dname` varchar(64) DEFAULT NULL COMMENT '考勤设备名称',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`),
  KEY `inx_stu` (`day`,`sid`),
  KEY `inx_cla` (`day`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤明细历史表';

-- ----------------------------
-- Records of kq_detail_stu_his_00000028
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_stu_his_00000029`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_stu_his_00000029`;
CREATE TABLE `kq_detail_stu_his_00000029` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
  `sid` varchar(32) DEFAULT NULL COMMENT '学生ID',
  `sname` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `stype` varchar(16) DEFAULT NULL COMMENT '学生类型',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `cname` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `gid` varchar(32) DEFAULT NULL COMMENT '年级ID',
  `gname` varchar(63) DEFAULT NULL COMMENT '年级名称',
  `fid` varchar(32) DEFAULT NULL COMMENT '家长ID',
  `fname` varchar(64) DEFAULT NULL COMMENT '家长姓名',
  `tw` varchar(10) DEFAULT NULL COMMENT '体温',
  `ktime` varchar(8) DEFAULT NULL COMMENT '打卡时间HH:mm:ss',
  `kresult` varchar(32) DEFAULT NULL COMMENT '打卡结果',
  `kphoto` varchar(128) DEFAULT NULL COMMENT '打卡照片',
  `dcode` varchar(32) DEFAULT NULL COMMENT '考勤设备编号',
  `dname` varchar(64) DEFAULT NULL COMMENT '考勤设备名称',
  PRIMARY KEY (`id`),
  KEY `inx_day` (`day`),
  KEY `inx_stu` (`day`,`sid`),
  KEY `inx_cla` (`day`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤明细历史表';

-- ----------------------------
-- Records of kq_detail_stu_his_00000029
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_tch_00000001`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_tch_00000001`;
CREATE TABLE `kq_detail_tch_00000001` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤明细表';

-- ----------------------------
-- Records of kq_detail_tch_00000001
-- ----------------------------
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803131403377847100635', null, '20180313', 'PERIOD', 'P1N', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803131403377851444060', '0123456788', '20180313', 'PERIOD', 'P1N', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', '08:09:00', 'LATE', 'photo', 'testdev', null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803131403378351169404', '0123456788', '20180313', 'PERIOD', 'P1F', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', '11:34:00', 'NORMAL', 'photo', 'testdev', null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803131403378356624052', null, '20180313', 'PERIOD', 'P1F', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803131403378523963323', '0123456788', '20180313', 'PERIOD', 'P2N', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803131403378523997906', null, '20180313', 'PERIOD', 'P2N', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803131403378694585467', '0123456788', '20180313', 'PERIOD', 'P2F', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803131403378698623064', null, '20180313', 'PERIOD', 'P2F', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803131419499616388034', '0123456788', '20180313', 'PERIOD', null, '0', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', null, '08:09:00', null, 'photo', 'testdev', null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628052597542046', '0123456788', '20180314', 'PERIOD', 'P1N', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628052599637818', null, '20180314', 'PERIOD', 'P1N', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628052933000961', null, '20180314', 'PERIOD', 'P1F', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628052938842602', '0123456788', '20180314', 'PERIOD', 'P1F', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053091553665', null, '20180314', 'PERIOD', 'P2N', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053091576464', '0123456788', '20180314', 'PERIOD', 'P2N', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053257590764', '0123456788', '20180314', 'PERIOD', 'P2F', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053258251343', null, '20180314', 'PERIOD', 'P2F', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053435906018', '0123456788', '20180314', 'PERIOD', 'P3N', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053439901269', null, '20180314', 'PERIOD', 'P3N', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053593808633', null, '20180314', 'PERIOD', 'P1N', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053594654409', null, '20180314', 'PERIOD', 'P1N', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053763462091', null, '20180314', 'PERIOD', 'P1F', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053767425375', null, '20180314', 'PERIOD', 'P1F', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053915041855', null, '20180314', 'PERIOD', 'P2N', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628053917334552', null, '20180314', 'PERIOD', 'P2N', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628054085579675', null, '20180314', 'PERIOD', 'P2F', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628054088971151', null, '20180314', 'PERIOD', 'P2F', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628054251318449', null, '20180314', 'PERIOD', 'P3N', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803141628054258740180', null, '20180314', 'PERIOD', 'P3N', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230011331951135', null, '20180316', 'PERIOD', 'P1N', '1', '00000001201803151640311894211213', '222', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230011332633569', null, '20180316', 'PERIOD', 'P1N', '1', '00000001201803151626311955776660', '11', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230011337211569', null, '20180316', 'PERIOD', 'P1N', '1', '00000001201803151819591662405685', '111', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230011490709837', null, '20180316', 'PERIOD', 'P1F', '1', '00000001201803151640311894211213', '222', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230011493370101', null, '20180316', 'PERIOD', 'P1F', '1', '00000001201803151819591662405685', '111', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230011498163794', null, '20180316', 'PERIOD', 'P1F', '1', '00000001201803151626311955776660', '11', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230011643670947', null, '20180316', 'PERIOD', 'P2N', '1', '00000001201803151626311955776660', '11', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230011644058510', null, '20180316', 'PERIOD', 'P2N', '1', '00000001201803151819591662405685', '111', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230011646375358', null, '20180316', 'PERIOD', 'P2N', '1', '00000001201803151640311894211213', '222', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230013689588444', null, '20180316', 'PERIOD', 'P1N', '1', '00000001201803151640571933055792', '333', '00000001201801301422098545211769', '教导主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230013994081516', null, '20180316', 'PERIOD', 'P1F', '1', '00000001201803151640571933055792', '333', '00000001201801301422098545211769', '教导主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230014468833703', null, '20180316', 'PERIOD', 'P2N', '1', '00000001201803151640571933055792', '333', '00000001201801301422098545211769', '教导主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230014611553257', null, '20180316', 'PERIOD', 'P1N', '1', '00000001201803151641568208099218', '111', '00000001201802261818372740143390', '校领导', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230014775188589', null, '20180316', 'PERIOD', 'P1F', '1', '00000001201803151641568208099218', '111', '00000001201802261818372740143390', '校领导', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230015087474074', null, '20180316', 'PERIOD', 'P2N', '1', '00000001201803151641568208099218', '111', '00000001201802261818372740143390', '校领导', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230015713410481', null, '20180316', 'PERIOD', 'P1N', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230015715566424', '0123456788', '20180316', 'PERIOD', 'P1N', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230015716457591', '1689688117', '20180316', 'PERIOD', 'P1N', '1', '00000001201803151548189744350797', 'Luis', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230015862708857', '0123456788', '20180316', 'PERIOD', 'P1F', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230015867914761', null, '20180316', 'PERIOD', 'P1F', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230015868815827', '1689688117', '20180316', 'PERIOD', 'P1F', '1', '00000001201803151548189744350797', 'Luis', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230016176610795', '0123456788', '20180316', 'PERIOD', 'P2N', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230016177196615', null, '20180316', 'PERIOD', 'P2N', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230016179656362', '1689688117', '20180316', 'PERIOD', 'P2N', '1', '00000001201803151548189744350797', 'Luis', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230016333400753', null, '20180316', 'PERIOD', 'P1N', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230016337222579', null, '20180316', 'PERIOD', 'P1N', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230016494406363', null, '20180316', 'PERIOD', 'P1F', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230016494558304', null, '20180316', 'PERIOD', 'P1F', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230016809057801', null, '20180316', 'PERIOD', 'P2N', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161230016809186621', null, '20180316', 'PERIOD', 'P2N', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161400014493137991', null, '20180316', 'PERIOD', 'P2F', '1', '00000001201803151819591662405685', '111', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161400014495032019', null, '20180316', 'PERIOD', 'P2F', '1', '00000001201803151640311894211213', '222', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161400014498004129', null, '20180316', 'PERIOD', 'P2F', '1', '00000001201803151626311955776660', '11', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161400015155492920', null, '20180316', 'PERIOD', 'P2F', '1', '00000001201803151640571933055792', '333', '00000001201801301422098545211769', '教导主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161400015323543825', null, '20180316', 'PERIOD', 'P2F', '1', '00000001201803151641568208099218', '111', '00000001201802261818372740143390', '校领导', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161400015652350106', null, '20180316', 'PERIOD', 'P2F', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161400015653577963', '1689688117', '20180316', 'PERIOD', 'P2F', '1', '00000001201803151548189744350797', 'Luis', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161400015654512161', '0123456788', '20180316', 'PERIOD', 'P2F', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161400015829061418', null, '20180316', 'PERIOD', 'P2F', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803161400015829770910', null, '20180316', 'PERIOD', 'P2F', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030014732617466', null, '20180319', 'PERIOD', 'P1N', '1', '00000001201803151626311955776660', '11', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030014735215889', null, '20180319', 'PERIOD', 'P1N', '1', '00000001201803151640311894211213', '222', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030014815735010', null, '20180319', 'PERIOD', 'P1F', '1', '00000001201803151640311894211213', '222', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030014818991297', null, '20180319', 'PERIOD', 'P1F', '1', '00000001201803151626311955776660', '11', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030014898220433', null, '20180319', 'PERIOD', 'P2N', '1', '00000001201803151626311955776660', '11', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030014909244624', null, '20180319', 'PERIOD', 'P2N', '1', '00000001201803151640311894211213', '222', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030015733877245', null, '20180319', 'PERIOD', 'P1N', '1', '00000001201803151640571933055792', '333', '00000001201801301422098545211769', '教导主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030015811713270', null, '20180319', 'PERIOD', 'P1F', '1', '00000001201803151640571933055792', '333', '00000001201801301422098545211769', '教导主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030015892142214', null, '20180319', 'PERIOD', 'P2N', '1', '00000001201803151640571933055792', '333', '00000001201801301422098545211769', '教导主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016654022915', '0123456788', '20180319', 'PERIOD', 'P1N', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016658374647', null, '20180319', 'PERIOD', 'P1N', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016658918721', '1689688117', '20180319', 'PERIOD', 'P1N', '1', '00000001201803151548189744350797', 'Luis', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016732017505', '1689688117', '20180319', 'PERIOD', 'P1F', '1', '00000001201803151548189744350797', 'Luis', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016735647952', '0123456788', '20180319', 'PERIOD', 'P1F', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016738976989', null, '20180319', 'PERIOD', 'P1F', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016814365989', '0123456788', '20180319', 'PERIOD', 'P2N', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016817322106', '1689688117', '20180319', 'PERIOD', 'P2N', '1', '00000001201803151548189744350797', 'Luis', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016817715543', null, '20180319', 'PERIOD', 'P2N', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016907561458', null, '20180319', 'PERIOD', 'P1N', '1', '00000001201803170930356028125829', '李老师', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016909149150', null, '20180319', 'PERIOD', 'P1N', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016909457873', null, '20180319', 'PERIOD', 'P1N', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016980417482', null, '20180319', 'PERIOD', 'P1F', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016983473305', null, '20180319', 'PERIOD', 'P1F', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030016986964556', null, '20180319', 'PERIOD', 'P1F', '1', '00000001201803170930356028125829', '李老师', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030017064709786', null, '20180319', 'PERIOD', 'P2N', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030017068535939', null, '20180319', 'PERIOD', 'P2N', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191030017072181695', null, '20180319', 'PERIOD', 'P2N', '1', '00000001201803170930356028125829', '李老师', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191400002912412184', null, '20180319', 'PERIOD', 'P2F', '1', '00000001201803151640311894211213', '222', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191400002915790360', null, '20180319', 'PERIOD', 'P2F', '1', '00000001201803151626311955776660', '11', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191400003419003927', null, '20180319', 'PERIOD', 'P2F', '1', '00000001201803151640571933055792', '333', '00000001201801301422098545211769', '教导主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191400003660144851', '1689688117', '20180319', 'PERIOD', 'P2F', '1', '00000001201803151548189744350797', 'Luis', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191400003661519769', null, '20180319', 'PERIOD', 'P2F', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191400003666251451', '0123456788', '20180319', 'PERIOD', 'P2F', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191400003745154018', null, '20180319', 'PERIOD', 'P2F', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191400003745958370', null, '20180319', 'PERIOD', 'P2F', '1', '00000001201803170930356028125829', '李老师', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191400003747599266', null, '20180319', 'PERIOD', 'P2F', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191600002172492995', null, '20180319', 'PERIOD', 'P3N', '1', '00000001201803151626311955776660', '11', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191600002173643182', null, '20180319', 'PERIOD', 'P3N', '1', '00000001201803151640311894211213', '222', '00000001201801031740460184825309', '保安', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191600002584782734', null, '20180319', 'PERIOD', 'P3N', '1', '00000001201803151640571933055792', '333', '00000001201801301422098545211769', '教导主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191600002831393938', '1689688117', '20180319', 'PERIOD', 'P3N', '1', '00000001201803151548189744350797', 'Luis', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191600002831508466', '0123456788', '20180319', 'PERIOD', 'P3N', '1', '00000001201803131358536331387000', '考勤教师(切勿删)', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191600002839493164', null, '20180319', 'PERIOD', 'P3N', '1', '00000001201801051715494924647560', '何老師', '00000001201802261819179235577256', '班主任', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191600002920691473', null, '20180319', 'PERIOD', 'P3N', '1', '00000001201801051723560728074112', '啦啦啦', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191600002926370068', null, '20180319', 'PERIOD', 'P3N', '1', '00000001201803170930356028125829', '李老师', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);
INSERT INTO `kq_detail_tch_00000001` VALUES ('00000001201803191600002926549589', null, '20180319', 'PERIOD', 'P3N', '1', '00000001201801051725327358076250', '啦啦啦1', '00000001201802261819305500229022', '科任老师', null, 'UNBRUSH', null, null, null);

-- ----------------------------
-- Table structure for `kq_detail_tch_00000026`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_tch_00000026`;
CREATE TABLE `kq_detail_tch_00000026` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤明细表';

-- ----------------------------
-- Records of kq_detail_tch_00000026
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_tch_00000028`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_tch_00000028`;
CREATE TABLE `kq_detail_tch_00000028` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤明细表';

-- ----------------------------
-- Records of kq_detail_tch_00000028
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_tch_00000029`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_tch_00000029`;
CREATE TABLE `kq_detail_tch_00000029` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤明细表';

-- ----------------------------
-- Records of kq_detail_tch_00000029
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_tch_his_00000001`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_tch_his_00000001`;
CREATE TABLE `kq_detail_tch_his_00000001` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤明细历史表';

-- ----------------------------
-- Records of kq_detail_tch_his_00000001
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_tch_his_00000026`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_tch_his_00000026`;
CREATE TABLE `kq_detail_tch_his_00000026` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤明细历史表';

-- ----------------------------
-- Records of kq_detail_tch_his_00000026
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_tch_his_00000028`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_tch_his_00000028`;
CREATE TABLE `kq_detail_tch_his_00000028` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤明细历史表';

-- ----------------------------
-- Records of kq_detail_tch_his_00000028
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_detail_tch_his_00000029`
-- ----------------------------
DROP TABLE IF EXISTS `kq_detail_tch_his_00000029`;
CREATE TABLE `kq_detail_tch_his_00000029` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `card` varchar(32) DEFAULT NULL COMMENT '卡号',
  `day` int(8) DEFAULT NULL COMMENT '日期yyyyMMdd',
  `ktype` varchar(8) DEFAULT NULL COMMENT '考勤类型-分段/实时',
  `period` varchar(32) DEFAULT NULL COMMENT '考勤时段',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效刷卡(1:有效,其它:无效)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤明细历史表';

-- ----------------------------
-- Records of kq_detail_tch_his_00000029
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
  `ut` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `inx_hid` (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤群组';

-- ----------------------------
-- Records of kq_group
-- ----------------------------
INSERT INTO `kq_group` VALUES ('00000001201802271746448143373865', '00000001', '0', 'STU', '学生群组', '07:30', '08:00', '08:30', '11:00', '11:30', '12:00', '12:30', '13:00', '13:30', '16:00', '16:30', '17:00', '18:00', '18:30', '19:00', '22:00', '22:30', '23:00', '2018-02-28 15:02:37');
INSERT INTO `kq_group` VALUES ('00000001201802271746448143373866', '00000001', '0', 'TCH', '教师群组', '07:30', '08:00', '08:30', '11:00', '11:30', '12:00', '12:30', '13:00', '13:30', '16:00', '16:30', '17:00', '18:00', '18:30', '19:00', '22:00', '22:30', '23:00', '2018-02-28 15:02:37');
INSERT INTO `kq_group` VALUES ('00000001201802271746448143373867', '00000001', '1', 'STU', '学生默认', '07:30', '08:00', '08:30', '11:00', '11:30', '12:00', '12:30', '13:00', '13:30', '16:00', '16:30', '17:00', '18:00', '18:30', '19:00', '22:00', '22:30', '23:00', '2018-02-28 15:02:37');
INSERT INTO `kq_group` VALUES ('00000001201802271746448143373868', '00000001', '1', 'TCH', '教师默认', '07:30', '08:00', '08:30', '11:00', '11:30', '12:00', '12:30', '13:00', '13:30', '16:00', '16:30', '17:00', '18:00', '18:30', '19:00', '22:00', '22:30', '23:00', '2018-02-28 15:02:37');

-- ----------------------------
-- Table structure for `kq_group_dts`
-- ----------------------------
DROP TABLE IF EXISTS `kq_group_dts`;
CREATE TABLE `kq_group_dts` (
  `gid` varchar(32) NOT NULL DEFAULT '' COMMENT '群组ID',
  `day` varchar(8) NOT NULL DEFAULT '' COMMENT '日期yyyyMMdd',
  `hid` varchar(8) DEFAULT NULL COMMENT '学校ID',
  `dtype` tinyint(1) DEFAULT '1' COMMENT '日期类型(1:工作日默认时间,2:工作日自定义时间,其它:非工作日)',
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
  PRIMARY KEY (`gid`,`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤群组日期自定义配置';

-- ----------------------------
-- Records of kq_group_dts
-- ----------------------------
INSERT INTO `kq_group_dts` VALUES ('00000001201802271746448143373865', '20180229', '00000001', '2', '07:30', '08:00', '08:30', '11:00', '11:30', '12:00', '12:30', '13:00', '13:30', '16:00', '16:30', '17:00', '18:00', '18:30', '19:00', '22:00', '22:30', '23:00');

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
INSERT INTO `kq_group_stu` VALUES ('00000001201802271746448143373865', '00000001201803121547079170506088', 'DORM');
INSERT INTO `kq_group_stu` VALUES ('00000001201802271746448143373865', '00000001201803121547079170506088', 'NOON');
INSERT INTO `kq_group_stu` VALUES ('00000001201802271746448143373865', '00000001201803121547079170506088', 'WALK');

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
INSERT INTO `kq_group_tch` VALUES ('00000001201802271746448143373866', '00000001201802261819179235577256');

-- ----------------------------
-- Table structure for `kq_init_stu`
-- ----------------------------
DROP TABLE IF EXISTS `kq_init_stu`;
CREATE TABLE `kq_init_stu` (
  `hid` varchar(8) NOT NULL DEFAULT '' COMMENT '学校ID',
  `cid` varchar(32) NOT NULL DEFAULT '' COMMENT '群组ID',
  `period` varchar(8) NOT NULL DEFAULT '' COMMENT '考勤时段',
  `day` varchar(8) NOT NULL DEFAULT '' COMMENT '日期(yyyyMMdd)',
  `type` varchar(10) NOT NULL DEFAULT '' COMMENT '学生类型(WALK:走读 DORM:住读 NOON:午休)',
  PRIMARY KEY (`cid`,`period`,`day`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤初始化表';

-- ----------------------------
-- Records of kq_init_stu
-- ----------------------------
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180312', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180312', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180312', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1F', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180312', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180312', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180312', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P1N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180312', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180312', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180312', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2F', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180312', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180312', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180312', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P2N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180312', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180312', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180312', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547079170506088', 'P3N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1F', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P1N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2F', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P2N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P3N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P3N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P3N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P3N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P3N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P3N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P3N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P3N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547163726221280', 'P3N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1F', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P1N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2F', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P2N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P3N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P3N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P3N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P3N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P3N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P3N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P3N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P3N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547240560049970', 'P3N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1F', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P1N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2F', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P2N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P3N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P3N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P3N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P3N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P3N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P3N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P3N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P3N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547313803308326', 'P3N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1F', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P1N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2F', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180313', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180313', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180313', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P2N', '20180319', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P3N', '20180314', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P3N', '20180314', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P3N', '20180314', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P3N', '20180316', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P3N', '20180316', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P3N', '20180316', 'WALK');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P3N', '20180319', 'DORM');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P3N', '20180319', 'NOON');
INSERT INTO `kq_init_stu` VALUES ('00000001', '00000001201803121547404328776155', 'P3N', '20180319', 'WALK');

-- ----------------------------
-- Table structure for `kq_init_stu_youer`
-- ----------------------------
DROP TABLE IF EXISTS `kq_init_stu_youer`;
CREATE TABLE `kq_init_stu_youer` (
  `hid` varchar(8) NOT NULL DEFAULT '' COMMENT '学校ID',
  `period` varchar(8) NOT NULL DEFAULT '' COMMENT '考勤时段',
  `day` varchar(8) NOT NULL DEFAULT '' COMMENT '日期(yyyyMMdd)',
  PRIMARY KEY (`hid`,`period`,`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤初始化表';

-- ----------------------------
-- Records of kq_init_stu_youer
-- ----------------------------
INSERT INTO `kq_init_stu_youer` VALUES ('00000028', 'P1F', '20180319');
INSERT INTO `kq_init_stu_youer` VALUES ('00000028', 'P1N', '20180319');
INSERT INTO `kq_init_stu_youer` VALUES ('00000028', 'P2F', '20180319');
INSERT INTO `kq_init_stu_youer` VALUES ('00000028', 'P2N', '20180319');

-- ----------------------------
-- Table structure for `kq_init_tch`
-- ----------------------------
DROP TABLE IF EXISTS `kq_init_tch`;
CREATE TABLE `kq_init_tch` (
  `hid` varchar(8) NOT NULL DEFAULT '' COMMENT '学校ID',
  `rid` varchar(32) NOT NULL DEFAULT '' COMMENT '群组ID',
  `period` varchar(8) NOT NULL DEFAULT '' COMMENT '考勤时段',
  `day` varchar(8) NOT NULL DEFAULT '' COMMENT '日期(yyyyMMdd)',
  PRIMARY KEY (`rid`,`period`,`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤初始化表';

-- ----------------------------
-- Records of kq_init_tch
-- ----------------------------
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P1F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P1F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P1F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P1F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P1N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P1N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P1N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P1N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P2F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P2F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P2F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P2F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P2N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P2N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P2N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P2N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P3N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801031740460184825309', 'P3N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P1F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P1F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P1F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P1F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P1N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P1N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P1N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P1N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P2F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P2F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P2F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P2F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P2N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P2N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P2N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P2N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P3N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201801301422098545211769', 'P3N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P1F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P1F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P1F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P1F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P1N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P1N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P1N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P1N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P2F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P2F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P2F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P2F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P2N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P2N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P2N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P2N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P3N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261818372740143390', 'P3N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P1F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P1F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P1F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P1F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P1N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P1N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P1N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P1N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P2F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P2F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P2F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P2F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P2N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P2N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P2N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P2N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P3N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819005594031791', 'P3N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P1F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P1F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P1F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P1F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P1N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P1N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P1N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P1N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P2F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P2F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P2F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P2F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P2N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P2N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P2N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P2N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P3N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819118319172881', 'P3N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P1F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P1F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P1F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P1F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P1N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P1N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P1N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P1N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P2F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P2F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P2F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P2F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P2N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P2N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P2N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P2N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P3N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819179235577256', 'P3N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P1F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P1F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P1F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P1F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P1N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P1N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P1N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P1N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P2F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P2F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P2F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P2F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P2N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P2N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P2N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P2N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P3N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819305500229022', 'P3N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P1F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P1F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P1F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P1F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P1N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P1N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P1N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P1N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P2F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P2F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P2F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P2F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P2N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P2N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P2N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P2N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P3N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819390612528322', 'P3N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P1F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P1F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P1F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P1F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P1N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P1N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P1N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P1N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P2F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P2F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P2F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P2F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P2N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P2N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P2N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P2N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P3N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261819585939320461', 'P3N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P1F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P1F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P1F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P1F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P1N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P1N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P1N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P1N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P2F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P2F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P2F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P2F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P2N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P2N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P2N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P2N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P3N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201802261820159364870527', 'P3N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P1F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P1F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P1F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P1F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P1N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P1N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P1N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P1N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P2F', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P2F', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P2F', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P2F', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P2N', '20180313');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P2N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P2N', '20180316');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P2N', '20180319');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P3N', '20180314');
INSERT INTO `kq_init_tch` VALUES ('00000001', '00000001201803011038519333012777', 'P3N', '20180319');

-- ----------------------------
-- Table structure for `kq_statis_cla_00000001`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_cla_00000001`;
CREATE TABLE `kq_statis_cla_00000001` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `day` int(8) DEFAULT NULL COMMENT '日期(yyyyMMdd)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级考勤统计表';

-- ----------------------------
-- Records of kq_statis_cla_00000001
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_cla_00000026`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_cla_00000026`;
CREATE TABLE `kq_statis_cla_00000026` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `day` int(8) DEFAULT NULL COMMENT '日期(yyyyMMdd)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级考勤统计表';

-- ----------------------------
-- Records of kq_statis_cla_00000026
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_cla_00000028`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_cla_00000028`;
CREATE TABLE `kq_statis_cla_00000028` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `day` int(8) DEFAULT NULL COMMENT '日期(yyyyMMdd)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级考勤统计表';

-- ----------------------------
-- Records of kq_statis_cla_00000028
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_cla_00000029`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_cla_00000029`;
CREATE TABLE `kq_statis_cla_00000029` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `cid` varchar(32) DEFAULT NULL COMMENT '班级ID',
  `day` int(8) DEFAULT NULL COMMENT '日期(yyyyMMdd)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级考勤统计表';

-- ----------------------------
-- Records of kq_statis_cla_00000029
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_role_00000001`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_role_00000001`;
CREATE TABLE `kq_statis_role_00000001` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `rid` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `day` int(8) DEFAULT NULL COMMENT '日期(yyyyMMdd)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师角色考勤统计表';

-- ----------------------------
-- Records of kq_statis_role_00000001
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_role_00000026`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_role_00000026`;
CREATE TABLE `kq_statis_role_00000026` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `rid` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `day` int(8) DEFAULT NULL COMMENT '日期(yyyyMMdd)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师角色考勤统计表';

-- ----------------------------
-- Records of kq_statis_role_00000026
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_role_00000028`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_role_00000028`;
CREATE TABLE `kq_statis_role_00000028` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `rid` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `day` int(8) DEFAULT NULL COMMENT '日期(yyyyMMdd)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师角色考勤统计表';

-- ----------------------------
-- Records of kq_statis_role_00000028
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_role_00000029`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_role_00000029`;
CREATE TABLE `kq_statis_role_00000029` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `rid` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `day` int(8) DEFAULT NULL COMMENT '日期(yyyyMMdd)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师角色考勤统计表';

-- ----------------------------
-- Records of kq_statis_role_00000029
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_stu_00000001`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_stu_00000001`;
CREATE TABLE `kq_statis_stu_00000001` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `mon` int(6) DEFAULT NULL COMMENT '月份(yyyyMM)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤统计表';

-- ----------------------------
-- Records of kq_statis_stu_00000001
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_stu_00000026`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_stu_00000026`;
CREATE TABLE `kq_statis_stu_00000026` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `mon` int(6) DEFAULT NULL COMMENT '月份(yyyyMM)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤统计表';

-- ----------------------------
-- Records of kq_statis_stu_00000026
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_stu_00000028`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_stu_00000028`;
CREATE TABLE `kq_statis_stu_00000028` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `mon` int(6) DEFAULT NULL COMMENT '月份(yyyyMM)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤统计表';

-- ----------------------------
-- Records of kq_statis_stu_00000028
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_stu_00000029`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_stu_00000029`;
CREATE TABLE `kq_statis_stu_00000029` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `mon` int(6) DEFAULT NULL COMMENT '月份(yyyyMM)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生考勤统计表';

-- ----------------------------
-- Records of kq_statis_stu_00000029
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_tch_00000001`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_tch_00000001`;
CREATE TABLE `kq_statis_tch_00000001` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `mon` int(6) DEFAULT NULL COMMENT '月份(yyyyMM)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤统计表';

-- ----------------------------
-- Records of kq_statis_tch_00000001
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_tch_00000026`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_tch_00000026`;
CREATE TABLE `kq_statis_tch_00000026` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `mon` int(6) DEFAULT NULL COMMENT '月份(yyyyMM)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤统计表';

-- ----------------------------
-- Records of kq_statis_tch_00000026
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_tch_00000027`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_tch_00000027`;
CREATE TABLE `kq_statis_tch_00000027` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `mon` int(6) DEFAULT NULL COMMENT '月份(yyyyMM)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤统计表';

-- ----------------------------
-- Records of kq_statis_tch_00000027
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_tch_00000028`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_tch_00000028`;
CREATE TABLE `kq_statis_tch_00000028` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `mon` int(6) DEFAULT NULL COMMENT '月份(yyyyMM)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤统计表';

-- ----------------------------
-- Records of kq_statis_tch_00000028
-- ----------------------------

-- ----------------------------
-- Table structure for `kq_statis_tch_00000029`
-- ----------------------------
DROP TABLE IF EXISTS `kq_statis_tch_00000029`;
CREATE TABLE `kq_statis_tch_00000029` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `mon` int(6) DEFAULT NULL COMMENT '月份(yyyyMM)',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师考勤统计表';

-- ----------------------------
-- Records of kq_statis_tch_00000029
-- ----------------------------
