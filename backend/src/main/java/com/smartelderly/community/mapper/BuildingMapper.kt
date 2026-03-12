package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.Building
import org.apache.ibatis.annotations.Mapper

/**
 * 楼栋Mapper
 */
@Mapper
interface BuildingMapper : BaseMapper<Building>
