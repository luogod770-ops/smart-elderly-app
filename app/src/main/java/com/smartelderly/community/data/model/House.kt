package com.smartelderly.community.data.model

import com.google.gson.annotations.SerializedName

/**
 * 房屋模型
 */
data class House(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("userId")
    val userId: Long = 0,

    @SerializedName("buildingId")
    val buildingId: Long = 0,

    @SerializedName("roomNumber")
    val roomNumber: String = "",

    @SerializedName("floor")
    val floor: Int = 0,

    @SerializedName("unit")
    val unit: Int = 0,

    @SerializedName("area")
    val area: Double = 0.0,

    @SerializedName("type")
    val type: Int = 1,

    @SerializedName("status")
    val status: Int = 1,

    @SerializedName("rejectReason")
    val rejectReason: String = "",

    @SerializedName("isMain")
    val isMain: Int = 0,

    // 楼栋信息
    @SerializedName("buildingName")
    val buildingName: String? = null,

    @SerializedName("complexName")
    val complexName: String? = null,

    @SerializedName("communityName")
    val communityName: String? = null,

    @SerializedName("address")
    val address: String? = null
)
