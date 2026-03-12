package com.smartelderly.community.service.interface

import com.smartelderly.community.dto.*

/**
 * 帖子服务接口
 */
interface PostService {

    /**
     * 发布帖子
     */
    fun createPost(userId: Long, request: CreatePostRequest): Long?

    /**
     * 更新帖子
     */
    fun updatePost(postId: Long, userId: Long, request: CreatePostRequest): Boolean

    /**
     * 删除帖子
     */
    fun deletePost(postId: Long, userId: Long): Boolean

    /**
     * 获取帖子列表
     */
    fun getPostList(userId: Long?, request: PostListRequest): Map<String, Any>

    /**
     * 获取帖子详情
     */
    fun getPostDetail(postId: Long, userId: Long?): PostDTO?

    /**
     * 点赞/取消点赞帖子
     */
    fun likePost(userId: Long, request: LikePostRequest): Boolean

    /**
     * 转发帖子
     */
    fun sharePost(userId: Long, request: SharePostRequest): Long?

    /**
     * 举报帖子
     */
    fun reportPost(userId: Long, request: ReportPostRequest): Boolean

    /**
     * 置顶/取消置顶
     */
    fun toggleTop(postId: Long): Boolean

    /**
     * 设置/取消精华
     */
    fun toggleEssence(postId: Long): Boolean

    /**
     * 增加评论数
     */
    fun incrementCommentCount(postId: Long): Boolean
}
