package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.SystemConfig
import org.apache.ibatis.annotations.Mapper

/**
 * 系统配置Mapper
 */
@Mapper
interface SystemConfigMapper : BaseMapper<SystemConfig>
