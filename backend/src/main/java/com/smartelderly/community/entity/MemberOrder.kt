package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 会员订单实体
 */
@TableName("member_order")
data class MemberOrder(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 订单号
     */
    var orderNo: String? = null,

    /**
     * 用户ID
     */
    var userId: Long? = null,

    /**
     * 套餐ID
     */
    var packageId: Long? = null,

    /**
     * 套餐名称
     */
    var packageName: String? = null,

    /**
     * 订单金额
     */
    var amount: BigDecimal? = null,

    /**
     * 支付方式: 1-支付宝 2-微信支付
     */
    var payType: Int? = null,

    /**
     * 第三方交易号
     */
    var tradeNo: String? = null,

    /**
     * 订单状态: 1-待支付 2-已支付 3-已取消 4-已退款
     */
    var status: Int? = 1,

    /**
     * 支付时间
     */
    var payTime: LocalDateTime? = null,

    /**
     * 到期时间
     */
    var expireTime: LocalDateTime? = null,

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
