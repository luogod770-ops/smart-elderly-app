package com.smartelderly.community.data.model

import com.google.gson.annotations.SerializedName

/**
 * 系统配置模型
 */
data class SystemConfig(
    @SerializedName("customerServicePhone")
    val customerServicePhone: String = "",

    @SerializedName("customerServiceTime")
    val customerServiceTime: String = "",

    @SerializedName("elderModeEnabled")
    val elderModeEnabled: Boolean = false,

    @SerializedName("sosEnabled")
    val sosEnabled: Boolean = true,

    @SerializedName("sosPhone")
    val sosPhone: String = "110",

    @SerializedName("bannerEnabled")
    val bannerEnabled: Boolean = true,

    @SerializedName("checkInEnabled")
    val checkInEnabled: Boolean = true,

    @SerializedName("checkInPoints")
    val checkInPoints: Int = 10,

    @SerializedName("maxOnlineTime")
    val maxOnlineTime: Int = 720 // 分钟
)
