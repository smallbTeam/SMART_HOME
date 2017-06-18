/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 100113
Source Host           : localhost:3306
Source Database       : smallb

Target Server Type    : MYSQL
Target Server Version : 100113
File Encoding         : 65001

Date: 2017-06-18 06:18:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for Customer
-- ----------------------------
DROP TABLE IF EXISTS `Customer`;
CREATE TABLE `Customer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `MobelPhone` varchar(16) DEFAULT NULL COMMENT '电话',
  `Password` varchar(50) DEFAULT NULL COMMENT '密码',
  `WxId` varchar(255) DEFAULT NULL COMMENT '微信Id',
  `NickName` varbinary(255) DEFAULT NULL COMMENT '昵称',
  `Birthday` date DEFAULT NULL COMMENT '出生日期',
  `Sex` tinyint(4) DEFAULT NULL COMMENT '性别 1男 2 女 0其他',
  `Token` varchar(255) DEFAULT NULL COMMENT 'token',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifiedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `IsDeleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1:是 2:否',
  `Reserve` varchar(255) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of Customer
-- ----------------------------
INSERT INTO `Customer` VALUES ('2', '15620608789', '1amam', null, null, null, null, null, '2017-06-06 21:26:32', '2017-06-06 21:26:32', '0', null);
INSERT INTO `Customer` VALUES ('3', '1562084524894', '666666', null, 0x6E69636B4E616D65, '1994-10-16', '1', null, '2017-06-06 21:30:50', '2017-06-06 21:30:50', '0', null);
INSERT INTO `Customer` VALUES ('4', '13937788421', '123456', null, null, null, null, null, '2017-06-10 00:09:50', '2017-06-10 00:09:50', '0', null);
INSERT INTO `Customer` VALUES ('5', '13937788421', '123456', null, null, null, null, null, '2017-06-10 00:11:26', '2017-06-10 00:11:26', '0', null);
INSERT INTO `Customer` VALUES ('6', '13937788421', '123456', null, null, null, null, null, '2017-06-10 00:18:16', '2017-06-10 00:18:16', '0', null);
INSERT INTO `Customer` VALUES ('7', '13937788421', '123456', null, null, null, null, null, '2017-06-10 00:18:17', '2017-06-10 00:18:17', '0', null);
INSERT INTO `Customer` VALUES ('8', '13937788421', '123456', null, null, null, null, null, '2017-06-10 00:18:18', '2017-06-10 00:18:18', '0', null);
INSERT INTO `Customer` VALUES ('9', '15764227900', '123456', null, null, null, null, null, '2017-06-10 00:53:24', '2017-06-10 00:53:24', '0', null);
INSERT INTO `Customer` VALUES ('10', '15764227900', '123456', null, null, null, null, null, '2017-06-10 00:54:23', '2017-06-10 00:54:23', '0', null);
INSERT INTO `Customer` VALUES ('11', '15764227900', '123456', null, null, null, null, null, '2017-06-10 01:07:41', '2017-06-10 01:07:41', '0', null);
INSERT INTO `Customer` VALUES ('12', '15764227900', '123456', null, null, null, null, null, '2017-06-10 01:19:40', '2017-06-10 01:19:40', '0', null);
INSERT INTO `Customer` VALUES ('13', '15613879756', '123456', null, null, null, null, null, '2017-06-10 01:20:19', '2017-06-10 01:20:19', '0', null);
INSERT INTO `Customer` VALUES ('14', '12457895456', '123456', null, null, null, null, null, '2017-06-10 01:37:05', '2017-06-10 01:37:05', '0', null);
INSERT INTO `Customer` VALUES ('15', '15620608789', '1', null, null, null, null, null, '2017-06-17 09:16:33', '2017-06-17 09:16:33', '0', null);
INSERT INTO `Customer` VALUES ('16', '13645284152', '48uyhgsdfghjk', 'wertyuioikjhgfdftgyhutu', 0x6C616C61, '2017-06-11', '1', null, '2017-06-18 03:55:27', '2017-06-18 03:55:27', '0', null);

-- ----------------------------
-- Table structure for CustomerGateway
-- ----------------------------
DROP TABLE IF EXISTS `CustomerGateway`;
CREATE TABLE `CustomerGateway` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `CustomerId` int(11) NOT NULL COMMENT '用户Id',
  `GatewayId` int(11) NOT NULL COMMENT '网关ID',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifiedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `IsDeleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1:是 2:否',
  `Reserve` varchar(255) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户网关关系表';

-- ----------------------------
-- Records of CustomerGateway
-- ----------------------------
INSERT INTO `CustomerGateway` VALUES ('1', '1', '1', '2017-06-18 04:57:27', '2017-06-18 04:57:27', '0', null);
INSERT INTO `CustomerGateway` VALUES ('2', '2', '1', '2017-06-18 05:00:32', '2017-06-18 05:00:32', '0', null);

-- ----------------------------
-- Table structure for Device
-- ----------------------------
DROP TABLE IF EXISTS `Device`;
CREATE TABLE `Device` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `DeviceTypeId` int(11) NOT NULL COMMENT '设备类型Id',
  `GetwayId` int(11) DEFAULT NULL COMMENT '网关Id',
  `State` tinyint(4) DEFAULT NULL COMMENT '设备状态',
  `Name` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `DeviceNo` varchar(255) DEFAULT NULL COMMENT '设备号',
  `DeviceData` varchar(500) DEFAULT NULL COMMENT '设备数据',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifiedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `IsDeleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1:是 2:否',
  `Reserve1` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='设备表';

