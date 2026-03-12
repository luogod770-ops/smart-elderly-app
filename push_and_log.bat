@echo off
chcp 65001 >nul

echo.
echo ======================================
echo 推送代码到GitHub
echo ======================================
echo.

cd /d c:\Users\jd\CodeBuddy\20260311175444

echo [1/4] 配置远程仓库...
git remote remove origin
git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git
echo 远程仓库已配置
echo.

echo [2/4] 设置主分支...
git branch -M main
echo 主分支已设置
echo.

echo [3/4] 推送代码到GitHub...
echo.

git push -u origin main

echo.
if %errorlevel% equ 0 (
    echo [成功] 推送成功!
    echo.
    echo 仓库地址: https://github.com/luogod770-ops/smart-elderly-app
    echo.
    start "" "https://github.com/luogod770-ops/smart-elderly-app"
) else (
    echo [失败] 推送失败,错误码: %errorlevel%
)

echo.
pause
