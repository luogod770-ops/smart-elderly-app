package com.smartelderly.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartelderly.community.data.model.*
import com.smartelderly.community.data.repository.SystemRepository
import com.smartelderly.community.utils.LogUtil
import kotlinx.coroutines.launch

/**
 * 系统ViewModel
 */
class SystemViewModel : ViewModel() {

    private val repository = SystemRepository()

    private val _systemConfig = MutableLiveData<SystemConfig>()
    val systemConfig: LiveData<SystemConfig> = _systemConfig

    private val _bannerList = MutableLiveData<List<Banner>>()
    val bannerList: LiveData<List<Banner>> = _bannerList

    private val _updateInfo = MutableLiveData<UpdateInfo>()
    val updateInfo: LiveData<UpdateInfo> = _updateInfo

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    /**
     * 加载系统配置
     */
    fun loadSystemConfig() {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.getSystemConfig()
                    .onSuccess { config ->
                        _systemConfig.value = config
                        LogUtil.d("系统配置加载成功")
                    }
                    .onFailure { e ->
                        _error.value = "加载系统配置失败: ${e.message}"
                        LogUtil.e(e, "加载系统配置失败")
                    }
            } finally {
                _loading.value = false
            }
        }
    }

    /**
     * 加载Banner列表
     */
    fun loadBannerList() {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.getBannerList()
                    .onSuccess { banners ->
                        _bannerList.value = banners
                        LogUtil.d("Banner列表加载成功,数量: ${banners.size}")
                    }
                    .onFailure { e ->
                        _error.value = "加载Banner失败: ${e.message}"
                        LogUtil.e(e, "加载Banner失败")
                    }
            } finally {
                _loading.value = false
            }
        }
    }

    /**
     * 检查更新
     */
    fun checkUpdate() {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.checkUpdate()
                    .onSuccess { updateInfo ->
                        _updateInfo.value = updateInfo
                        LogUtil.d("检查更新完成: ${updateInfo.hasUpdate}")
                    }
                    .onFailure { e ->
                        _error.value = "检查更新失败: ${e.message}"
                        LogUtil.e(e, "检查更新失败")
                    }
            } finally {
                _loading.value = false
            }
        }
    }
}
