package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 帖子实体
 */
@TableName("post")
data class Post(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 版块ID
     */
    var boardId: Long? = null,

    /**
     * 用户ID
     */
    var userId: Long? = null,

    /**
     * 用户昵称
     */
    var userNickname: String? = null,

    /**
     * 用户头像
     */
    var userAvatar: String? = null,

    /**
     * 帖子标题
     */
    var title: String? = null,

    /**
     * 帖子内容
     */
    var content: String? = null,

    /**
     * 图片列表(JSON)
     */
    var images: String? = null,

    /**
     * 视频
     */
    var video: String? = null,

    /**
     * 位置
     */
    var location: String? = null,

    /**
     * 点赞数
     */
    var likeCount: Int? = 0,

    /**
     * 评论数
     */
    var commentCount: Int? = 0,

    /**
     * 转发数
     */
    var shareCount: Int? = 0,

    /**
     * 是否置顶: 0-否 1-是
     */
    var isTop: Int? = 0,

    /**
     * 是否精华: 0-否 1-是
     */
    var isEssence: Int? = 0,

    /**
     * 状态: 0-草稿 1-已发布 2-已删除
     */
    var status: Int? = 1,

    /**
     * 审核状态: 0-待审核 1-已通过 2-已拒绝
     */
    var auditStatus: Int? = 1,

    /**
     * 创建时间
     */
    var createTime: LocalDateTime? = null,

    /**
     * 更新时间
     */
    var updateTime: LocalDateTime? = null,

    /**
     * 删除标记: 0-未删除 1-已删除
     */
    var deleted: Int? = 0
)
