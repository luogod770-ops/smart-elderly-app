package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.Community
import org.apache.ibatis.annotations.Mapper

/**
 * 社区Mapper
 */
@Mapper
interface CommunityMapper : BaseMapper<Community>
