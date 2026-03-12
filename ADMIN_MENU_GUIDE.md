# 系统配置和后台菜单开发说明

## 模块概述

系统配置模块提供APP的核心配置管理功能,包括:
- 系统参数配置
- Banner轮播图管理
- APP版本管理
- 后台菜单权限管理

## 已完成功能

### 1. 后端API ✅

#### 1.1 数据库表
- ✅ `system_config` - 系统配置表
- ✅ `banner` - Banner表
- ✅ `app_version` - APP版本表

#### 1.2 实体类(Entity)
- ✅ `SystemConfig.kt` - 系统配置实体
- ✅ `Banner.kt` - Banner实体
- ✅ `AppVersion.kt` - APP版本实体
- ✅ `BaseEntity.kt` - 基础实体类

#### 1.3 数据访问层(Mapper)
- ✅ `SystemConfigMapper.kt`
- ✅ `BannerMapper.kt`
- ✅ `AppVersionMapper.kt`

#### 1.4 业务逻辑层(Service)
- ✅ `SystemService.kt` - 系统服务接口
- ✅ `SystemServiceImpl.kt` - 系统服务实现

#### 1.5 控制器层(Controller)
- ✅ `SystemController.kt` - 系统配置控制器

#### 1.6 API接口
```
GET  /api/system/config              - 获取系统配置
PUT  /api/system/config              - 更新系统配置
GET  /api/system/banner/list         - 获取Banner列表
POST /api/system/banner/add          - 添加Banner
PUT  /api/system/banner/update       - 更新Banner
DELETE /api/system/banner/{id}        - 删除Banner
GET  /api/system/checkUpdate         - 检查更新
POST /api/system/version/publish     - 发布新版本
GET  /api/system/version/latest      - 获取最新版本
```

### 2. Android端 ✅

#### 2.1 数据模型(Model)
- ✅ `SystemConfig.kt` - 系统配置模型
- ✅ `Banner.kt` - Banner模型
- ✅ `UpdateInfo.kt` - 更新信息模型

#### 2.2 数据仓库(Repository)
- ✅ `SystemRepository.kt` - 系统数据仓库

#### 2.3 视图模型(ViewModel)
- ✅ `SystemViewModel.kt` - 系统ViewModel

#### 2.4 UI界面
- ✅ 更新 `HomeFragment` - 添加Banner展示
- ✅ 更新首页布局 - 添加功能菜单

#### 2.5 网络拦截器
- ✅ `HeaderInterceptor.kt` - 请求头拦截器
- ✅ `LogInterceptor.kt` - 日志拦截器

### 3. 数据库初始化 ✅
- ✅ `init_system.sql` - 系统配置初始化脚本

## 后台菜单结构

### 系统管理
```
系统管理
├── 系统配置
│   ├── 基础配置
│   ├── 功能开关
│   └── 参数设置
├── Banner管理
│   ├── Banner列表
│   ├── 添加Banner
│   └── 编辑Banner
├── 版本管理
│   ├── 版本列表
│   ├── 发布版本
│   └── 版本历史
└── 后台菜单
    ├── 菜单列表
    ├── 添加菜单
    └── 权限分配
```

## 系统配置项说明

### 基础配置
| 配置键 | 配置值 | 说明 | 类型 |
|--------|--------|------|------|
| customer_service_phone | 400-888-8888 | 客服电话 | 字符串 |
| customer_service_time | 9:00-18:00 | 客服时间 | 字符串 |

### 功能开关
| 配置键 | 配置值 | 说明 | 类型 |
|--------|--------|------|------|
| elder_mode_enabled | true | 是否启用老人模式 | 布尔 |
| sos_enabled | true | 是否启用SOS | 布尔 |
| banner_enabled | true | 是否启用Banner | 布尔 |
| check_in_enabled | true | 是否启用签到 | 布尔 |

### 参数设置
| 配置键 | 配置值 | 说明 | 类型 |
|--------|--------|------|------|
| sos_phone | 110,120,119 | SOS紧急电话 | 字符串 |
| check_in_points | 10 | 签到积分 | 数字 |
| max_online_time | 720 | 最大在线时长(分钟) | 数字 |

## 使用说明

### 1. 初始化数据库

```sql
-- 执行初始化脚本
source backend/src/main/resources/sql/init_system.sql;
```

### 2. 启动后端服务

```bash
cd backend
./gradlew bootRun
```

### 3. 访问Swagger文档

```
http://localhost:8080/api/doc.html
```

### 4. 测试API接口

**获取系统配置:**
```bash
curl -X GET http://localhost:8080/api/system/config
```

**获取Banner列表:**
```bash
curl -X GET http://localhost:8080/api/system/banner/list
```

**检查更新:**
```bash
curl -X GET "http://localhost:8080/api/system/checkUpdate?platform=1&versionCode=1"
```

## 后续开发建议

### 1. 后台管理界面
- [ ] 开发系统配置管理页面
- [ ] 开发Banner管理页面
- [ ] 开发版本管理页面
- [ ] 开发菜单权限管理页面

### 2. Android端优化
- [ ] 实现Banner轮播适配器
- [ ] 实现版本更新弹窗
- [ ] 优化系统配置缓存
- [ ] 添加设置页面

### 3. 功能增强
- [ ] 添加配置项分组
- [ ] 支持配置项导入导出
- [ ] 添加配置变更日志
- [ ] 实现配置项版本控制

## 技术要点

### 1. MyBatis Plus使用
- 使用`@TableName`指定表名
- 使用`@TableId`指定主键
- 使用`@TableLogic`实现逻辑删除
- 使用`QueryWrapper`构建查询条件

### 2. Redis缓存
- 系统配置可缓存到Redis
- 减少数据库查询压力
- 提高响应速度

### 3. 版本更新逻辑
- 比较客户端版本号
- 判断是否需要更新
- 区分推荐更新和强制更新

## 测试用例

### 1. 获取系统配置
```kotlin
@Test
fun testGetSystemConfig() {
    val result = systemController.getSystemConfig()
    assertTrue(result.code == 200)
    assertNotNull(result.data)
}
```

### 2. 添加Banner
```kotlin
@Test
fun testAddBanner() {
    val banner = Banner(
        title = "测试Banner",
        imageUrl = "https://example.com/test.jpg",
        linkType = 1
    )
    val result = systemController.addBanner(banner)
    assertTrue(result.code == 200)
}
```

### 3. 检查更新
```kotlin
@Test
fun testCheckUpdate() {
    val result = systemController.checkUpdate(1, 0)
    assertTrue(result.code == 200)
    assertTrue(result.data?.get("hasUpdate") == true)
}
```

## 常见问题

### Q1: 系统配置修改后不生效?
A: 需要清除Redis缓存,或设置缓存过期时间

### Q2: Banner图片无法显示?
A: 检查图片URL是否正确,检查CORS配置

### Q3: 版本检查返回错误?
A: 检查versionCode和platform参数是否正确

## 相关文档

- [README.md](README.md) - 项目总览
- [PROJECT_STRUCTURE.md](PROJECT_STRUCTURE.md) - 项目结构
- [API文档](http://localhost:8080/api/doc.html) - Swagger文档

---

**开发时间**: 2026-03-12
**开发状态**: ✅ 已完成基础框架
**下一步**: 开发房屋绑定模块
