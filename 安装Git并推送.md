# 🔧 安装 Git 并推送到 GitHub 的完整步骤

## 📋 当前状态

**问题：** Git 未安装，无法执行推送命令

**解决方案：** 安装 Git → 配置 → 推送代码

---

## 📥 步骤 1：安装 Git

### 方法 A：使用 winget（推荐，最快）

打开 PowerShell（以管理员身份），执行：

```powershell
winget install Git.Git
```

等待安装完成（约1-2分钟）

---

### 方法 B：手动下载安装

1. **下载 Git 安装程序：**
   访问：https://git-scm.com/download/win

2. **运行安装程序：**
   - 双击下载的 `.exe` 文件
   - 点击 "Next" 使用默认选项
   - 一路点击 "Next" 直到完成

3. **验证安装：**
   打开新的命令提示符，执行：
   ```bash
   git --version
   ```
   应该看到类似：`git version 2.47.0`

---

## 📝 步骤 2：配置 Git

安装完成后，配置用户信息：

```bash
# 设置用户名（替换为你的 GitHub 用户名）
git config --global user.name "luogod770-ops"

# 设置邮箱（替换为你的 GitHub 邮箱）
git config --global user.email "your-email@example.com"
```

---

## 🚀 步骤 3：推送代码到 GitHub

### 方法 A：使用推送脚本（最简单）

1. **双击运行** `推送代码.bat`

2. **如果需要认证，按照提示操作：**
   - 用户名：GitHub 用户名（`luogod770-ops`）
   - 密码：Personal Access Token（**不是** GitHub 密码）

---

### 方法 B：手动执行命令

打开**命令提示符**（不是 PowerShell），执行以下命令：

```bash
# 1. 进入项目目录
cd c:\Users\jd\CodeBuddy\20260311175444

# 2. 初始化 Git 仓库
git init

# 3. 添加所有文件
git add .

# 4. 提交更改
git commit -m "Build APK - Smart Elderly Community App"

# 5. 设置分支名
git branch -M main

# 6. 添加远程仓库
git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git

# 7. 推送到 GitHub
git push -u origin main
```

**逐行执行，不要一次性复制粘贴！**

---

## 🔑 步骤 4：获取 Personal Access Token（如果需要认证）

### 4.1 生成 Token

1. **访问 GitHub Token 设置：**
   https://github.com/settings/tokens/new

2. **配置 Token：**
   - **Note（备注）：** 输入 `CodeBuddy Build`
   - **Expiration（过期时间）：** 选择 `No expiration` 或 `90 days`
   - **Scopes（权限）：** 勾选 `repo`（展开后全部勾选）

3. **生成 Token：**
   - 滚动到页面底部
   - 点击绿色按钮 `Generate token`

4. **复制 Token：**
   - Token 生成后会显示在页面顶部
   - **立即复制**（只显示一次！）
   - 格式类似：`ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx`

---

### 4.2 使用 Token 推送

当提示输入密码时：

```
Username: luogod770-ops
Password: 粘贴刚刚复制的 Token
```

**注意：** 密码输入时不会显示任何字符，这是正常的！

---

## 📊 验证推送成功

推送成功后，访问：
https://github.com/luogod770-ops/smart-elderly-app

您应该能看到：
- 仓库的所有文件
- 最近的提交记录
- README.md（如果有的话）

---

## 🔄 触发 GitHub Actions 构建

推送成功后，GitHub Actions 会**自动开始构建**！

### 查看构建进度

访问 Actions 页面：
https://github.com/luogod770-ops/smart-elderly-app/actions

**预期：**
- 看到名为 `Build Android APK` 的工作流
- 状态为黄色（进行中）或绿色（已完成）
- 点击工作流查看详细日志

---

## ⚠️ 常见问题

### 问题 1：Git 命令找不到

**错误：** `'git' 不是内部或外部命令`

**解决：**
1. 确认 Git 已安装
2. **重启命令提示符**（重要！）
3. 再次运行 `git --version`

---

### 问题 2：推送失败 - Authentication failed

**错误：** `remote: Invalid username or password.`

**解决：**
1. 检查用户名是否正确（`luogod770-ops`）
2. 确认使用的是 Personal Access Token（不是 GitHub 密码）
3. 重新生成 Token（可能已过期或权限不足）

---

### 问题 3：仓库不存在

**错误：** `fatal: repository 'https://github.com/luogod770-ops/smart-elderly-app.git/' not found`

**解决：**
1. 访问：https://github.com/luogod770-ops/smart-elderly-app
2. 如果页面显示 404，说明仓库不存在
3. 需要先在 GitHub 创建仓库

---

### 问题 4：推送成功但没有触发构建

**可能原因：**
- 分支名称不是 `main` 或 `master`

**检查方法：**
```bash
git branch
```

**解决：**
```bash
# 重命名分支为 main
git branch -M main

# 重新推送
git push -u origin main
```

---

### 问题 5：Actions 页面空白

**解决：**
1. 刷新浏览器页面
2. 确认已登录 GitHub
3. 检查仓库地址是否正确

---

## 📱 下一步：下载 APK

构建成功后：

1. **在 Actions 页面找到成功的工作流**
2. **滚动到底部 Artifacts 区域**
3. **点击 `app-debug` 下载**
4. **解压文件**
5. **找到 `app-debug.apk`**

---

## ✅ 完成检查清单

- [ ] Git 已安装
- [ ] Git 已配置（用户名和邮箱）
- [ ] Personal Access Token 已生成
- [ ] 代码已推送到 GitHub
- [ ] GitHub 仓库能看到文件
- [ ] Actions 页面显示工作流
- [ ] 构建状态为绿色 ✔
- [ ] 已下载 app-debug artifact
- [ ] 已获得 app-debug.apk

---

## 🎯 快速参考命令

### 安装 Git
```powershell
winget install Git.Git
```

### 配置 Git
```bash
git config --global user.name "luogod770-ops"
git config --global user.email "your-email@example.com"
```

### 推送代码（逐行执行）
```bash
cd c:\Users\jd\CodeBuddy\20260311175444
git init
git add .
git commit -m "Build APK"
git branch -M main
git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git
git push -u origin main
```

---

## 📞 相关链接

- **Git 下载：** https://git-scm.com/download/win
- **Token 设置：** https://github.com/settings/tokens/new
- **仓库地址：** https://github.com/luogod770-ops/smart-elderly-app
- **Actions 页面：** https://github.com/luogod770-ops/smart-elderly-app/actions

---

## 💡 提示

1. **重启命令提示符很重要！**
   - Git 安装后必须打开新的命令提示符
   - 否则无法识别 git 命令

2. **逐行执行命令**
   - 不要一次性复制粘贴所有命令
   - 一行一行执行，确保每一步成功

3. **密码输入不显示**
   - 输入 Token 时不会显示字符
   - 这是正常的安全机制
   - 直接粘贴后按回车即可

4. **Token 只显示一次**
   - 生成后立即复制保存
   - 丢失后需要重新生成

---

## 🚀 开始安装

**打开 PowerShell（管理员身份），执行：**
```powershell
winget install Git.Git
```

然后按照本文档的步骤依次操作！

---

**预计总耗时：** 10-15 分钟
- 安装 Git：2-3 分钟
- 配置和推送：3-5 分钟
- GitHub Actions 构建：5-10 分钟
