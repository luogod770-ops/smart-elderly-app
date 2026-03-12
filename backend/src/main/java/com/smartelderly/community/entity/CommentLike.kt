package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 评论点赞实体
 */
@TableName("comment_like")
data class CommentLike(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 评论ID
     */
    var commentId: Long? = null,

    /**
     * 用户ID
     */
    var userId: Long? = null,

    /**
     * 创建时间
     */
    var createTime: LocalDateTime? = null,

    /**
     * 删除标记: 0-未删除 1-已删除
     */
    var deleted: Int? = 0
)
