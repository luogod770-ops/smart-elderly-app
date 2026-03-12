package com.smartelderly.community.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.smartelderly.community.dto.*
import com.smartelderly.community.entity.*
import com.smartelderly.community.mapper.*
import com.smartelderly.community.service.interface.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * 会员服务实现
 */
@Service
class MemberServiceImpl @Autowired constructor(
    private val memberPackageMapper: MemberPackageMapper,
    private val memberOrderMapper: MemberOrderMapper,
    private val userMemberMapper: UserMemberMapper
) : MemberService {

    override fun getPackageList(): List<MemberPackageDTO> {
        val packages = memberPackageMapper.selectList(
            QueryWrapper<MemberPackage>()
                .eq("status", 1)
                .eq("deleted", 0)
                .orderByAsc("sort")
        )

        return packages.map { pkg ->
            MemberPackageDTO(
                id = pkg.id,
                name = pkg.name,
                description = pkg.description,
                price = pkg.price,
                originalPrice = pkg.originalPrice,
                duration = pkg.duration,
                icon = pkg.icon
            )
        }
    }

    override fun getPackageDetail(packageId: Long): MemberPackageDTO? {
        val pkg = memberPackageMapper.selectOne(
            QueryWrapper<MemberPackage>()
                .eq("id", packageId)
                .eq("deleted", 0)
        ) ?: return null

        return MemberPackageDTO(
            id = pkg.id,
            name = pkg.name,
            description = pkg.description,
            price = pkg.price,
            originalPrice = pkg.originalPrice,
            duration = pkg.duration,
            icon = pkg.icon
        )
    }

    @Transactional
    override fun createOrder(userId: Long, request: CreateOrderRequest): MemberOrderDTO? {
        val pkg = memberPackageMapper.selectOne(
            QueryWrapper<MemberPackage>()
                .eq("id", request.packageId)
                .eq("status", 1)
                .eq("deleted", 0)
        ) ?: return null

        // 生成订单号
        val orderNo = generateOrderNo()

        // 计算到期时间
        val expireTime = LocalDateTime.now().plusDays(pkg.duration?.toLong() ?: 0)

        // 创建订单
        val order = MemberOrder(
            orderNo = orderNo,
            userId = userId,
            packageId = pkg.id,
            packageName = pkg.name,
            amount = pkg.price,
            status = 1, // 待支付
            expireTime = expireTime
        )

        val result = memberOrderMapper.insert(order)

        return if (result > 0) {
            MemberOrderDTO(
                id = order.id,
                orderNo = order.orderNo,
                packageId = order.packageId,
                packageName = order.packageName,
                amount = order.amount,
                payType = order.payType,
                status = order.status,
                statusName = getOrderStatusName(order.status),
                payTime = order.payTime,
                expireTime = order.expireTime
            )
        } else null
    }

    override fun getOrderDetail(orderNo: String): MemberOrderDTO? {
        val order = memberOrderMapper.selectOne(
            QueryWrapper<MemberOrder>()
                .eq("order_no", orderNo)
                .eq("deleted", 0)
        ) ?: return null

        return MemberOrderDTO(
            id = order.id,
            orderNo = order.orderNo,
            packageId = order.packageId,
            packageName = order.packageName,
            amount = order.amount,
            payType = order.payType,
            status = order.status,
            statusName = getOrderStatusName(order.status),
            payTime = order.payTime,
            expireTime = order.expireTime
        )
    }

    override fun getMyOrders(userId: Long): List<MemberOrderDTO> {
        val orders = memberOrderMapper.selectList(
            QueryWrapper<MemberOrder>()
                .eq("user_id", userId)
                .eq("deleted", 0)
                .orderByDesc("create_time")
        )

        return orders.map { order ->
            MemberOrderDTO(
                id = order.id,
                orderNo = order.orderNo,
                packageId = order.packageId,
                packageName = order.packageName,
                amount = order.amount,
                payType = order.payType,
                status = order.status,
                statusName = getOrderStatusName(order.status),
                payTime = order.payTime,
                expireTime = order.expireTime
            )
        }
    }

    override fun getUserMember(userId: Long): UserMemberDTO? {
        val now = LocalDateTime.now()

        val userMember = userMemberMapper.selectOne(
            QueryWrapper<UserMember>()
                .eq("user_id", userId)
                .eq("status", 1)
                .gt("expire_time", now)
                .eq("deleted", 0)
                .orderByDesc("create_time")
                .last("LIMIT 1")
        )

        userMember ?: return null

        return UserMemberDTO(
            id = userMember.id,
            packageId = userMember.packageId,
            memberType = userMember.memberType,
            memberTypeName = getMemberTypeName(userMember.memberType),
            status = userMember.status,
            startTime = userMember.startTime,
            expireTime = userMember.expireTime
        )
    }

    override fun payOrder(orderNo: String, payType: Int): Map<String, Any>? {
        val order = memberOrderMapper.selectOne(
            QueryWrapper<MemberOrder>()
                .eq("order_no", orderNo)
                .eq("deleted", 0)
        ) ?: return null

        if (order.status != 1) {
            return null // 订单已处理
        }

        // 生成支付参数(这里返回模拟数据)
        val payParams = mapOf(
            "orderNo" to orderNo,
            "amount" to order.amount,
            "payType" to payType,
            "timestamp" to System.currentTimeMillis()
        )

        return payParams
    }

    @Transactional
    override fun payCallback(request: PayCallbackRequest): Boolean {
        val order = memberOrderMapper.selectOne(
            QueryWrapper<MemberOrder>()
                .eq("order_no", request.orderNo)
                .eq("deleted", 0)
        ) ?: return false

        if (order.status != 1) {
            return false // 订单已处理
        }

        // 更新订单状态
        order.status = 2 // 已支付
        order.payType = request.payType
        order.tradeNo = request.tradeNo
        order.payTime = LocalDateTime.now()

        val result = memberOrderMapper.updateById(order) > 0

        if (result) {
            // 创建会员记录
            createMember(order)
        }

        return result
    }

    @Transactional
    override fun cancelOrder(orderNo: String): Boolean {
        val order = memberOrderMapper.selectOne(
            QueryWrapper<MemberOrder>()
                .eq("order_no", orderNo)
                .eq("deleted", 0)
        ) ?: return false

        if (order.status != 1) {
            return false // 只能取消待支付订单
        }

        order.status = 3 // 已取消

        return memberOrderMapper.updateById(order) > 0
    }

    override fun checkMemberStatus(userId: Long): Boolean {
        val now = LocalDateTime.now()

        val count = userMemberMapper.selectCount(
            QueryWrapper<UserMember>()
                .eq("user_id", userId)
                .eq("status", 1)
                .gt("expire_time", now)
                .eq("deleted", 0)
        )

        return count > 0
    }

    @Transactional
    override fun renewMember(userId: Long, packageId: Long): Boolean {
        val pkg = memberPackageMapper.selectOne(
            QueryWrapper<MemberPackage>()
                .eq("id", packageId)
                .eq("deleted", 0)
        ) ?: return false

        // 查询当前会员信息
        val now = LocalDateTime.now()
        val currentMember = userMemberMapper.selectOne(
            QueryWrapper<UserMember>()
                .eq("user_id", userId)
                .eq("status", 1)
                .gt("expire_time", now)
                .eq("deleted", 0)
                .orderByDesc("create_time")
                .last("LIMIT 1")
        )

        val startTime = if (currentMember != null && currentMember.expireTime?.isAfter(now) == true) {
            currentMember.expireTime
        } else {
            now
        }

        val expireTime = startTime.plusDays(pkg.duration?.toLong() ?: 0)

        // 创建新会员记录
        val member = UserMember(
            userId = userId,
            packageId = pkg.id,
            memberType = getMemberType(pkg.duration),
            startTime = startTime,
            expireTime = expireTime,
            status = 1
        )

        return userMemberMapper.insert(member) > 0
    }

    private fun createMember(order: MemberOrder) {
        val pkg = memberPackageMapper.selectById(order.packageId) ?: return

        val startTime = LocalDateTime.now()
        val expireTime = startTime.plusDays(pkg.duration?.toLong() ?: 0)

        val member = UserMember(
            userId = order.userId,
            packageId = pkg.id,
            memberType = getMemberType(pkg.duration),
            startTime = startTime,
            expireTime = expireTime,
            status = 1
        )

        userMemberMapper.insert(member)
    }

    private fun generateOrderNo(): String {
        return "ORD" + System.currentTimeMillis() + Random().nextInt(1000)
    }

    private fun getOrderStatusName(status: Int?): String {
        return when (status) {
            1 -> "待支付"
            2 -> "已支付"
            3 -> "已取消"
            4 -> "已退款"
            else -> "未知"
        }
    }

    private fun getMemberTypeName(type: Int?): String {
        return when (type) {
            1 -> "月卡"
            2 -> "季卡"
            3 -> "年卡"
            4 -> "永久"
            else -> "未知"
        }
    }

    private fun getMemberType(duration: Int?): Int {
        return when (duration) {
            30 -> 1 // 月卡
            90 -> 2 // 季卡
            365 -> 3 // 年卡
            else -> 4 // 永久
        }
    }
}
