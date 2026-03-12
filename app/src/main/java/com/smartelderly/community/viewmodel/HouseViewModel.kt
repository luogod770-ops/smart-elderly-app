package com.smartelderly.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartelderly.community.data.model.*
import com.smartelderly.community.data.repository.HouseRepository
import com.smartelderly.community.utils.LogUtil
import kotlinx.coroutines.launch

/**
 * 房屋ViewModel
 */
class HouseViewModel : ViewModel() {

    private val repository = HouseRepository()

    private val _cityList = MutableLiveData<List<City>>()
    val cityList: LiveData<List<City>> = _cityList

    private val _communityList = MutableLiveData<List<Community>>()
    val communityList: LiveData<List<Community>> = _communityList

    private val _complexList = MutableLiveData<List<Complex>>()
    val complexList: LiveData<List<Complex>> = _complexList

    private val _buildingList = MutableLiveData<List<Building>>()
    val buildingList: LiveData<List<Building>> = _buildingList

    private val _myHouses = MutableLiveData<List<House>>()
    val myHouses: LiveData<List<House>> = _myHouses

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _bindSuccess = MutableLiveData<Boolean>()
    val bindSuccess: LiveData<Boolean> = _bindSuccess

    /**
     * 加载城市列表
     */
    fun loadCityList() {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.getCityList()
                    .onSuccess { cities ->
                        _cityList.value = cities
                        LogUtil.d("城市列表加载成功,数量: ${cities.size}")
                    }
                    .onFailure { e ->
                        _error.value = "加载城市列表失败: ${e.message}"
                        LogUtil.e(e, "加载城市列表失败")
                    }
            } finally {
                _loading.value = false
            }
        }
    }

    /**
     * 加载社区列表
     */
    fun loadCommunityList(cityId: Long) {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.getCommunityList(cityId)
                    .onSuccess { communities ->
                        _communityList.value = communities
                        LogUtil.d("社区列表加载成功,数量: ${communities.size}")
                    }
                    .onFailure { e ->
                        _error.value = "加载社区列表失败: ${e.message}"
                        LogUtil.e(e, "加载社区列表失败")
                    }
            } finally {
                _loading.value = false
            }
        }
    }

    /**
     * 加载小区列表
     */
    fun loadComplexList(communityId: Long) {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.getComplexList(communityId)
                    .onSuccess { complexes ->
                        _complexList.value = complexes
                        LogUtil.d("小区列表加载成功,数量: ${complexes.size}")
                    }
                    .onFailure { e ->
                        _error.value = "加载小区列表失败: ${e.message}"
                        LogUtil.e(e, "加载小区列表失败")
                    }
            } finally {
                _loading.value = false
            }
        }
    }

    /**
     * 加载楼栋列表
     */
    fun loadBuildingList(complexId: Long) {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.getBuildingList(complexId)
                    .onSuccess { buildings ->
                        _buildingList.value = buildings
                        LogUtil.d("楼栋列表加载成功,数量: ${buildings.size}")
                    }
                    .onFailure { e ->
                        _error.value = "加载楼栋列表失败: ${e.message}"
                        LogUtil.e(e, "加载楼栋列表失败")
                    }
            } finally {
                _loading.value = false
            }
        }
    }

    /**
     * 申请绑定房屋
     */
    fun applyBindHouse(buildingId: Long, roomNumber: String, floor: Int, unit: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.applyBindHouse(buildingId, roomNumber, floor, unit)
                    .onSuccess {
                        _bindSuccess.value = true
                        LogUtil.d("房屋绑定申请成功")
                    }
                    .onFailure { e ->
                        _error.value = "绑定申请失败: ${e.message}"
                        LogUtil.e(e, "绑定申请失败")
                    }
            } finally {
                _loading.value = false
            }
        }
    }

    /**
     * 加载我的房屋
     */
    fun loadMyHouses() {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.getMyHouses()
                    .onSuccess { houses ->
                        _myHouses.value = houses
                        LogUtil.d("我的房屋加载成功,数量: ${houses.size}")
                    }
                    .onFailure { e ->
                        _error.value = "加载房屋列表失败: ${e.message}"
                        LogUtil.e(e, "加载房屋列表失败")
                    }
            } finally {
                _loading.value = false
            }
        }
    }
}
