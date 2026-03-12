package com.smartelderly.community.data.model

import com.google.gson.annotations.SerializedName

/**
 * 用户模型
 */
data class User(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("phone")
    val phone: String = "",

    @SerializedName("nickname")
    val nickname: String = "",

    @SerializedName("avatar")
    val avatar: String = "",

    @SerializedName("gender")
    val gender: Int = 0, // 0:未知 1:男 2:女

    @SerializedName("age")
    val age: Int = 0,

    @SerializedName("realName")
    val realName: String = "",

    @SerializedName("idCard")
    val idCard: String = "",

    @SerializedName("memberType")
    val memberType: Int = 0, // 0:普通会员 1:会员 2:高级会员

    @SerializedName("memberExpireTime")
    val memberExpireTime: Long = 0,

    @SerializedName("isElderMode")
    val isElderMode: Boolean = false,

    @SerializedName("createTime")
    val createTime: Long = 0
)
