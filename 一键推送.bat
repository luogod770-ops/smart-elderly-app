@echo off
chcp 65001 >nul

echo.
echo ======================================
echo 智慧养老社区APP - 推送到GitHub
echo ======================================
echo.
echo 即将执行推送脚本...
echo.

cd /d c:\Users\jd\CodeBuddy\20260311175444

powershell -ExecutionPolicy Bypass -File quick_push.ps1

if %errorlevel% neq 0 (
    echo.
    echo 脚本执行出错
    echo.
)

echo.
pause
