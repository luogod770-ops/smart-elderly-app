package com.smartelderly.community.controller

import com.smartelderly.community.common.Result
import com.smartelderly.community.dto.*
import com.smartelderly.community.service.interface.DeviceService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 设备管理控制器
 */
@Tag(name = "设备管理", description = "设备相关接口")
@RestController
@RequestMapping("/device")
class DeviceController @Autowired constructor(
    private val deviceService: DeviceService
) {

    @Operation(summary = "绑定设备")
    @PostMapping("/bind")
    fun bindDevice(
        @RequestHeader userId: Long,
        @RequestBody request: BindDeviceRequest
    ): Result<Long?> {
        return Result.success(deviceService.bindDevice(userId, request))
    }

    @Operation(summary = "解绑设备")
    @PostMapping("/unbind")
    fun unbindDevice(
        @RequestHeader userId: Long,
        @RequestParam deviceId: Long
    ): Result<Boolean> {
        return Result.success(deviceService.unbindDevice(userId, deviceId))
    }

    @Operation(summary = "获取我的设备列表")
    @GetMapping("/my")
    fun getMyDevices(@RequestHeader userId: Long): Result<List<DeviceDTO>> {
        return Result.success(deviceService.getMyDevices(userId))
    }

    @Operation(summary = "获取设备详情")
    @GetMapping("/detail/{deviceId}")
    fun getDeviceDetail(
        @RequestHeader userId: Long,
        @PathVariable deviceId: Long
    ): Result<DeviceDTO?> {
        return Result.success(deviceService.getDeviceDetail(deviceId, userId))
    }

    @Operation(summary = "同步设备数据")
    @PostMapping("/data/sync")
    fun syncDeviceData(
        @RequestHeader userId: Long,
        @RequestBody request: SyncDeviceDataRequest
    ): Result<Boolean> {
        return Result.success(deviceService.syncDeviceData(userId, request))
    }

    @Operation(summary = "获取设备数据")
    @GetMapping("/data")
    fun getDeviceData(
        @RequestHeader userId: Long,
        @RequestParam(value = "deviceId", required = false) deviceId: Long?,
        @RequestParam(value = "dataType", required = false) dataType: Int?,
        @RequestParam(value = "startDate", required = false) startDate: String?,
        @RequestParam(value = "endDate", required = false) endDate: String?,
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "20") size: Int
    ): Result<Map<String, Any>> {
        val request = GetDeviceDataRequest(
            deviceId = deviceId,
            dataType = dataType,
            startDate = startDate,
            endDate = endDate,
            page = page,
            size = size
        )
        return Result.success(deviceService.getDeviceData(userId, request))
    }

    @Operation(summary = "更新设备连接信息")
    @PostMapping("/updateConnectInfo")
    fun updateDeviceConnectInfo(
        @RequestParam deviceId: Long,
        @RequestParam batteryLevel: Int
    ): Result<Boolean> {
        return Result.success(deviceService.updateDeviceConnectInfo(deviceId, batteryLevel))
    }

    @Operation(summary = "更新设备电量")
    @PostMapping("/updateBattery")
    fun updateBatteryLevel(
        @RequestParam deviceId: Long,
        @RequestParam batteryLevel: Int
    ): Result<Boolean> {
        return Result.success(deviceService.updateBatteryLevel(deviceId, batteryLevel))
    }
}
