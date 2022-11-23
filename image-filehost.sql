/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : db_test

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 08/01/2021 21:37:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_host
-- ----------------------------
DROP TABLE IF EXISTS `file_host`;
CREATE TABLE `file_host` (
  `id` bigint NOT NULL,
  `file_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `file_type_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `download_count` int DEFAULT '0',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `file_size` double DEFAULT NULL,
  `upload_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `deleted` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for file_type
-- ----------------------------
DROP TABLE IF EXISTS `file_type`;
CREATE TABLE `file_type` (
  `id` bigint NOT NULL,
  `file_type` varchar(255) COLLATE utf8_bin NOT NULL,
  `file_type_detail` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for image_host
-- ----------------------------
DROP TABLE IF EXISTS `image_host`;
CREATE TABLE `image_host` (
  `id` bigint NOT NULL,
  `image_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `image_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `download_count` int DEFAULT '0',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `thumbnail_path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `file_size` double DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `upload_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `deleted` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for image_type
-- ----------------------------
DROP TABLE IF EXISTS `image_type`;
CREATE TABLE `image_type` (
  `image_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`image_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user_admin
-- ----------------------------
DROP TABLE IF EXISTS `user_admin`;
CREATE TABLE `user_admin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `avatar_path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `user_role` int DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1346825448704716802 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user_ip
-- ----------------------------
DROP TABLE IF EXISTS `user_ip`;
CREATE TABLE `user_ip` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `target_classify` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `target_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;