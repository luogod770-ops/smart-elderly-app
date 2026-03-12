package com.smartelderly.community.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.smartelderly.community.entity.*
import com.smartelderly.community.mapper.*
import com.smartelderly.community.service.interface.HouseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 房屋服务实现
 */
@Service
class HouseServiceImpl @Autowired constructor(
    private val cityMapper: CityMapper,
    private val communityMapper: CommunityMapper,
    private val complexMapper: ComplexMapper,
    private val buildingMapper: BuildingMapper,
    private val houseMapper: HouseMapper,
    private val houseApplicationMapper: HouseApplicationMapper
) : HouseService {

    override fun getCityList(): List<City> {
        return cityMapper.selectList(
            QueryWrapper<City>()
                .eq("status", 1)
                .eq("deleted", 0)
                .orderByAsc("sort")
        )
    }

    override fun getCommunityList(cityId: Long): List<Community> {
        return communityMapper.selectList(
            QueryWrapper<Community>()
                .eq("city_id", cityId)
                .eq("status", 1)
                .eq("deleted", 0)
                .orderByAsc("sort")
        )
    }

    override fun getComplexList(communityId: Long): List<Complex> {
        return complexMapper.selectList(
            QueryWrapper<Complex>()
                .eq("community_id", communityId)
                .eq("status", 1)
                .eq("deleted", 0)
                .orderByAsc("sort")
        )
    }

    override fun getBuildingList(complexId: Long): List<Building> {
        return buildingMapper.selectList(
            QueryWrapper<Building>()
                .eq("complex_id", complexId)
                .eq("status", 1)
                .eq("deleted", 0)
                .orderByAsc("sort")
        )
    }

    @Transactional
    override fun applyBindHouse(application: HouseApplication): Boolean {
        // 检查是否已有待审核申请
        val existingApplication = houseApplicationMapper.selectOne(
            QueryWrapper<HouseApplication>()
                .eq("user_id", application.userId)
                .eq("building_id", application.buildingId)
                .eq("room_number", application.roomNumber)
                .eq("status", 1)
                .eq("deleted", 0)
        )

        if (existingApplication != null) {
            return false
        }

        // 检查该房屋是否已被绑定
        val existingHouse = houseMapper.selectOne(
            QueryWrapper<House>()
                .eq("building_id", application.buildingId)
                .eq("room_number", application.roomNumber)
                .eq("status", 2)
                .eq("deleted", 0)
        )

        if (existingHouse != null) {
            return false
        }

        return houseApplicationMapper.insert(application) > 0
    }

    @Transactional
    override fun reapply(applicationId: Long): Boolean {
        val application = houseApplicationMapper.selectById(applicationId)
            ?: return false

        if (application.status == 2) {
            return false // 已通过的不需要重新申请
        }

        // 重置申请状态
        application.status = 1
        application.auditOpinion = null
        application.auditTime = null

        return houseApplicationMapper.updateById(application) > 0
    }

    override fun deleteApplication(applicationId: Long): Boolean {
        return houseApplicationMapper.deleteById(applicationId) > 0
    }

    override fun getMyHouses(userId: Long): List<House> {
        return houseMapper.selectList(
            QueryWrapper<House>()
                .eq("user_id", userId)
                .eq("status", 2)
                .eq("deleted", 0)
        )
    }

    override fun getMyApplications(userId: Long): List<HouseApplication> {
        return houseApplicationMapper.selectList(
            QueryWrapper<HouseApplication>()
                .eq("user_id", userId)
                .eq("deleted", 0)
                .orderByDesc("create_time")
        )
    }

    @Transactional
    override fun auditApplication(applicationId: Long, status: Int, opinion: String): Boolean {
        val application = houseApplicationMapper.selectById(applicationId)
            ?: return false

        // 更新申请状态
        application.status = status
        application.auditOpinion = opinion
        application.auditTime = java.time.LocalDateTime.now()

        val result = houseApplicationMapper.updateById(application) > 0

        // 如果审核通过,创建房屋记录
        if (result && status == 2) {
            val house = House(
                userId = application.userId,
                buildingId = application.buildingId,
                roomNumber = application.roomNumber,
                floor = application.floor,
                unit = application.unit,
                status = 2,
                isMain = 0
            )
            houseMapper.insert(house)
        }

        return result
    }
}
