package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 社区实体
 */
@TableName("community")
data class Community(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 城市ID
     */
    var cityId: Long? = null,

    /**
     * 社区名称
     */
    var name: String? = null,

    /**
     * 社区代码
     */
    var code: String? = null,

    /**
     * 社区地址
     */
    var address: String? = null,

    /**
     * 联系电话
     */
    var phone: String? = null,

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
