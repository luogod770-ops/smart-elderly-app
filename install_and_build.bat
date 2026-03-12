@echo off
REM 智慧养老社区APP - 自动安装和构建脚本 (Windows批处理版本)
chcp 65001 >nul

echo ======================================
echo 智慧养老社区APP - 自动安装和构建
echo ======================================
echo.

REM 检查管理员权限
net session >nul 2>&1
if %errorLevel% neq 0 (
    echo [警告] 没有管理员权限,某些操作可能失败
    echo.
)

echo [1/5] 检查Java环境...
where java >nul 2>&1
if %errorLevel% equ 0 (
    echo [成功] Java已安装
    java -version
) else (
    echo [错误] Java未安装
    echo.
    echo 请下载并安装Java JDK 17:
    echo https://www.oracle.com/java/technologies/downloads/
    echo.
    echo 下载后请重新运行此脚本
    pause
    exit /b 1
)

echo.
echo [2/5] 检查Android Studio...
if exist "C:\Program Files\Android\Android Studio\bin\studio64.exe" (
    echo [成功] Android Studio已安装
) else if exist "C:\Program Files (x86)\Android\Android Studio\bin\studio64.exe" (
    echo [成功] Android Studio已安装
) else (
    echo [提示] Android Studio未安装
    echo.
    echo 正在尝试使用便携版Android Studio...
    
    set STUDIO_DIR=C:\AndroidStudio
    set STUDIO_ZIP=%TEMP%\android-studio.zip
    set STUDIO_URL=https://redirector.gvt1.com/edgedl/android/studio/ide-zips/2023.1.1.33/android-studio-2023.1.1.33-windows.zip
    
    echo 下载Android Studio (约1.2GB,请耐心等待)...
    
    REM 检查是否有curl
    where curl >nul 2>&1
    if %errorLevel% equ 0 (
        curl -L -o "%STUDIO_ZIP%" %STUDIO_URL%
    ) else (
        REM 检查是否有PowerShell
        echo 使用PowerShell下载...
        powershell -Command "Invoke-WebRequest -Uri '%STUDIO_URL%' -OutFile '%STUDIO_ZIP%' -UseBasicParsing"
    )
    
    if exist "%STUDIO_ZIP%" (
        echo [成功] Android Studio下载完成
        echo 解压到 %STUDIO_DIR%...
        
        REM 使用PowerShell解压
        powershell -Command "Expand-Archive -Path '%STUDIO_ZIP%' -DestinationPath '%STUDIO_DIR%' -Force"
        
        if exist "%STUDIO_DIR%\bin\studio64.exe" (
            echo [成功] Android Studio解压完成
            
            REM 创建桌面快捷方式
            powershell -Command "$WshShell = New-Object -ComObject WScript.Shell; $Shortcut = $WshShell.CreateShortcut('%USERPROFILE%\Desktop\Android Studio.lnk'); $Shortcut.TargetPath = '%STUDIO_DIR%\bin\studio64.exe'; $Shortcut.Save()"
            echo [成功] 已创建桌面快捷方式
        ) else (
            echo [错误] Android Studio解压失败
        )
    ) else (
        echo [错误] Android Studio下载失败
        echo.
        echo 请手动下载: https://developer.android.com/studio
        echo 下载后解压到: %STUDIO_DIR%
    )
)

echo.
echo [3/5] Android Studio设置...
echo 请执行以下操作:
echo 1. 启动Android Studio (双击桌面图标)
echo 2. 完成初始设置向导
echo 3. 等待SDK下载完成 (可能需要10-30分钟)
echo 4. 关闭Android Studio
echo.
pause

echo.
echo [4/5] 配置环境变量...
set STUDIO_BIN=C:\AndroidStudio\bin

REM 添加到当前会话的PATH
set PATH=%PATH%;%STUDIO_BIN%

echo [成功] 环境变量已配置

echo.
echo [5/5] 构建APK...

set PROJECT_DIR=c:\Users\jd\CodeBuddy\20260311175444
cd /d %PROJECT_DIR%

if exist gradlew.bat (
    echo 清理项目...
    call gradlew.bat clean
    
    echo.
    echo 构建Debug版本APK...
    call gradlew.bat assembleDebug
    
    if %errorLevel% equ 0 (
        echo.
        echo ======================================
        echo [成功] APK构建成功!
        echo ======================================
        echo.
        echo APK文件位置:
        echo %PROJECT_DIR%\app\build\outputs\apk\debug\app-debug.apk
        echo.
        
        if exist "%PROJECT_DIR%\app\build\outputs\apk\debug\app-debug.apk" (
            echo 打开APK所在文件夹...
            explorer "%PROJECT_DIR%\app\build\outputs\apk\debug\"
        )
    ) else (
        echo.
        echo [错误] APK构建失败
        echo 请检查错误信息
    )
) else (
    echo [错误] 未找到gradlew.bat文件
    echo 请确认项目路径正确: %PROJECT_DIR%
)

echo.
echo ======================================
echo 脚本执行完成
echo ======================================
echo.
echo 如需帮助,请查看: APK_BUILD_GUIDE_DETAILED.md
echo.
pause
