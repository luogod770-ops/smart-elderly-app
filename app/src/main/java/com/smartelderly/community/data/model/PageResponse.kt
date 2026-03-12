package com.smartelderly.community.data.model

import com.google.gson.annotations.SerializedName

/**
 * 分页响应
 */
data class PageResponse<T>(
    @SerializedName("list")
    val list: List<T> = emptyList(),

    @SerializedName("total")
    val total: Long = 0,

    @SerializedName("page")
    val page: Int = 1,

    @SerializedName("size")
    val size: Int = 10
)
