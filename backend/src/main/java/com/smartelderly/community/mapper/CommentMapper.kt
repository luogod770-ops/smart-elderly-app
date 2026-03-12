package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.Comment
import org.apache.ibatis.annotations.Mapper

/**
 * 评论Mapper
 */
@Mapper
interface CommentMapper : BaseMapper<Comment>
