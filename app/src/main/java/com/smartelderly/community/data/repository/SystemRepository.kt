package com.smartelderly.community.data.repository

import com.smartelderly.community.data.model.*
import com.smartelderly.community.data.network.RetrofitClient
import com.smartelderly.community.data.network.RetrofitClient.createService
import com.smartelderly.community.data.preference.PreferenceManager

/**
 * 系统数据仓库
 */
class SystemRepository {

    private val apiService by lazy {
        RetrofitClient.createService(com.smartelderly.community.data.network.ApiService::class.java)
    }

    /**
     * 获取系统配置
     */
    suspend fun getSystemConfig(): Result<SystemConfig> {
        return try {
            val response = apiService.getSystemConfig(
                "Bearer ${PreferenceManager.getToken()}"
            )
            if (response.isSuccess) {
                Result.success(response.data ?: SystemConfig())
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * 获取Banner列表
     */
    suspend fun getBannerList(): Result<List<Banner>> {
        return try {
            val response = apiService.getBannerList()
            if (response.isSuccess) {
                Result.success(response.data ?: emptyList())
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * 检查更新
     */
    suspend fun checkUpdate(): Result<UpdateInfo> {
        return try {
            val response = apiService.checkUpdate()
            if (response.isSuccess) {
                Result.success(response.data ?: UpdateInfo())
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
