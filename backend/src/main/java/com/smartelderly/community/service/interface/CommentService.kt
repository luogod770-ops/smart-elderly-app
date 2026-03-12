package com.smartelderly.community.service.interface

import com.smartelderly.community.dto.*

/**
 * 评论服务接口
 */
interface CommentService {

    /**
     * 创建评论
     */
    fun createComment(userId: Long, request: CreateCommentRequest): Long?

    /**
     * 删除评论
     */
    fun deleteComment(commentId: Long, userId: Long): Boolean

    /**
     * 获取评论列表
     */
    fun getCommentList(userId: Long?, request: CommentListRequest): Map<String, Any>

    /**
     * 点赞/取消点赞评论
     */
    fun likeComment(userId: Long, request: LikeCommentRequest): Boolean

    /**
     * 获取评论回复列表
     */
    fun getCommentReplies(parentId: Long, page: Int, size: Int): Map<String, Any>
}
