package com.smartelderly.community.data.model

import com.google.gson.annotations.SerializedName

/**
 * 社区模型
 */
data class Community(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("cityId")
    val cityId: Long = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("code")
    val code: String = "",

    @SerializedName("address")
    val address: String = "",

    @SerializedName("phone")
    val phone: String = ""
)
