package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * Banner实体
 */
@TableName("banner")
data class Banner(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * Banner标题
     */
    var title: String? = null,

    /**
     * Banner图片URL
     */
    var imageUrl: String? = null,

    /**
     * 跳转链接
     */
    var linkUrl: String? = null,

    /**
     * 跳转类型: 1-不跳转 2-网页 3-帖子 4-活动
     */
    var linkType: Int? = 1,

    /**
     * 目标ID
     */
    var targetId: Long? = null,

    /**
     * 排序
     */
    var sort: Int? = 0,

    /**
     * 状态: 0-禁用 1-启用
     */
    var status: Int? = 1,

    /**
     * 开始时间
     */
    var startTime: LocalDateTime? = null,

    /**
     * 结束时间
     */
    var endTime: LocalDateTime? = null,

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
