package com.smartelderly.community.service.interface

import com.smartelderly.community.entity.*
import java.util.*

/**
 * 系统服务接口
 */
interface SystemService {

    /**
     * 获取系统配置
     */
    fun getSystemConfig(): Map<String, Any>

    /**
     * 更新系统配置
     */
    fun updateSystemConfig(configKey: String, configValue: String): Boolean

    /**
     * 获取Banner列表
     */
    fun getBannerList(): List<Banner>

    /**
     * 添加Banner
     */
    fun addBanner(banner: Banner): Boolean

    /**
     * 更新Banner
     */
    fun updateBanner(banner: Banner): Boolean

    /**
     * 删除Banner
     */
    fun deleteBanner(id: Long): Boolean

    /**
     * 检查更新
     */
    fun checkUpdate(platform: Int, versionCode: Int): Map<String, Any>

    /**
     * 发布新版本
     */
    fun publishVersion(appVersion: AppVersion): Boolean

    /**
     * 获取最新版本
     */
    fun getLatestVersion(platform: Int): AppVersion?
}
