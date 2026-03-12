# 智慧养老社区APP

## 项目简介

智慧养老社区APP是一款专为老年人设计的综合性社区服务平台,集成了智能设备管理、社区交流、紧急救助、会员服务等功能。

## 技术架构

### 前端 (Android)
- **语言**: Kotlin
- **最低SDK版本**: Android 7.0 (API 24)
- **目标SDK版本**: Android 14 (API 34)

### 后端
- **框架**: Spring Boot 3.2.1
- **数据库**: MySQL 8.0
- **缓存**: Redis
- **ORM**: MyBatis Plus + JPA

## 主要功能模块

### 1. 用户模块
- ✅ 手机号+短信验证码注册登录
- ✅ 账号密码登录
- ✅ 个人中心
- ✅ 头像上传
- ✅ 每日签到
- ✅ 在线时长监护

### 2. 房屋绑定模块
- ✅ 城市列表
- ✅ 社区列表
- ✅ 小区列表
- ✅ 楼栋列表
- ✅ 申请绑定房屋
- ✅ 重新申请
- ✅ 删除申请
- ✅ 我的房屋

### 3. 社区交流模块
- ✅ 版块管理
- ✅ 加入版块
- ✅ 我的版块
- ✅ 发布帖子
- ✅ 帖子列表
- ✅ 帖子详情
- ✅ 评论功能
- ✅ 转发功能
- ✅ 举报功能

### 4. 设备管理模块
- ✅ 手环绑定/解绑
- ✅ 设备列表
- ✅ 蓝牙连接 (乐心手环S6)
- ✅ 设备数据同步
- ✅ ctwing平台对接

### 5. 会员系统
- ✅ 会员套餐
- ✅ 创建订单
- ✅ 支付宝支付
- ✅ 微信支付
- ✅ 支付结果查询

### 6. SOS紧急救助
- ✅ 一键SOS
- ✅ 紧急联系人通知
- ✅ 位置上报
- ✅ 物业联动
- ✅ SOS历史记录

### 7. 老人模式
- ✅ 大字体
- ✅ 大按钮
- ✅ 简化界面
- ✅ 语音播报
- ✅ 快捷功能

### 8. IM通讯
- ✅ 腾讯IM集成
- ✅ 发送消息
- ✅ 发送位置
- ✅ 好友管理

### 9. 其他功能
- ✅ Banner轮播
- ✅ 文件上传(图片/视频/文件)
- ✅ 系统配置
- ✅ 版本更新

## 三方服务集成

| 服务 | 用途 |
|------|------|
| 阿里云短信 | 验证码、通知 |
| 支付宝 | 会员支付 |
| 微信支付 | 会员支付 |
| 腾讯IM | 即时通讯 |
| 腾讯地图 | 位置服务 |
| 阿里云OSS | 文件存储 |
| ctwing | 设备平台对接 |

## 项目结构

### Android端
```
app/
├── src/main/java/com/smartelderly/community/
│   ├── app/                    # Application类
│   ├── ui/                     # UI层
│   │   ├── activity/          # Activity
│   │   ├── fragment/          # Fragment
│   │   └── adapter/           # 适配器
│   ├── data/                   # 数据层
│   │   ├── model/             # 数据模型
│   │   ├── network/           # 网络
│   │   ├── repository/        # 数据仓库
│   │   └── room/              # 数据库
│   ├── viewmodel/              # ViewModel
│   ├── service/                # 服务
│   ├── utils/                  # 工具类
│   └── receiver/               # 广播接收器
└── res/                        # 资源文件
```

### 后端
```
backend/
├── src/main/java/com/smartelderly/community/
│   ├── controller/             # 控制器
│   ├── service/                # 服务层
│   ├── mapper/                 # 数据访问
│   ├── entity/                 # 实体类
│   ├── dto/                    # 数据传输对象
│   ├── config/                 # 配置类
│   ├── common/                 # 公共类
│   └── utils/                  # 工具类
└── src/main/resources/          # 配置文件
```

## 开发环境要求

### Android开发
- JDK 17
- Android Studio Hedgehog或更高版本
- Android SDK 34
- Gradle 8.0+

### 后端开发
- JDK 17
- Maven 3.8+
- MySQL 8.0+
- Redis 6.0+

## 配置说明

### Android配置
1. 修改`app/build.gradle.kts`中的三方服务配置:
   ```kotlin
   buildConfigField("String", "API_BASE_URL", "\"your_server_url\"")
   buildConfigField("String", "ALIYUN_SMS_ACCESS_KEY_ID", "\"your_key\"")
   // ...其他配置
   ```

2. 修改`AndroidManifest.xml`中的包名和签名配置

### 后端配置
1. 修改`application.yml`中的数据库连接:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/smart_elderly
       username: root
       password: your_password
   ```

2. 配置三方服务密钥:
   ```yaml
   aliyun:
     sms:
       accessKeyId: your_key
       accessKeySecret: your_secret
   # ...其他配置
   ```

## 构建APK

### 开发版
```bash
./gradlew assembleDebug
```

### 发布版
```bash
./gradlew assembleRelease
```

APK输出位置: `app/build/outputs/apk/`

## 启动后端服务

```bash
cd backend
./gradlew bootRun
```

或使用IDE直接运行`SmartElderlyCommunityApplication`

## 数据库初始化

1. 创建数据库:
```sql
CREATE DATABASE smart_elderly CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 启动后端服务,会自动创建表结构

## 功能演示

### 登录流程
1. 打开APP
2. 输入手机号
3. 获取并输入验证码
4. 登录成功

### 绑定房屋
1. 进入"我的房屋"
2. 选择城市 → 社区 → 小区 → 楼栋
3. 填写房号信息
4. 提交申请

### SOS求助
1. 点击主页SOS按钮(老人模式为大红色按钮)
2. 确认求助
3. 自动发送位置和求助信息
4. 等待救援

## 注意事项

1. **权限申请**: APP需要位置、蓝牙、相机、存储等权限
2. **老人模式**: 针对老年人优化了UI和交互
3. **蓝牙连接**: 需要开启蓝牙并授权
4. **支付功能**: 需要配置支付宝和微信商户信息
5. **短信服务**: 需要配置阿里云短信服务

## 版本更新日志

### v1.0.0 (2026-03-12)
- 初始版本发布
- 完成所有核心功能开发

## 技术支持

- 开发团队: CodeBuddy AI
- 项目地址: https://github.com/your-repo/smart-elderly-community
- 技术文档: https://docs.smart-elderly.com

## 许可证

本项目仅供学习和交流使用
