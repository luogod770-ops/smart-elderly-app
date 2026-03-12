package com.smartelderly.community.service.interface

import com.smartelderly.community.dto.*

/**
 * 设备服务接口
 */
interface DeviceService {

    /**
     * 绑定设备
     */
    fun bindDevice(userId: Long, request: BindDeviceRequest): Long?

    /**
     * 解绑设备
     */
    fun unbindDevice(userId: Long, deviceId: Long): Boolean

    /**
     * 获取我的设备列表
     */
    fun getMyDevices(userId: Long): List<DeviceDTO>

    /**
     * 获取设备详情
     */
    fun getDeviceDetail(deviceId: Long, userId: Long): DeviceDTO?

    /**
     * 同步设备数据
     */
    fun syncDeviceData(userId: Long, request: SyncDeviceDataRequest): Boolean

    /**
     * 获取设备数据
     */
    fun getDeviceData(userId: Long, request: GetDeviceDataRequest): Map<String, Any>

    /**
     * 更新设备连接信息
     */
    fun updateDeviceConnectInfo(deviceId: Long, batteryLevel: Int): Boolean

    /**
     * 更新设备电量
     */
    fun updateBatteryLevel(deviceId: Long, batteryLevel: Int): Boolean
}
