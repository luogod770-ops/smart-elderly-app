package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.Complex
import org.apache.ibatis.annotations.Mapper

/**
 * 小区Mapper
 */
@Mapper
interface ComplexMapper : BaseMapper<Complex>
