package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 设备数据实体
 */
@TableName("device_data")
data class DeviceData(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 设备ID
     */
    var deviceId: Long? = null,

    /**
     * 用户ID
     */
    var userId: Long? = null,

    /**
     * 数据类型: 1-心率 2-步数 3-血氧 4-睡眠 5-血压 6-体温
     */
    var dataType: Int? = null,

    /**
     * 数据值(JSON格式)
     */
    var value: String? = null,

    /**
     * 记录时间
     */
    var recordTime: LocalDateTime? = null,

    /**
     * 数据来源: 1-蓝牙 2-手动 3-同步
     */
    var source: Int? = 1,

    /**
     * 创建时间
     */
    var createTime: LocalDateTime? = null,

    /**
     * 删除标记: 0-未删除 1-已删除
     */
    var deleted: Int? = 0
)
