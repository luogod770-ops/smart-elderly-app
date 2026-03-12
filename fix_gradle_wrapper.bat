@echo off
chcp 65001 >nul
echo ========================================
echo 修复 Gradle Wrapper
echo ========================================
echo.

cd /d "%~dp0"
echo 当前目录: %CD%
echo.

echo [1/3] 检查目录结构...
if not exist "gradle\wrapper" (
    mkdir gradle\wrapper
    echo ✓ 创建 gradle\wrapper 目录
) else (
    echo ✓ gradle\wrapper 目录已存在
)
echo.

echo [2/3] 下载 gradle-wrapper.jar...
powershell -Command "Invoke-WebRequest -Uri 'https://github.com/gradle/gradle/raw/v8.3.0/gradle/wrapper/gradle-wrapper.jar' -OutFile 'gradle\wrapper\gradle-wrapper.jar'"

if exist "gradle\wrapper\gradle-wrapper.jar" (
    echo ✓ gradle-wrapper.jar 下载成功
) else (
    echo ✗ 下载失败，尝试备用源...
    powershell -Command "Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.3-bin.zip' -OutFile 'gradle-8.3-bin.zip'"
    echo 请手动将 gradle-8.3-bin.zip 中的 gradle-wrapper.jar 复制到 gradle\wrapper\ 目录
)
echo.

echo [3/3] 验证文件...
if exist "gradle\wrapper\gradle-wrapper.properties" (
    echo ✓ gradle-wrapper.properties 存在
) else (
    echo ✗ gradle-wrapper.properties 不存在
)

if exist "gradlew" (
    echo ✓ gradlew 存在
) else (
    echo ✗ gradlew 不存在
)

if exist "gradlew.bat" (
    echo ✓ gradlew.bat 存在
) else (
    echo ✗ gradlew.bat 不存在
)

echo.
echo ========================================
echo 修复完成！
echo ========================================
echo.
echo 现在可以尝试运行: gradlew assembleDebug
echo.
pause
