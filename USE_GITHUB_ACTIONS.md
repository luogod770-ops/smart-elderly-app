# 使用GitHub Actions构建APK - 完整操作指南

## 📋 前置准备

### 1. 下载并安装Git

如果您的电脑还没有Git,请先安装:

#### Windows下载地址:
```
https://git-scm.com/download/win
```

#### 安装步骤:
1. 下载Git for Windows安装程序
2. 双击运行安装程序
3. 使用默认设置,一路点击"Next"
4. 完成安装后,打开新的命令行窗口

---

## 🚀 完整操作步骤

### 第1步: 创建GitHub账号 (如果没有)

1. 访问 https://github.com
2. 点击右上角 "Sign up"
3. 填写信息完成注册
4. 验证邮箱

---

### 第2步: 创建新的GitHub仓库

1. 登录GitHub
2. 点击右上角 "+" 图标
3. 选择 "New repository"
4. 填写信息:
   - Repository name: `smart-elderly-app`
   - Description: `智慧养老社区APP`
   - 选择 Public 或 Private
   - 勾选 "Initialize this repository with a README"
5. 点击 "Create repository"

---

### 第3步: 配置Git (首次使用)

打开命令提示符或PowerShell,执行:

```bash
git config --global user.name "你的名字"
git config --global user.email "你的GitHub邮箱"
```

示例:
```bash
git config --global user.name "张三"
git config --global user.email "zhangsan@example.com"
```

---

### 第4步: 初始化本地Git仓库

```bash
# 进入项目目录
cd c:\Users\jd\CodeBuddy\20260311175444

# 初始化Git仓库
git init

# 添加所有文件
git add .

# 提交代码
git commit -m "智慧养老社区APP - 完整项目"
```

---

### 第5步: 连接到GitHub仓库

```bash
# 添加远程仓库 (替换下面的URL为你的仓库地址)
git remote add origin https://github.com/你的GitHub用户名/smart-elderly-app.git

# 推送代码到GitHub
git branch -M main
git push -u origin main
```

**注意:** 第一次推送时,GitHub会要求你登录:
1. 点击弹出的链接
2. 输入GitHub账号密码
3. 授权Git访问
4. 复制授权码并粘贴到命令行

---

### 第6步: 验证GitHub Actions自动构建

推送代码后,GitHub会自动触发构建:

1. 访问你的GitHub仓库页面
2. 点击顶部的 "Actions" 标签
3. 你会看到名为 "Build Android APK" 的工作流正在运行
4. 等待5-10分钟,构建完成

---

### 第7步: 下载APK文件

构建成功后,下载APK:

1. 在Actions页面,点击最新的构建任务
2. 向下滚动到页面底部
3. 找到 "Artifacts" 部分
4. 你会看到两个文件:
   - `app-debug-apk` (测试版本)
   - `app-release-apk` (发布版本)
5. 点击文件名右侧的下载图标下载

---

## 🎯 手动触发构建 (可选)

如果你不想推送代码,也可以手动触发:

1. 访问仓库的 "Actions" 页面
2. 点击左侧 "Build Android APK"
3. 点击右侧 "Run workflow" 按钮
4. 选择分支,点击 "Run workflow"
5. 等待构建完成

---

## 📱 测试APK

### 安装到Android设备:

#### 方法1: 通过USB连接
```bash
# 启用开发者模式和USB调试后
adb install app-debug.apk
```

#### 方法2: 直接安装
1. 将APK文件传输到手机
2. 在手机上点击APK文件
3. 允许安装未知来源应用
4. 完成安装

---

## 🔄 重新构建APK

如果修改了代码,需要重新构建:

```bash
# 添加修改
git add .

# 提交修改
git commit -m "更新功能描述"

# 推送到GitHub (自动触发构建)
git push
```

---

## 📊 构建时间说明

- **首次构建**: 约10-15分钟 (需要下载依赖)
- **后续构建**: 约5-10分钟 (使用缓存)

---

## ⚠️ 常见问题

### 问题1: 推送时提示权限错误

**解决方法:**
1. 检查远程仓库地址是否正确
2. 确认你在GitHub上有该仓库的权限
3. 尝试使用Personal Access Token

### 问题2: 构建失败

**解决方法:**
1. 在Actions页面查看详细日志
2. 检查代码语法错误
3. 查看是哪一步出错的

### 问题3: 找不到APK下载链接

**解决方法:**
1. 确认构建状态为绿色(成功)
2. 滚动到页面最底部
3. 展开Artifacts部分

### 问题4: APK无法安装

**解决方法:**
1. 确认APK文件完整(重新下载)
2. 检查Android版本是否满足要求
3. 允许安装未知来源应用

---

## 📚 参考文档

- GitHub Actions官方文档: https://docs.github.com/cn/actions
- Git官方文档: https://git-scm.com/doc

---

## ✅ 完成检查清单

使用此指南前,请确认:

- [ ] 已安装Git
- [ ] 已有GitHub账号
- [ ] 已创建GitHub仓库
- [ ] 已配置Git用户信息
- [ ] 已推送代码到GitHub
- [ ] Actions显示构建成功
- [ ] 已下载APK文件

---

## 🎉 恭喜!

完成以上步骤后,您就已经成功构建了第一个APK!

如果遇到任何问题,请查看"常见问题"部分或查阅GitHub Actions文档。

---

**预计总时间: 15-20分钟**

**包括:**
- 安装Git: 5分钟
- 创建GitHub仓库: 2分钟
- 配置并推送代码: 5分钟
- 等待构建: 5-10分钟
- 下载APK: 1分钟

**开始构建您的APK吧! 🚀**
