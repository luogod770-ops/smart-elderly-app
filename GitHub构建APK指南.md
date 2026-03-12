# GitHub Actions 自动构建 APK 指南

## 🎯 最简单快捷的方法（推荐）

### 前置准备
- Git 已安装
- GitHub 账号
- 仓库地址：https://github.com/luogod770-ops/smart-elderly-app

---

## 📋 详细步骤

### 步骤 1：初始化 Git 仓库并推送

打开 PowerShell 或命令提示符，执行：

```bash
# 进入项目目录
cd c:\Users\jd\CodeBuddy\20260311175444

# 初始化 Git
git init

# 添加所有文件
git add .

# 提交
git commit -m "Initial commit - Smart Elderly Community App"

# 设置分支名
git branch -M main

# 添加远程仓库
git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git

# 推送到 GitHub
git push -u origin main
```

**如果推送失败（需要认证）：**
- 方法1：使用 GitHub Personal Access Token
  1. 访问 https://github.com/settings/tokens
  2. 生成新 token（权限选择：repo）
  3. 使用 token 替代密码

- 方法2：使用 SSH
  ```bash
  git remote set-url origin git@github.com:luogod770-ops/smart-elderly-app.git
  ```

---

### 步骤 2：触发 GitHub Actions 构建

1. **访问仓库 Actions 页面**
   https://github.com/luogod770-ops/smart-elderly-app/actions

2. **等待自动构建开始**
   - 代码推送后会自动触发
   - 或者手动点击 "Run workflow"

3. **查看构建进度**
   - 点击最新的工作流
   - 实时查看构建日志

---

### 步骤 3：下载 APK

构建成功后：

1. **在 Actions 页面找到成功的构建**

2. **下载 Artifacts**
   - 点击工作流底部的 "Artifacts" 区域
   - 下载 `app-debug`（约20-50MB）

3. **解压并使用**
   - 解压下载的 zip 文件
   - 找到 `app-debug.apk`
   - 可以直接安装到 Android 设备

---

## 📦 GitHub Actions 工作流说明

项目已配置了 `.github/workflows/build-apk.yml`，它会：

✅ **自动触发：**
- 代码推送到 main/master 分支
- 创建 Pull Request
- 手动触发（workflow_dispatch）

✅ **构建流程：**
1. 检出代码
2. 安装 JDK 17
3. 授予 gradlew 执行权限
4. 构建 Debug APK
5. 上传构建产物

✅ **输出：**
- app-debug.apk（可安装的 APK 文件）

---

## ⚙️ 高级选项

### 修改输出文件名

编辑 `.github/workflows/build-apk.yml`，添加重命名步骤：

```yaml
- name: Rename APK
  run: |
    cp app/build/outputs/apk/debug/app-debug.apk app/build/outputs/apk/debug/smart-elderly-app-v1.0.apk
```

### 构建多个版本

添加 Release APK 构建：

```yaml
- name: Build Release APK
  run: ./gradlew assembleRelease

- name: Upload Release APK
  uses: actions/upload-artifact@v4
  with:
    name: app-release
    path: app/build/outputs/apk/release/*.apk
```

### 签名 APK（需要签名配置）

```yaml
- name: Sign APK
  env:
    KEYSTORE_FILE: ${{ secrets.KEYSTORE_FILE }}
    KEYSTORE_PASSWORD: ${{ secrets.KEYSTORE_PASSWORD }}
    KEY_ALIAS: ${{ secrets.KEY_ALIAS }}
    KEY_PASSWORD: ${{ secrets.KEY_PASSWORD }}
  run: |
    echo "$KEYSTORE_FILE" | base64 -d > release.keystore
    ./gradlew assembleRelease -PkeystoreFile=release.keystore
```

---

## 🔧 故障排除

### 构建失败

**查看构建日志：**
1. 点击失败的工作流
2. 展开失败的步骤
3. 查看详细错误信息

**常见错误：**

1. **Gradle 版本不匹配**
   - 修改 `gradle/wrapper/gradle-wrapper.properties`
   - 更新 `distributionUrl` 到兼容版本

2. **依赖下载失败**
   - GitHub Actions 会自动重试
   - 确保网络连接正常

3. **编译错误**
   - 检查代码语法
   - 查看本地构建是否成功

---

## 📱 安装 APK

### 安装到 Android 设备

1. **启用未知来源**
   - 设置 → 安全 → 允许未知来源

2. **安装 APK**
   ```bash
   adb install app-debug.apk
   ```

3. **或直接在设备上安装**
   - 将 APK 文件复制到设备
   - 使用文件管理器打开并安装

---

## 📊 构建时间参考

- **首次构建：** 5-10 分钟（下载依赖）
- **后续构建：** 3-5 分钟（使用缓存）
- **APK 大小：** 20-50 MB（取决于代码大小）

---

## 🎁 优势

✅ **不需要本地环境**
- 无需安装 Android Studio
- 无需安装 Java JDK
- 无需配置本地 Gradle

✅ **云端自动化**
- 自动触发构建
- 保存构建历史
- 多人协作友好

✅ **快速可靠**
- 使用 GitHub 强大的服务器
- 稳定的构建环境
- 自动下载依赖

---

## 📝 总结

**最快获得 APK 的方法：**

1. ✅ 运行推送命令（30秒）
2. ✅ 等待 GitHub Actions 构建（5-10分钟）
3. ✅ 下载构建产物（1分钟）

**总耗时：约10-15分钟**

---

## 📞 需要帮助？

- GitHub Actions 文档：https://docs.github.com/en/actions
- Gradle 文档：https://docs.gradle.org
- 项目位置：`c:\Users\jd\CodeBuddy\20260311175444`
- 仓库地址：https://github.com/luogod770-ops/smart-elderly-app
