-- 系统配置初始化SQL

-- 系统配置表
CREATE TABLE IF NOT EXISTS `system_config` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `config_key` VARCHAR(100) NOT NULL COMMENT '配置键',
    `config_value` VARCHAR(500) DEFAULT NULL COMMENT '配置值',
    `description` VARCHAR(200) DEFAULT NULL COMMENT '配置描述',
    `config_type` TINYINT(1) DEFAULT 1 COMMENT '配置类型: 1-字符串 2-数字 3-布尔 4-JSON',
    `is_system` TINYINT(1) DEFAULT 0 COMMENT '是否系统配置: 0-否 1-是',
    `sort` INT(11) DEFAULT 0 COMMENT '排序',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_config_key` (`config_key`, `deleted`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

-- Banner表
CREATE TABLE IF NOT EXISTS `banner` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `title` VARCHAR(200) DEFAULT NULL COMMENT 'Banner标题',
    `image_url` VARCHAR(500) NOT NULL COMMENT 'Banner图片URL',
    `link_url` VARCHAR(500) DEFAULT NULL COMMENT '跳转链接',
    `link_type` TINYINT(1) DEFAULT 1 COMMENT '跳转类型: 1-不跳转 2-网页 3-帖子 4-活动',
    `target_id` BIGINT(20) DEFAULT NULL COMMENT '目标ID',
    `sort` INT(11) DEFAULT 0 COMMENT '排序',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `start_time` DATETIME DEFAULT NULL COMMENT '开始时间',
    `end_time` DATETIME DEFAULT NULL COMMENT '结束时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_status` (`status`, `sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Banner表';

-- APP版本表
CREATE TABLE IF NOT EXISTS `app_version` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `version` VARCHAR(20) NOT NULL COMMENT '版本号',
    `version_code` INT(11) NOT NULL COMMENT '版本代码',
    `update_type` TINYINT(1) DEFAULT 1 COMMENT '更新类型: 1-不更新 2-推荐更新 3-强制更新',
    `download_url` VARCHAR(500) NOT NULL COMMENT '下载链接',
    `file_size` BIGINT(20) DEFAULT NULL COMMENT '文件大小(字节)',
    `md5` VARCHAR(32) DEFAULT NULL COMMENT 'MD5值',
    `update_log` TEXT COMMENT '更新说明',
    `publish_status` TINYINT(1) DEFAULT 0 COMMENT '发布状态: 0-未发布 1-已发布',
    `platform` TINYINT(1) DEFAULT 1 COMMENT '平台: 1-Android 2-iOS',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_platform` (`platform`, `publish_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='APP版本表';

-- 插入默认系统配置
INSERT INTO `system_config` (`config_key`, `config_value`, `description`, `config_type`, `is_system`, `sort`, `status`) VALUES
('customer_service_phone', '400-888-8888', '客服电话', 1, 1, 1, 1),
('customer_service_time', '9:00-18:00', '客服时间', 1, 1, 2, 1),
('elder_mode_enabled', 'true', '是否启用老人模式', 3, 1, 3, 1),
('sos_enabled', 'true', '是否启用SOS', 3, 1, 4, 1),
('sos_phone', '110,120,119', 'SOS紧急电话', 1, 1, 5, 1),
('banner_enabled', 'true', '是否启用Banner', 3, 1, 6, 1),
('check_in_enabled', 'true', '是否启用签到', 3, 1, 7, 1),
('check_in_points', '10', '签到积分', 2, 1, 8, 1),
('max_online_time', '720', '最大在线时长(分钟)', 2, 1, 9, 1);

-- 插入示例Banner
INSERT INTO `banner` (`title`, `image_url`, `link_url`, `link_type`, `target_id`, `sort`, `status`) VALUES
('智慧养老社区', 'https://example.com/banner1.jpg', '', 1, NULL, 1, 1),
('社区活动', 'https://example.com/banner2.jpg', '', 1, NULL, 2, 1),
('健康生活', 'https://example.com/banner3.jpg', '', 1, NULL, 3, 1);

-- 插入APP版本信息
INSERT INTO `app_version` (`version`, `version_code`, `update_type`, `download_url`, `file_size`, `md5`, `update_log`, `publish_status`, `platform`) VALUES
('1.0.0', 1, 1, 'https://example.com/app-v1.0.0.apk', 50000000, 'abc123def456', '初始版本发布', 1, 1);
