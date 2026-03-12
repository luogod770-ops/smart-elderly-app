package com.smartelderly.community.controller

import com.smartelderly.community.common.Result
import com.smartelderly.community.dto.*
import com.smartelderly.community.service.interface.CommentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 评论管理控制器
 */
@Tag(name = "评论管理", description = "评论相关接口")
@RestController
@RequestMapping("/comment")
class CommentController @Autowired constructor(
    private val commentService: CommentService
) {

    @Operation(summary = "创建评论")
    @PostMapping("/create")
    fun createComment(
        @RequestHeader userId: Long,
        @RequestBody request: CreateCommentRequest
    ): Result<Long?> {
        return Result.success(commentService.createComment(userId, request))
    }

    @Operation(summary = "删除评论")
    @DeleteMapping("/delete/{commentId}")
    fun deleteComment(
        @RequestHeader userId: Long,
        @PathVariable commentId: Long
    ): Result<Boolean> {
        return Result.success(commentService.deleteComment(commentId, userId))
    }

    @Operation(summary = "获取评论列表")
    @GetMapping("/list")
    fun getCommentList(
        @RequestHeader(value = "userId", required = false) userId: Long?,
        @RequestParam postId: Long,
        @RequestParam(value = "parentId", required = false) parentId: Long?,
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "20") size: Int
    ): Result<Map<String, Any>> {
        val request = CommentListRequest(
            postId = postId,
            parentId = parentId,
            page = page,
            size = size
        )
        return Result.success(commentService.getCommentList(userId, request))
    }

    @Operation(summary = "点赞/取消点赞评论")
    @PostMapping("/like")
    fun likeComment(
        @RequestHeader userId: Long,
        @RequestBody request: LikeCommentRequest
    ): Result<Boolean> {
        return Result.success(commentService.likeComment(userId, request))
    }

    @Operation(summary = "获取评论回复列表")
    @GetMapping("/replies")
    fun getCommentReplies(
        @RequestParam parentId: Long,
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int
    ): Result<Map<String, Any>> {
        return Result.success(commentService.getCommentReplies(parentId, page, size))
    }
}
