@echo off
chcp 65001 >nul

echo.
echo ======================================
echo 重新初始化并推送到GitHub
echo ======================================
echo.

cd /d c:\Users\jd\CodeBuddy\20260311175444

echo [1/6] 备份.git目录...
if exist .git (
    move .git .git.backup >nul
    echo .git目录已备份
) else (
    echo .git目录不存在
)
echo.

echo [2/6] 重新初始化Git仓库...
git init
echo.

echo [3/6] 添加所有文件...
git add .
echo.

echo [4/6] 提交代码...
git commit -m "智慧养老社区APP - 完整项目代码

包含功能:
- 社区交流模块(版块、帖子、评论)
- 设备管理模块(手环绑定、蓝牙连接)
- 会员系统(套餐、订单、支付)
- 三方服务集成(短信、IM、地图)

技术栈:
- 后端: Spring Boot + Kotlin + MyBatis-Plus
- 前端: Android原生 + Kotlin
- 数据库: MySQL

API接口: 47个
数据库表: 20张"
echo.

echo [5/6] 添加远程仓库...
git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git
echo.

echo [6/6] 推送到GitHub...
echo 正在推送,请稍候...
echo.

git push -u origin main --force

if %errorlevel% equ 0 (
    echo.
    echo ======================================
    echo 推送成功!
    echo ======================================
    echo.
    echo 仓库地址: https://github.com/luogod770-ops/smart-elderly-app
    echo.

    start "" "https://github.com/luogod770-ops/smart-elderly-app"
    echo 已在浏览器中打开仓库
) else (
    echo.
    echo 推送失败,错误码: %errorlevel%
    echo.
    echo 可能的原因:
    echo 1. 仓库地址不正确
    echo 2. GitHub权限不足
    echo 3. 网络连接问题
    echo 4. 需要登录验证
    echo.
    echo 请检查后重试
)

echo.
echo 按任意键退出...
pause >nul
