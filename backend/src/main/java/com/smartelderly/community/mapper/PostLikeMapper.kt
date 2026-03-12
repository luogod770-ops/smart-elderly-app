package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.PostLike
import org.apache.ibatis.annotations.Mapper

/**
 * 帖子点赞Mapper
 */
@Mapper
interface PostLikeMapper : BaseMapper<PostLike>
