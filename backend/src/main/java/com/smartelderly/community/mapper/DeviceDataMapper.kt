package com.smartelderly.community.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.smartelderly.community.entity.DeviceData
import org.apache.ibatis.annotations.Mapper

/**
 * 设备数据Mapper
 */
@Mapper
interface DeviceDataMapper : BaseMapper<DeviceData>
