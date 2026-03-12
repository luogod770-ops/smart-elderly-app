package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 楼栋实体
 */
@TableName("building")
data class Building(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 小区ID
     */
    var complexId: Long? = null,

    /**
     * 楼栋号
     */
    var name: String? = null,

    /**
     * 楼栋代码
     */
    var code: String? = null,

    /**
     * 楼层数
     */
    var floors: Int? = 0,

    /**
     * 单元数
     */
    var units: Int? = 0,

    /**
     * 排序
     */
    var sort: Int? = 0,

    /**
     * 状态: 0-禁用 1-启用
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
