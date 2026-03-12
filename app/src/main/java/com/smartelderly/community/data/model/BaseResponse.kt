package com.smartelderly.community.data.model

import com.google.gson.annotations.SerializedName

/**
 * 基础响应
 */
data class BaseResponse<T>(
    @SerializedName("code")
    val code: Int = 0,

    @SerializedName("message")
    val message: String = "",

    @SerializedName("data")
    val data: T? = null
) {
    val isSuccess: Boolean
        get() = code == 0
}
