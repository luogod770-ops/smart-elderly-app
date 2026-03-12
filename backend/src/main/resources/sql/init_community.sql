-- 社区交流模块初始化SQL

-- 版块表
CREATE TABLE IF NOT EXISTS `board` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` VARCHAR(50) NOT NULL COMMENT '版块名称',
    `description` VARCHAR(200) DEFAULT NULL COMMENT '版块描述',
    `icon` VARCHAR(500) DEFAULT NULL COMMENT '版块图标',
    `sort` INT(11) DEFAULT 0 COMMENT '排序',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-禁用 1-启用',
    `post_count` INT(11) DEFAULT 0 COMMENT '帖子数',
    `member_count` INT(11) DEFAULT 0 COMMENT '成员数',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_status` (`status`),
    KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='版块表';

-- 版块成员表
CREATE TABLE IF NOT EXISTS `board_member` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `board_id` BIGINT(20) NOT NULL COMMENT '版块ID',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_board_user` (`board_id`, `user_id`, `deleted`),
    KEY `idx_board_id` (`board_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='版块成员表';

-- 帖子表
CREATE TABLE IF NOT EXISTS `post` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `board_id` BIGINT(20) NOT NULL COMMENT '版块ID',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `user_nickname` VARCHAR(50) DEFAULT NULL COMMENT '用户昵称',
    `user_avatar` VARCHAR(500) DEFAULT NULL COMMENT '用户头像',
    `title` VARCHAR(100) NOT NULL COMMENT '帖子标题',
    `content` TEXT DEFAULT NULL COMMENT '帖子内容',
    `images` TEXT DEFAULT NULL COMMENT '图片列表(JSON)',
    `video` VARCHAR(500) DEFAULT NULL COMMENT '视频',
    `location` VARCHAR(200) DEFAULT NULL COMMENT '位置',
    `like_count` INT(11) DEFAULT 0 COMMENT '点赞数',
    `comment_count` INT(11) DEFAULT 0 COMMENT '评论数',
    `share_count` INT(11) DEFAULT 0 COMMENT '转发数',
    `is_top` TINYINT(1) DEFAULT 0 COMMENT '是否置顶: 0-否 1-是',
    `is_essence` TINYINT(1) DEFAULT 0 COMMENT '是否精华: 0-否 1-是',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-草稿 1-已发布 2-已删除',
    `audit_status` TINYINT(1) DEFAULT 1 COMMENT '审核状态: 0-待审核 1-已通过 2-已拒绝',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_board_id` (`board_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`),
    KEY `idx_is_top` (`is_top`),
    KEY `idx_is_essence` (`is_essence`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帖子表';

-- 评论表
CREATE TABLE IF NOT EXISTS `comment` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `post_id` BIGINT(20) NOT NULL COMMENT '帖子ID',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `user_nickname` VARCHAR(50) DEFAULT NULL COMMENT '用户昵称',
    `user_avatar` VARCHAR(500) DEFAULT NULL COMMENT '用户头像',
    `parent_id` BIGINT(20) DEFAULT 0 COMMENT '父评论ID (0表示顶级评论)',
    `reply_user_id` BIGINT(20) DEFAULT NULL COMMENT '回复的用户ID',
    `reply_nickname` VARCHAR(50) DEFAULT NULL COMMENT '回复的用户昵称',
    `content` TEXT DEFAULT NULL COMMENT '评论内容',
    `images` TEXT DEFAULT NULL COMMENT '图片(JSON)',
    `like_count` INT(11) DEFAULT 0 COMMENT '点赞数',
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态: 0-已删除 1-正常',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_post_id` (`post_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

-- 帖子点赞表
CREATE TABLE IF NOT EXISTS `post_like` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `post_id` BIGINT(20) NOT NULL COMMENT '帖子ID',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_post_user` (`post_id`, `user_id`, `deleted`),
    KEY `idx_post_id` (`post_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帖子点赞表';

-- 评论点赞表
CREATE TABLE IF NOT EXISTS `comment_like` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `comment_id` BIGINT(20) NOT NULL COMMENT '评论ID',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标记: 0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_comment_user` (`comment_id`, `user_id`, `deleted`),
    KEY `idx_comment_id` (`comment_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论点赞表';

-- 插入版块示例数据
INSERT INTO `board` (`name`, `description`, `icon`, `sort`, `status`, `post_count`, `member_count`) VALUES
('邻里互助', '邻里之间互相帮助,分享生活经验', '/icons/board_help.png', 1, 1, 0, 0),
('活动聚会', '社区活动信息发布,邻里聚会交流', '/icons/board_activity.png', 2, 1, 0, 0),
('闲置置换', '家中闲置物品转让,绿色环保', '/icons/board_exchange.png', 3, 1, 0, 0),
('物业服务', '物业公告,报修申请,服务评价', '/icons/board_service.png', 4, 1, 0, 0),
('健康养生', '健康知识分享,养生经验交流', '/icons/board_health.png', 5, 1, 0, 0),
('美食分享', '美食制作,菜品推荐,聚餐活动', '/icons/board_food.png', 6, 1, 0, 0);

-- 插入示例帖子数据
INSERT INTO `post` (`board_id`, `user_id`, `user_nickname`, `user_avatar`, `title`, `content`, `images`, `location`, `status`, `audit_status`) VALUES
(1, 1, '张大爷', '/avatars/user1.jpg', '家里有台旧电视机要送人', '家里换新电视了,这台旧的还能用,哪位邻居需要可以来拿,不收钱,就是不想浪费。', '["/images/tv1.jpg", "/images/tv2.jpg"]', '幸福家园1号楼', 1, 1),
(2, 2, '李阿姨', '/avatars/user2.jpg', '周末组织社区广场舞活动', '周六下午3点在小区广场组织广场舞活动,欢迎大家参加!请自带饮用水和舒适的鞋子。', '["/images/dance1.jpg"]', '幸福家园小区广场', 1, 1),
(3, 3, '王大爷', '/avatars/user3.jpg', '闲置自行车低价转让', '品牌自行车,九成新,原价2000元,现价800元转让,自提。', '["/images/bike1.jpg", "/images/bike2.jpg"]', '幸福家园2号楼', 1, 1),
(1, 4, '赵奶奶', '/avatars/user4.jpg', '寻求帮助:寻找走失的宠物狗', '我家的狗狗走失了,是一只黄色的小狗,名字叫旺财,有见到的邻居请联系我,必有重谢!联系电话:138****1234', '["/images/dog1.jpg"]', '幸福家园小区', 1, 1),
(5, 5, '孙爷爷', '/avatars/user5.jpg', '分享几个简单的养生小贴士', '1. 每天喝足够的水\n2. 保持适量运动\n3. 保持良好的作息\n4. 多吃蔬菜水果', NULL, NULL, 1, 1),
(6, 1, '张大爷', '/avatars/user1.jpg', '分享一道简单易学的家常菜', '今天给大家分享一道红烧肉的做法,简单又美味!详细做法请看图片。', '["/images/food1.jpg", "/images/food2.jpg", "/images/food3.jpg"]', '幸福家园1号楼', 1, 1);

-- 插入示例评论数据
INSERT INTO `comment` (`post_id`, `user_id`, `user_nickname`, `user_avatar`, `parent_id`, `reply_user_id`, `reply_nickname`, `content`) VALUES
(1, 2, '李阿姨', '/avatars/user2.jpg', 0, NULL, NULL, '张大爷好样的!我可以去看看吗?'),
(1, 3, '王大爷', '/avatars/user3.jpg', 0, NULL, NULL, '我也要一台,还有吗?'),
(1, 2, '李阿姨', '/avatars/user2.jpg', 2, 3, '王大爷', '哈哈,王大爷你也想要呀!'),
(2, 1, '张大爷', '/avatars/user1.jpg', 0, NULL, NULL, '我一定参加!'),
(2, 3, '王大爷', '/avatars/user3.jpg', 0, NULL, NULL, '我带音响来!'),
(2, 4, '赵奶奶', '/avatars/user4.jpg', 0, NULL, NULL, '太好了,期待这个活动!'),
(5, 1, '张大爷', '/avatars/user1.jpg', 0, NULL, NULL, '很有用的建议,谢谢分享!'),
(5, 2, '李阿姨', '/avatars/user2.jpg', 0, NULL, NULL, '受教了,我会注意的!'),
(6, 2, '李阿姨', '/avatars/user2.jpg', 0, NULL, NULL, '看起来很好吃!周末试试'),
(6, 3, '王大爷', '/avatars/user3.jpg', 0, NULL, NULL, '张大爷厨艺不错啊!');
