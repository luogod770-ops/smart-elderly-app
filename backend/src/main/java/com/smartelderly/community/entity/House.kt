package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 房屋实体
 */
@TableName("house")
data class House(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 用户ID
     */
    var userId: Long? = null,

    /**
     * 楼栋ID
     */
    var buildingId: Long? = null,

    /**
     * 房号
     */
    var roomNumber: String? = null,

    /**
     * 楼层
     */
    var floor: Int? = 0,

    /**
     * 单元
     */
    var unit: Int? = 0,

    /**
     * 面积
     */
    var area: Double? = 0.0,

    /**
     * 类型: 1-住宅 2-商铺 3-其他
     */
    var type: Int? = 1,

    /**
     * 状态: 1-待审核 2-已通过 3-已拒绝
     */
    var status: Int? = 1,

    /**
     * 拒绝原因
     */
    var rejectReason: String? = null,

    /**
     * 是否主房: 0-否 1-是
     */
    var isMain: Int? = 0,

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
