package com.smartelderly.community.service.interface

import com.smartelderly.community.dto.*

/**
 * 会员服务接口
 */
interface MemberService {

    /**
     * 获取套餐列表
     */
    fun getPackageList(): List<MemberPackageDTO>

    /**
     * 获取套餐详情
     */
    fun getPackageDetail(packageId: Long): MemberPackageDTO?

    /**
     * 创建订单
     */
    fun createOrder(userId: Long, request: CreateOrderRequest): MemberOrderDTO?

    /**
     * 获取订单详情
     */
    fun getOrderDetail(orderNo: String): MemberOrderDTO?

    /**
     * 获取我的订单列表
     */
    fun getMyOrders(userId: Long): List<MemberOrderDTO>

    /**
     * 获取用户会员信息
     */
    fun getUserMember(userId: Long): UserMemberDTO?

    /**
     * 支付订单(生成支付参数)
     */
    fun payOrder(orderNo: String, payType: Int): Map<String, Any>?

    /**
     * 支付回调
     */
    fun payCallback(request: PayCallbackRequest): Boolean

    /**
     * 取消订单
     */
    fun cancelOrder(orderNo: String): Boolean

    /**
     * 检查会员状态
     */
    fun checkMemberStatus(userId: Long): Boolean

    /**
     * 续费会员
     */
    fun renewMember(userId: Long, packageId: Long): Boolean
}
