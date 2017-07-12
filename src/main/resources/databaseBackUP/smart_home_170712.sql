/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 100113
Source Host           : localhost:3306
Source Database       : smart_home

Target Server Type    : MYSQL
Target Server Version : 100113
File Encoding         : 65001

Date: 2017-07-12 20:10:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `mobelPhone` varchar(16) DEFAULT NULL COMMENT '电话',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `nickName` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `permissionLevel` tinyint(4) DEFAULT NULL COMMENT '权限级别',
  `token` varchar(255) DEFAULT NULL COMMENT 'token',
  `createdDate` datetime NOT NULL COMMENT '创建时间',
  `modifiedDate` datetime NOT NULL COMMENT '修改时间',
  `isDeleted` tinyint(1) NOT NULL COMMENT '是否删除 1:是 2:否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for category_parameter
-- ----------------------------
DROP TABLE IF EXISTS `category_parameter`;
CREATE TABLE `category_parameter` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `deviceCategoryId` bigint(11) NOT NULL COMMENT '设备类型ID',
  `code` varchar(32) DEFAULT NULL COMMENT '属性code',
  `name` varchar(32) DEFAULT NULL COMMENT '属性名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型(0.固有属性，1.状态类，2.状态切换类，3.参数类，4.参数设定类，5.故障类，6.故障旁路类，7.特殊功能类)',
  `unit` varchar(16) DEFAULT NULL COMMENT '单位',
  `dataType` tinyint(4) DEFAULT NULL COMMENT '数据类型，0. short，1. bool，2. float，3. byte，4. byte array',
  `isReadOnly` tinyint(1) DEFAULT NULL COMMENT '是否只读 1:只读 2:非只读',
  `createdDate` datetime NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `isDeleted` tinyint(1) NOT NULL COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备参数表';

-- ----------------------------
-- Records of category_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `mobelPhone` varchar(16) NOT NULL COMMENT '电话',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `wxId` varchar(255) DEFAULT NULL COMMENT '微信Id',
  `nickName` varbinary(64) DEFAULT NULL COMMENT '昵称',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别 1男 2 女 0其他',
  `token` varchar(255) DEFAULT NULL COMMENT 'token',
  `createdDate` datetime NOT NULL COMMENT '创建时间',
  `modifiedDate` datetime NOT NULL COMMENT '修改时间',
  `isDeleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 1:是 2:否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `seriaNumber` varchar(64) NOT NULL COMMENT '设备序列号',
  `deviceCategoryId` bigint(11) NOT NULL COMMENT '设备类型Id',
  `gatewaySerialNumber` varchar(64) NOT NULL COMMENT '网关Id',
  `parentId` bigint(11) DEFAULT NULL COMMENT '父设备Id',
  `state` tinyint(4) DEFAULT NULL COMMENT '开关状态',
  `name` varchar(64) DEFAULT NULL COMMENT '设备名称',
  `createdDate` datetime NOT NULL COMMENT '创建时间',
  `modifiedDate` datetime NOT NULL COMMENT '修改时间',
  `isDeleted` tinyint(1) NOT NULL COMMENT '是否删除 1:是 2:否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备表';

-- ----------------------------
-- Records of device
-- ----------------------------

-- ----------------------------
-- Table structure for device_category
-- ----------------------------
DROP TABLE IF EXISTS `device_category`;
CREATE TABLE `device_category` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备类型ID',
  `model` varchar(64) NOT NULL COMMENT '设备型号',
  `name` varchar(64) DEFAULT NULL COMMENT '类型名称',
  `parentId` bigint(11) DEFAULT NULL COMMENT '父设备类型Id',
  `attention` varchar(500) DEFAULT NULL COMMENT '设备类型注意事项',
  `describtion` varchar(500) DEFAULT NULL COMMENT '设备类型介绍',
  `createdDate` datetime NOT NULL COMMENT '创建时间',
  `modifiedDate` datetime NOT NULL COMMENT '修改时间',
  `isDeleted` tinyint(1) NOT NULL COMMENT '是否删除 1:是 2:否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备类型表';

-- ----------------------------
-- Records of device_category
-- ----------------------------

-- ----------------------------
-- Table structure for device_data_day
-- ----------------------------
DROP TABLE IF EXISTS `device_data_day`;
CREATE TABLE `device_data_day` (
  `deviceId` bigint(11) NOT NULL COMMENT '设备Id',
  `value` double(11,5) DEFAULT NULL COMMENT '参数值',
  `categoryParameterId` bigint(11) NOT NULL COMMENT '参数类型',
  `recordTime` datetime NOT NULL COMMENT '采集时间',
  PRIMARY KEY (`recordTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备每日数据表';

