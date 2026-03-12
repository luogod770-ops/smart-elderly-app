package com.smartelderly.community.data.model

import com.google.gson.annotations.SerializedName

/**
 * 更新信息模型
 */
data class UpdateInfo(
    @SerializedName("hasUpdate")
    val hasUpdate: Boolean = false,

    @SerializedName("updateType")
    val updateType: Int = 1, // 1-不更新 2-推荐更新 3-强制更新

    @SerializedName("version")
    val version: String? = null,

    @SerializedName("versionCode")
    val versionCode: Int? = null,

    @SerializedName("downloadUrl")
    val downloadUrl: String? = null,

    @SerializedName("fileSize")
    val fileSize: Long? = null,

    @SerializedName("updateLog")
    val updateLog: String? = null,

    @SerializedName("md5")
    val md5: String? = null
)
