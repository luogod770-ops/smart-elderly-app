# 项目配置指南

## 快速开始

### 第一步: 配置后端数据库

1. **安装MySQL 8.0+**
   - 下载: https://dev.mysql.com/downloads/mysql/
   - 安装并设置root密码

2. **创建数据库**
```sql
CREATE DATABASE smart_elderly CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

3. **修改后端配置**
编辑 `backend/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/smart_elderly
    username: root
    password: 你的MySQL密码
  redis:
    host: localhost
    port: 6379
```

### 第二步: 配置三方服务

#### 阿里云短信
1. 登录阿里云控制台
2. 开通短信服务
3. 创建签名和模板
4. 获取AccessKey ID和Secret
5. 配置到 `application.yml` 和 `app/build.gradle.kts`

#### 支付宝
1. 登录支付宝开放平台
2. 创建应用
3. 配置密钥
4. 配置到 `application.yml`

#### 微信支付
1. 登录微信商户平台
2. 获取商户号和密钥
3. 配置到 `application.yml`

#### 腾云IM
1. 登录腾讯云控制台
2. 创建IM应用
3. 获取SDKAppID
4. 配置到 `app/build.gradle.kts`

#### 腾讯地图
1. 登录腾讯位置服务
2. 创建应用获取Key
3. 配置到 `app/build.gradle.kts`

### 第三步: 构建APK

**Windows:**
```bash
build.bat
```

**Mac/Linux:**
```bash
chmod +x gradlew
./gradlew assembleDebug
```

生成的APK位于: `app/build/outputs/apk/debug/app-debug.apk`

### 第四步: 安装APK

将APK传输到Android设备并安装,或使用以下命令:
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 开发调试

### 启动后端服务
```bash
cd backend
./gradlew bootRun
```

### 连接Android Studio
1. 用Android Studio打开项目
2. 等待Gradle同步完成
3. 点击运行按钮或按Shift+F10

### 常见问题

**问题1: Gradle同步失败**
- 检查网络连接
- 配置阿里云Maven镜像
- 清理Gradle缓存: `./gradlew clean`

**问题2: 构建APK失败**
- 检查JDK版本是否为17
- 检查Android SDK是否完整
- 更新Gradle到最新版本

**问题3: 网络请求失败**
- 检查API_BASE_URL配置
- 检查网络权限
- 检查防火墙设置

**问题4: 蓝牙连接失败**
- 检查蓝牙权限
- 检查蓝牙是否开启
- 检查设备配对状态

## 部署到生产环境

### 后端部署
1. 打包: `cd backend && ./gradlew bootJar`
2. 上传jar到服务器
3. 运行: `java -jar smart-elderly-community-backend.jar`

### APK发布
1. 修改 `app/build.gradle.kts` 中的 `versionName` 和 `versionCode`
2. 构建Release版: `./gradlew assembleRelease`
3. 签名APK
4. 上传到应用市场

## 备案信息

需要在以下平台进行备案:
- APP备案: https://beian.miit.gov.cn/
- 域名备案: 同上

## 联系支持

如有问题请联系开发团队
