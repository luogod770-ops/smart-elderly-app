package com.smartelderly.community.common

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 统一响应结果
 */
@Schema(description = "统一响应结果")
data class Result<T>(
    @Schema(description = "响应码")
    var code: Int = 200,

    @Schema(description = "响应消息")
    var message: String = "success",

    @Schema(description = "响应数据")
    var data: T? = null
) {
    companion object {
        @JvmStatic
        fun <T> success(data: T? = null): Result<T> {
            return Result(200, "success", data)
        }

        @JvmStatic
        fun <T> error(message: String = "error"): Result<T> {
            return Result(500, message, null)
        }

        @JvmStatic
        fun <T> error(code: Int, message: String): Result<T> {
            return Result(code, message, null)
        }
    }
}
