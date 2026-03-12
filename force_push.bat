@echo off
chcp 65001 >nul

echo.
echo ======================================
echo 强制推送到GitHub
echo ======================================
echo.

cd /d c:\Users\jd\CodeBuddy\20260311175444

echo [1/5] 检查Git状态...
git status
echo.

echo [2/5] 查看分支...
git branch -a
echo.

echo [3/5] 查看远程仓库...
git remote -v
echo.

echo [4/5] 尝试推送...
git push -u origin main --force
echo.

if %errorlevel% equ 0 (
    echo [成功] 推送成功!
) else (
    echo [失败] 推送失败,错误码: %errorlevel%
    echo.
    echo 尝试重新初始化Git...
)

echo.
pause
