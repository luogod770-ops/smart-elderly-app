package com.smartelderly.community.data.network

import com.smartelderly.community.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * API服务接口
 */
interface ApiService {

    // ==================== 用户相关 ====================

    /**
     * 发送短信验证码
     */
    @POST("/auth/sms/code")
    suspend fun sendSmsCode(@Body request: SendSmsRequest): BaseResponse<String>

    /**
     * 手机号登录
     */
    @POST("/auth/login/sms")
    suspend fun loginBySms(@Body request: SmsLoginRequest): BaseResponse<LoginResponse>

    /**
     * 账号密码登录
     */
    @POST("/auth/login/password")
    suspend fun loginByPassword(@Body request: PasswordLoginRequest): BaseResponse<LoginResponse>

    /**
     * 注册
     */
    @POST("/auth/register")
    suspend fun register(@Body request: RegisterRequest): BaseResponse<LoginResponse>

    /**
     * 退出登录
     */
    @POST("/auth/logout")
    suspend fun logout(@Header("Authorization") token: String): BaseResponse<Unit>

    /**
     * 刷新Token
     */
    @POST("/auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): BaseResponse<LoginResponse>

    /**
     * 获取用户信息
     */
    @GET("/user/info")
    suspend fun getUserInfo(@Header("Authorization") token: String): BaseResponse<User>

    /**
     * 更新用户信息
     */
    @PUT("/user/info")
    suspend fun updateUserInfo(
        @Header("Authorization") token: String,
        @Body request: UpdateUserRequest
    ): BaseResponse<User>

    /**
     * 上传头像
     */
    @Multipart
    @POST("/user/avatar")
    suspend fun uploadAvatar(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): BaseResponse<String>

    // ==================== 房屋相关 ====================

    /**
     * 获取城市列表
     */
    @GET("/house/city/list")
    suspend fun getCityList(): BaseResponse<List<City>>

    /**
     * 获取社区列表
     */
    @GET("/house/community/list")
    suspend fun getCommunityList(@Query("cityId") cityId: Long): BaseResponse<List<Community>>

    /**
     * 获取小区列表
     */
    @GET("/house/complex/list")
    suspend fun getComplexList(@Query("communityId") communityId: Long): BaseResponse<List<Complex>>

    /**
     * 获取楼栋列表
     */
    @GET("/house/building/list")
    suspend fun getBuildingList(@Query("complexId") complexId: Long): BaseResponse<List<Building>>

    /**
     * 申请绑定房屋
     */
    @POST("/house/bind/apply")
    suspend fun applyBindHouse(
        @Header("Authorization") token: String,
        @Body request: BindHouseRequest
    ): BaseResponse<Unit>

    /**
     * 重新申请
     */
    @POST("/house/bind/reapply")
    suspend fun reapplyBindHouse(
        @Header("Authorization") token: String,
        @Query("applicationId") applicationId: Long
    ): BaseResponse<Unit>

    /**
     * 删除申请
     */
    @DELETE("/house/bind/application/{applicationId}")
    suspend fun deleteApplication(
        @Header("Authorization") token: String,
        @Path("applicationId") applicationId: Long
    ): BaseResponse<Unit>

    /**
     * 我的房屋列表
     */
    @GET("/house/my")
    suspend fun getMyHouses(@Header("Authorization") token: String): BaseResponse<List<House>>

    // ==================== 社区相关 ====================

    /**
     * 全部版块列表
     */
    @GET("/community/board/list")
    suspend fun getAllBoards(): BaseResponse<List<Board>>

    /**
     * 加入版块
     */
    @POST("/community/board/join")
    suspend fun joinBoard(
        @Header("Authorization") token: String,
        @Query("boardId") boardId: Long
    ): BaseResponse<Unit>

    /**
     * 我加入的版块
     */
    @GET("/community/board/my")
    suspend fun getMyBoards(@Header("Authorization") token: String): BaseResponse<List<Board>>

    /**
     * 版块帖子列表
     */
    @GET("/community/post/list")
    suspend fun getPostList(
        @Query("boardId") boardId: Long,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): BaseResponse<PageResponse<Post>>

    /**
     * 发布帖子
     */
    @Multipart
    @POST("/community/post/publish")
    suspend fun publishPost(
        @Header("Authorization") token: String,
        @Part("request") request: RequestBody,
        @Part images: List<MultipartBody.Part>?
    ): BaseResponse<Post>

    /**
     * 帖子详情
     */
    @GET("/community/post/{postId}")
    suspend fun getPostDetail(
        @Header("Authorization") token: String,
        @Path("postId") postId: Long
    ): BaseResponse<PostDetail>

    /**
     * 评论帖子
     */
    @POST("/community/post/comment")
    suspend fun commentPost(
        @Header("Authorization") token: String,
        @Body request: CommentRequest
    ): BaseResponse<Comment>

    /**
     * 转发帖子
     */
    @POST("/community/post/repost")
    suspend fun repostPost(
        @Header("Authorization") token: String,
        @Body request: RepostRequest
    ): BaseResponse<Unit>

    /**
     * 举报帖子
     */
    @POST("/community/post/report")
    suspend fun reportPost(
        @Header("Authorization") token: String,
        @Body request: ReportRequest
    ): BaseResponse<Unit>

    // ==================== 会员相关 ====================

    /**
     * 会员套餐列表
     */
    @GET("/member/package/list")
    suspend fun getPackageList(): BaseResponse<List<MemberPackage>>

    /**
     * 创建订单
     */
    @POST("/member/order/create")
    suspend fun createOrder(
        @Header("Authorization") token: String,
        @Body request: CreateOrderRequest
    ): BaseResponse<Order>

    /**
     * 支付宝支付
     */
    @POST("/payment/alipay")
    suspend fun alipay(
        @Header("Authorization") token: String,
        @Body request: PaymentRequest
    ): BaseResponse<String>

    /**
     * 微信支付
     */
    @POST("/payment/wechat")
    suspend fun wechatPay(
        @Header("Authorization") token: String,
        @Body request: PaymentRequest
    ): BaseResponse<Map<String, String>>

    /**
     * 支付结果查询
     */
    @GET("/payment/status/{orderId}")
    suspend fun getPaymentStatus(
        @Header("Authorization") token: String,
        @Path("orderId") orderId: String
    ): BaseResponse<PaymentStatus>

    // ==================== 设备相关 ====================

    /**
     * 绑定设备
     */
    @POST("/device/bind")
    suspend fun bindDevice(
        @Header("Authorization") token: String,
        @Body request: BindDeviceRequest
    ): BaseResponse<Device>

    /**
     * 解绑设备
     */
    @DELETE("/device/unbind/{deviceId}")
    suspend fun unbindDevice(
        @Header("Authorization") token: String,
        @Path("deviceId") deviceId: Long
    ): BaseResponse<Unit>

    /**
     * 我的设备列表
     */
    @GET("/device/my")
    suspend fun getMyDevices(@Header("Authorization") token: String): BaseResponse<List<Device>>

    /**
     * 设备数据
     */
    @GET("/device/data/{deviceId}")
    suspend fun getDeviceData(
        @Header("Authorization") token: String,
        @Path("deviceId") deviceId: Long
    ): BaseResponse<DeviceData>

    // ==================== SOS相关 ====================

    /**
     * 发送SOS求救
     */
    @POST("/sos/send")
    suspend fun sendSOS(
        @Header("Authorization") token: String,
        @Body request: SOSRequest
    ): BaseResponse<Unit>

    /**
     * SOS记录列表
     */
    @GET("/sos/history")
    suspend fun getSOSHistory(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): BaseResponse<PageResponse<SOSRecord>>

    // ==================== 系统相关 ====================

    /**
     * 获取Banner列表
     */
    @GET("/system/banner/list")
    suspend fun getBannerList(): BaseResponse<List<Banner>>

    /**
     * 获取系统配置
     */
    @GET("/system/config")
    suspend fun getSystemConfig(): BaseResponse<SystemConfig>

    /**
     * 检查更新
     */
    @GET("/system/checkUpdate")
    suspend fun checkUpdate(): BaseResponse<UpdateInfo>

    /**
     * 每日签到
     */
    @POST("/user/checkin")
    suspend fun checkIn(@Header("Authorization") token: String): BaseResponse<CheckInResult>

    /**
     * 签到记录
     */
    @GET("/user/checkin/history")
    suspend fun getCheckInHistory(
        @Header("Authorization") token: String,
        @Query("year") year: Int,
        @Query("month") month: Int
    ): BaseResponse<List<CheckInRecord>>

    // ==================== 文件上传 ====================

    /**
     * 上传图片
     */
    @Multipart
    @POST("/file/upload/image")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): BaseResponse<UploadResult>

    /**
     * 上传视频
     */
    @Multipart
    @POST("/file/upload/video")
    suspend fun uploadVideo(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): BaseResponse<UploadResult>

    /**
     * 上传文件
     */
    @Multipart
    @POST("/file/upload/file")
    suspend fun uploadFile(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): BaseResponse<UploadResult>
}
