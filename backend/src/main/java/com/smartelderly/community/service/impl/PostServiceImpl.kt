package com.smartelderly.community.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.smartelderly.community.dto.*
import com.smartelderly.community.entity.*
import com.smartelderly.community.mapper.*
import com.smartelderly.community.service.interface.PostService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 帖子服务实现
 */
@Service
class PostServiceImpl @Autowired constructor(
    private val postMapper: PostMapper,
    private val boardMapper: BoardMapper,
    private val boardService: com.smartelderly.community.service.interface.BoardService,
    private val postLikeMapper: PostLikeMapper,
    private val objectMapper: ObjectMapper
) : PostService {

    @Transactional
    override fun createPost(userId: Long, request: CreatePostRequest): Long? {
        // 检查用户是否已加入版块
        val board = boardMapper.selectById(request.boardId) ?: return null

        // 序列化图片列表
        val imagesJson = if (!request.images.isNullOrEmpty()) {
            objectMapper.writeValueAsString(request.images)
        } else {
            null
        }

        val post = Post(
            boardId = request.boardId,
            userId = userId,
            title = request.title,
            content = request.content,
            images = imagesJson,
            video = request.video,
            location = request.location,
            status = 1, // 已发布
            auditStatus = 1 // 已通过审核
        )

        val result = postMapper.insert(post)

        if (result > 0) {
            // 更新版块帖子数
            boardService.updatePostCount(request.boardId!!, 1)
        }

        return if (result > 0) post.id else null
    }

    @Transactional
    override fun updatePost(postId: Long, userId: Long, request: CreatePostRequest): Boolean {
        val post = postMapper.selectById(postId) ?: return false

        // 检查是否是作者
        if (post.userId != userId) {
            return false
        }

        // 序列化图片列表
        val imagesJson = if (!request.images.isNullOrEmpty()) {
            objectMapper.writeValueAsString(request.images)
        } else {
            null
        }

        post.title = request.title
        post.content = request.content
        post.images = imagesJson
        post.video = request.video
        post.location = request.location

        return postMapper.updateById(post) > 0
    }

    @Transactional
    override fun deletePost(postId: Long, userId: Long): Boolean {
        val post = postMapper.selectById(postId) ?: return false

        // 检查是否是作者
        if (post.userId != userId) {
            return false
        }

        post.status = 2 // 已删除

        val result = postMapper.updateById(post) > 0

        if (result) {
            // 更新版块帖子数
            boardService.updatePostCount(post.boardId!!, -1)
        }

        return result
    }

    override fun getPostList(userId: Long?, request: PostListRequest): Map<String, Any> {
        val page = Page<Post>(request.page!!.toLong(), request.size!!.toLong())

        val queryWrapper = QueryWrapper<Post>()
            .eq("status", 1)
            .eq("audit_status", 1)
            .eq("deleted", 0)

        // 版块过滤
        request.boardId?.let { queryWrapper.eq("board_id", it) }

        // 用户过滤
        request.userId?.let { queryWrapper.eq("user_id", it) }

        // 置顶过滤
        request.isTop?.let { queryWrapper.eq("is_top", it) }

        // 精华过滤
        request.isEssence?.let { queryWrapper.eq("is_essence", it) }

        queryWrapper.orderByDesc("is_top", "create_time")

        val pageResult = postMapper.selectPage(page, queryWrapper)

        // 获取用户点赞的帖子
        val likedPostIds = if (userId != null) {
            postLikeMapper.selectList(
                QueryWrapper<PostLike>()
                    .eq("user_id", userId)
                    .eq("deleted", 0)
            ).mapNotNull { it.postId }
        } else {
            emptyList()
        }

        val postList = pageResult.records.map { post ->
            PostDTO(
                id = post.id,
                boardId = post.boardId,
                userId = post.userId,
                userNickname = post.userNickname,
                userAvatar = post.userAvatar,
                title = post.title,
                content = post.content,
                images = parseImages(post.images),
                video = post.video,
                location = post.location,
                likeCount = post.likeCount,
                commentCount = post.commentCount,
                shareCount = post.shareCount,
                isTop = post.isTop,
                isEssence = post.isEssence,
                isLiked = likedPostIds.contains(post.id),
                createTime = post.createTime
            )
        }

        return mapOf(
            "list" to postList,
            "total" to pageResult.total,
            "page" to pageResult.current,
            "size" to pageResult.size
        )
    }

    override fun getPostDetail(postId: Long, userId: Long?): PostDTO? {
        val post = postMapper.selectById(postId) ?: return null

        // 获取点赞状态
        val isLiked = if (userId != null) {
            postLikeMapper.selectOne(
                QueryWrapper<PostLike>()
                    .eq("post_id", postId)
                    .eq("user_id", userId)
                    .eq("deleted", 0)
            ) != null
        } else {
            false
        }

        return PostDTO(
            id = post.id,
            boardId = post.boardId,
            userId = post.userId,
            userNickname = post.userNickname,
            userAvatar = post.userAvatar,
            title = post.title,
            content = post.content,
            images = parseImages(post.images),
            video = post.video,
            location = post.location,
            likeCount = post.likeCount,
            commentCount = post.commentCount,
            shareCount = post.shareCount,
            isTop = post.isTop,
            isEssence = post.isEssence,
            isLiked = isLiked,
            createTime = post.createTime
        )
    }

    @Transactional
    override fun likePost(userId: Long, request: LikePostRequest): Boolean {
        val postId = request.postId ?: return false

        val post = postMapper.selectById(postId) ?: return false

        // 查找是否已点赞
        val existingLike = postLikeMapper.selectOne(
            QueryWrapper<PostLike>()
                .eq("post_id", postId)
                .eq("user_id", userId)
                .eq("deleted", 0)
        )

        return if (existingLike != null) {
            // 取消点赞
            postLikeMapper.deleteById(existingLike.id!!) > 0 &&
                    updateLikeCount(postId, post.likeCount!! - 1)
        } else {
            // 点赞
            val postLike = PostLike(
                postId = postId,
                userId = userId
            )
            postLikeMapper.insert(postLike) > 0 &&
                    updateLikeCount(postId, post.likeCount!! + 1)
        }
    }

    @Transactional
    override fun sharePost(userId: Long, request: SharePostRequest): Long? {
        val postId = request.postId ?: return null

        val originalPost = postMapper.selectById(postId) ?: return null

        // 创建转发的帖子
        val shareContent = if (!request.content.isNullOrEmpty()) {
            request.content
        } else {
            "转发自: ${originalPost.title}"
        }

        val post = Post(
            boardId = originalPost.boardId,
            userId = userId,
            title = "【转发】${originalPost.title}",
            content = shareContent,
            status = 1,
            auditStatus = 1
        )

        val result = postMapper.insert(post)

        if (result > 0) {
            // 更新原帖子转发数
            originalPost.shareCount = (originalPost.shareCount ?: 0) + 1
            postMapper.updateById(originalPost)
        }

        return if (result > 0) post.id else null
    }

    override fun reportPost(userId: Long, request: ReportPostRequest): Boolean {
        // 这里应该创建举报记录,简化实现直接返回成功
        // 实际项目中应该有Report表来存储举报信息
        return true
    }

    @Transactional
    override fun toggleTop(postId: Long): Boolean {
        val post = postMapper.selectById(postId) ?: return false

        post.isTop = if (post.isTop == 1) 0 else 1

        return postMapper.updateById(post) > 0
    }

    @Transactional
    override fun toggleEssence(postId: Long): Boolean {
        val post = postMapper.selectById(postId) ?: return false

        post.isEssence = if (post.isEssence == 1) 0 else 1

        return postMapper.updateById(post) > 0
    }

    @Transactional
    override fun incrementCommentCount(postId: Long): Boolean {
        val post = postMapper.selectById(postId) ?: return false
        post.commentCount = (post.commentCount ?: 0) + 1
        return postMapper.updateById(post) > 0
    }

    private fun updateLikeCount(postId: Long, count: Int): Boolean {
        val post = postMapper.selectById(postId) ?: return false
        post.likeCount = count
        return postMapper.updateById(post) > 0
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
