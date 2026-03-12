package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.Banner
import org.apache.ibatis.annotations.Mapper

/**
 * Banner Mapper
 */
@Mapper
interface BannerMapper : BaseMapper<Banner>
