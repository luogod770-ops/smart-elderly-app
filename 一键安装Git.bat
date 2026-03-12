@echo off
chcp 65001 >nul
echo ========================================
echo 一键安装 Git
echo ========================================
echo.

echo [1/3] 检查 Git 是否已安装...
git --version >nul 2>&1
if %errorlevel% equ 0 (
    echo ✓ Git 已安装
    git --version
    echo.
    echo 无需重新安装。
    echo.
    pause
    exit /b 0
)

echo ✗ Git 未安装，开始安装...
echo.

echo [2/3] 使用 winget 安装 Git...
echo 这需要管理员权限，可能需要确认。
echo.

powershell -Command "Start-Process winget -ArgumentList 'install Git.Git --accept-source-agreements --accept-package-agreements' -Verb RunAs -Wait"

if %errorlevel% equ 0 (
    echo ✓ Git 安装成功
) else (
    echo ✗ winget 安装失败
    echo.
    echo 请手动下载并安装：
    echo 1. 访问：https://git-scm.com/download/win
    echo 2. 下载安装程序
    echo 3. 运行安装程序（使用默认选项）
    echo.
    pause
    exit /b 1
)

echo.
echo [3/3] 配置 Git...
echo.

set /p USERNAME="请输入 GitHub 用户名（默认：luogod770-ops）："
if "%USERNAME%"=="" set USERNAME=luogod770-ops

set /p EMAIL="请输入 GitHub 邮箱："

git config --global user.name "%USERNAME%"
git config --global user.email "%EMAIL%"

echo.
echo ✓ Git 配置完成
echo   用户名：%USERNAME%
echo   邮箱：%EMAIL%
echo.

echo ========================================
echo 安装完成！
echo ========================================
echo.
echo 重要提示：
echo 1. 请关闭当前命令提示符
echo 2. 重新打开命令提示符
echo 3. 然后运行推送脚本：推送代码.bat
echo.
echo 或者手动执行：
echo cd c:\Users\jd\CodeBuddy\20260311175444
echo git init
echo git add .
echo git commit -m "Build APK"
echo git branch -M main
echo git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git
echo git push -u origin main
echo.

pause
