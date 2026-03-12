# 快速开始指南

## 📱 项目简介

**智慧养老社区APP** - 一款专为老年人设计的综合性社区服务平台

**版本**: v1.0.0
**状态**: ✅ 基础架构完成 | ⚠️ 功能持续开发

---

## 🚀 立即开始

### 1. 环境要求

**Android开发:**
- JDK 17
- Android Studio Hedgehog+
- Android SDK 34

**后端开发:**
- JDK 17
- MySQL 8.0+
- Redis 6.0+

### 2. 打开项目

1. 启动Android Studio
2. 点击 `File` → `Open`
3. 选择项目文件夹: `c:/Users/jd/CodeBuddy/20260311175444`
4. 等待Gradle同步完成

### 3. 构建APK

**Windows:**
```bash
build.bat
```

**Mac/Linux:**
```bash
chmod +x gradlew
./gradlew assembleDebug
```

**Android Studio:**
1. 点击 `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
2. 等待构建完成
3. 点击 `locate` 查找APK

### 4. 安装APK

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

或直接将APK传输到手机安装

---

## 📋 功能清单

### ✅ 已实现
- [x] 项目基础架构
- [x] 登录页面UI
- [x] 主页面和底部导航
- [x] SOS紧急求助功能
- [x] 基础页面框架
- [x] 资源文件和样式

### ⚠️ 框架已搭建,需继续开发
- [ ] 网络请求对接
- [ ] 房屋绑定模块
- [ ] 社区交流模块
- [ ] 设备管理和蓝牙
- [ ] 会员系统
- [ ] 支付集成
- [ ] 三方服务集成

---

## 📁 项目结构

```
SmartElderlyCommunity/
├── app/                          # Android应用
│   ├── src/main/
│   │   ├── java/...              # Kotlin源代码
│   │   └── res/                 # 资源文件
│   └── build.gradle.kts         # 构建配置
│
├── backend/                      # Spring Boot后端
│   ├── src/main/
│   │   ├── java/...             # Kotlin源代码
│   │   └── resources/           # 配置文件
│   └── build.gradle.kts         # 构建配置
│
├── README.md                    # 项目说明
├── PROJECT_STRUCTURE.md          # 详细结构
├── PROJECT_SETUP_GUIDE.md        # 配置指南
├── APK_BUILD_GUIDE.md           # 构建指南
└── build.bat                    # 构建脚本
```

---

## 🎯 核心功能预览

### SOS紧急求助
- 一键SOS大按钮
- 自动获取位置
- 发送求助信号
- 通知联系人和物业

### 房屋绑定
- 多级联动选择
- 城市 → 社区 → 小区 → 楼栋
- 申请绑定流程
- 我的房屋管理

### 社区交流
- 版块浏览
- 发布帖子
- 评论互动
- 点赞转发

### 设备管理
- 智能手环绑定
- 蓝牙连接
- 健康数据同步
- 告警通知

### 会员系统
- 套餐选择
- 在线支付
- 会员权益

---

## ⚙️ 配置说明

### 修改API地址
编辑 `app/build.gradle.kts`:
```kotlin
buildConfigField("String", "API_BASE_URL", "\"http://your-server.com/api\"")
```

### 配置三方服务
编辑 `backend/src/main/resources/application.yml`:
```yaml
aliyun:
  sms:
    accessKeyId: your_key
    accessKeySecret: your_secret
```

---

## 📚 文档导航

| 文档 | 说明 |
|------|------|
| [README.md](README.md) | 项目总览 |
| [PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md) | 详细项目结构 |
| [PROJECT_SETUP_GUIDE.md](PROJECT_SETUP_GUIDE.md) | 环境配置 |
| [APK_BUILD_GUIDE.md](APK_BUILD_GUIDE.md) | APK构建 |
| [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) | 项目总结 |

---

## 🔧 常见问题

**Q: Gradle同步失败?**
A: 检查网络,配置Maven镜像,清理缓存

**Q: 构建APK失败?**
A: 检查JDK版本,更新SDK,查看错误日志

**Q: APK无法安装?**
A: 检查设备版本(需Android 7.0+),允许未知来源

**Q: 功能无法使用?**
A: 当前为基础版本,需要继续开发具体功能

---

## 📞 技术支持

- 查看文档: 项目根目录下的Markdown文件
- 代码注释: 源代码中的详细注释
- 官方文档: 各技术栈官方文档

---

## 🎉 开始使用

1. ✅ 阅读本文档
2. ✅ 配置开发环境
3. ✅ 打开Android Studio
4. ✅ 同步Gradle
5. ✅ 构建APK
6. ✅ 安装到设备
7. ✅ 开始开发!

---

**祝你开发顺利!** 🚀
