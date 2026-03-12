package com.smartelderly.community.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用户实体
 */
@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: Long = 0,
    val phone: String = "",
    val nickname: String = "",
    val avatar: String = "",
    val gender: Int = 0,
    val age: Int = 0,
    val realName: String = "",
    val idCard: String = "",
    val memberType: Int = 0,
    val memberExpireTime: Long = 0,
    val isElderMode: Boolean = false
)
