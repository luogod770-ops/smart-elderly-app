package com.smartelderly.community.data.model

import com.google.gson.annotations.SerializedName

/**
 * 小区模型
 */
data class Complex(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("communityId")
    val communityId: Long = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("code")
    val code: String = "",

    @SerializedName("address")
    val address: String = "",

    @SerializedName("propertyCompany")
    val propertyCompany: String = "",

    @SerializedName("propertyPhone")
    val propertyPhone: String = ""
)
