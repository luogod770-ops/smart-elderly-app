package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.Post
import org.apache.ibatis.annotations.Mapper

/**
 * 帖子Mapper
 */
@Mapper
interface PostMapper : BaseMapper<Post>
