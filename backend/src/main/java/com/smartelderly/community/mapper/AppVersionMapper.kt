package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.AppVersion
import org.apache.ibatis.annotations.Mapper

/**
 * APP版本Mapper
 */
@Mapper
interface AppVersionMapper : BaseMapper<AppVersion>