-- ----------------------------
-- Records of Device
-- ----------------------------
INSERT INTO `Device` VALUES ('1', '1', '1', '1', '设备', 'trewq3457', '设devivesdads', '2017-06-18 05:35:26', '2017-06-18 05:45:48', '0', null);
INSERT INTO `Device` VALUES ('2', '1', '1', '1', '设备', 'trewq3457', '设devivesdads', '2017-06-18 05:39:05', '2017-06-18 05:45:44', '1', null);
INSERT INTO `Device` VALUES ('3', '1', '1', '1', '设备', 'trewq3457', '设devivesdads', '2017-06-18 05:39:05', '2017-06-18 05:45:19', '0', null);
INSERT INTO `Device` VALUES ('4', '1', '1', '1', '设备', 'trewq3457', '设devivesdads', '2017-06-18 05:39:05', '2017-06-18 05:45:52', '0', null);
INSERT INTO `Device` VALUES ('5', '1', null, '1', '设备', 'trewq3457', '设devivesdads', '2017-06-18 05:39:06', '2017-06-18 05:39:06', '0', null);
INSERT INTO `Device` VALUES ('6', '1', null, '1', '设备', 'trewq3457', '设devivesdads', '2017-06-18 05:39:06', '2017-06-18 05:39:06', '0', null);
INSERT INTO `Device` VALUES ('7', '1', '1', '1', '设备', 'trewq3457', '设devivesdads', '2017-06-18 05:40:10', '2017-06-18 05:40:10', '0', null);

-- ----------------------------
-- Table structure for DeviceDailyData
-- ----------------------------
DROP TABLE IF EXISTS `DeviceDailyData`;
CREATE TABLE `DeviceDailyData` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `DeviceID` int(11) NOT NULL COMMENT '设备Id',
  `DailyData` varchar(500) NOT NULL COMMENT '设备数据',
  `RecordTime` date NOT NULL COMMENT '采集时间',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifiedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `IsDeleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1:是 2:否',
  `Reserve` varchar(255) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备日数据表';

-- ----------------------------
-- Records of DeviceDailyData
-- ----------------------------

-- ----------------------------
-- Table structure for DeviceData
-- ----------------------------
DROP TABLE IF EXISTS `DeviceData`;
CREATE TABLE `DeviceData` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `DeviceId` int(11) NOT NULL COMMENT '设备Id',
  `DeviceData` varchar(500) NOT NULL COMMENT '设备数据',
  `RecordTime` datetime NOT NULL COMMENT '采集时间',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifiedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `IsDeleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1:是 2:否',
  `Reserve` varchar(255) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备实时数据表';

-- ----------------------------
-- Records of DeviceData
-- ----------------------------

-- ----------------------------
-- Table structure for DeviceMonthData
-- ----------------------------
DROP TABLE IF EXISTS `DeviceMonthData`;
CREATE TABLE `DeviceMonthData` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `DeviceID` int(11) NOT NULL COMMENT '设备Id',
  `MounthData` varchar(500) NOT NULL COMMENT '设备数据',
  `RecordTime` date NOT NULL COMMENT '采集时间',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifiedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `IsDeleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1:是 2:否',
  `Reserve` varchar(255) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备月数据表';

-- ----------------------------
-- Records of DeviceMonthData
-- ----------------------------

-- ----------------------------
-- Table structure for DeviceType
-- ----------------------------
DROP TABLE IF EXISTS `DeviceType`;
CREATE TABLE `DeviceType` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备类型ID',
  `Name` varchar(255) NOT NULL COMMENT '类型名称',
  `Model` varchar(255) NOT NULL COMMENT '设备型号',
  `Attention` varchar(500) DEFAULT NULL COMMENT '设备类型注意事项',
  `Describtion` varchar(500) DEFAULT NULL COMMENT '设备类型介绍',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifiedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `IsDeleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1:是 2:否',
  `Reserve` varchar(255) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='设备类型表';

-- ----------------------------
-- Records of DeviceType
-- ----------------------------
INSERT INTO `DeviceType` VALUES ('1', '设别类型修改名', 'sxc', '设备修改注意', '设备修改描述', '2017-06-18 05:12:01', '2017-06-18 05:21:22', '0', null);
INSERT INTO `DeviceType` VALUES ('2', '设别类型名1', 'sxcw13', '设备注意', '设备描述1', '2017-06-18 05:12:11', '2017-06-18 05:23:40', '1', null);
INSERT INTO `DeviceType` VALUES ('3', '设别类型名1', 'sxcw13', '设备注意', '设备描述1', '2017-06-18 05:12:14', '2017-06-18 05:12:14', '0', null);

-- ----------------------------
-- Table structure for Gateway
-- ----------------------------
DROP TABLE IF EXISTS `Gateway`;
CREATE TABLE `Gateway` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '网关ID',
  `GatewayPort` int(8) NOT NULL COMMENT '端口',
  `IP` varchar(255) NOT NULL COMMENT '网关IP',
  `Address` varchar(255) NOT NULL COMMENT '网关地址',
  `CreatedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifiedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `IsDeleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1:是 2:否',
  `Reserve` varchar(255) DEFAULT NULL COMMENT '预留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='网关表';

-- ----------------------------
-- Records of Gateway
-- ----------------------------
INSERT INTO `Gateway` VALUES ('1', '5', '192.168.0.15', '192.168.0.1', '2017-06-18 04:50:58', '2017-06-18 05:07:14', '1', null);
INSERT INTO `Gateway` VALUES ('2', '5', '192.168.0.15', '192.168.0.1', '2017-06-18 05:03:40', '2017-06-18 05:03:40', '0', null);
INSERT INTO `Gateway` VALUES ('3', '5', '192.168.0.16', '192.168.0.2', '2017-06-18 05:06:50', '2017-06-18 05:06:50', '0', null);
