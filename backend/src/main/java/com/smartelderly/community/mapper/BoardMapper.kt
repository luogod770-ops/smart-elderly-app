package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.Board
import org.apache.ibatis.annotations.Mapper

/**
 * 版块Mapper
 */
@Mapper
interface BoardMapper : BaseMapper<Board>
