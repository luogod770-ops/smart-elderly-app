@echo off
chcp 65001 >nul
echo ========================================
echo 初始化 Gradle Wrapper
echo ========================================
echo.

cd /d "%~dp0"

echo [1/4] 创建目录结构...
if not exist gradle\wrapper mkdir gradle\wrapper
echo ✓ 目录创建完成
echo.

echo [2/4] 尝试下载 gradle-wrapper.jar...
powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; $ProgressPreference = 'SilentlyContinue'; try { Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-8.3-bin.zip' -OutFile 'gradle-8.3-bin.zip' -UseBasicParsing; Write-Host 'Download success'; exit 0 } catch { Write-Host 'Download failed:' $_.Exception.Message; exit 1 }}"

if %errorlevel% equ 0 (
    echo ✓ Gradle 下载成功

    echo [3/4] 从 Gradle 包中提取 gradle-wrapper.jar...
    powershell -Command "& {Add-Type -AssemblyName System.IO.Compression.FileSystem; [System.IO.Compression.ZipFile]::ExtractToDirectory('gradle-8.3-bin.zip', '.'); Copy-Item 'gradle-8.3/lib/gradle-wrapper-8.3.jar' 'gradle/wrapper/gradle-wrapper.jar'; Remove-Item -Recurse -Force 'gradle-8.3', 'gradle-8.3-bin.zip'; Write-Host 'Extract complete'}"

    if exist gradle\wrapper\gradle-wrapper.jar (
        echo ✓ gradle-wrapper.jar 提取成功
        dir gradle\wrapper\gradle-wrapper.jar
    ) else (
        echo ✗ 提取失败
    )
) else (
    echo ✗ Gradle 下载失败，尝试其他方法...
    echo.
    echo 请手动下载：
    echo 1. 访问：https://services.gradle.org/distributions/gradle-8.3-bin.zip
    echo 2. 下载后解压
    echo 3. 复制 gradle-8.3\lib\gradle-wrapper-8.3.jar 到 gradle\wrapper\gradle-wrapper.jar
)

echo.
echo [4/4] 验证文件...
if exist gradle\wrapper\gradle-wrapper.jar (
    echo [√] gradle-wrapper.jar 存在
    if exist gradle\wrapper\gradle-wrapper.properties (
        echo [√] gradle-wrapper.properties 存在
    ) else (
        echo [×] gradle-wrapper.properties 不存在
    )
    if exist gradlew.bat (
        echo [√] gradlew.bat 存在
    ) else (
        echo [×] gradlew.bat 不存在
    )
) else (
    echo [×] gradle-wrapper.jar 不存在
)

echo.
echo ========================================
echo 接下来：
echo ========================================
echo 1. 如果 gradle-wrapper.jar 存在，运行: gradlew.bat assembleDebug
echo 2. 如果不存在，请查看上面的手动下载步骤
echo 3. 或直接推送代码到 GitHub，使用 GitHub Actions 构建
echo.
pause
