package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 评论实体
 */
@TableName("comment")
data class Comment(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 帖子ID
     */
    var postId: Long? = null,

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
     * 父评论ID (0表示顶级评论)
     */
    var parentId: Long? = 0,

    /**
     * 回复的用户ID
     */
    var replyUserId: Long? = null,

    /**
     * 回复的用户昵称
     */
    var replyNickname: String? = null,

    /**
     * 评论内容
     */
    var content: String? = null,

    /**
     * 图片
     */
    var images: String? = null,

    /**
     * 点赞数
     */
    var likeCount: Int? = 0,

    /**
     * 状态: 0-已删除 1-正常
     */
    var status: Int? = 1,

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
