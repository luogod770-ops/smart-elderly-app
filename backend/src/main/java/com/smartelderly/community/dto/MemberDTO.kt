package com.smartelderly.community.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 会员套餐响应DTO
 */
data class MemberPackageDTO(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var price: BigDecimal? = null,
    var originalPrice: BigDecimal? = null,
    var duration: Int? = null,
    var icon: String? = null
)

/**
 * 会员订单响应DTO
 */
data class MemberOrderDTO(
    var id: Long? = null,
    var orderNo: String? = null,
    var packageId: Long? = null,
    var packageName: String? = null,
    var amount: BigDecimal? = null,
    var payType: Int? = null,
    var status: Int? = null,
    var statusName: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var payTime: LocalDateTime? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var expireTime: LocalDateTime? = null
)

/**
 * 创建订单请求DTO
 */
data class CreateOrderRequest(
    var packageId: Long? = null
)

/**
 * 支付请求DTO
 */
data class PayOrderRequest(
    var orderNo: String? = null,
    var payType: Int? = null // 1-支付宝 2-微信
)

/**
 * 用户会员响应DTO
 */
data class UserMemberDTO(
    var id: Long? = null,
    var packageId: Long? = null,
    var memberType: Int? = null,
    var memberTypeName: String? = null,
    var status: Int? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var startTime: LocalDateTime? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var expireTime: LocalDateTime? = null
)

/**
 * 支付回调请求DTO
 */
data class PayCallbackRequest(
    var orderNo: String? = null,
    var tradeNo: String? = null,
    var payType: Int? = null
)
