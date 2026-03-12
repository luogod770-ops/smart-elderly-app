package com.smartelderly.community.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

/**
 * 评论响应DTO
 */
data class CommentDTO(
    var id: Long? = null,
    var postId: Long? = null,
    var userId: Long? = null,
    var userNickname: String? = null,
    var userAvatar: String? = null,
    var parentId: Long? = 0,
    var replyUserId: Long? = null,
    var replyNickname: String? = null,
    var content: String? = null,
    var images: List<String>? = null,
    var likeCount: Int? = 0,
    var isLiked: Boolean? = false,
    var replyCount: Int? = 0,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createTime: LocalDateTime? = null
)

/**
 * 创建评论请求DTO
 */
data class CreateCommentRequest(
    var postId: Long? = null,
    var parentId: Long? = 0,
    var replyUserId: Long? = null,
    var content: String? = null,
    var images: List<String>? = null
)

/**
 * 评论列表请求DTO
 */
data class CommentListRequest(
    var postId: Long? = null,
    var parentId: Long? = null,
    var page: Int? = 1,
    var size: Int? = 20
)

/**
 * 点赞评论请求DTO
 */
data class LikeCommentRequest(
    var commentId: Long? = null
)

/**
 * 删除评论请求DTO
 */
data class DeleteCommentRequest(
    var commentId: Long? = null
)
