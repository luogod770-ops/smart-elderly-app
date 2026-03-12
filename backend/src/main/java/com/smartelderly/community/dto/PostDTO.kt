package com.smartelderly.community.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

/**
 * 帖子响应DTO
 */
data class PostDTO(
    var id: Long? = null,
    var boardId: Long? = null,
    var boardName: String? = null,
    var userId: Long? = null,
    var userNickname: String? = null,
    var userAvatar: String? = null,
    var title: String? = null,
    var content: String? = null,
    var images: List<String>? = null,
    var video: String? = null,
    var location: String? = null,
    var likeCount: Int? = 0,
    var commentCount: Int? = 0,
    var shareCount: Int? = 0,
    var isTop: Int? = 0,
    var isEssence: Int? = 0,
    var isLiked: Boolean? = false,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createTime: LocalDateTime? = null
)

/**
 * 发布帖子请求DTO
 */
data class CreatePostRequest(
    var boardId: Long? = null,
    var title: String? = null,
    var content: String? = null,
    var images: List<String>? = null,
    var video: String? = null,
    var location: String? = null
)

/**
 * 帖子列表请求DTO
 */
data class PostListRequest(
    var boardId: Long? = null,
    var userId: Long? = null,
    var isTop: Int? = null,
    var isEssence: Int? = null,
    var page: Int? = 1,
    var size: Int? = 10
)

/**
 * 点赞/取消点赞请求DTO
 */
data class LikePostRequest(
    var postId: Long? = null
)

/**
 * 转发帖子请求DTO
 */
data class SharePostRequest(
    var postId: Long? = null,
    var content: String? = null
)

/**
 * 举报帖子请求DTO
 */
data class ReportPostRequest(
    var postId: Long? = null,
    var reason: String? = null
)
