/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : mdsp_upms

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 17/04/2019 09:15:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resources_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `addition_information` varchar(1000) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `sys_oauth_client_details` VALUES ('cloud', NULL, '$2a$10$X1HOPGX6ADkQn4rvtk.C4uaz8vF1TdpY2aP/iC.3UKlonvco/k9e.', 'server', 'password,refresh_token,authorization_code', NULL, NULL, NULL, NULL, NULL, 'false');
COMMIT;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '资源类型 0-菜单 1-按钮',
  `path` varchar(128) DEFAULT NULL COMMENT '前端url',
  `permission` varchar(128) DEFAULT NULL COMMENT '按钮权限资源标识',
  `color` varchar(64) DEFAULT NULL COMMENT '颜色',
  `parent_id` bigint(11) NOT NULL COMMENT '父资源id',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `component` varchar(128) DEFAULT NULL COMMENT '组件路径',
  `sort` int(11) DEFAULT NULL COMMENT '排序权重',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除 1-删除，0-未删除',
  `url` varchar(128) DEFAULT NULL COMMENT '后端路径',
  `method` varchar(11) DEFAULT NULL COMMENT '请求方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1116634105528655881 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='资源表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` VALUES (1, '系统管理', '0', '/admin', '/admin', NULL, -1, 'xitongguanli', 'Layout', 2, '2017-11-07 20:56:00', '2019-03-03 17:00:46', '0', '', NULL);
INSERT INTO `sys_resource` VALUES (2, '用户管理', '0', 'user', '/admin/user', NULL, 1, 'yonghuguanli', 'views/admin/user/index', 2, '2017-11-02 22:24:37', '2019-03-03 17:05:20', '0', '', NULL);
INSERT INTO `sys_resource` VALUES (3, '资源管理', '0', 'menu', '/admin/resource', NULL, 1, 'caidanguanli', 'views/admin/menu/index', 3, '2017-11-08 09:57:27', '2019-03-03 17:05:21', '0', '', NULL);
INSERT INTO `sys_resource` VALUES (4, '角色管理', '0', 'role', '/admin/role', NULL, 1, 'jueseguanli', 'views/admin/role/index', 4, '2017-11-08 10:13:37', '2019-03-03 17:05:22', '0', '', NULL);
INSERT INTO `sys_resource` VALUES (6, '系统监控', '0', '/system', '/system:view', NULL, -1, 'iconbmgl', 'System', 8, '2018-01-22 12:30:41', '2019-03-03 22:30:20', '0', NULL, NULL);
INSERT INTO `sys_resource` VALUES (9, '查看用户', '1', NULL, '/admin/user:select', NULL, 2, NULL, NULL, 2, '2018-10-17 16:32:36', '2019-03-03 22:06:03', '0', '/admin/user/*', 'GET');
INSERT INTO `sys_resource` VALUES (10, '测试', '0', '/a/b', '/test', NULL, -1, 'xx', '/a/b', 2, '2018-10-29 20:48:37', '2019-01-31 15:07:03', '1', NULL, NULL);
INSERT INTO `sys_resource` VALUES (13, '添加用户', '1', NULL, '/admin/user:add', NULL, 2, NULL, NULL, 1, '2018-11-05 15:49:44', '2019-03-03 22:06:42', '0', '/admin/user/*', 'POST');
INSERT INTO `sys_resource` VALUES (14, '修改用户', '1', NULL, '/admin/user:update', NULL, 2, NULL, NULL, 1, '2018-11-05 15:50:02', '2019-03-03 22:07:33', '0', '/admin/user/*', 'PUT');
INSERT INTO `sys_resource` VALUES (15, '删除用户', '1', NULL, '/admin/user:delete', NULL, 2, NULL, NULL, 5, '2018-11-05 15:50:26', '2019-03-03 22:07:40', '0', '/admin/user/*', 'DELETE');
INSERT INTO `sys_resource` VALUES (16, '添加资源', '1', '', '/admin/menu:add', NULL, 3, 'caidanguanli', 'views/admin/menu/index', 0, '2017-11-07 20:56:00', '2019-03-03 22:07:44', '0', '/admin/resource/**', 'POST');
INSERT INTO `sys_resource` VALUES (17, '编辑资源', '1', NULL, '/admin/menu:update', NULL, 3, NULL, 'views/admin/menu/index', 1, '2018-11-05 15:50:26', '2019-03-03 22:07:48', '0', '/admin/resource/**', 'PUT');
INSERT INTO `sys_resource` VALUES (18, '删除资源', '1', NULL, '/admin/menu:delete', NULL, 3, NULL, 'views/admin/menu/index', 1, '2018-11-05 15:50:26', '2019-03-03 22:07:53', '0', '/admin/resource/**', 'DELETE');
INSERT INTO `sys_resource` VALUES (19, '查询资源', '1', NULL, '/admin/menu:select', NULL, 3, NULL, 'views/admin/menu/index', 1, '2018-11-05 15:50:26', '2019-03-03 22:07:58', '0', '/admin/resource/**', 'GET');
INSERT INTO `sys_resource` VALUES (20, '查看资源', '1', NULL, '/admin/role:select', NULL, 4, NULL, 'views/admin/role/index', 1, '2018-11-05 15:50:26', '2019-03-03 22:08:01', '0', '/admin/role/**', 'GET');
INSERT INTO `sys_resource` VALUES (21, '添加资源', '1', NULL, '/admin/role:add', NULL, 4, NULL, 'views/admin/role/index', 1, '2018-11-05 15:50:26', '2019-03-03 22:08:06', '0', '/admin/role/**', 'POST');
INSERT INTO `sys_resource` VALUES (22, '编辑资源', '1', NULL, '/admin/role:update', NULL, 4, NULL, 'views/admin/role/index', 1, '2018-11-05 15:50:26', '2019-03-03 22:08:09', '0', '/admin/role/**', 'PUT');
INSERT INTO `sys_resource` VALUES (23, '删除资源', '1', NULL, '/admin/role:delete', NULL, 4, NULL, 'views/admin/role/index', 1, '2018-11-05 15:50:26', '2019-03-03 22:08:13', '0', '/admin/role/**', 'DELETE');
INSERT INTO `sys_resource` VALUES (29, '查询', '1', NULL, '/gen/code:select', NULL, 61, NULL, 'views/gen/code/index', 1, '2018-11-08 18:02:20', '2019-03-03 22:08:18', '0', '/gen/code/**', 'GET');
INSERT INTO `sys_resource` VALUES (30, '下载', '1', NULL, '/gen/code:download', NULL, 61, NULL, 'views/gen/code/index', 1, '2018-11-08 18:02:42', '2019-03-03 22:08:22', '0', '/gen/code/**', 'POST');
INSERT INTO `sys_resource` VALUES (31, '研发管理', '0', '/gen', '/gen', NULL, -1, 'develop', 'Layout', 4, '2018-01-22 12:30:41', '2019-04-07 10:50:06', '0', '', NULL);
INSERT INTO `sys_resource` VALUES (55, '消息管理', '0', '/tsc', '/tsc', NULL, -1, 'develop', 'Layout', 2, '2019-03-02 15:07:08', '2019-03-03 22:32:28', '0', '', NULL);
INSERT INTO `sys_resource` VALUES (57, '消息处理', '0', 'msg', '/tsc/msg', NULL, 55, 'code', 'views/msg/index', 1, '2019-03-02 15:16:38', '2019-03-03 22:25:23', '0', '/tsc/msg/**', 'POST');
INSERT INTO `sys_resource` VALUES (61, '代码生成', '0', 'code', '/gen/code', NULL, 31, 'code', 'views/gen/code/index', 1, '2019-03-03 17:55:21', '2019-04-07 10:50:10', '0', '/gen/code/**', 'GET');
INSERT INTO `sys_resource` VALUES (62, '查看异常', '0', 'exception', '/syslog/exception', NULL, 64, 'rizhiguanli', 'views/admin/log/exception', 1, '2017-11-20 14:06:22', '2019-04-14 01:00:31', '0', '/syslog/log/*', 'GET');
INSERT INTO `sys_resource` VALUES (63, '查看日志', '0', 'log', '/syslog/log', NULL, 64, 'rizhiguanli', 'views/admin/log/index', 1, '2017-11-20 14:06:22', '2019-03-03 22:32:10', '0', '/syslog/log/*', 'GET');
INSERT INTO `sys_resource` VALUES (64, '日志管理', '0', '/syslog', '/syslog', NULL, -1, 'rizhiguanli', 'Layout', 5, '2017-11-20 14:06:22', '2019-03-03 22:32:29', '0', '', NULL);
INSERT INTO `sys_resource` VALUES (66, '添加数据库', '1', NULL, 'sys_gen_db_config_add', NULL, 65, NULL, 'views/gen/sysGenDbConfig/index', 1, '2018-11-05 15:49:44', '2019-04-16 17:03:43', '1', '/gen/sysGenDbConfig/*', 'POST');
INSERT INTO `sys_resource` VALUES (67, '更新数据库', '1', NULL, 'sys_gen_db_config_update', NULL, 65, NULL, 'views/gen/sysGenDbConfig/index', 1, '2018-11-05 15:50:02', '2019-04-16 17:03:43', '1', '/gen/sysGenDbConfig/*', 'PUT');
INSERT INTO `sys_resource` VALUES (68, '删除数据库', '1', NULL, 'sys_gen_db_config_delete', NULL, 65, NULL, 'views/gen/sysGenDbConfig/index', 2, '2018-11-05 15:50:26', '2019-04-16 17:03:43', '1', '/gen/sysGenDbConfig/*', 'DELETE');
INSERT INTO `sys_resource` VALUES (69, '选择数据库', '1', NULL, 'sys_gen_db_config_select', NULL, 65, NULL, 'views/gen/sysGenDbConfig/index', 1, '2018-11-05 15:50:26', '2019-04-16 17:03:43', '1', '/gen/sysGenDbConfig/*', 'GET');
INSERT INTO `sys_resource` VALUES (1116634105528655874, '网站管理', '0', '/website', '/website', NULL, -1, 'develop', 'Layout', 2, '2019-03-02 15:07:08', '2019-03-03 22:32:28', '0', '', NULL);
INSERT INTO `sys_resource` VALUES (1116634105528655875, '订单管理', '0', 'intentOrder', '/website/intentOrder', NULL, 1116634105528655874, 'yonghuguanli', 'views/website/intentOrder/index', 2, '2017-11-02 22:24:37', '2019-03-03 17:05:20', '0', '', NULL);
INSERT INTO `sys_resource` VALUES (1116634105528655876, '意向客户', '0', '/website/intentOrder', '/website/intentOrder', NULL, 1116634105528655875, 'yonghuguanli', 'views/website/intentOrder/index', 2, '2017-11-02 22:24:37', '2019-03-03 17:05:20', '0', '', NULL);
INSERT INTO `sys_resource` VALUES (1116634105528655877, '添加', '1', NULL, 'intent_order_add', NULL, 1116634105528655876, NULL, NULL, 1, '2018-11-05 15:49:44', '2019-03-03 22:06:42', '0', '/website/intentOrder/*', 'POST');
INSERT INTO `sys_resource` VALUES (1116634105528655878, '修改', '1', NULL, 'intent_order_update', NULL, 1116634105528655876, NULL, NULL, 1, '2018-11-05 15:50:02', '2019-03-03 22:07:33', '0', '/website/intentOrder/*', 'PUT');
INSERT INTO `sys_resource` VALUES (1116634105528655879, '删除', '1', NULL, 'intent_order_delete', NULL, 1116634105528655876, NULL, NULL, 5, '2018-11-05 15:50:26', '2019-03-03 22:07:40', '0', '/website/intentOrder/*', 'DELETE');
INSERT INTO `sys_resource` VALUES (1116634105528655880, '查询', '1', NULL, 'intent_order_select', NULL, 1116634105528655876, NULL, NULL, 1, '2018-11-05 15:50:26', '2019-03-03 22:07:58', '0', '/website/intentOrder/*', 'GET');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(32) NOT NULL COMMENT '角色code用于springsecurity角色标识码',
  `role_name` varchar(128) NOT NULL COMMENT '角色名称',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除 1-删除，0-未删除',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 'ROLE_ADMIN', '管理员用户', '2018-10-16 17:47:54', '2018-11-02 16:38:27', '0');
INSERT INTO `sys_role` VALUES (2, 'ROLE_DEMO', '测试用户', '2018-10-16 17:48:12', '2019-02-23 18:03:40', '0');
INSERT INTO `sys_role` VALUES (4, 'ROLE_TEST', 'test', '2018-11-06 15:05:30', NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `role_id` bigint(64) NOT NULL COMMENT '主键',
  `resource_id` bigint(64) NOT NULL COMMENT '主键',
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色资源关联表';

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_resource` VALUES (1, 1);
INSERT INTO `sys_role_resource` VALUES (1, 2);
INSERT INTO `sys_role_resource` VALUES (1, 3);
INSERT INTO `sys_role_resource` VALUES (1, 4);
INSERT INTO `sys_role_resource` VALUES (1, 9);
INSERT INTO `sys_role_resource` VALUES (1, 13);
INSERT INTO `sys_role_resource` VALUES (1, 14);
INSERT INTO `sys_role_resource` VALUES (1, 15);
INSERT INTO `sys_role_resource` VALUES (1, 16);
INSERT INTO `sys_role_resource` VALUES (1, 17);
INSERT INTO `sys_role_resource` VALUES (1, 18);
INSERT INTO `sys_role_resource` VALUES (1, 19);
INSERT INTO `sys_role_resource` VALUES (1, 20);
INSERT INTO `sys_role_resource` VALUES (1, 21);
INSERT INTO `sys_role_resource` VALUES (1, 22);
INSERT INTO `sys_role_resource` VALUES (1, 23);
INSERT INTO `sys_role_resource` VALUES (1, 29);
INSERT INTO `sys_role_resource` VALUES (1, 30);
INSERT INTO `sys_role_resource` VALUES (1, 31);
INSERT INTO `sys_role_resource` VALUES (1, 55);
INSERT INTO `sys_role_resource` VALUES (1, 57);
INSERT INTO `sys_role_resource` VALUES (1, 61);
INSERT INTO `sys_role_resource` VALUES (1, 62);
INSERT INTO `sys_role_resource` VALUES (1, 63);
INSERT INTO `sys_role_resource` VALUES (1, 64);
INSERT INTO `sys_role_resource` VALUES (1, 1116634105528655874);
INSERT INTO `sys_role_resource` VALUES (1, 1116634105528655875);
INSERT INTO `sys_role_resource` VALUES (1, 1116634105528655876);
INSERT INTO `sys_role_resource` VALUES (1, 1116634105528655877);
INSERT INTO `sys_role_resource` VALUES (1, 1116634105528655878);
INSERT INTO `sys_role_resource` VALUES (1, 1116634105528655879);
INSERT INTO `sys_role_resource` VALUES (1, 1116634105528655880);
INSERT INTO `sys_role_resource` VALUES (2, 1);
INSERT INTO `sys_role_resource` VALUES (2, 4);
INSERT INTO `sys_role_resource` VALUES (2, 8);
INSERT INTO `sys_role_resource` VALUES (2, 20);
INSERT INTO `sys_role_resource` VALUES (2, 24);
INSERT INTO `sys_role_resource` VALUES (4, 1);
INSERT INTO `sys_role_resource` VALUES (4, 2);
INSERT INTO `sys_role_resource` VALUES (4, 3);
INSERT INTO `sys_role_resource` VALUES (4, 4);
INSERT INTO `sys_role_resource` VALUES (4, 9);
INSERT INTO `sys_role_resource` VALUES (4, 19);
INSERT INTO `sys_role_resource` VALUES (4, 20);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '手机号码',
  `qq` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'qq号码',
  `wechat` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信号码',
  `weibo` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微博url',
  `avatar` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像url',
  `qq_openid` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'qq openid',
  `wechat_openid` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信openid',
  `weibo_openid` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微博openid',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '是否删除 0-未删除 1-删除',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_idx_username` (`username`),
  UNIQUE KEY `user_idx_mobile` (`mobile`),
  UNIQUE KEY `user_idx_emal` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (50, 'admin', '$2a$10$2kV7N5klgIu6F0SbHVFpJuvh/Yzw/lUsHQEKGd1f1E0qcqSn3Bq3y', NULL, '18757569277', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-10-08 16:45:43', '2019-02-22 15:22:44', '0');
INSERT INTO `sys_user` VALUES (56, 'super2', '$2a$10$2.ZEZdpqg9XMmIGOrlSeQ.n7ettP6.EvLyN9BI8BVHWTaxKmgQ85a', NULL, '13986861398', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2019-03-02 17:07:32', NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(64) NOT NULL COMMENT '主键',
  `role_id` bigint(64) NOT NULL COMMENT '主键',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (50, 1);
INSERT INTO `sys_user_role` VALUES (51, 1);
INSERT INTO `sys_user_role` VALUES (51, 2);
INSERT INTO `sys_user_role` VALUES (51, 4);
INSERT INTO `sys_user_role` VALUES (52, 2);
INSERT INTO `sys_user_role` VALUES (54, 1);
INSERT INTO `sys_user_role` VALUES (55, 2);
INSERT INTO `sys_user_role` VALUES (56, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
