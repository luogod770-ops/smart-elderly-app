package com.smartelderly.community.service.interface

import com.smartelderly.community.entity.*

/**
 * 房屋服务接口
 */
interface HouseService {

    /**
     * 获取城市列表
     */
    fun getCityList(): List<City>

    /**
     * 获取社区列表
     */
    fun getCommunityList(cityId: Long): List<Community>

    /**
     * 获取小区列表
     */
    fun getComplexList(communityId: Long): List<Complex>

    /**
     * 获取楼栋列表
     */
    fun getBuildingList(complexId: Long): List<Building>

    /**
     * 申请绑定房屋
     */
    fun applyBindHouse(application: HouseApplication): Boolean

    /**
     * 重新申请
     */
    fun reapply(applicationId: Long): Boolean

    /**
     * 删除申请
     */
    fun deleteApplication(applicationId: Long): Boolean

    /**
     * 我的房屋列表
     */
    fun getMyHouses(userId: Long): List<House>

    /**
     * 我的申请列表
     */
    fun getMyApplications(userId: Long): List<HouseApplication>

    /**
     * 审核申请
     */
    fun auditApplication(applicationId: Long, status: Int, opinion: String): Boolean
}
