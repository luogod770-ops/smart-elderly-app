package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableLogic
import java.time.LocalDateTime

/**
 * 基础实体类
 */
open class BaseEntity {
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null

    /**
     * 删除标记: 0-未删除 1-已删除
     */
    @TableLogic
    var deleted: Int? = 0
}
