# GitHub Actions 构建 APK - 完整指南（已修复）

## ✅ 问题已解决

GitHub Actions 工作流已更新，现在会自动下载 gradle-wrapper.jar！

---

## 🚀 快速开始（3步）

### 步骤 1：推送代码

**双击运行** `推送代码.bat` 或手动执行：

```bash
cd c:\Users\jd\CodeBuddy\20260311175444

# 初始化 Git
git init

# 添加所有文件
git add .

# 提交
git commit -m "Build APK with GitHub Actions"

# 添加远程仓库
git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git

# 推送
git push -u origin main
```

**如果推送时需要认证：**
- 用户名：GitHub 用户名
- 密码：Personal Access Token（不是 GitHub 密码！）

---

### 步骤 2：查看构建

访问 GitHub Actions 页面：
https://github.com/luogod770-ops/smart-elderly-app/actions

- 查看构建进度
- 等待约 5-10 分钟
- 确认状态为绿色 ✔

---

### 步骤 3：下载 APK

构建成功后：
1. 在 Actions 页面底部找到 "Artifacts"
2. 点击 `app-debug` 下载
3. 解压得到 `app-debug.apk`

---

## 🔧 工作流说明

已修复的 `.github/workflows/build-apk.yml` 包含：

✅ **自动下载 gradle-wrapper.jar**
```yaml
- name: Create Gradle Wrapper
  run: |
    curl -L https://raw.githubusercontent.com/gradle/gradle/v8.3.0/gradle/wrapper/gradle-wrapper.jar -o gradle/wrapper/gradle-wrapper.jar
```

✅ **完整的构建流程**
1. 检出代码
2. 设置 JDK 17
3. 设置 Gradle 环境
4. 下载 gradle-wrapper.jar
5. 构建 Debug APK
6. 上传构建产物

---

## 📋 认证说明

### 如何获取 Personal Access Token

1. **访问 GitHub Token 设置：**
   https://github.com/settings/tokens/new

2. **配置 Token：**
   - Note: 输入描述，如 "CodeBuddy Build"
   - Expiration: 选择过期时间（建议 90 days 或 No expiration）
   - 勾选权限：`repo`（全部勾选）

3. **生成 Token：**
   - 点击 "Generate token"
   - **立即复制 token**（只显示一次！）

4. **使用 Token 推送：**
   ```bash
   git push -u origin main
   # Username: 你的 GitHub 用户名
   # Password: 粘贴刚刚复制的 token
   ```

---

## 🔍 故障排除

### 问题 1：推送失败 - Authentication failed

**错误信息：**
```
remote: Invalid username or password.
fatal: Authentication failed
```

**解决方法：**
1. 确认使用了 Personal Access Token（不是密码）
2. 检查 Token 是否有 `repo` 权限
3. 确认仓库地址正确

---

### 问题 2：推送失败 - Repository not found

**错误信息：**
```
fatal: repository 'https://github.com/luogod770-ops/smart-elderly-app.git/' not found
```

**解决方法：**
1. 确认仓库地址拼写正确
2. 确认仓库已创建在 GitHub 上
3. 确认你有该仓库的访问权限

---

### 问题 3：GitHub Actions 构建失败

**查看详细日志：**
1. 访问：https://github.com/luogod770-ops/smart-elderly-app/actions
2. 点击失败的工作流
3. 展开失败的步骤

**常见错误：**

**错误：curl 下载失败**
```
解决：检查网络连接，GitHub Actions 会自动重试
```

**错误：编译错误**
```
解决：检查代码语法，确保本地没有明显错误
```

**错误：超时**
```
解决：通常是网络问题，点击 "Re-run jobs" 重新构建
```

---

## 📱 安装 APK

### 方法 1：ADB 安装（推荐）

```bash
# 安装 ADB（如果还没有）
# 下载：https://developer.android.com/studio/releases/platform-tools

# 连接设备，启用 USB 调试
adb devices

# 安装 APK
adb install app-debug.apk
```

### 方法 2：直接安装

1. **传输 APK 到设备**
   - USB 数据线
   - 微信/QQ
   - 云盘下载

2. **安装**
   - 打开文件管理器
   - 找到 APK 文件
   - 点击安装
   - 允许"未知来源"

---

## 📊 构建信息

| 项目 | 信息 |
|------|------|
| **JDK 版本** | 17 |
| **Gradle 版本** | 8.3 |
| **构建类型** | Debug |
| **预计时间** | 5-10 分钟 |
| **APK 大小** | 20-50 MB |
| **输出位置** | app/build/outputs/apk/debug/app-debug.apk |

---

## ✅ 成功标志

构建成功时，您会看到：

1. **GitHub Actions 状态：** 绿色 ✔
2. **工作流名称：** Build Android APK
3. **状态文字：** Success

**Artifacts 区域：**
- `app-debug`（下载链接可用）

---

## 🔄 重新构建

如果需要重新构建：

1. 访问：https://github.com/luogod770-ops/smart-elderly-app/actions
2. 找到之前的工作流
3. 点击 "Re-run jobs"
4. 等待完成

或手动触发：
1. 点击 "Run workflow"
2. 选择分支
3. 点击 "Run workflow"

---

## 📝 代码推送命令总结

```bash
# 完整命令（复制粘贴即可）
cd c:\Users\jd\CodeBuddy\20260311175444 && git init && git add . && git commit -m "Build APK" && git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git && git push -u origin main
```

**一行命令完成所有操作！**

---

## 🎯 总结

### ✅ 已完成
- [x] GitHub Actions 工作流已修复
- [x] 自动下载 gradle-wrapper.jar
- [x] 推送脚本已创建
- [x] 完整文档已准备

### 📌 下一步
1. 推送代码到 GitHub
2. 等待 GitHub Actions 构建
3. 下载 APK

### 💡 关键优势
- ✅ 无需本地环境配置
- ✅ 无需手动下载 gradle-wrapper.jar
- ✅ GitHub Actions 自动处理所有依赖
- ✅ 云端构建，速度快
- ✅ 可重复，历史可追溯

---

## 📞 相关链接

- **项目路径：** `c:\Users\jd\CodeBuddy\20260311175444`
- **仓库地址：** https://github.com/luogod770-ops/smart-elderly-app
- **Actions 页面：** https://github.com/luogod770-ops/smart-elderly-app/actions
- **Token 设置：** https://github.com/settings/tokens/new
- **GitHub Actions 文档：** https://docs.github.com/en/actions

---

## 🚀 立即开始

**复制以下命令到 PowerShell 或命令提示符：**

```bash
cd c:\Users\jd\CodeBuddy\20260311175444 && git init && git add . && git commit -m "Build APK" && git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git && git push -u origin main
```

然后访问 https://github.com/luogod770-ops/smart-elderly-app/actions 等待构建完成！
