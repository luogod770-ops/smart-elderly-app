package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.UserMember
import org.apache.ibatis.annotations.Mapper

/**
 * 用户会员Mapper
 */
@Mapper
interface UserMemberMapper : BaseMapper<UserMember>
