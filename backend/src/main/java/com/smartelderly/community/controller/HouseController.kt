package com.smartelderly.community.controller

import com.smartelderly.community.common.Result
import com.smartelderly.community.entity.*
import com.smartelderly.community.service.interface.HouseService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 房屋管理控制器
 */
@Tag(name = "房屋管理", description = "房屋绑定相关接口")
@RestController
@RequestMapping("/house")
class HouseController @Autowired constructor(
    private val houseService: HouseService
) {

    @Operation(summary = "获取城市列表")
    @GetMapping("/city/list")
    fun getCityList(): Result<List<City>> {
        return Result.success(houseService.getCityList())
    }

    @Operation(summary = "获取社区列表")
    @GetMapping("/community/list")
    fun getCommunityList(@RequestParam cityId: Long): Result<List<Community>> {
        return Result.success(houseService.getCommunityList(cityId))
    }

    @Operation(summary = "获取小区列表")
    @GetMapping("/complex/list")
    fun getComplexList(@RequestParam communityId: Long): Result<List<Complex>> {
        return Result.success(houseService.getComplexList(communityId))
    }

    @Operation(summary = "获取楼栋列表")
    @GetMapping("/building/list")
    fun getBuildingList(@RequestParam complexId: Long): Result<List<Building>> {
        return Result.success(houseService.getBuildingList(complexId))
    }

    @Operation(summary = "申请绑定房屋")
    @PostMapping("/bind/apply")
    fun applyBindHouse(@RequestBody application: HouseApplication): Result<Boolean> {
        return Result.success(houseService.applyBindHouse(application))
    }

    @Operation(summary = "重新申请")
    @PostMapping("/bind/reapply")
    fun reapply(@RequestParam applicationId: Long): Result<Boolean> {
        return Result.success(houseService.reapply(applicationId))
    }

    @Operation(summary = "删除申请")
    @DeleteMapping("/bind/application/{applicationId}")
    fun deleteApplication(@PathVariable applicationId: Long): Result<Boolean> {
        return Result.success(houseService.deleteApplication(applicationId))
    }

    @Operation(summary = "我的房屋列表")
    @GetMapping("/my")
    fun getMyHouses(@RequestHeader("userId") userId: Long): Result<List<House>> {
        return Result.success(houseService.getMyHouses(userId))
    }

    @Operation(summary = "我的申请列表")
    @GetMapping("/my/applications")
    fun getMyApplications(@RequestHeader("userId") userId: Long): Result<List<HouseApplication>> {
        return Result.success(houseService.getMyApplications(userId))
    }

    @Operation(summary = "审核申请")
    @PostMapping("/audit")
    fun auditApplication(
        @RequestParam applicationId: Long,
        @RequestParam status: Int,
        @RequestParam(required = false) opinion: String?
    ): Result<Boolean> {
        return Result.success(houseService.auditApplication(applicationId, status, opinion ?: ""))
    }
}
