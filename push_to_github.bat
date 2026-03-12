@echo off
chcp 65001 >nul

echo ======================================
echo 推送代码到GitHub
echo ======================================
echo.

cd /d c:\Users\jd\CodeBuddy\20260311175444

echo [1/4] 请输入您的GitHub仓库地址
echo.
echo 请按以下格式输入:
echo https://github.com/你的用户名/仓库名.git
echo.
echo 示例:
echo https://github.com/johndoe/smart-elderly-app.git
echo.

set /p REPO_URL="请输入GitHub仓库地址: "

if "%REPO_URL%"=="" (
    echo 错误: 仓库地址不能为空
    pause
    exit /b 1
)

echo.
echo [2/4] 添加远程仓库...
git remote add origin %REPO_URL%

if %errorlevel% neq 0 (
    echo 错误: 添加远程仓库失败
    pause
    exit /b 1
)

echo 远程仓库已添加
echo.

echo [3/4] 设置主分支...
git branch -M main

echo.
echo [4/4] 推送代码到GitHub...
echo 注意: 第一次推送时需要登录GitHub
echo.

git push -u origin main

if %errorlevel% equ 0 (
    echo.
    echo ======================================
    echo 代码推送成功!
    echo ======================================
    echo.
    echo 下一步操作:
    echo 1. 访问您的GitHub仓库
    echo 2. 点击 Actions 标签
    echo 3. 等待APK构建完成 (约5-10分钟)
    echo 4. 构建完成后,下载APK文件
    echo.
    echo APK文件位置: Artifacts -^> app-debug-apk
    echo.
    echo 是否打开GitHub仓库? (Y/N)
    choice /c YN /n
    if %errorlevel% equ 1 (
        set URL=%REPO_URL:.git=%
        start "" "%URL%"
    )
) else (
    echo.
    echo 推送失败
    echo.
    echo 可能的原因:
    echo 1. 仓库地址不正确
    echo 2. GitHub权限不足
    echo 3. 网络连接问题
    echo.
    echo 请检查后重试
)

echo.
echo 脚本执行完成
echo.
pause
