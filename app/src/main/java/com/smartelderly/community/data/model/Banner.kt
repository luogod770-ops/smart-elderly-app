package com.smartelderly.community.data.model

import com.google.gson.annotations.SerializedName

/**
 * Banner模型
 */
data class Banner(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("imageUrl")
    val imageUrl: String = "",

    @SerializedName("linkUrl")
    val linkUrl: String = "",

    @SerializedName("linkType")
    val linkType: Int = 1, // 1-不跳转 2-网页 3-帖子 4-活动

    @SerializedName("targetId")
    val targetId: Long? = null
)
