package com.smartelderly.community.common

/**
 * 响应码枚举
 */
enum class ResultCode(val code: Int, val message: String) {
    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    PARAM_ERROR(400, "参数错误"),

    // 用户相关
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_EXIST(1002, "用户已存在"),
    PASSWORD_ERROR(1003, "密码错误"),
    SMS_CODE_ERROR(1004, "验证码错误"),
    SMS_CODE_EXPIRED(1005, "验证码已过期"),
    TOKEN_EXPIRED(1006, "Token已过期"),
    TOKEN_INVALID(1007, "Token无效"),

    // 房屋相关
    HOUSE_NOT_EXIST(2001, "房屋不存在"),
    HOUSE_ALREADY_BOUND(2002, "房屋已绑定"),
    HOUSE_APPLICATION_EXIST(2003, "申请已存在"),

    // 社区相关
    POST_NOT_EXIST(3001, "帖子不存在"),
    BOARD_NOT_EXIST(3002, "版块不存在"),

    // 设备相关
    DEVICE_NOT_EXIST(4001, "设备不存在"),
    DEVICE_ALREADY_BOUND(4002, "设备已绑定"),

    // 订单相关
    ORDER_NOT_EXIST(5001, "订单不存在"),
    ORDER_PAID(5002, "订单已支付"),

    // 系统相关
    SYSTEM_ERROR(9001, "系统错误"),
    NETWORK_ERROR(9002, "网络错误"),
    SERVER_ERROR(9003, "服务器错误")
}
