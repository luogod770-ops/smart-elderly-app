package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.Device
import org.apache.ibatis.annotations.Mapper

/**
 * 设备Mapper
 */
@Mapper
interface DeviceMapper : BaseMapper<Device>
