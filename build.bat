@echo off
chcp 65001 >nul
echo ====================================
echo 智慧养老社区APP - 构建脚本
echo ====================================
echo.

echo [1/3] 检查Java环境...
java -version
if %errorlevel% neq 0 (
    echo [错误] 未检测到Java环境，请先安装JDK 17
    pause
    exit /b 1
)
echo [OK] Java环境检查通过
echo.

echo [2/3] 开始构建Android APK...
call gradlew.bat assembleDebug
if %errorlevel% neq 0 (
    echo [错误] 构建失败
    pause
    exit /b 1
)
echo [OK] APK构建成功
echo.

echo [3/3] 查找生成的APK文件...
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo.
    echo ====================================
    echo 构建完成！
    echo APK文件位置: app\build\outputs\apk\debug\app-debug.apk
    echo ====================================
    echo.
    explorer app\build\outputs\apk\debug\
) else (
    echo [警告] 未找到APK文件，请检查构建日志
)
echo.

pause
