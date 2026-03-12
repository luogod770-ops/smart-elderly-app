# 智慧养老社区APP - 完整项目结构

## 项目文件清单

### 根目录文件
```
SmartElderlyCommunity/
├── settings.gradle.kts              # Gradle设置
├── build.gradle.kts                # 根构建配置
├── gradle.properties               # Gradle属性
├── build.bat                       # Windows构建脚本
├── build.sh                        # Linux/Mac构建脚本
├── README.md                       # 项目说明
├── PROJECT_SETUP_GUIDE.md          # 配置指南
└── PROJECT_STRUCTURE.md            # 本文档
```

## Android应用模块 (app/)

### 配置文件
```
app/
├── build.gradle.kts                # 应用构建配置
├── proguard-rules.pro              # 混淆规则
└── src/
    └── main/
        ├── AndroidManifest.xml     # 应用清单
        ├── java/com/smartelderly/community/
        │   └── app/
        │       └── MyApplication.kt
        └── res/
            ├── values/
            │   ├── strings.xml
            │   ├── colors.xml
            │   ├── themes.xml
            │   └── dimens.xml
            ├── drawable/
            ├── layout/
            ├── mipmap-hdpi/
            ├── mipmap-mdpi/
            ├── mipmap-xhdpi/
            ├── mipmap-xxhdpi/
            └── xml/
                ├── network_security_config.xml
                ├── backup_rules.xml
                └── data_extraction_rules.xml
```

### Java/Kotlin源代码结构
```
app/src/main/java/com/smartelderly/community/
│
├── app/
│   ├── MyApplication.kt           # Application类
│   └── Constants.kt                # 常量定义
│
├── ui/
│   ├── activity/
│   │   ├── MainActivity.kt         # 主页面
│   │   ├── LoginActivity.kt        # 登录页
│   │   ├── PersonalCenterActivity.kt  # 个人中心
│   │   ├── HouseBindingActivity.kt    # 房屋绑定
│   │   ├── CommunityActivity.kt       # 社区首页
│   │   ├── PostDetailActivity.kt       # 帖子详情
│   │   ├── PublishPostActivity.kt     # 发帖
│   │   ├── DeviceManagementActivity.kt # 设备管理
│   │   ├── MemberCenterActivity.kt     # 会员中心
│   │   ├── SOSActivity.kt             # SOS紧急救助
│   │   ├── ElderModeActivity.kt        # 老人模式
│   │   └── WebViewActivity.kt          # WebView通用页
│   │
│   ├── fragment/
│   │   ├── HomeFragment.kt        # 首页
│   │   ├── CommunityFragment.kt   # 社区
│   │   ├── DeviceFragment.kt       # 设备
│   │   ├── MemberFragment.kt      # 会员
│   │   └── MineFragment.kt        # 我的
│   │
│   └── adapter/
│       ├── BannerAdapter.kt       # Banner适配器
│       ├── PostAdapter.kt         # 帖子适配器
│       ├── CommentAdapter.kt      # 评论适配器
│       ├── BoardAdapter.kt        # 版块适配器
│       ├── HouseAdapter.kt        # 房屋适配器
│       ├── DeviceAdapter.kt       # 设备适配器
│       ├── PackageAdapter.kt      # 套餐适配器
│       ├── ContactAdapter.kt      # 联系人适配器
│       └── SOSHistoryAdapter.kt   # SOS记录适配器
│
├── data/
│   ├── model/
│   │   ├── BaseResponse.kt        # 基础响应
│   │   ├── User.kt                # 用户
│   │   ├── LoginResponse.kt       # 登录响应
│   │   ├── City.kt                # 城市
│   │   ├── Community.kt           # 社区
│   │   ├── Complex.kt             # 小区
│   │   ├── Building.kt            # 楼栋
│   │   ├── House.kt               # 房屋
│   │   ├── Board.kt               # 版块
│   │   ├── Post.kt                # 帖子
│   │   ├── Comment.kt             # 评论
│   │   ├── Device.kt              # 设备
│   │   ├── MemberPackage.kt       # 会员套餐
│   │   ├── Order.kt               # 订单
│   │   ├── Banner.kt              # Banner
│   │   ├── SystemConfig.kt        # 系统配置
│   │   ├── SOSRecord.kt           # SOS记录
│   │   └── CheckInRecord.kt       # 签到记录
│   │
│   ├── network/
│   │   ├── RetrofitClient.kt      # Retrofit客户端
│   │   ├── ApiService.kt          # API接口
│   │   ├── interceptor/
│   │   │   ├── HeaderInterceptor.kt   # 请求头拦截器
│   │   │   └── LogInterceptor.kt      # 日志拦截器
│   │   └── request/
│   │       ├── SendSmsRequest.kt
│   │       ├── SmsLoginRequest.kt
│   │       ├── PasswordLoginRequest.kt
│   │       ├── RegisterRequest.kt
│   │       ├── BindHouseRequest.kt
│   │       ├── PublishPostRequest.kt
│   │       ├── CommentRequest.kt
│   │       ├── BindDeviceRequest.kt
│   │       └── SOSRequest.kt
│   │
│   ├── repository/
│   │   ├── UserRepository.kt      # 用户仓库
│   │   ├── HouseRepository.kt     # 房屋仓库
│   │   ├── CommunityRepository.kt # 社区仓库
│   │   ├── DeviceRepository.kt    # 设备仓库
│   │   ├── MemberRepository.kt    # 会员仓库
│   │   └── SystemRepository.kt    # 系统仓库
│   │
│   ├── preference/
│   │   └── PreferenceManager.kt   # SharedPreferences管理
│   │
│   └── room/
│       ├── AppDatabase.kt         # 数据库
│       ├── dao/
│       │   ├── UserDao.kt
│       │   ├── PostDao.kt
│       │   └── DeviceDao.kt
│       └── entity/
│           ├── UserEntity.kt
│           ├── PostEntity.kt
│           └── DeviceEntity.kt
│
├── viewmodel/
│   ├── UserViewModel.kt           # 用户ViewModel
│   ├── LoginViewModel.kt          # 登录ViewModel
│   ├── HouseViewModel.kt          # 房屋ViewModel
│   ├── CommunityViewModel.kt      # 社区ViewModel
│   ├── DeviceViewModel.kt         # 设备ViewModel
│   ├── MemberViewModel.kt         # 会员ViewModel
│   └── SOSViewModel.kt            # SOS ViewModel
│
├── service/
│   ├── SOSForegroundService.kt    # SOS前台服务
│   ├── BluetoothService.kt        # 蓝牙服务
│   ├── LocationTrackingService.kt # 定位追踪服务
│   └── IMService.kt               # IM服务
│
├── receiver/
│   ├── SOSBroadcastReceiver.kt    # SOS广播接收器
│   └── BootReceiver.kt            # 开机广播
│
├── utils/
│   ├── LogUtil.kt                 # 日志工具
│   ├── ToastUtil.kt               # Toast工具
│   ├── SPUtil.kt                  # SP工具
│   ├── GsonUtil.kt                # Gson工具
│   ├── DeviceUtil.kt              # 设备工具
│   ├── DateUtil.kt                # 日期工具
│   ├── FileUtil.kt                # 文件工具
│   ├── ImageUtil.kt               # 图片工具
│   ├── PermissionUtil.kt          # 权限工具
│   └── CrashHandler.kt           # 崩溃处理
│
└── widget/
    ├── SOSButton.kt              # SOS按钮
    ├── ElderModeView.kt           # 老人模式视图
    └── LoadMoreView.kt            # 加载更多视图
```

