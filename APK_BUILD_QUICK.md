# APK快速构建指南

## 📱 Android APK构建方法

### 前置条件
1. 安装JDK 17或更高版本
2. 安装Android Studio (包含Android SDK)
3. 配置ANDROID_HOME环境变量

### 方法一: 使用Android Studio (推荐)

#### 步骤:
1. **打开项目**
   ```
   打开Android Studio
   File → Open
   选择: c:/Users/jd/CodeBuddy/20260311175444
   等待Gradle同步完成
   ```

2. **构建Debug APK**
   ```
   点击菜单: Build → Build Bundle(s) / APK(s) → Build APK(s)
   等待构建完成(约2-5分钟)
   ```

3. **查找APK**
   ```
   构建完成后会自动提示打开位置
   或者手动打开:
   app/build/outputs/apk/debug/app-debug.apk
   ```

4. **构建Release APK** (如需要)
   ```
   先配置签名(见下方)
   点击菜单: Build → Generate Signed Bundle / APK
   选择APK
   使用签名证书签名
   ```

### 方法二: 使用命令行

#### Windows系统:
```bash
cd c:/Users/jd/CodeBuddy/20260311175444

# 构建Debug APK
gradlew.bat assembleDebug

# 构建Release APK
gradlew.bat assembleRelease
```

#### Linux/Mac系统:
```bash
cd /path/to/project

# 构建Debug APK
./gradlew assembleDebug

# 构建Release APK
./gradlew assembleRelease
```

### 方法三: 使用构建脚本

#### Windows:
```bash
cd c:/Users/jd/CodeBuddy/20260311175444
build_apk.bat
```

## 🔐 Release APK签名配置

### 1. 创建签名证书
```bash
keytool -genkey -v -keystore smart-elderly.keystore -alias smart-elderly -keyalg RSA -keysize 2048 -validity 10000
```

### 2. 配置签名
编辑 `app/build.gradle.kts`,添加:
```kotlin
android {
    signingConfigs {
        create("release") {
            storeFile = file("smart-elderly.keystore")
            storePassword = "your_store_password"
            keyAlias = "smart-elderly"
            keyPassword = "your_key_password"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
```

### 3. 构建已签名的APK
```bash
gradlew.bat assembleRelease
```

## 📦 APK输出位置

### Debug版本:
```
app/build/outputs/apk/debug/app-debug.apk
```

### Release版本:
```
app/build/outputs/apk/release/app-release-unsigned.apk  (未签名)
app/build/outputs/apk/release/app-release.apk          (已签名)
```

## ⚙️ 配置修改

### 1. 修改后端API地址
编辑 `app/build.gradle.kts`:
```kotlin
android {
    defaultConfig {
        buildConfigField("String", "API_BASE_URL", "\"http://your-server-ip:8080\"")
    }
}
```

### 2. 配置三方服务密钥
编辑 `app/build.gradle.kts`:
```kotlin
android {
    defaultConfig {
        buildConfigField("String", "ALIYUN_SMS_ACCESS_KEY_ID", "\"your_key\"")
        buildConfigField("String", "ALIPAY_APP_ID", "\"your_app_id\"")
        buildConfigField("String", "WECHAT_APP_ID", "\"your_app_id\"")
        buildConfigField("String", "TENCENT_IM_SDK_APP_ID", "\"your_sdk_app_id\"")
        buildConfigField("String", "TENCENT_MAP_KEY", "\"your_map_key\"")
    }
}
```

修改后需要重新构建APK。

## 🐛 常见问题

### 1. Gradle同步失败
**原因**: 网络问题或Gradle版本不匹配
**解决**:
- 检查网络连接
- 打开Gradle设置,使用本地Gradle
- 或配置Gradle镜像源

### 2. SDK版本不匹配
**原因**: Android SDK未安装
**解决**:
- 打开SDK Manager
- 安装Android 14 (API 34)
- 安装Android SDK Build-Tools 34.0.0

### 3. 构建失败: AGP版本
**原因**: Android Gradle Plugin版本问题
**解决**:
- 打开File → Project Structure → Project
- 检查AGP版本
- 保持与项目配置一致

### 4. 签名失败
**原因**: 证书路径错误或密码错误
**解决**:
- 检查keystore文件路径
- 确认密码正确
- 使用绝对路径

## 📱 安装APK

### 方法一: 直接安装
1. 将APK文件复制到Android设备
2. 在设备上点击APK文件
3. 按提示完成安装

### 方法二: 使用ADB
```bash
# 连接设备
adb devices

# 安装APK
adb install app-debug.apk

# 卸载应用
adb uninstall com.smartelderly.community
```

### 方法三: 使用Android Studio
1. 连接Android设备
2. 点击Run按钮
3. 选择设备
4. 自动安装并运行

## 🎯 构建验证

### 检查APK:
```bash
# 查看APK信息
aapt dump badging app-debug.apk

# 解压APK
unzip app-debug.apk -d app-debug
```

### 测试安装:
1. 在不同Android版本测试安装
2. 检查权限申请
3. 测试核心功能
4. 检查UI显示

## 📋 构建前检查清单

- [ ] JDK 17已安装
- [ ] Android SDK已安装
- [ ] Gradle已配置
- [ ] 后端API地址已配置
- [ ] 三方服务密钥已配置(可选)
- [ ] 签名证书已准备(Release)
- [ ] 网络连接正常
- [ ] 磁盘空间充足

## 🚀 快速命令参考

```bash
# 清理构建
gradlew.bat clean

# 构建Debug
gradlew.bat assembleDebug

# 构建Release
gradlew.bat assembleRelease

# 查看所有任务
gradlew.bat tasks

# 卸载设备应用
adb uninstall com.smartelderly.community

# 安装APK
adb install -r app-debug.apk

# 启动应用
adb shell am start -n com.smartelderly.community/.ui.activity.MainActivity
```

## 📞 技术支持

如遇问题,请参考:
- 项目文档: `c:/Users/jd/CodeBuddy/20260311175444/`
- 交付文档: `PROJECT_DELIVERY_COMPLETE.md`
- 构建指南: `APK_BUILD_GUIDE.md`

---

**最后更新**: 2026-03-12
