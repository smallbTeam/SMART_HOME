/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 100113
Source Host           : localhost:3306
Source Database       : smallb

Target Server Type    : MYSQL
Target Server Version : 100113
File Encoding         : 65001

Date: 2017-06-27 22:57:25
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户网关关系表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备类型表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网关表';

-- ----------------------------
-- Table structure for propertyMap
-- ----------------------------
DROP TABLE IF EXISTS `propertyMap`;
CREATE TABLE `propertyMap` (
  `propkey` varchar(225) NOT NULL,
  `propval` varchar(550) DEFAULT NULL,
  PRIMARY KEY (`propkey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