## 后端模块 (backend/)

### 配置文件
```
backend/
├── build.gradle.kts               # 后端构建配置
└── src/main/
    ├── resources/
    │   ├── application.yml        # 应用配置
    │   ├── application-dev.yml   # 开发环境
    │   └── application-prod.yml  # 生产环境
    └── java/com/smartelderly/community/
        └── SmartElderlyCommunityApplication.kt
```

### Java源代码结构
```
backend/src/main/java/com/smartelderly/community/
│
├── SmartElderlyCommunityApplication.kt  # 启动类
│
├── controller/
│   ├── AuthController.kt           # 认证控制器
│   ├── UserController.kt           # 用户控制器
│   ├── HouseController.kt          # 房屋控制器
│   ├── CommunityController.kt      # 社区控制器
│   ├── PostController.kt           # 帖子控制器
│   ├── CommentController.kt       # 评论控制器
│   ├── DeviceController.kt        # 设备控制器
│   ├── MemberController.kt        # 会员控制器
│   ├── OrderController.kt         # 订单控制器
│   ├── PaymentController.kt       # 支付控制器
│   ├── SOSController.kt           # SOS控制器
│   ├── FileController.kt          # 文件控制器
│   └── SystemController.kt        # 系统控制器
│
├── service/
│   ├── impl/
│   │   ├── UserServiceImpl.kt
│   │   ├── HouseServiceImpl.kt
│   │   ├── CommunityServiceImpl.kt
│   │   ├── PostServiceImpl.kt
│   │   ├── CommentServiceImpl.kt
│   │   ├── DeviceServiceImpl.kt
│   │   ├── MemberServiceImpl.kt
│   │   ├── OrderServiceImpl.kt
│   │   ├── PaymentServiceImpl.kt
│   │   ├── SOSServiceImpl.kt
│   │   ├── FileServiceImpl.kt
│   │   └── SystemServiceImpl.kt
│   └── interface/
│       ├── UserService.kt
│       ├── HouseService.kt
│       ├── CommunityService.kt
│       ├── PostService.kt
│       ├── CommentService.kt
│       ├── DeviceService.kt
│       ├── MemberService.kt
│       ├── OrderService.kt
│       ├── PaymentService.kt
│       ├── SOSService.kt
│       ├── FileService.kt
│       └── SystemService.kt
│
├── mapper/
│   ├── UserMapper.kt
│   ├── CityMapper.kt
│   ├── CommunityMapper.kt
│   ├── ComplexMapper.kt
│   ├── BuildingMapper.kt
│   ├── HouseMapper.kt
│   ├── BoardMapper.kt
│   ├── PostMapper.kt
│   ├── CommentMapper.kt
│   ├── DeviceMapper.kt
│   ├── MemberPackageMapper.kt
│   ├── OrderMapper.kt
│   ├── BannerMapper.kt
│   ├── SystemConfigMapper.kt
│   ├── SOSRecordMapper.kt
│   ├── CheckInRecordMapper.kt
│   └── ContactMapper.kt
│
├── entity/
│   ├── User.kt
│   ├── City.kt
│   ├── Community.kt
│   ├── Complex.kt
│   ├── Building.kt
│   ├── House.kt
│   ├── HouseApplication.kt
│   ├── Board.kt
│   ├── Post.kt
│   ├── Comment.kt
│   ├── Device.kt
│   ├── MemberPackage.kt
│   ├── Order.kt
│   ├── Payment.kt
│   ├── Banner.kt
│   ├── SystemConfig.kt
│   ├── SOSRecord.kt
│   ├── CheckInRecord.kt
│   ├── Contact.kt
│   ├── AppVersion.kt
│   └── BaseEntity.kt
│
├── dto/
│   ├── request/
│   │   ├── LoginRequest.kt
│   │   ├── RegisterRequest.kt
│   │   ├── UpdateUserRequest.kt
│   │   ├── BindHouseRequest.kt
│   │   ├── PublishPostRequest.kt
│   │   ├── CommentRequest.kt
│   │   ├── BindDeviceRequest.kt
│   │   ├── CreateOrderRequest.kt
│   │   ├── PaymentRequest.kt
│   │   └── SOSRequest.kt
│   └── response/
│       ├── LoginResponse.kt
│       ├── UserInfoResponse.kt
│       ├── PostDetailResponse.kt
│       └── PaymentResponse.kt
│
├── config/
│   ├── SecurityConfig.kt           # 安全配置
│   ├── RedisConfig.kt             # Redis配置
│   ├── SwaggerConfig.kt           # Swagger配置
│   ├── JwtConfig.kt               # JWT配置
│   ├── MybatisPlusConfig.kt       # MyBatis Plus配置
│   ├── CorsConfig.kt              # 跨域配置
│   ├── AliyunOSSConfig.kt         # 阿里云OSS配置
│   ├── AliyunSmsConfig.kt         # 阿里云短信配置
│   ├── WechatPayConfig.kt         # 微信支付配置
│   ├── AlipayConfig.kt            # 支付宝配置
│   ├── TencentIMConfig.kt         # 腾讯IM配置
│   └── CtwingConfig.kt            # ctwing配置
│
├── common/
│   ├── Result.kt                  # 统一响应
│   ├── ResultCode.kt              # 响应码
│   ├── BusinessException.kt       # 业务异常
│   ├── GlobalExceptionHandler.kt  # 全局异常处理
│   ├── JwtUtil.kt                 # JWT工具
│   ├── PageResult.kt              # 分页结果
│   └── SecurityUtil.kt            # 安全工具
│
├── utils/
│   ├── AliyunSmsUtil.kt           # 阿里云短信工具
│   ├── AliyunOssUtil.kt           # 阿里云OSS工具
│   ├── AlipayUtil.kt              # 支付宝工具
│   ├── WechatPayUtil.kt           # 微信支付工具
│   ├── CtwingUtil.kt              # ctwing工具
│   ├── IpUtil.kt                  # IP工具
│   └── DateUtil.kt                # 日期工具
│
└── security/
    ├── JwtAuthenticationFilter.kt # JWT认证过滤器
    ├── JwtAuthenticationEntryPoint.kt
    ├── UserDetailsServiceImpl.kt
    └── UserDetailsImpl.kt
```

