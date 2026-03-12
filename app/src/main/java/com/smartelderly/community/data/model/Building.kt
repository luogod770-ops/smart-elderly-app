package com.smartelderly.community.data.model

import com.google.gson.annotations.SerializedName

/**
 * 楼栋模型
 */
data class Building(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("complexId")
    val complexId: Long = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("code")
    val code: String = "",

    @SerializedName("floors")
    val floors: Int = 0,

    @SerializedName("units")
    val units: Int = 0
)
