@echo off
chcp 65001 >nul
echo ========================================
echo 检查 Gradle Wrapper 文件
echo ========================================
echo.

if exist gradlew.bat (
    echo [√] gradlew.bat 存在
) else (
    echo [×] gradlew.bat 不存在
)

if exist gradle\wrapper\gradle-wrapper.jar (
    echo [√] gradle-wrapper.jar 存在
) else (
    echo [×] gradle-wrapper.jar 不存在 - 需要下载！
)

if exist gradle\wrapper\gradle-wrapper.properties (
    echo [√] gradle-wrapper.properties 存在
) else (
    echo [×] gradle-wrapper.properties 不存在
)

if exist build.gradle.kts (
    echo [√] build.gradle.kts 存在
) else (
    echo [×] build.gradle.kts 不存在
)

if exist app\build.gradle.kts (
    echo [√] app/build.gradle.kts 存在
) else (
    echo [×] app/build.gradle.kts 不存在
)

echo.
echo ========================================
echo 建议：使用方案一（GitHub Actions）
echo ========================================
echo.
echo 详细步骤请查看: 问题解决方案.md
echo.
pause
