package com.smartelderly.community.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

/**
 * 设备响应DTO
 */
data class DeviceDTO(
    var id: Long? = null,
    var userId: Long? = null,
    var deviceName: String? = null,
    var deviceType: Int? = null,
    var deviceTypeName: String? = null,
    var brandModel: String? = null,
    var macAddress: String? = null,
    var serialNumber: String? = null,
    var bindStatus: Int? = null,
    var batteryLevel: Int? = null,
    var firmwareVersion: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var lastConnectTime: LocalDateTime? = null
)

/**
 * 绑定设备请求DTO
 */
data class BindDeviceRequest(
    var deviceName: String? = null,
    var deviceType: Int? = 1,
    var brandModel: String? = null,
    var macAddress: String? = null,
    var serialNumber: String? = null
)

/**
 * 设备数据响应DTO
 */
data class DeviceDataDTO(
    var id: Long? = null,
    var deviceId: Long? = null,
    var dataType: Int? = null,
    var dataTypeName: String? = null,
    var value: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var recordTime: LocalDateTime? = null
)

/**
 * 同步设备数据请求DTO
 */
data class SyncDeviceDataRequest(
    var deviceId: Long? = null,
    var dataType: Int? = null,
    var value: String? = null,
    var recordTime: String? = null
)

/**
 * 获取设备数据请求DTO
 */
data class GetDeviceDataRequest(
    var deviceId: Long? = null,
    var dataType: Int? = null,
    var startDate: String? = null,
    var endDate: String? = null,
    var page: Int? = 1,
    var size: Int? = 20
)
