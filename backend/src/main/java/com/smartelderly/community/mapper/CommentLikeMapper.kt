package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.CommentLike
import org.apache.ibatis.annotations.Mapper

/**
 * 评论点赞Mapper
 */
@Mapper
interface CommentLikeMapper : BaseMapper<CommentLike>
