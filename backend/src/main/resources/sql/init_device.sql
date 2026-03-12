-- 设备管理模块初始化SQL

-- 设备表
CREATE TABLE IF NOT EXISTS `device` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `device_name` VARCHAR(50) DEFAULT NULL COMMENT '设备名称',
    `device_type` TINYINT(1) DEFAULT 1 COMMENT '设备类型: 1-智能手环 2-智能手表 3-健康监测器',
    `brand_model` VARCHAR(100) DEFAULT NULL COMMENT '品牌型号',
    `mac_address` VARCHAR(50) DEFAULT NULL COMMENT '设备MAC地址',
    `serial_number` VARCHAR(100) DEFAULT NULL COMMENT '设备序列号',
    `bind_status` TINYINT(1) DEFAULT 0 COMMENT '绑定状态: 0-未绑定 1-已绑定 2-已解绑',
    `last_connect_time` DATETIME DEFAULT NULL COMMENT '最后连接时间',
    `battery_level` INT(11) DEFAULT 0 COMMENT '电量百分比',
    `firmware_version` VARCHAR(50) DEFAULT NULL COMMENT '固件版本',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_mac_address` (`mac_address`),
    KEY `idx_bind_status` (`bind_status`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备表';

-- 设备数据表
CREATE TABLE IF NOT EXISTS `device_data` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `device_id` BIGINT(20) NOT NULL COMMENT '设备ID',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `data_type` TINYINT(1) NOT NULL COMMENT '数据类型: 1-心率 2-步数 3-血氧 4-睡眠 5-血压 6-体温',
    `value` TEXT DEFAULT NULL COMMENT '数据值(JSON格式)',
    `record_time` DATETIME NOT NULL COMMENT '记录时间',
    `source` TINYINT(1) DEFAULT 1 COMMENT '数据来源: 1-蓝牙 2-手动 3-同步',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_device_id` (`device_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_data_type` (`data_type`),
    KEY `idx_record_time` (`record_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备数据表';

-- 插入示例设备数据
INSERT INTO `device` (`user_id`, `device_name`, `device_type`, `brand_model`, `mac_address`, `serial_number`, `bind_status`, `battery_level`, `status`) VALUES
(1, '我的乐心手环', 1, '乐心手环S6', 'AA:BB:CC:DD:EE:FF', 'LX20240312001', 1, 85, 1),
(2, '健康监测器', 3, '华为Watch GT3', '11:22:33:44:55:66', 'HW20240312002', 1, 60, 1);

-- 插入示例设备数据
INSERT INTO `device_data` (`device_id`, `user_id`, `data_type`, `value`, `record_time`, `source`) VALUES
(1, 1, 1, '{"heartRate": 72}', DATE_SUB(NOW(), INTERVAL 1 HOUR), 1),
(1, 1, 2, '{"steps": 5432, "calories": 256}', DATE_SUB(NOW(), INTERVAL 1 HOUR), 1),
(1, 1, 3, '{"spo2": 98}', DATE_SUB(NOW(), INTERVAL 1 HOUR), 1),
(1, 1, 1, '{"heartRate": 75}', NOW(), 1),
(1, 1, 2, '{"steps": 6123, "calories": 289}', NOW(), 1),
(2, 2, 1, '{"heartRate": 68}', NOW(), 1),
(2, 2, 3, '{"spo2": 97}', NOW(), 1);
