package com.smartelderly.community.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.smartelderly.community.entity.*
import com.smartelderly.community.mapper.*
import com.smartelderly.community.service.interface.SystemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

/**
 * 系统服务实现
 */
@Service
class SystemServiceImpl @Autowired constructor(
    private val systemConfigMapper: SystemConfigMapper,
    private val bannerMapper: BannerMapper,
    private val appVersionMapper: AppVersionMapper,
    private val redisTemplate: RedisTemplate<String, Any>
) : SystemService {

    override fun getSystemConfig(): Map<String, Any> {
        val configs = systemConfigMapper.selectList(
            QueryWrapper<SystemConfig>()
                .eq("status", 1)
                .eq("deleted", 0)
                .orderByAsc("sort")
        )

        val result = mutableMapOf<String, Any>()
        configs.forEach { config ->
            config.configKey?.let { key ->
                when (config.configType) {
                    2 -> config.configValue?.toIntOrNull()?.let { result[key] = it }
                    3 -> config.configValue?.toBooleanStrictOrNull()?.let { result[key] = it }
                    else -> config.configValue?.let { result[key] = it }
                }
            }
        }

        return result
    }

    override fun updateSystemConfig(configKey: String, configValue: String): Boolean {
        val config = systemConfigMapper.selectOne(
            QueryWrapper<SystemConfig>()
                .eq("config_key", configKey)
                .eq("deleted", 0)
        )

        return if (config != null) {
            config.configValue = configValue
            systemConfigMapper.updateById(config) > 0
        } else {
            false
        }
    }

    override fun getBannerList(): List<Banner> {
        return bannerMapper.selectList(
            QueryWrapper<Banner>()
                .eq("status", 1)
                .eq("deleted", 0)
                .orderByAsc("sort")
        )
    }

    override fun addBanner(banner: Banner): Boolean {
        return bannerMapper.insert(banner) > 0
    }

    override fun updateBanner(banner: Banner): Boolean {
        return bannerMapper.updateById(banner) > 0
    }

    override fun deleteBanner(id: Long): Boolean {
        return bannerMapper.deleteById(id) > 0
    }

    override fun checkUpdate(platform: Int, versionCode: Int): Map<String, Any> {
        val result = mutableMapOf<String, Any>()
        result["hasUpdate"] = false
        result["updateType"] = 1

        // 查询最新版本
        val latestVersion = appVersionMapper.selectOne(
            QueryWrapper<AppVersion>()
                .eq("platform", platform)
                .eq("publish_status", 1)
                .eq("deleted", 0)
                .orderByDesc("version_code")
                .last("LIMIT 1")
        )

        if (latestVersion != null && latestVersion.versionCode!! > versionCode) {
            result["hasUpdate"] = true
            result["updateType"] = latestVersion.updateType ?: 1
            result["version"] = latestVersion.version
            result["versionCode"] = latestVersion.versionCode
            result["downloadUrl"] = latestVersion.downloadUrl
            result["fileSize"] = latestVersion.fileSize
            result["updateLog"] = latestVersion.updateLog
            result["md5"] = latestVersion.md5
        }

        return result
    }

    override fun publishVersion(appVersion: AppVersion): Boolean {
        return appVersionMapper.insert(appVersion) > 0
    }

    override fun getLatestVersion(platform: Int): AppVersion? {
        return appVersionMapper.selectOne(
            QueryWrapper<AppVersion>()
                .eq("platform", platform)
                .eq("publish_status", 1)
                .eq("deleted", 0)
                .orderByDesc("version_code")
                .last("LIMIT 1")
        )
    }
}
