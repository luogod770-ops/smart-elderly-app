package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.House
import org.apache.ibatis.annotations.Mapper

/**
 * 房屋Mapper
 */
@Mapper
interface HouseMapper : BaseMapper<House>
