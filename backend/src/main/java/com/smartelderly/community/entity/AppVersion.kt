package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * APP版本实体
 */
@TableName("app_version")
data class AppVersion(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 版本号 (如: 1.0.0)
     */
    var version: String? = null,

    /**
     * 版本代码 (如: 100)
     */
    var versionCode: Int? = null,

    /**
     * 更新类型: 1-不更新 2-推荐更新 3-强制更新
     */
    var updateType: Int? = 1,

    /**
     * 下载链接
     */
    var downloadUrl: String? = null,

    /**
     * 文件大小 (字节)
     */
    var fileSize: Long? = null,

    /**
     * MD5值
     */
    var md5: String? = null,

    /**
     * 更新说明
     */
    var updateLog: String? = null,

    /**
     * 发布状态: 0-未发布 1-已发布
     */
    var publishStatus: Int? = 0,

    /**
     * 平台: 1-Android 2-iOS
     */
    var platform: Int? = 1,

    /**
     * 创建时间
     */
    var createTime: LocalDateTime? = null,

    /**
     * 更新时间
     */
    var updateTime: LocalDateTime? = null,

    /**
     * 删除标记: 0-未删除 1-已删除
     */
    var deleted: Int? = 0
)
