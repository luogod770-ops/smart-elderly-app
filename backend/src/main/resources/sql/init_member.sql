-- 会员系统模块初始化SQL

-- 会员套餐表
CREATE TABLE IF NOT EXISTS `member_package` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` VARCHAR(50) NOT NULL COMMENT '套餐名称',
    `description` VARCHAR(200) DEFAULT NULL COMMENT '套餐描述',
    `price` DECIMAL(10,2) NOT NULL COMMENT '价格',
    `original_price` DECIMAL(10,2) DEFAULT NULL COMMENT '原价',
    `duration` INT(11) NOT NULL COMMENT '有效期(天)',
    `icon` VARCHAR(500) DEFAULT NULL COMMENT '套餐图标',
    `sort` INT(11) DEFAULT 0 COMMENT '排序',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-下架 1-上架',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员套餐表';

-- 会员订单表
CREATE TABLE IF NOT EXISTS `member_order` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `package_id` BIGINT(20) NOT NULL COMMENT '套餐ID',
    `package_name` VARCHAR(50) DEFAULT NULL COMMENT '套餐名称',
    `amount` DECIMAL(10,2) NOT NULL COMMENT '订单金额',
    `pay_type` TINYINT(1) DEFAULT NULL COMMENT '支付方式: 1-支付宝 2-微信支付',
    `trade_no` VARCHAR(100) DEFAULT NULL COMMENT '第三方交易号',
    `status` TINYINT(1) DEFAULT 1 COMMENT '订单状态: 1-待支付 2-已支付 3-已取消 4-已退款',
    `pay_time` DATETIME DEFAULT NULL COMMENT '支付时间',
    `expire_time` DATETIME DEFAULT NULL COMMENT '到期时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_package_id` (`package_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员订单表';

-- 用户会员表
CREATE TABLE IF NOT EXISTS `user_member` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `package_id` BIGINT(20) NOT NULL COMMENT '套餐ID',
    `member_type` TINYINT(1) DEFAULT 1 COMMENT '会员类型: 1-月卡 2-季卡 3-年卡 4-永久',
    `start_time` DATETIME NOT NULL COMMENT '开始时间',
    `expire_time` DATETIME NOT NULL COMMENT '到期时间',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-已过期 1-生效中',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_package_id` (`package_id`),
    KEY `idx_status` (`status`),
    KEY `idx_expire_time` (`expire_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户会员表';

-- 插入会员套餐示例数据
INSERT INTO `member_package` (`name`, `description`, `price`, `original_price`, `duration`, `icon`, `sort`, `status`) VALUES
('月卡会员', '享受月卡会员所有权益', 29.90, 39.90, 30, '/icons/month.png', 1, 1),
('季卡会员', '享受季卡会员所有权益,立省30元', 79.90, 119.70, 90, '/icons/quarter.png', 2, 1),
('年卡会员', '享受年卡会员所有权益,立省360元', 299.90, 358.80, 365, '/icons/year.png', 3, 1),
('永久会员', '享受永久会员所有权益', 999.00, 1999.00, 3650, '/icons/forever.png', 4, 1);
