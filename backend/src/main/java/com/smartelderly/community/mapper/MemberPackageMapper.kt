package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.MemberPackage
import org.apache.ibatis.annotations.Mapper

/**
 * 会员套餐Mapper
 */
@Mapper
interface MemberPackageMapper : BaseMapper<MemberPackage>
