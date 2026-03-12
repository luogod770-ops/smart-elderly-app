# APK构建指南

## 项目状态

✅ **已完成基础架构搭建**
- Android项目结构完整
- Spring Boot后端基础配置
- 核心Activity和Fragment已创建
- 登录模块基础实现
- SOS紧急求助功能实现

⚠️ **需要继续开发的功能**
- 完整的网络请求对接
- 房屋绑定模块
- 社区交流模块
- 设备管理和蓝牙对接
- 会员系统和支付集成
- 三方服务集成

## 快速构建APK

### 方法一:使用命令行

**Windows:**
```bash
build.bat
```

**Linux/Mac:**
```bash
chmod +x gradlew
./gradlew assembleDebug
```

### 方法二:使用Android Studio

1. 打开Android Studio
2. 打开项目文件夹 `c:/Users/jd/CodeBuddy/20260311175444`
3. 等待Gradle同步完成
4. 点击菜单 `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
5. 等待构建完成
6. 点击右侧通知栏中的 `locate` 找到APK文件

### 方法三:使用Gradle Wrapper

```bash
./gradlew clean
./gradlew assembleDebug
```

## APK输出位置

构建成功后,APK文件位于:
```
app/build/outputs/apk/debug/app-debug.apk
```

## 当前APK功能

已实现的功能:
- ✅ 启动页
- ✅ 登录页面(手机号+验证码/密码)
- ✅ 主页面(底部导航)
- ✅ 首页(SOS紧急求助按钮)
- ✅ 个人中心入口
- ✅ 基础页面框架

## 安装APK到设备

### 方法一:直接安装
1. 将APK文件传输到Android设备
2. 在设备上点击APK文件
3. 允许未知来源应用安装
4. 点击安装

### 方法二:使用ADB
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### 方法三:通过Android Studio
1. 连接Android设备(开启USB调试)
2. 点击工具栏的运行按钮 ▶
3. 选择设备后点击OK

## 后续开发建议

### 1. 网络请求对接
- 配置API_BASE_URL
- 实现所有API接口调用
- 处理网络异常

### 2. 功能模块开发
- 按照TODO列表逐个开发
- 使用已创建的Activity和Fragment
- 实现对应的布局和逻辑

### 3. 三方服务配置
- 阿里云短信
- 支付宝/微信支付
- 腾讯IM
- 腾讯地图

### 4. 测试和调试
- 使用模拟器或真机测试
- 使用Logcat查看日志
- 修复bug和优化性能

## 常见问题

### Q1: Gradle同步失败
A: 检查网络连接,配置Maven镜像,清理Gradle缓存

### Q2: 构建APK失败
A: 检查JDK版本(需要17),更新Android SDK,查看错误日志

### Q3: APK无法安装
A: 检查设备SDK版本(最低Android 7.0),清除应用缓存后重试

### Q4: 功能无法使用
A: 当前为基础版本,需要继续开发具体功能模块

## 技术支持

查看文档:
- README.md - 项目总览
- PROJECT_STRUCTURE.md - 项目结构
- PROJECT_SETUP_GUIDE.md - 配置指南
- PROJECT_STRUCTURE.md - 详细API文档

## 版本信息

- 版本号: 1.0.0
- 版本代码: 1
- 构建类型: Debug
- 最低SDK: 24 (Android 7.0)
- 目标SDK: 34 (Android 14)
