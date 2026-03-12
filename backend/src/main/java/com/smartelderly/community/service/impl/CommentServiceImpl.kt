package com.smartelderly.community.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.smartelderly.community.dto.*
import com.smartelderly.community.entity.*
import com.smartelderly.community.mapper.*
import com.smartelderly.community.service.interface.CommentService
import com.smartelderly.community.service.interface.PostService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 评论服务实现
 */
@Service
class CommentServiceImpl @Autowired constructor(
    private val commentMapper: CommentMapper,
    private val commentLikeMapper: CommentLikeMapper,
    private val postService: PostService,
    private val postMapper: PostMapper,
    private val objectMapper: ObjectMapper
) : CommentService {

    @Transactional
    override fun createComment(userId: Long, request: CreateCommentRequest): Long? {
        val postId = request.postId ?: return null

        // 检查帖子是否存在
        val post = postMapper.selectById(postId) ?: return null

        // 序列化图片列表
        val imagesJson = if (!request.images.isNullOrEmpty()) {
            objectMapper.writeValueAsString(request.images)
        } else {
            null
        }

        val comment = Comment(
            postId = postId,
            userId = userId,
            parentId = request.parentId ?: 0,
            replyUserId = request.replyUserId,
            content = request.content,
            images = imagesJson,
            status = 1
        )

        val result = commentMapper.insert(comment)

        if (result > 0) {
            // 更新帖子评论数
            postService.incrementCommentCount(postId)
        }

        return if (result > 0) comment.id else null
    }

    @Transactional
    override fun deleteComment(commentId: Long, userId: Long): Boolean {
        val comment = commentMapper.selectById(commentId) ?: return false

        // 检查是否是评论作者
        if (comment.userId != userId) {
            return false
        }

        comment.status = 0 // 已删除

        return commentMapper.updateById(comment) > 0
    }

    override fun getCommentList(userId: Long?, request: CommentListRequest): Map<String, Any> {
        val postId = request.postId ?: return emptyMap()

        // 如果指定了parentId,返回子评论列表
        if (request.parentId != null && request.parentId!! > 0) {
            return getCommentReplies(request.parentId!!, request.page!!, request.size!!)
        }

        // 否则返回顶级评论列表
        val page = Page<Comment>(request.page!!.toLong(), request.size!!.toLong())

        val queryWrapper = QueryWrapper<Comment>()
            .eq("post_id", postId)
            .eq("parent_id", 0)
            .eq("status", 1)
            .eq("deleted", 0)
            .orderByDesc("create_time")

        val pageResult = commentMapper.selectPage(page, queryWrapper)

        // 获取用户点赞的评论
        val likedCommentIds = if (userId != null) {
            commentLikeMapper.selectList(
                QueryWrapper<CommentLike>()
                    .eq("user_id", userId)
                    .eq("deleted", 0)
            ).mapNotNull { it.commentId }
        } else {
            emptyList()
        }

        val commentList = pageResult.records.map { comment ->
            // 获取回复数
            val replyCount = commentMapper.selectCount(
                QueryWrapper<Comment>()
                    .eq("parent_id", comment.id)
                    .eq("status", 1)
                    .eq("deleted", 0)
            )

            CommentDTO(
                id = comment.id,
                postId = comment.postId,
                userId = comment.userId,
                userNickname = comment.userNickname,
                userAvatar = comment.userAvatar,
                parentId = comment.parentId,
                replyUserId = comment.replyUserId,
                replyNickname = comment.replyNickname,
                content = comment.content,
                images = parseImages(comment.images),
                likeCount = comment.likeCount,
                isLiked = likedCommentIds.contains(comment.id),
                replyCount = replyCount.toInt(),
                createTime = comment.createTime
            )
        }

        return mapOf(
            "list" to commentList,
            "total" to pageResult.total,
            "page" to pageResult.current,
            "size" to pageResult.size
        )
    }

    override fun getCommentReplies(parentId: Long, page: Int, size: Int): Map<String, Any> {
        val pageObj = Page<Comment>(page.toLong(), size.toLong())

        val queryWrapper = QueryWrapper<Comment>()
            .eq("parent_id", parentId)
            .eq("status", 1)
            .eq("deleted", 0)
            .orderByAsc("create_time")

        val pageResult = commentMapper.selectPage(pageObj, queryWrapper)

        val replyList = pageResult.records.map { comment ->
            CommentDTO(
                id = comment.id,
                postId = comment.postId,
                userId = comment.userId,
                userNickname = comment.userNickname,
                userAvatar = comment.userAvatar,
                parentId = comment.parentId,
                replyUserId = comment.replyUserId,
                replyNickname = comment.replyNickname,
                content = comment.content,
                images = parseImages(comment.images),
                likeCount = comment.likeCount,
                isLiked = false,
                replyCount = 0,
                createTime = comment.createTime
            )
        }

        return mapOf(
            "list" to replyList,
            "total" to pageResult.total,
            "page" to pageResult.current,
            "size" to pageResult.size
        )
    }

    @Transactional
    override fun likeComment(userId: Long, request: LikeCommentRequest): Boolean {
        val commentId = request.commentId ?: return false

        val comment = commentMapper.selectById(commentId) ?: return false

        // 查找是否已点赞
        val existingLike = commentLikeMapper.selectOne(
            QueryWrapper<CommentLike>()
                .eq("comment_id", commentId)
                .eq("user_id", userId)
                .eq("deleted", 0)
        )

        return if (existingLike != null) {
            // 取消点赞
            commentLikeMapper.deleteById(existingLike.id!!) > 0 &&
                    updateLikeCount(commentId, comment.likeCount!! - 1)
        } else {
            // 点赞
            val commentLike = CommentLike(
                commentId = commentId,
                userId = userId
            )
            commentLikeMapper.insert(commentLike) > 0 &&
                    updateLikeCount(commentId, comment.likeCount!! + 1)
        }
    }

    private fun updateLikeCount(commentId: Long, count: Int): Boolean {
        val comment = commentMapper.selectById(commentId) ?: return false
        comment.likeCount = count
        return commentMapper.updateById(comment) > 0
    }

    private fun parseImages(imagesJson: String?): List<String>? {
        if (imagesJson.isNullOrEmpty()) return null

        return try {
            objectMapper.readValue(imagesJson, Array<String>::class.java).toList()
        } catch (e: Exception) {
            null
        }
    }
}