-- ----------------------------
-- Records of device_data_day
-- ----------------------------

-- ----------------------------
-- Table structure for device_data_hour
-- ----------------------------
DROP TABLE IF EXISTS `device_data_hour`;
CREATE TABLE `device_data_hour` (
  `deviceId` bigint(11) NOT NULL COMMENT '设备Id',
  `value` double(11,5) DEFAULT NULL COMMENT '参数值',
  `categoryParameterId` bigint(11) NOT NULL COMMENT '参数类型',
  `recordTime` datetime NOT NULL COMMENT '采集时间',
  PRIMARY KEY (`recordTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备每小时数据表';

-- ----------------------------
-- Records of device_data_hour
-- ----------------------------

-- ----------------------------
-- Table structure for device_data_now
-- ----------------------------
DROP TABLE IF EXISTS `device_data_now`;
CREATE TABLE `device_data_now` (
  `deviceId` bigint(11) NOT NULL COMMENT '设备Id',
  `value` double(11,5) DEFAULT NULL COMMENT '参数值',
  `categoryParameterId` bigint(11) NOT NULL COMMENT '参数类型',
  `recordTime` datetime NOT NULL COMMENT '采集时间',
  PRIMARY KEY (`recordTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备实时数据表';

-- ----------------------------
-- Records of device_data_now
-- ----------------------------

-- ----------------------------
-- Table structure for device_data_week
-- ----------------------------
DROP TABLE IF EXISTS `device_data_week`;
CREATE TABLE `device_data_week` (
  `deviceId` bigint(11) NOT NULL COMMENT '设备Id',
  `value` double(11,5) DEFAULT NULL COMMENT '参数值',
  `categoryParameterId` bigint(11) NOT NULL COMMENT '参数类型',
  `recordTime` datetime NOT NULL COMMENT '采集时间',
  PRIMARY KEY (`recordTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备每周数据表';

-- ----------------------------
-- Records of device_data_week
-- ----------------------------

-- ----------------------------
-- Table structure for gateway
-- ----------------------------
DROP TABLE IF EXISTS `gateway`;
CREATE TABLE `gateway` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '网关ID',
  `serialNumber` varchar(64) NOT NULL COMMENT '网关序列号',
  `address` varchar(255) DEFAULT NULL COMMENT '网关所在地地址',
  `port` int(8) DEFAULT NULL COMMENT '端口',
  `parameter` varchar(255) DEFAULT NULL COMMENT '网关参数',
  `ip` varchar(24) DEFAULT NULL COMMENT '网关IP',
  `createdDate` datetime NOT NULL COMMENT '创建时间',
  `modifiedDate` datetime NOT NULL COMMENT '修改时间',
  `isDeleted` tinyint(1) NOT NULL COMMENT '是否删除 1:是 2:否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网关表';

-- ----------------------------
-- Records of gateway
-- ----------------------------

-- ----------------------------
-- Table structure for property_map
-- ----------------------------
DROP TABLE IF EXISTS `property_map`;
CREATE TABLE `property_map` (
  `propkey` varchar(225) NOT NULL COMMENT '配置 - 属性',
  `propval` varchar(550) DEFAULT NULL,
  PRIMARY KEY (`propkey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置表';

-- ----------------------------
-- Records of property_map
-- ----------------------------

-- ----------------------------
-- Table structure for rel_customer_gateway
-- ----------------------------
DROP TABLE IF EXISTS `rel_customer_gateway`;
CREATE TABLE `rel_customer_gateway` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户-网关关联Id',
  `customerId` bigint(11) NOT NULL COMMENT '用户Id',
  `gatewaySerialNumber` varchar(64) NOT NULL COMMENT '网关设备序号',
  `isOnwer` tinyint(1) NOT NULL,
  `gatewayName` varchar(64) NOT NULL COMMENT '网关名称',
  `createdDate` datetime NOT NULL COMMENT '创建时间',
  `modifiedDate` datetime NOT NULL COMMENT '修改时间',
  `isDeleted` tinyint(1) NOT NULL COMMENT '是否删除 1:是 2:否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户网关关系表';

-- ----------------------------
-- Records of rel_customer_gateway
-- ----------------------------
