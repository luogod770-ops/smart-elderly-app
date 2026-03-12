package com.smartelderly.community.controller

import com.smartelderly.community.common.Result
import com.smartelderly.community.dto.*
import com.smartelderly.community.service.interface.PostService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 帖子管理控制器
 */
@Tag(name = "帖子管理", description = "帖子相关接口")
@RestController
@RequestMapping("/post")
class PostController @Autowired constructor(
    private val postService: PostService
) {

    @Operation(summary = "发布帖子")
    @PostMapping("/create")
    fun createPost(
        @RequestHeader userId: Long,
        @RequestBody request: CreatePostRequest
    ): Result<Long?> {
        return Result.success(postService.createPost(userId, request))
    }

    @Operation(summary = "更新帖子")
    @PostMapping("/update")
    fun updatePost(
        @RequestHeader userId: Long,
        @RequestParam postId: Long,
        @RequestBody request: CreatePostRequest
    ): Result<Boolean> {
        return Result.success(postService.updatePost(postId, userId, request))
    }

    @Operation(summary = "删除帖子")
    @DeleteMapping("/delete/{postId}")
    fun deletePost(
        @RequestHeader userId: Long,
        @PathVariable postId: Long
    ): Result<Boolean> {
        return Result.success(postService.deletePost(postId, userId))
    }

    @Operation(summary = "获取帖子列表")
    @GetMapping("/list")
    fun getPostList(
        @RequestHeader(value = "userId", required = false) userId: Long?,
        @RequestParam(value = "boardId", required = false) boardId: Long?,
        @RequestParam(value = "userId_filter", required = false) userIdFilter: Long?,
        @RequestParam(value = "isTop", required = false) isTop: Int?,
        @RequestParam(value = "isEssence", required = false) isEssence: Int?,
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int
    ): Result<Map<String, Any>> {
        val request = PostListRequest(
            boardId = boardId,
            userId = userIdFilter,
            isTop = isTop,
            isEssence = isEssence,
            page = page,
            size = size
        )
        return Result.success(postService.getPostList(userId, request))
    }

    @Operation(summary = "获取帖子详情")
    @GetMapping("/detail/{postId}")
    fun getPostDetail(
        @RequestHeader(value = "userId", required = false) userId: Long?,
        @PathVariable postId: Long
    ): Result<PostDTO?> {
        return Result.success(postService.getPostDetail(postId, userId))
    }

    @Operation(summary = "点赞/取消点赞帖子")
    @PostMapping("/like")
    fun likePost(
        @RequestHeader userId: Long,
        @RequestBody request: LikePostRequest
    ): Result<Boolean> {
        return Result.success(postService.likePost(userId, request))
    }

    @Operation(summary = "转发帖子")
    @PostMapping("/share")
    fun sharePost(
        @RequestHeader userId: Long,
        @RequestBody request: SharePostRequest
    ): Result<Long?> {
        return Result.success(postService.sharePost(userId, request))
    }

    @Operation(summary = "举报帖子")
    @PostMapping("/report")
    fun reportPost(
        @RequestHeader userId: Long,
        @RequestBody request: ReportPostRequest
    ): Result<Boolean> {
        return Result.success(postService.reportPost(userId, request))
    }

    @Operation(summary = "置顶/取消置顶")
    @PostMapping("/toggleTop")
    fun toggleTop(@RequestParam postId: Long): Result<Boolean> {
        return Result.success(postService.toggleTop(postId))
    }

    @Operation(summary = "设置/取消精华")
    @PostMapping("/toggleEssence")
    fun toggleEssence(@RequestParam postId: Long): Result<Boolean> {
        return Result.success(postService.toggleEssence(postId))
    }
}
