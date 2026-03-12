@echo off
echo ====================================
echo 智慧养老社区APP - APK构建脚本
echo ====================================
echo.

cd /d "%~dp0"

echo [1/3] 清理构建缓存...
call gradlew clean
if %errorlevel% neq 0 (
    echo 清理失败!
    pause
    exit /b 1
)

echo [2/3] 构建Debug APK...
call gradlew assembleDebug
if %errorlevel% neq 0 (
    echo Debug构建失败!
    pause
    exit /b 1
)

echo [3/3] 构建Release APK...
call gradlew assembleRelease
if %errorlevel% neq 0 (
    echo Release构建失败!
    pause
    exit /b 1
)

echo.
echo ====================================
echo 构建成功!
echo ====================================
echo.
echo APK输出位置:
echo Debug版本: app\build\outputs\apk\debug\app-debug.apk
echo Release版本: app\build\outputs\apk\release\app-release.apk
echo.
echo 正在打开输出目录...
explorer "app\build\outputs\apk"
echo.
pause
