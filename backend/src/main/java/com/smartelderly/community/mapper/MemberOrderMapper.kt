package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.MemberOrder
import org.apache.ibatis.annotations.Mapper

/**
 * 会员订单Mapper
 */
@Mapper
interface MemberOrderMapper : BaseMapper<MemberOrder>
