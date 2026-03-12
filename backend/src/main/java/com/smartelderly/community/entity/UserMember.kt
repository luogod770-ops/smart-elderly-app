package com.smartelderly.community.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import java.time.LocalDateTime

/**
 * 用户会员实体
 */
@TableName("user_member")
data class UserMember(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,

    /**
     * 用户ID
     */
    var userId: Long? = null,

    /**
     * 套餐ID
     */
    var packageId: Long? = null,

    /**
     * 会员类型: 1-月卡 2-季卡 3-年卡 4-永久
     */
    var memberType: Int? = null,

    /**
     * 开始时间
     */
    var startTime: LocalDateTime? = null,

    /**
     * 到期时间
     */
    var expireTime: LocalDateTime? = null,

    /**
     * 状态: 0-已过期 1-生效中
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
