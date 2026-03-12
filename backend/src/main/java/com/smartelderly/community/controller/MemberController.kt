package com.smartelderly.community.controller

import com.smartelderly.community.common.Result
import com.smartelderly.community.dto.*
import com.smartelderly.community.service.interface.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 会员管理控制器
 */
@Tag(name = "会员管理", description = "会员相关接口")
@RestController
@RequestMapping("/member")
class MemberController @Autowired constructor(
    private val memberService: MemberService
) {

    @Operation(summary = "获取套餐列表")
    @GetMapping("/package/list")
    fun getPackageList(): Result<List<MemberPackageDTO>> {
        return Result.success(memberService.getPackageList())
    }

    @Operation(summary = "获取套餐详情")
    @GetMapping("/package/detail/{packageId}")
    fun getPackageDetail(@PathVariable packageId: Long): Result<MemberPackageDTO?> {
        return Result.success(memberService.getPackageDetail(packageId))
    }

    @Operation(summary = "创建订单")
    @PostMapping("/order/create")
    fun createOrder(
        @RequestHeader userId: Long,
        @RequestBody request: CreateOrderRequest
    ): Result<MemberOrderDTO?> {
        return Result.success(memberService.createOrder(userId, request))
    }

    @Operation(summary = "获取订单详情")
    @GetMapping("/order/detail")
    fun getOrderDetail(@RequestParam orderNo: String): Result<MemberOrderDTO?> {
        return Result.success(memberService.getOrderDetail(orderNo))
    }

    @Operation(summary = "获取我的订单列表")
    @GetMapping("/order/my")
    fun getMyOrders(@RequestHeader userId: Long): Result<List<MemberOrderDTO>> {
        return Result.success(memberService.getMyOrders(userId))
    }

    @Operation(summary = "获取用户会员信息")
    @GetMapping("/info")
    fun getUserMember(@RequestHeader userId: Long): Result<UserMemberDTO?> {
        return Result.success(memberService.getUserMember(userId))
    }

    @Operation(summary = "支付订单")
    @PostMapping("/order/pay")
    fun payOrder(
        @RequestParam orderNo: String,
        @RequestParam payType: Int
    ): Result<Map<String, Any>?> {
        return Result.success(memberService.payOrder(orderNo, payType))
    }

    @Operation(summary = "支付回调")
    @PostMapping("/order/callback")
    fun payCallback(@RequestBody request: PayCallbackRequest): Result<Boolean> {
        return Result.success(memberService.payCallback(request))
    }

    @Operation(summary = "取消订单")
    @PostMapping("/order/cancel")
    fun cancelOrder(@RequestParam orderNo: String): Result<Boolean> {
        return Result.success(memberService.cancelOrder(orderNo))
    }

    @Operation(summary = "检查会员状态")
    @GetMapping("/check")
    fun checkMemberStatus(@RequestHeader userId: Long): Result<Boolean> {
        return Result.success(memberService.checkMemberStatus(userId))
    }

    @Operation(summary = "续费会员")
    @PostMapping("/renew")
    fun renewMember(
        @RequestHeader userId: Long,
        @RequestParam packageId: Long
    ): Result<Boolean> {
        return Result.success(memberService.renewMember(userId, packageId))
    }
}
