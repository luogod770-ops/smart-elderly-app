package com.smartelderly.community.service.interface

import com.smartelderly.community.dto.*

/**
 * 版块服务接口
 */
interface BoardService {

    /**
     * 获取版块列表
     */
    fun getBoardList(userId: Long?, request: BoardListRequest): List<BoardDTO>

    /**
     * 获取我的版块列表
     */
    fun getMyBoards(userId: Long): List<BoardDTO>

    /**
     * 加入版块
     */
    fun joinBoard(userId: Long, request: JoinBoardRequest): Boolean

    /**
     * 退出版块
     */
    fun leaveBoard(userId: Long, boardId: Long): Boolean

    /**
     * 根据ID获取版块详情
     */
    fun getBoardDetail(boardId: Long): BoardDTO?

    /**
     * 更新版块帖子数
     */
    fun updatePostCount(boardId: Long, increment: Int): Boolean

    /**
     * 更新版块成员数
     */
    fun updateMemberCount(boardId: Long, increment: Int): Boolean
}
