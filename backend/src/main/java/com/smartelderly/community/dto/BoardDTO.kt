package com.smartelderly.community.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

/**
 * 版块响应DTO
 */
data class BoardDTO(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var icon: String? = null,
    var postCount: Int? = 0,
    var memberCount: Int? = 0,
    var isJoined: Boolean? = false,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createTime: LocalDateTime? = null
)

/**
 * 版块列表请求DTO
 */
data class BoardListRequest(
    var status: Int? = 1  // 0-禁用 1-启用
)

/**
 * 加入版块请求DTO
 */
data class JoinBoardRequest(
    var boardId: Long? = null
)
