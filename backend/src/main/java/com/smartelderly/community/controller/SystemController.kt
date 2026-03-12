package com.smartelderly.community.controller

import com.smartelderly.community.common.Result
import com.smartelderly.community.entity.*
import com.smartelderly.community.service.interface.SystemService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 系统配置控制器
 */
@Tag(name = "系统管理", description = "系统配置相关接口")
@RestController
@RequestMapping("/system")
class SystemController @Autowired constructor(
    private val systemService: SystemService
) {

    @Operation(summary = "获取系统配置")
    @GetMapping("/config")
    fun getSystemConfig(): Result<Map<String, Any>> {
        return Result.success(systemService.getSystemConfig())
    }

    @Operation(summary = "更新系统配置")
    @PutMapping("/config")
    fun updateSystemConfig(
        @RequestParam configKey: String,
        @RequestParam configValue: String
    ): Result<Boolean> {
        return Result.success(systemService.updateSystemConfig(configKey, configValue))
    }

    @Operation(summary = "获取Banner列表")
    @GetMapping("/banner/list")
    fun getBannerList(): Result<List<Banner>> {
        return Result.success(systemService.getBannerList())
    }

    @Operation(summary = "添加Banner")
    @PostMapping("/banner/add")
    fun addBanner(@RequestBody banner: Banner): Result<Boolean> {
        return Result.success(systemService.addBanner(banner))
    }

    @Operation(summary = "更新Banner")
    @PutMapping("/banner/update")
    fun updateBanner(@RequestBody banner: Banner): Result<Boolean> {
        return Result.success(systemService.updateBanner(banner))
    }

    @Operation(summary = "删除Banner")
    @DeleteMapping("/banner/{id}")
    fun deleteBanner(@PathVariable id: Long): Result<Boolean> {
        return Result.success(systemService.deleteBanner(id))
    }

    @Operation(summary = "检查更新")
    @GetMapping("/checkUpdate")
    fun checkUpdate(
        @RequestParam platform: Int,
        @RequestParam versionCode: Int
    ): Result<Map<String, Any>> {
        return Result.success(systemService.checkUpdate(platform, versionCode))
    }

    @Operation(summary = "发布新版本")
    @PostMapping("/version/publish")
    fun publishVersion(@RequestBody appVersion: AppVersion): Result<Boolean> {
        return Result.success(systemService.publishVersion(appVersion))
    }

    @Operation(summary = "获取最新版本")
    @GetMapping("/version/latest")
    fun getLatestVersion(@RequestParam platform: Int): Result<AppVersion?> {
        return Result.success(systemService.getLatestVersion(platform))
    }
}
