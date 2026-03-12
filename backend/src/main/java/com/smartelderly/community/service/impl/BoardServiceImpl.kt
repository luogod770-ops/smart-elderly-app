package com.smartelderly.community.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.smartelderly.community.dto.*
import com.smartelderly.community.entity.*
import com.smartelderly.community.mapper.*
import com.smartelderly.community.service.interface.BoardService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 版块服务实现
 */
@Service
class BoardServiceImpl @Autowired constructor(
    private val boardMapper: BoardMapper,
    private val boardMemberMapper: BoardMemberMapper,
    private val objectMapper: ObjectMapper
) : BoardService {

    override fun getBoardList(userId: Long?, request: BoardListRequest): List<BoardDTO> {
        val boards = boardMapper.selectList(
            QueryWrapper<Board>()
                .eq("status", request.status ?: 1)
                .eq("deleted", 0)
                .orderByAsc("sort")
        )

        // 获取用户已加入的版块
        val joinedBoardIds = if (userId != null) {
            boardMemberMapper.selectList(
                QueryWrapper<BoardMember>()
                    .eq("user_id", userId)
                    .eq("deleted", 0)
            ).map { it.boardId }
        } else {
            emptyList()
        }

        return boards.map { board ->
            BoardDTO(
                id = board.id,
                name = board.name,
                description = board.description,
                icon = board.icon,
                postCount = board.postCount,
                memberCount = board.memberCount,
                isJoined = joinedBoardIds.contains(board.id),
                createTime = board.createTime
            )
        }
    }

    override fun getMyBoards(userId: Long): List<BoardDTO> {
        val boardMembers = boardMemberMapper.selectList(
            QueryWrapper<BoardMember>()
                .eq("user_id", userId)
                .eq("deleted", 0)
        )

        val boardIds = boardMembers.mapNotNull { it.boardId }

        if (boardIds.isEmpty()) {
            return emptyList()
        }

        val boards = boardMapper.selectList(
            QueryWrapper<Board>()
                .`in`("id", boardIds)
                .eq("status", 1)
                .eq("deleted", 0)
                .orderByAsc("sort")
        )

        return boards.map { board ->
            BoardDTO(
                id = board.id,
                name = board.name,
                description = board.description,
                icon = board.icon,
                postCount = board.postCount,
                memberCount = board.memberCount,
                isJoined = true,
                createTime = board.createTime
            )
        }
    }

    @Transactional
    override fun joinBoard(userId: Long, request: JoinBoardRequest): Boolean {
        val boardId = request.boardId ?: return false

        // 检查版块是否存在
        val board = boardMapper.selectById(boardId)
            ?: return false

        // 检查是否已加入
        val existingMember = boardMemberMapper.selectOne(
            QueryWrapper<BoardMember>()
                .eq("board_id", boardId)
                .eq("user_id", userId)
                .eq("deleted", 0)
        )

        if (existingMember != null) {
            return true // 已加入
        }

        // 加入版块
        val boardMember = BoardMember(
            boardId = boardId,
            userId = userId
        )
        val result = boardMemberMapper.insert(boardMember) > 0

        // 更新版块成员数
        if (result) {
            updateMemberCount(boardId, 1)
        }

        return result
    }

    @Transactional
    override fun leaveBoard(userId: Long, boardId: Long): Boolean {
        val member = boardMemberMapper.selectOne(
            QueryWrapper<BoardMember>()
                .eq("board_id", boardId)
                .eq("user_id", userId)
                .eq("deleted", 0)
        ) ?: return false

        val result = boardMemberMapper.deleteById(member.id!!) > 0

        // 更新版块成员数
        if (result) {
            updateMemberCount(boardId, -1)
        }

        return result
    }

    override fun getBoardDetail(boardId: Long): BoardDTO? {
        val board = boardMapper.selectById(boardId) ?: return null

        return BoardDTO(
            id = board.id,
            name = board.name,
            description = board.description,
            icon = board.icon,
            postCount = board.postCount,
            memberCount = board.memberCount,
            isJoined = false,
            createTime = board.createTime
        )
    }

    override fun updatePostCount(boardId: Long, increment: Int): Boolean {
        val board = boardMapper.selectById(boardId) ?: return false
        board.postCount = (board.postCount ?: 0) + increment
        return boardMapper.updateById(board) > 0
    }

    override fun updateMemberCount(boardId: Long, increment: Int): Boolean {
        val board = boardMapper.selectById(boardId) ?: return false
        board.memberCount = (board.memberCount ?: 0) + increment
        return boardMapper.updateById(board) > 0
    }
}
