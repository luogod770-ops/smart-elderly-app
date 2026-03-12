package com.smartelderly.community.controller

import com.smartelderly.community.common.Result
import com.smartelderly.community.dto.*
import com.smartelderly.community.service.interface.BoardService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 版块管理控制器
 */
@Tag(name = "版块管理", description = "社区版块相关接口")
@RestController
@RequestMapping("/board")
class BoardController @Autowired constructor(
    private val boardService: BoardService
) {

    @Operation(summary = "获取版块列表")
    @GetMapping("/list")
    fun getBoardList(
        @RequestHeader(value = "userId", required = false) userId: Long?,
        @RequestParam(value = "status", defaultValue = "1") status: Int
    ): Result<List<BoardDTO>> {
        val request = BoardListRequest(status = status)
        return Result.success(boardService.getBoardList(userId, request))
    }

    @Operation(summary = "获取我的版块列表")
    @GetMapping("/my")
    fun getMyBoards(@RequestHeader userId: Long): Result<List<BoardDTO>> {
        return Result.success(boardService.getMyBoards(userId))
    }

    @Operation(summary = "获取版块详情")
    @GetMapping("/detail/{boardId}")
    fun getBoardDetail(@PathVariable boardId: Long): Result<BoardDTO?> {
        return Result.success(boardService.getBoardDetail(boardId))
    }

    @Operation(summary = "加入版块")
    @PostMapping("/join")
    fun joinBoard(
        @RequestHeader userId: Long,
        @RequestBody request: JoinBoardRequest
    ): Result<Boolean> {
        return Result.success(boardService.joinBoard(userId, request))
    }

    @Operation(summary = "退出版块")
    @PostMapping("/leave")
    fun leaveBoard(
        @RequestHeader userId: Long,
        @RequestParam boardId: Long
    ): Result<Boolean> {
        return Result.success(boardService.leaveBoard(userId, boardId))
    }
}
