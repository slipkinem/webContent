/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : vue_admin

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-06-14 14:51:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for crm_order
-- ----------------------------
DROP TABLE IF EXISTS `crm_order`;
CREATE TABLE `crm_order` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(20) DEFAULT NULL,
  `caller_number` varchar(20) DEFAULT NULL,
  `called_number` varchar(20) DEFAULT NULL,
  `call_type` varchar(20) DEFAULT NULL,
  `call_time` datetime DEFAULT NULL,
  `answer_time` datetime DEFAULT NULL,
  `call_time_length` varchar(20) DEFAULT NULL,
  `answer_seating` varchar(20) DEFAULT NULL,
  `job_number` varchar(20) DEFAULT NULL,
  `call_status` varchar(20) DEFAULT NULL,
  `skill_groups` varchar(20) DEFAULT NULL,
  `diabolo_time_length` varchar(20) DEFAULT NULL,
  `satisfaction` int(10) DEFAULT NULL,
  `qc_grade` int(10) DEFAULT NULL,
  `queue_time_length` varchar(20) DEFAULT NULL,
  `hangup_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `comment_id` int(12) NOT NULL AUTO_INCREMENT,
  `post_id` int(12) DEFAULT NULL,
  `user_id` int(12) DEFAULT NULL,
  `username` varchar(12) DEFAULT NULL,
  `comment_content` text,
  `comment_rate` int(1) DEFAULT NULL,
  `comment_nemo` int(1) DEFAULT '0' COMMENT '0 是具名 1是匿名',
  `comment_type` int(1) DEFAULT '0' COMMENT '0是普通留言',
  `comment_delete` int(11) DEFAULT '0' COMMENT '1删除 0是存在',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_post
-- ----------------------------
DROP TABLE IF EXISTS `t_post`;
CREATE TABLE `t_post` (
  `post_id` int(12) NOT NULL AUTO_INCREMENT,
  `post_content` text,
  `post_title` varchar(50) DEFAULT NULL,
  `user_id` int(12) DEFAULT NULL,
  `post_good` int(1) DEFAULT '0' COMMENT '0一般 1推荐 2精华',
  `post_delete` int(1) DEFAULT '0' COMMENT '0不删除 1删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_table
-- ----------------------------
DROP TABLE IF EXISTS `t_table`;
CREATE TABLE `t_table` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `zip` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(11) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_delete` int(1) DEFAULT '0' COMMENT '0正常 1是删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
