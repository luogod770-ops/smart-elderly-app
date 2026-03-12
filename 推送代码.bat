@echo off
chcp 65001 >nul
echo ========================================
echo 推送代码到 GitHub
echo ========================================
echo.

cd /d "%~dp0"
echo 当前目录: %CD%
echo.

echo [1/5] 初始化 Git 仓库...
if not exist .git (
    git init
    echo ✓ Git 仓库已初始化
) else (
    echo ✓ Git 仓库已存在
)
echo.

echo [2/5] 添加所有文件到暂存区...
git add .
echo ✓ 文件已添加
echo.

echo [3/5] 提交更改...
git commit -m "Build APK - Smart Elderly Community App"
if %errorlevel% equ 0 (
    echo ✓ 提交成功
) else (
    echo ℹ 没有新的更改需要提交
)
echo.

echo [4/5] 添加远程仓库...
git remote get-url origin >nul 2>&1
if %errorlevel% neq 0 (
    git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git
    echo ✓ 远程仓库已添加
) else (
    echo ✓ 远程仓库已配置
)
echo.

echo [5/5] 推送代码到 GitHub...
echo.
echo ========================================
echo ⚠ 认证说明：
echo ========================================
echo 如果提示输入密码，请使用：
echo - 用户名：GitHub 用户名
echo - 密码：Personal Access Token (不是 GitHub 密码！)
echo.
echo 如何获取 Token：
echo 1. 访问：https://github.com/settings/tokens/new
echo 2. 勾选 'repo' 权限
echo 3. 点击 'Generate token'
echo 4. 复制生成的 token
echo ========================================
echo.
pause

git push -u origin main

if %errorlevel% equ 0 (
    echo.
    echo ========================================
    echo ✓ 推送成功！
    echo ========================================
    echo.
    echo 下一步：
    echo 1. 访问：https://github.com/luogod770-ops/smart-elderly-app/actions
    echo 2. 等待构建完成（5-10分钟）
    echo 3. 下载 'app-debug' artifact
    echo.
) else (
    echo.
    echo ========================================
    echo ✗ 推送失败
    echo ========================================
    echo.
    echo 请检查：
    echo 1. 网络连接是否正常
    echo 2. GitHub 仓库地址是否正确
    echo 3. 是否使用了正确的认证方式
    echo.
    echo 详细说明请查看：最终解决方案.md
    echo.
)

pause
