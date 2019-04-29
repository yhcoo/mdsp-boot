/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : mdsp_route

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 17/04/2019 09:15:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_zuul_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_zuul_route`;
CREATE TABLE `sys_zuul_route` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'router Id',
  `path` varchar(255) NOT NULL COMMENT '路由路径',
  `service_id` varchar(255) NOT NULL COMMENT '服务名称',
  `url` varchar(255) DEFAULT NULL COMMENT 'url代理',
  `strip_prefix` char(1) DEFAULT '1' COMMENT '转发去掉前缀',
  `retryable` char(1) DEFAULT '1' COMMENT '是否重试',
  `enabled` char(1) DEFAULT '1' COMMENT '是否启用',
  `sensitiveHeaders_list` varchar(255) DEFAULT NULL COMMENT '敏感请求头',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='动态路由配置表';

-- ----------------------------
-- Records of sys_zuul_route
-- ----------------------------
BEGIN;
INSERT INTO `sys_zuul_route` VALUES (4, '/admin/**', 'mdsp-upms-service', '', '1', '1', '1', '', '2019-03-23 17:27:17', '2019-03-20 23:54:59', '0');
INSERT INTO `sys_zuul_route` VALUES (5, '/auth/**', 'mdsp-auth-service', '', '1', '1', '1', '', '2019-03-23 17:27:17', '2019-03-20 17:30:58', '0');
INSERT INTO `sys_zuul_route` VALUES (6, '/syslog/**', 'mdsp-log-service', ' ', '1', '1', '1', '', '2019-03-23 17:27:17', '2019-03-20 17:30:57', '0');
INSERT INTO `sys_zuul_route` VALUES (7, '/gen/**', 'mdsp-gen-service', '', '1', '1', '1', '', '2019-03-23 17:27:17', '2019-03-20 17:30:56', '0');
INSERT INTO `sys_zuul_route` VALUES (8, '/tsc/**', 'mdsp-transaction-console-service', '', '1', '1', '1', '', '2019-03-23 17:27:17', '2019-03-25 13:51:34', '0');
INSERT INTO `sys_zuul_route` VALUES (9, '/website/**', 'mdsp-website-service', '', '1', '1', '1', '', '2019-03-23 17:27:17', '2019-04-13 11:37:42', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
