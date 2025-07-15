-- ========================================
-- SpringCloud SaaS 项目数据库初始化脚本
-- ========================================

-- ========================================
-- 用户表（sys_user）
-- ========================================
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像地址',
  `gender` tinyint DEFAULT '0' COMMENT '性别（0：女，1：男）',
  `status` tinyint DEFAULT '1' COMMENT '状态（0：禁用，1：启用）',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` bigint DEFAULT '0' COMMENT '版本号（用于乐观锁）',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标识（0：未删除，1：已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- ========================================
-- 基础数据表（sys_base_data）
-- ========================================
DROP TABLE IF EXISTS `sys_base_data`;
CREATE TABLE `sys_base_data` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` varchar(50) NOT NULL COMMENT '数据类型',
  `code` varchar(100) NOT NULL COMMENT '数据编码',
  `name` varchar(100) NOT NULL COMMENT '数据名称',
  `value` varchar(500) DEFAULT NULL COMMENT '数据值',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `sort` int DEFAULT '0' COMMENT '排序',
  `status` tinyint DEFAULT '1' COMMENT '状态（0：禁用，1：启用）',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` bigint DEFAULT '0' COMMENT '版本号（用于乐观锁）',
  `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标识（0：未删除，1：已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='基础数据表';

-- ========================================
-- 初始化数据
-- ========================================

-- 插入系统管理员用户
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `email`, `phone`, `gender`, `status`, `create_by`, `update_by`) VALUES
(1, 'admin', '$2a$10$7JB720yubVSoCvgfQG3qtOGv2nNOUcwdDdJ2JJcDaHyU.HgwR7Rqy', '系统管理员', 'admin@focus.com', '13800138000', 1, 1, 1, 1),
(2, 'test', '$2a$10$7JB720yubVSoCvgfQG3qtOGv2nNOUcwdDdJ2JJcDaHyU.HgwR7Rqy', '测试用户', 'test@focus.com', '13800138001', 0, 1, 1, 1);

-- 插入基础数据示例
INSERT INTO `sys_base_data` (`id`, `type`, `code`, `name`, `value`, `description`, `sort`, `status`, `create_by`, `update_by`) VALUES
(1, 'USER_STATUS', '0', '禁用', '0', '用户状态-禁用', 1, 1, 1, 1),
(2, 'USER_STATUS', '1', '启用', '1', '用户状态-启用', 2, 1, 1, 1),
(3, 'GENDER', '0', '女', '0', '性别-女', 1, 1, 1, 1),
(4, 'GENDER', '1', '男', '1', '性别-男', 2, 1, 1, 1),
(5, 'SYSTEM_CONFIG', 'DEFAULT_PASSWORD', '默认密码', '123456', '新用户默认密码', 1, 1, 1, 1),
(6, 'SYSTEM_CONFIG', 'LOGIN_RETRY_TIMES', '登录重试次数', '5', '用户登录失败重试次数', 2, 1, 1, 1);