## 数据库表结构

```
# 用户相关
user                           # 用户表
check_in_record                # 签到记录
contact                        # 联系人

# 房屋相关
city                           # 城市表
community                      # 社区表
complex                        # 小区表
building                       # 楼栋表
house                          # 房屋表
house_application              # 房屋申请表

# 社区相关
board                          # 版块表
post                          # 帖子表
comment                       # 评论表
post_like                     # 帖子点赞
post_report                   # 帖子举报

# 设备相关
device                        # 设备表
device_data                   # 设备数据
device_alert                 # 设备告警

# 会员相关
member_package               # 会员套餐
order                        # 订单表
payment                      # 支付记录

# SOS相关
sos_record                   # SOS记录

# 系统相关
banner                       # Banner表
system_config                # 系统配置
app_version                  # APP版本
```

## API接口文档

访问地址: http://localhost:8080/api/doc.html

### 认证接口
- POST /api/auth/sms/code - 发送短信验证码
- POST /api/auth/login/sms - 短信登录
- POST /api/auth/login/password - 密码登录
- POST /api/auth/logout - 退出登录

### 用户接口
- GET /api/user/info - 获取用户信息
- PUT /api/user/info - 更新用户信息
- POST /api/user/checkin - 每日签到

### 房屋接口
- GET /api/house/city/list - 城市列表
- GET /api/house/community/list - 社区列表
- GET /api/house/complex/list - 小区列表
- GET /api/house/building/list - 楼栋列表
- POST /api/house/bind/apply - 申请绑定
- GET /api/house/my - 我的房屋

