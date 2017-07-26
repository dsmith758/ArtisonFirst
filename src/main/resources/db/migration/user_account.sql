/*
Navicat MySQL Data Transfer

Source Server         : Local Host
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : artisonfirst

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-26 11:35:10
*/

SET FOREIGN_KEY_CHECKS=0;

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
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES ('1', '102af817-1e72-419f-a390-ed41cd451daa', 'david@walkersmithtech.com', 'David', 'Doctor73#', '3d29ea3d-bc41-4b1b-af66-47673bf0b95f', '1', '2017-07-25 11:42:03', '2017-07-25 11:42:03');

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

-- ----------------------------
-- Records of user_session
-- ----------------------------
INSERT INTO `user_session` VALUES ('1', '2a72fa60-680c-46ae-b79a-5873a8385bfa', '0:0:0:0:0:0:0:1', 'ppclKdDTfUkpfbre2bAPXP1qgcTzN4NLhaQ9h1JwWgpo60yN3mz39BSjkftu4mwZmWbD8zrSGtGHEtmr/pEqu9vaFDCsLSJT6VkyC4uJQVqLvrtbC96oIBcCuDB3vULd+u89lDO0gxM6GGUCyO/9FeDw+ywUfjuM3YAAX1EsGdM=', '94bb7711-7b29-48', '1', '2017-07-25 11:45:28', '2017-07-25 11:45:28');
SET FOREIGN_KEY_CHECKS=1;
