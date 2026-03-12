package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 版块实体
 */
@TableName("board")
data class Board(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 版块名称
     */
    var name: String? = null,

    /**
     * 版块描述
     */
    var description: String? = null,

    /**
     * 版块图标
     */
    var icon: String? = null,

    /**
     * 排序
     */
    var sort: Int? = 0,

    /**
     * 状态: 0-禁用 1-启用
     */
    var status: Int? = 1,

    /**
     * 帖子数
     */
    var postCount: Int? = 0,

    /**
     * 成员数
     */
    var memberCount: Int? = 0,

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
