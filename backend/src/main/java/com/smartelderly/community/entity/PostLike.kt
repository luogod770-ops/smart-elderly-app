package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 帖子点赞实体
 */
@TableName("post_like")
data class PostLike(
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
     * 创建时间
     */
    var createTime: LocalDateTime? = null,

    /**
     * 删除标记: 0-未删除 1-已删除
     */
    var deleted: Int? = 0
)
