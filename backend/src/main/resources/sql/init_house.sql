-- 房屋绑定模块初始化SQL

-- 城市表
CREATE TABLE IF NOT EXISTS `city` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` VARCHAR(50) NOT NULL COMMENT '城市名称',
    `code` VARCHAR(20) NOT NULL COMMENT '城市代码',
    `province_id` BIGINT(20) DEFAULT NULL COMMENT '省份ID',
    `province_name` VARCHAR(50) DEFAULT NULL COMMENT '省份名称',
    `sort` INT(11) DEFAULT 0 COMMENT '排序',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='城市表';

-- 社区表
CREATE TABLE IF NOT EXISTS `community` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `city_id` BIGINT(20) NOT NULL COMMENT '城市ID',
    `name` VARCHAR(100) NOT NULL COMMENT '社区名称',
    `code` VARCHAR(50) NOT NULL COMMENT '社区代码',
    `address` VARCHAR(200) DEFAULT NULL COMMENT '社区地址',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    `sort` INT(11) DEFAULT 0 COMMENT '排序',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_city_id` (`city_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社区表';

-- 小区表
CREATE TABLE IF NOT EXISTS `complex` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `community_id` BIGINT(20) NOT NULL COMMENT '社区ID',
    `name` VARCHAR(100) NOT NULL COMMENT '小区名称',
    `code` VARCHAR(50) NOT NULL COMMENT '小区代码',
    `address` VARCHAR(200) DEFAULT NULL COMMENT '小区地址',
    `property_company` VARCHAR(100) DEFAULT NULL COMMENT '物业公司',
    `property_phone` VARCHAR(20) DEFAULT NULL COMMENT '物业电话',
    `sort` INT(11) DEFAULT 0 COMMENT '排序',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_community_id` (`community_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='小区表';

-- 楼栋表
CREATE TABLE IF NOT EXISTS `building` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `complex_id` BIGINT(20) NOT NULL COMMENT '小区ID',
    `name` VARCHAR(50) NOT NULL COMMENT '楼栋号',
    `code` VARCHAR(50) NOT NULL COMMENT '楼栋代码',
    `floors` INT(11) DEFAULT 0 COMMENT '楼层数',
    `units` INT(11) DEFAULT 0 COMMENT '单元数',
    `sort` INT(11) DEFAULT 0 COMMENT '排序',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_complex_id` (`complex_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='楼栋表';

-- 房屋表
CREATE TABLE IF NOT EXISTS `house` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `building_id` BIGINT(20) NOT NULL COMMENT '楼栋ID',
    `room_number` VARCHAR(20) NOT NULL COMMENT '房号',
    `floor` INT(11) DEFAULT 0 COMMENT '楼层',
    `unit` INT(11) DEFAULT 0 COMMENT '单元',
    `area` DECIMAL(10,2) DEFAULT 0.00 COMMENT '面积',
    `type` TINYINT(1) DEFAULT 1 COMMENT '类型: 1-住宅 2-商铺 3-其他',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 1-待审核 2-已通过 3-已拒绝',
    `reject_reason` VARCHAR(200) DEFAULT NULL COMMENT '拒绝原因',
    `is_main` TINYINT(1) DEFAULT 0 COMMENT '是否主房: 0-否 1-是',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_building_id` (`building_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='房屋表';

-- 房屋申请表
CREATE TABLE IF NOT EXISTS `house_application` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `building_id` BIGINT(20) NOT NULL COMMENT '楼栋ID',
    `room_number` VARCHAR(20) NOT NULL COMMENT '房号',
    `floor` INT(11) DEFAULT 0 COMMENT '楼层',
    `unit` INT(11) DEFAULT 0 COMMENT '单元',
    `applicant_name` VARCHAR(50) DEFAULT NULL COMMENT '申请人姓名',
    `applicant_phone` VARCHAR(20) DEFAULT NULL COMMENT '申请人电话',
    `id_card` VARCHAR(18) DEFAULT NULL COMMENT '身份证号',
    `proof_url` VARCHAR(500) DEFAULT NULL COMMENT '证明材料URL',
    `status` TINYINT(1) DEFAULT 1 COMMENT '申请状态: 1-待审核 2-已通过 3-已拒绝',
    `audit_opinion` VARCHAR(200) DEFAULT NULL COMMENT '审核意见',
    `auditor_id` BIGINT(20) DEFAULT NULL COMMENT '审核人ID',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_building_id` (`building_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='房屋申请表';

-- 插入示例数据
INSERT INTO `city` (`name`, `code`, `province_id`, `province_name`, `sort`, `status`) VALUES
('北京市', '110000', 1, '北京市', 1, 1),
('上海市', '310000', 2, '上海市', 2, 1),
('广州市', '440100', 3, '广东省', 3, 1),
('深圳市', '440300', 3, '广东省', 4, 1);

INSERT INTO `community` (`city_id`, `name`, `code`, `address`, `phone`, `sort`, `status`) VALUES
(1, '朝阳区社区', 'BJ_CY', '北京市朝阳区', '010-12345678', 1, 1),
(1, '海淀区社区', 'BJ_HD', '北京市海淀区', '010-87654321', 2, 1),
(2, '浦东新区社区', 'SH_PD', '上海市浦东新区', '021-12345678', 1, 1);

INSERT INTO `complex` (`community_id`, `name`, `code`, `address`, `property_company`, `property_phone`, `sort`, `status`) VALUES
(1, '幸福家园小区', 'BJ_CY_XF', '朝阳区幸福路123号', '幸福物业公司', '010-11111111', 1, 1),
(1, '阳光小区', 'BJ_CY_YG', '朝阳区阳光路456号', '阳光物业公司', '010-22222222', 2, 1),
(2, '科技园小区', 'BJ_HD_KJ', '海淀区科技园路789号', '科技物业公司', '010-33333333', 1, 1);

INSERT INTO `building` (`complex_id`, `name`, `code`, `floors`, `units`, `sort`, `status`) VALUES
(1, '1号楼', 'BJ_CY_XF_1', 18, 3, 1, 1),
(1, '2号楼', 'BJ_CY_XF_2', 20, 4, 2, 1),
(1, '3号楼', 'BJ_CY_XF_3', 15, 2, 3, 1),
(2, '1号楼', 'BJ_CY_YG_1', 12, 2, 1, 1),
(3, '1号楼', 'BJ_HD_KJ_1', 25, 5, 1, 1);
