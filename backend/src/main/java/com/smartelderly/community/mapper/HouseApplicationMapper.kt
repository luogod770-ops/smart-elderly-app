package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.HouseApplication
import org.apache.ibatis.annotations.Mapper

/**
 * 房屋申请Mapper
 */
@Mapper
interface HouseApplicationMapper : BaseMapper<HouseApplication>