### 社区接口
- GET /api/community/board/list - 版块列表
- POST /api/community/board/join - 加入版块
- GET /api/community/post/list - 帖子列表
- POST /api/community/post/publish - 发布帖子
- GET /api/community/post/{id} - 帖子详情
- POST /api/community/post/comment - 评论

### 设备接口
- POST /api/device/bind - 绑定设备
- DELETE /api/device/unbind/{id} - 解绑设备
- GET /api/device/my - 我的设备
- GET /api/device/data/{id} - 设备数据

### 会员接口
- GET /api/member/package/list - 套餐列表
- POST /api/member/order/create - 创建订单
- POST /api/payment/alipay - 支付宝支付
- POST /api/payment/wechat - 微信支付

### SOS接口
- POST /api/sos/send - 发送SOS
- GET /api/sos/history - SOS历史

### 系统接口
- GET /api/system/banner/list - Banner列表
- GET /api/system/config - 系统配置
- GET /api/system/checkUpdate - 检查更新

### 文件接口
- POST /api/file/upload/image - 上传图片
- POST /api/file/upload/video - 上传视频
- POST /api/file/upload/file - 上传文件

## 功能模块详细说明

### 1. 用户模块
- 注册登录:支持短信验证码和密码两种方式
- 个人中心:查看和编辑个人信息、头像上传
- 每日签到:每日签到获取积分
- 在线时长:记录用户在线时长

### 2. 房屋绑定模块
- 多级联动:城市→社区→小区→楼栋
- 申请流程:提交→审核→绑定
- 房屋管理:查看已绑定房屋、删除申请

### 3. 社区交流模块
- 版块管理:查看版块、加入版块
- 帖子功能:发布、浏览、点赞、评论、转发、举报
- 内容支持:图文混排、视频、位置

### 4. 设备管理模块
- 设备绑定:绑定智能手环等设备
- 蓝牙连接:通过蓝牙连接乐心手环S6
- 数据同步:同步健康数据、运动数据
- 告警通知:设备告警推送

### 5. 会员系统
- 套餐展示:多种会员套餐
- 订单创建:选择套餐创建订单
- 多种支付:支持支付宝、微信支付
- 支付回调:处理支付结果

### 6. SOS紧急救助
- 一键求助:大按钮一键发送SOS
- 位置上报:自动发送当前位置
- 多方通知:通知联系人、物业
- 历史记录:记录所有SOS事件

### 7. 老人模式
- 大字体:适应老人视力
- 大按钮:方便老人操作
- 简化界面:去除复杂功能
- 语音播报:重要信息语音提示

### 8. IM通讯
- 即时消息:发送文字、图片、语音
- 位置共享:发送位置信息
- 好友管理:添加、删除好友
- 消息推送:离线消息推送

## 测试说明

### 单元测试
```bash
./gradlew test
```

### 集成测试
```bash
./gradlew integrationTest
```

### UI测试
```bash
./gradlew connectedAndroidTest
```

## 部署说明

### 环境要求
- JDK 17
- MySQL 8.0+
- Redis 6.0+
- Node.js 16+ (前端构建)

### 部署步骤
1. 修改配置文件
2. 构建后端jar包
3. 上传jar包到服务器
4. 启动服务
5. 配置Nginx反向代理
6. 配置SSL证书

## 维护说明

### 日志位置
- Android日志:adb logcat
- 后端日志:logs/smart-elderly.log

### 数据备份
- 数据库备份:每日自动备份
- 文件备份:OSS自动备份

### 监控告警
- 服务器监控:CPU、内存、磁盘
- 服务监控:接口响应时间、错误率
- 告警通知:邮件、短信、钉钉

## 版本历史

### v1.0.0 (2026-03-12)
- 初始版本
- 完成所有核心功能
