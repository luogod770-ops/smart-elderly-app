package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.BoardMember
import org.apache.ibatis.annotations.Mapper

/**
 * 版块成员Mapper
 */
@Mapper
interface BoardMemberMapper : BaseMapper<BoardMember>
