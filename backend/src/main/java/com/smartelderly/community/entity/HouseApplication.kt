package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 房屋申请实体
 */
@TableName("house_application")
data class HouseApplication(
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
     * 申请人姓名
     */
    var applicantName: String? = null,

    /**
     * 申请人电话
     */
    var applicantPhone: String? = null,

    /**
     * 身份证号
     */
    var idCard: String? = null,

    /**
     * 证明材料URL
     */
    var proofUrl: String? = null,

    /**
     * 申请状态: 1-待审核 2-已通过 3-已拒绝
     */
    var status: Int? = 1,

    /**
     * 审核意见
     */
    var auditOpinion: String? = null,

    /**
     * 审核人ID
     */
    var auditorId: Long? = null,

    /**
     * 审核时间
     */
    var auditTime: LocalDateTime? = null,

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
