package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 设备实体
 */
@TableName("device")
data class Device(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 用户ID
     */
    var userId: Long? = null,

    /**
     * 设备名称
     */
    var deviceName: String? = null,

    /**
     * 设备类型: 1-智能手环 2-智能手表 3-健康监测器
     */
    var deviceType: Int? = 1,

    /**
     * 品牌型号
     */
    var brandModel: String? = null,

    /**
     * 设备MAC地址
     */
    var macAddress: String? = null,

    /**
     * 设备序列号
     */
    var serialNumber: String? = null,

    /**
     * 绑定状态: 0-未绑定 1-已绑定 2-已解绑
     */
    var bindStatus: Int? = 0,

    /**
     * 最后连接时间
     */
    var lastConnectTime: LocalDateTime? = null,

    /**
     * 电量百分比
     */
    var batteryLevel: Int? = 0,

    /**
     * 固件版本
     */
    var firmwareVersion: String? = null,

    /**
     * 状态: 0-禁用 1-启用
     */
    var status: Int? = 1,

    /**
     * 创建时间
     */
    var createTime: LocalDateTime? = null,

    /**
     * 更新时间
     */
    var updateTime: LocalDateTime? = null,

    /**
     * 删除标记: 0-未删除 1-已删除
     */
    var deleted: Int? = 0
)
