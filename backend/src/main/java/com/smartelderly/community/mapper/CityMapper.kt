package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.City
import org.apache.ibatis.annotations.Mapper

/**
 * 城市Mapper
 */
@Mapper
interface CityMapper : BaseMapper<City>
