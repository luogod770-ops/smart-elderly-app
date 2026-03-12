package com.smartelderly.community.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.smartelderly.community.dto.*
import com.smartelderly.community.entity.Device
import com.smartelderly.community.entity.DeviceData
import com.smartelderly.community.mapper.DeviceDataMapper
import com.smartelderly.community.mapper.DeviceMapper
import com.smartelderly.community.service.interface.DeviceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 设备服务实现
 */
@Service
class DeviceServiceImpl @Autowired constructor(
    private val deviceMapper: DeviceMapper,
    private val deviceDataMapper: DeviceDataMapper
) : DeviceService {

    @Transactional
    override fun bindDevice(userId: Long, request: BindDeviceRequest): Long? {
        // 检查是否已绑定该设备
        val existingDevice = deviceMapper.selectOne(
            QueryWrapper<Device>()
                .eq("user_id", userId)
                .eq("mac_address", request.macAddress)
                .eq("deleted", 0)
        )

        if (existingDevice != null) {
            // 已存在,更新绑定状态
            existingDevice.bindStatus = 1
            existingDevice.deviceName = request.deviceName
            existingDevice.brandModel = request.brandModel
            deviceMapper.updateById(existingDevice)
            return existingDevice.id
        }

        // 创建新设备记录
        val device = Device(
            userId = userId,
            deviceName = request.deviceName,
            deviceType = request.deviceType,
            brandModel = request.brandModel,
            macAddress = request.macAddress,
            serialNumber = request.serialNumber,
            bindStatus = 1,
            status = 1
        )

        val result = deviceMapper.insert(device)

        return if (result > 0) device.id else null
    }

    @Transactional
    override fun unbindDevice(userId: Long, deviceId: Long): Boolean {
        val device = deviceMapper.selectOne(
            QueryWrapper<Device>()
                .eq("id", deviceId)
                .eq("user_id", userId)
                .eq("deleted", 0)
        ) ?: return false

        device.bindStatus = 2 // 已解绑

        return deviceMapper.updateById(device) > 0
    }

    override fun getMyDevices(userId: Long): List<DeviceDTO> {
        val devices = deviceMapper.selectList(
            QueryWrapper<Device>()
                .eq("user_id", userId)
                .eq("status", 1)
                .eq("deleted", 0)
                .orderByDesc("last_connect_time")
        )

        return devices.map { device ->
            DeviceDTO(
                id = device.id,
                userId = device.userId,
                deviceName = device.deviceName,
                deviceType = device.deviceType,
                deviceTypeName = getDeviceTypeName(device.deviceType),
                brandModel = device.brandModel,
                macAddress = device.macAddress,
                serialNumber = device.serialNumber,
                bindStatus = device.bindStatus,
                batteryLevel = device.batteryLevel,
                firmwareVersion = device.firmwareVersion,
                lastConnectTime = device.lastConnectTime
            )
        }
    }

    override fun getDeviceDetail(deviceId: Long, userId: Long): DeviceDTO? {
        val device = deviceMapper.selectOne(
            QueryWrapper<Device>()
                .eq("id", deviceId)
                .eq("user_id", userId)
                .eq("deleted", 0)
        ) ?: return null

        return DeviceDTO(
            id = device.id,
            userId = device.userId,
            deviceName = device.deviceName,
            deviceType = device.deviceType,
            deviceTypeName = getDeviceTypeName(device.deviceType),
            brandModel = device.brandModel,
            macAddress = device.macAddress,
            serialNumber = device.serialNumber,
            bindStatus = device.bindStatus,
            batteryLevel = device.batteryLevel,
            firmwareVersion = device.firmwareVersion,
            lastConnectTime = device.lastConnectTime
        )
    }

    @Transactional
    override fun syncDeviceData(userId: Long, request: SyncDeviceDataRequest): Boolean {
        // 验证设备所有权
        val device = deviceMapper.selectOne(
            QueryWrapper<Device>()
                .eq("id", request.deviceId)
                .eq("user_id", userId)
                .eq("bind_status", 1)
                .eq("deleted", 0)
        ) ?: return false

        // 解析记录时间
        val recordTime = try {
            LocalDateTime.parse(request.recordTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        } catch (e: Exception) {
            LocalDateTime.now()
        }

        // 创建数据记录
        val deviceData = DeviceData(
            deviceId = request.deviceId,
            userId = userId,
            dataType = request.dataType,
            value = request.value,
            recordTime = recordTime,
            source = 1 // 蓝牙同步
        )

        return deviceDataMapper.insert(deviceData) > 0
    }

    override fun getDeviceData(userId: Long, request: GetDeviceDataRequest): Map<String, Any> {
        val queryWrapper = QueryWrapper<DeviceData>()
            .eq("user_id", userId)
            .eq("deleted", 0)

        // 设备筛选
        request.deviceId?.let { queryWrapper.eq("device_id", it) }

        // 数据类型筛选
        request.dataType?.let { queryWrapper.eq("data_type", it) }

        // 日期范围筛选
        request.startDate?.let {
            val startTime = LocalDateTime.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            queryWrapper.ge("record_time", startTime)
        }
        request.endDate?.let {
            val endTime = LocalDateTime.parse(it + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            queryWrapper.le("record_time", endTime)
        }

        queryWrapper.orderByDesc("record_time")

        // 分页
        val page = com.baomidou.mybatisplus.extension.plugins.pagination.Page<DeviceData>(
            request.page!!.toLong(),
            request.size!!.toLong()
        )

        val pageResult = deviceDataMapper.selectPage(page, queryWrapper)

        val dataList = pageResult.records.map { data ->
            DeviceDataDTO(
                id = data.id,
                deviceId = data.deviceId,
                dataType = data.dataType,
                dataTypeName = getDataTypeName(data.dataType),
                value = data.value,
                recordTime = data.recordTime
            )
        }

        return mapOf(
            "list" to dataList,
            "total" to pageResult.total,
            "page" to pageResult.current,
            "size" to pageResult.size
        )
    }

    @Transactional
    override fun updateDeviceConnectInfo(deviceId: Long, batteryLevel: Int): Boolean {
        val device = deviceMapper.selectById(deviceId) ?: return false

        device.lastConnectTime = LocalDateTime.now()
        device.batteryLevel = batteryLevel

        return deviceMapper.updateById(device) > 0
    }

    @Transactional
    override fun updateBatteryLevel(deviceId: Long, batteryLevel: Int): Boolean {
        val device = deviceMapper.selectById(deviceId) ?: return false

        device.batteryLevel = batteryLevel

        return deviceMapper.updateById(device) > 0
    }

    private fun getDeviceTypeName(type: Int?): String {
        return when (type) {
            1 -> "智能手环"
            2 -> "智能手表"
            3 -> "健康监测器"
            else -> "未知设备"
        }
    }

    private fun getDataTypeName(type: Int?): String {
        return when (type) {
            1 -> "心率"
            2 -> "步数"
            3 -> "血氧"
            4 -> "睡眠"
            5 -> "血压"
            6 -> "体温"
            else -> "其他"
        }
    }
}
