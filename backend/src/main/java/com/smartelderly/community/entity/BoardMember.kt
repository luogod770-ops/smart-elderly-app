package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 版块成员实体
 */
@TableName("board_member")
data class BoardMember(
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
