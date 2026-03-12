@echo off
chcp 65001 >nul

echo.
echo ======================================
echo 检查Git状态并推送
echo ======================================
echo.

cd /d c:\Users\jd\CodeBuddy\20260311175444

echo [检查1] Git目录状态...
if exist .git (
    echo Git目录存在
) else (
    echo Git目录不存在,需要初始化
    goto :init
)
echo.

echo [检查2] 已跟踪文件...
for /f %%i in ('git ls-files ^| find /c /v ""') do set tracked=%%i
echo 已跟踪文件数: %tracked%

if %tracked% LEQ 0 (
    echo 没有已跟踪的文件,需要重新添加
    goto :init
)
echo.

echo [检查3] 提交历史...
git log --oneline -1 2>nul
if %errorlevel% neq 0 (
    echo 没有提交历史,需要重新提交
    goto :init
)
echo.

echo [检查4] 远程仓库配置...
git remote -v
echo.

echo [检查5] 尝试推送...
echo 正在推送到: https://github.com/luogod770-ops/smart-elderly-app.git
echo.

git push -u origin main --force

if %errorlevel% equ 0 (
    echo.
    echo ======================================
    echo 推送成功!
    echo ======================================
    echo.
    echo 请访问: https://github.com/luogod770-ops/smart-elderly-app
    echo.
    start "" "https://github.com/luogod770-ops/smart-elderly-app"
) else (
    echo.
    echo ======================================
    echo 推送失败
    echo ======================================
    echo.
    echo 尝试重新初始化Git...
    goto :init
)

goto :end

:init
echo.
echo [重新初始化Git]
echo.

echo [1/4] 备份并删除现有Git配置...
if exist .git (
    move .git .git.backup >nul 2>&1
)
echo 已备份.git目录
echo.

echo [2/4] 重新初始化Git...
git init
echo Git已初始化
echo.

echo [3/4] 添加所有文件...
git add .
echo 文件已添加
echo.

echo [4/4] 提交并推送...
git commit -m "智慧养老社区APP - 完整项目代码

包含功能:
- 社区交流模块(版块、帖子、评论)
- 设备管理模块(手环绑定、蓝牙连接)
- 会员系统(套餐、订单、支付)
- 三方服务集成(短信、IM、地图)"

git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git
git branch -M main

echo.
echo 正在推送到GitHub...
git push -u origin main --force

if %errorlevel% equ 0 (
    echo.
    echo ======================================
    echo 推送成功!
    echo ======================================
    echo.
    echo 请访问: https://github.com/luogod770-ops/smart-elderly-app
    echo.
    start "" "https://github.com/luogod770-ops/smart-elderly-app"
) else (
    echo.
    echo ======================================
    echo 推送失败
    echo ======================================
    echo.
    echo 请手动执行以下命令:
    echo cd c:\Users\jd\CodeBuddy\20260311175444
    echo git push -u origin main --force
)

:end
echo.
pause
