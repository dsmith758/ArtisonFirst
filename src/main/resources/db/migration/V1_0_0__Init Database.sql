/*
Navicat MySQL Data Transfer

Source Server         : Local Host
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : artisonfirst

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-29 11:26:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_data
-- ----------------------------
DROP TABLE IF EXISTS `file_data`;
CREATE TABLE `file_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL,
  `doc_type` varchar(32) NOT NULL,
  `data` longtext,
  `file` longblob NOT NULL,
  `created_on` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_uid` (`uid`),
  KEY `idx_doc_type` (`doc_type`,`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file_data_index
-- ----------------------------
DROP TABLE IF EXISTS `file_data_index`;
CREATE TABLE `file_data_index` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL,
  `type` varchar(255) NOT NULL,
  `data` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_uid` (`type`,`uid`),
  KEY `idx_uid_file` (`uid`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file_relation_data
-- ----------------------------
DROP TABLE IF EXISTS `file_relation_data`;
CREATE TABLE `file_relation_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL,
  `role` varchar(32) NOT NULL,
  `data` longtext,
  `object_uid` varchar(64) NOT NULL,
  `file_uid` varchar(64) NOT NULL,
  `created_on` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_object_uid` (`object_uid`),
  KEY `idx_file_uid` (`file_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file_relation_data_index
-- ----------------------------
DROP TABLE IF EXISTS `file_relation_data_index`;
CREATE TABLE `file_relation_data_index` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL,
  `type` varchar(255) NOT NULL,
  `data` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_relation_uid` (`type`,`uid`),
  KEY `idx_uid_file_relation` (`uid`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for object_data
-- ----------------------------
DROP TABLE IF EXISTS `object_data`;
CREATE TABLE `object_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL,
  `type` varchar(16) NOT NULL,
  `status` int(10) NOT NULL,
  `data` longtext NOT NULL,
  `created_on` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_object_uid` (`uid`),
  KEY `idx_uid_object` (`uid`,`type`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for object_data_index
-- ----------------------------
DROP TABLE IF EXISTS `object_data_index`;
CREATE TABLE `object_data_index` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL,
  `type` varchar(255) NOT NULL,
  `data` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_search_uid` (`type`,`uid`),
  KEY `idx_uid_search` (`uid`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for object_relation_data
-- ----------------------------
DROP TABLE IF EXISTS `object_relation_data`;
CREATE TABLE `object_relation_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL,
  `source_uid` varchar(64) NOT NULL,
  `target_uid` varchar(64) NOT NULL,
  `role` varchar(32) NOT NULL,
  `data` longtext NOT NULL,
  `created_on` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_on` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_data_relation` (`source_uid`,`target_uid`,`role`),
  UNIQUE KEY `idx_relation_uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for object_relation_data_index
-- ----------------------------
DROP TABLE IF EXISTS `object_relation_data_index`;
CREATE TABLE `object_relation_data_index` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(64) NOT NULL,
  `type` varchar(64) NOT NULL,
  `data` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_relation_uid` (`uid`,`type`),
  KEY `idx_uid_relation` (`type`,`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_uid` varchar(64) NOT NULL,
  `login_name` varchar(64) NOT NULL,
  `display_name` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `confirmation_code` varchar(64) DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  `created_on` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_email_address` (`login_name`),
  KEY `idx_person_uid` (`person_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_session
-- ----------------------------
DROP TABLE IF EXISTS `user_session`;
CREATE TABLE `user_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(64) NOT NULL,
  `ip_address` varchar(32) NOT NULL,
  `token` varchar(255) NOT NULL,
  `salt` varchar(64) NOT NULL,
  `never_expires` int(11) NOT NULL DEFAULT '0',
  `initialized_on` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_touched_on` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_session_id` (`session_id`),
  KEY `idx_ip_address` (`ip_address`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
