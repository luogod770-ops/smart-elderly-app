package com.smartelderly.community.data.model

import com.google.gson.annotations.SerializedName

/**
 * 城市模型
 */
data class City(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("code")
    val code: String = "",

    @SerializedName("provinceId")
    val provinceId: Long = 0,

    @SerializedName("provinceName")
    val provinceName: String = ""
)
