package com.smartelderly.community.data.repository

import com.smartelderly.community.data.model.*
import com.smartelderly.community.data.network.RetrofitClient
import com.smartelderly.community.data.preference.PreferenceManager
import com.smartelderly.community.data.network.interceptor.HeaderInterceptor
import com.smartelderly.community.data.network.interceptor.LogInterceptor
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull

/**
 * 房屋数据仓库
 */
class HouseRepository {

    private val apiService by lazy {
        RetrofitClient.createService(com.smartelderly.community.data.network.ApiService::class.java)
    }

    /**
     * 获取城市列表
     */
    suspend fun getCityList(): Result<List<City>> {
        return try {
            val response = apiService.getCityList()
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
     * 获取社区列表
     */
    suspend fun getCommunityList(cityId: Long): Result<List<Community>> {
        return try {
            val response = apiService.getCommunityList(cityId)
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
     * 获取小区列表
     */
    suspend fun getComplexList(communityId: Long): Result<List<Complex>> {
        return try {
            val response = apiService.getComplexList(communityId)
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
     * 获取楼栋列表
     */
    suspend fun getBuildingList(complexId: Long): Result<List<Building>> {
        return try {
            val response = apiService.getBuildingList(complexId)
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
     * 申请绑定房屋
     */
    suspend fun applyBindHouse(buildingId: Long, roomNumber: String, floor: Int, unit: Int): Result<Unit> {
        return try {
            val request = BindHouseRequest(
                buildingId = buildingId,
                roomNumber = roomNumber,
                floor = floor,
                unit = unit
            )
            val response = apiService.applyBindHouse("Bearer ${PreferenceManager.getToken()}", request)
            if (response.isSuccess) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * 我的房屋列表
     */
    suspend fun getMyHouses(): Result<List<House>> {
        return try {
            val response = apiService.getMyHouses("Bearer ${PreferenceManager.getToken()}")
            if (response.isSuccess) {
                Result.success(response.data ?: emptyList())
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

/**
 * 绑定房屋请求
 */
data class BindHouseRequest(
    val buildingId: Long,
    val roomNumber: String,
    val floor: Int,
    val unit: Int
)
