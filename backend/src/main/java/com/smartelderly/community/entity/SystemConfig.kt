package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 系统配置实体
 */
@TableName("system_config")
data class SystemConfig(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 配置键
     */
    var configKey: String? = null,

    /**
     * 配置值
     */
    var configValue: String? = null,

    /**
     * 配置描述
     */
    var description: String? = null,

    /**
     * 配置类型: 1-字符串 2-数字 3-布尔 4-JSON
     */
    var configType: Int? = null,

    /**
     * 是否系统配置: 0-否 1-是
     */
    var isSystem: Int? = 0,

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
