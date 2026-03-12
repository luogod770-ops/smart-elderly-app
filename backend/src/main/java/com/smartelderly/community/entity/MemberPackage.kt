package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 会员套餐实体
 */
@TableName("member_package")
data class MemberPackage(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 套餐名称
     */
    var name: String? = null,

    /**
     * 套餐描述
     */
    var description: String? = null,

    /**
     * 价格
     */
    var price: BigDecimal? = null,

    /**
     * 原价
     */
    var originalPrice: BigDecimal? = null,

    /**
     * 有效期(天)
     */
    var duration: Int? = null,

    /**
     * 套餐图标
     */
    var icon: String? = null,

    /**
     * 排序
     */
    var sort: Int? = 0,

    /**
     * 状态: 0-下架 1-上架
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
