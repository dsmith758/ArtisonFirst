/*
Navicat MySQL Data Transfer

Source Server         : Local Host
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : artisonfirst

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-26 11:45:50
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file_data
-- ----------------------------

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
-- Records of file_data_index
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file_relation_data
-- ----------------------------

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
-- Records of file_relation_data_index
-- ----------------------------

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
-- Records of object_data
-- ----------------------------
INSERT INTO `object_data` VALUES ('1', '102af817-1e72-419f-a390-ed41cd451daa', 'PERSON', '1', '{\"uid\":\"102af817-1e72-419f-a390-ed41cd451daa\",\"type\":\"PERSON\",\"firstName\":\"David\",\"lastName\":\"Smith\",\"personalExperience\":\"SWE\",\"imageUri\":\"image/system/blank-profile-hi.png\",\"contactInfo\":[{\"contactType\":\"FACEBOOK\",\"value\":\"david@walkersmithtech.com\"},{\"contactType\":\"EMAIL\",\"value\":\"david@regalcitymedia.com\"}]}', '2017-07-25 11:42:02', '2017-07-25 11:42:02');
INSERT INTO `object_data` VALUES ('2', '8dabfcf3-c023-41b8-b531-09bf5e1734ca', 'COMPANY', '1', '{\"uid\":\"8dabfcf3-c023-41b8-b531-09bf5e1734ca\",\"type\":\"COMPANY\",\"companyName\":\"Walker Smith Tech\",\"businessType\":\"Software Engineering\"}', '2017-07-25 11:42:03', '2017-07-25 15:44:03');
INSERT INTO `object_data` VALUES ('3', 'd6dba742-4bbe-4867-b90c-c4457741a1d0', 'LOCATION', '1', '{\"uid\":\"d6dba742-4bbe-4867-b90c-c4457741a1d0\",\"type\":\"LOCATION\",\"addressName\":\"Headquarters\",\"address1\":\"44647 Camino Gonzales\",\"address2\":\"Suite 2\",\"city\":\"Temecula\",\"state\":\"CA\",\"country\":\"US\",\"zip\":\"92592\"}', '2017-07-25 13:59:05', '2017-07-25 15:44:03');

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of object_data_index
-- ----------------------------
INSERT INTO `object_data_index` VALUES ('1', '102af817-1e72-419f-a390-ed41cd451daa', 'FIRSTNAME', 'David');
INSERT INTO `object_data_index` VALUES ('2', '102af817-1e72-419f-a390-ed41cd451daa', 'LASTNAME', 'Smith');
INSERT INTO `object_data_index` VALUES ('3', '102af817-1e72-419f-a390-ed41cd451daa', 'CONTACT_INFO', 'david@walkersmithtech.com');
INSERT INTO `object_data_index` VALUES ('4', '102af817-1e72-419f-a390-ed41cd451daa', 'CONTACT_INFO', 'david@regalcitymedia.com');
INSERT INTO `object_data_index` VALUES ('23', '8dabfcf3-c023-41b8-b531-09bf5e1734ca', 'COMPANY_NAME', 'Walker Smith Tech');
INSERT INTO `object_data_index` VALUES ('24', '8dabfcf3-c023-41b8-b531-09bf5e1734ca', 'BUSINESS_TYPE', 'Software Engineering');
INSERT INTO `object_data_index` VALUES ('25', 'd6dba742-4bbe-4867-b90c-c4457741a1d0', 'CITY', 'Temecula');
INSERT INTO `object_data_index` VALUES ('26', 'd6dba742-4bbe-4867-b90c-c4457741a1d0', 'STATE', 'CA');
INSERT INTO `object_data_index` VALUES ('27', 'd6dba742-4bbe-4867-b90c-c4457741a1d0', 'ZIP', '92592');
INSERT INTO `object_data_index` VALUES ('28', 'd6dba742-4bbe-4867-b90c-c4457741a1d0', 'COUNTRY', 'US');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of object_relation_data
-- ----------------------------
INSERT INTO `object_relation_data` VALUES ('1', '4ce12b59-a68e-4864-86a3-04d1d2ee7a88', '102af817-1e72-419f-a390-ed41cd451daa', '8dabfcf3-c023-41b8-b531-09bf5e1734ca', 'PERSON_COMPANY', '{\"role\":\"PERSON_COMPANY\",\"sourceUid\":\"102af817-1e72-419f-a390-ed41cd451daa\",\"targetUid\":\"8dabfcf3-c023-41b8-b531-09bf5e1734ca\"}', '2017-07-25 11:42:03', '2017-07-25 11:42:03');
INSERT INTO `object_relation_data` VALUES ('5', 'b5f8f658-cf5b-4701-bc84-d4fb09c0f9dd', '8dabfcf3-c023-41b8-b531-09bf5e1734ca', 'd6dba742-4bbe-4867-b90c-c4457741a1d0', 'COMPANY_LOCATION', '{\"role\":\"COMPANY_LOCATION\",\"sourceUid\":\"8dabfcf3-c023-41b8-b531-09bf5e1734ca\",\"targetUid\":\"d6dba742-4bbe-4867-b90c-c4457741a1d0\"}', '2017-07-25 15:44:03', '2017-07-25 15:44:03');

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
-- Records of object_relation_data_index
-- ----------------------------

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
