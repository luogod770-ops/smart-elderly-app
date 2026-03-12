# 智慧养老社区APP - 自动安装和构建脚本
# 此脚本将自动下载必要的软件并构建APK

# 设置错误处理
$ErrorActionPreference = "Stop"

Write-Host "======================================" -ForegroundColor Cyan
Write-Host "智慧养老社区APP - 自动安装和构建" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

# 检查管理员权限
$isAdmin = ([Security.Principal.WindowsPrincipal] [Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)
if (-not $isAdmin) {
    Write-Host "警告: 没有管理员权限,某些操作可能需要手动确认" -ForegroundColor Yellow
    Write-Host ""
}

# 1. 检查Java环境
Write-Host "[1/5] 检查Java环境..." -ForegroundColor Green
$javaInstalled = $false
try {
    $javaVersion = & java -version 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✓ Java已安装: $javaVersion[0]" -ForegroundColor Green
        $javaInstalled = $true
    }
} catch {
    Write-Host "✗ Java未安装" -ForegroundColor Red
}

if (-not $javaInstalled) {
    Write-Host "正在下载Java JDK 17..." -ForegroundColor Yellow
    $javaUrl = "https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.exe"
    $javaInstaller = "$env:TEMP\jdk-installer.exe"
    
    try {
        Invoke-WebRequest -Uri $javaUrl -OutFile $javaInstaller -UseBasicParsing
        Write-Host "✓ Java下载完成" -ForegroundColor Green
        Write-Host "请手动运行 $javaInstaller 安装Java JDK" -ForegroundColor Yellow
        Write-Host "按任意键继续安装Android Studio..." -ForegroundColor Yellow
        $null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
    } catch {
        Write-Host "✗ Java下载失败: $_" -ForegroundColor Red
        Write-Host "请手动下载Java: https://www.oracle.com/java/technologies/downloads/" -ForegroundColor Yellow
    }
}

# 2. 检查Android Studio
Write-Host ""
Write-Host "[2/5] 检查Android Studio..." -ForegroundColor Green
$studioInstalled = $false
$studioPaths = @(
    "C:\Program Files\Android\Android Studio\bin\studio64.exe",
    "C:\Program Files (x86)\Android\Android Studio\bin\studio64.exe"
)

foreach ($path in $studioPaths) {
    if (Test-Path $path) {
        Write-Host "✓ Android Studio已安装: $path" -ForegroundColor Green
        $studioInstalled = $true
        break
    }
}

if (-not $studioInstalled) {
    Write-Host "正在下载Android Studio..." -ForegroundColor Yellow
    $studioUrl = "https://redirector.gvt1.com/edgedl/android/studio/ide-zips/2023.1.1.33/android-studio-2023.1.1.33-windows.zip"
    $studioZip = "$env:TEMP\android-studio.zip"
    $studioDir = "C:\AndroidStudio"
    
    try {
        Write-Host "下载Android Studio (约1.2GB,请耐心等待)..." -ForegroundColor Yellow
        Invoke-WebRequest -Uri $studioUrl -OutFile $studioZip -UseBasicParsing
        Write-Host "✓ Android Studio下载完成" -ForegroundColor Green
        
        Write-Host "解压Android Studio到 $studioDir..." -ForegroundColor Yellow
        Expand-Archive -Path $studioZip -DestinationPath $studioDir -Force
        Write-Host "✓ Android Studio解压完成" -ForegroundColor Green
        
        Write-Host "请手动运行: $studioDir\bin\studio64.exe" -ForegroundColor Yellow
        Write-Host "按任意键继续..." -ForegroundColor Yellow
        $null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
        
        # 创建桌面快捷方式
        $WshShell = New-Object -ComObject WScript.Shell
        $Shortcut = $WshShell.CreateShortcut("$env:USERPROFILE\Desktop\Android Studio.lnk")
        $Shortcut.TargetPath = "$studioDir\bin\studio64.exe"
        $Shortcut.Save()
        Write-Host "✓ 已创建桌面快捷方式" -ForegroundColor Green
        
    } catch {
        Write-Host "✗ Android Studio下载失败: $_" -ForegroundColor Red
        Write-Host "请手动下载: https://developer.android.com/studio" -ForegroundColor Yellow
        Write-Host "下载后解压到: $studioDir" -ForegroundColor Yellow
    }
}

# 3. 等待用户确认Android Studio已启动并完成初始化
Write-Host ""
Write-Host "[3/5] Android Studio设置..." -ForegroundColor Green
Write-Host "请执行以下操作:" -ForegroundColor Yellow
Write-Host "1. 启动Android Studio" -ForegroundColor White
Write-Host "2. 完成初始设置向导" -ForegroundColor White
Write-Host "3. 等待SDK下载完成" -ForegroundColor White
Write-Host "4. 关闭Android Studio" -ForegroundColor White
Write-Host ""
Write-Host "按任意键继续..." -ForegroundColor Yellow
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")

# 4. 设置环境变量
Write-Host ""
Write-Host "[4/5] 配置环境变量..." -ForegroundColor Green

$envPath = [Environment]::GetEnvironmentVariable("Path", "Machine")
$studioBin = "C:\AndroidStudio\bin"

if ($envPath -notlike "*$studioBin*") {
    Write-Host "添加Android Studio到PATH..." -ForegroundColor Yellow
    try {
        [Environment]::SetEnvironmentVariable("Path", "$envPath;$studioBin", "Machine")
        Write-Host "✓ 环境变量已设置 (需要重启命令行)" -ForegroundColor Green
    } catch {
        Write-Host "⚠ 需要管理员权限设置环境变量" -ForegroundColor Yellow
    }
} else {
    Write-Host "✓ 环境变量已配置" -ForegroundColor Green
}

# 5. 构建APK
Write-Host ""
Write-Host "[5/5] 构建APK..." -ForegroundColor Green

$projectDir = "c:/Users/jd/CodeBuddy/20260311175444"
$gradlewBat = "$projectDir\gradlew.bat"

if (Test-Path $gradlewBat) {
    Write-Host "开始构建APK..." -ForegroundColor Yellow
    Write-Host "项目目录: $projectDir" -ForegroundColor Cyan
    Write-Host ""
    
    try {
        Set-Location $projectDir
        
        Write-Host "清理项目..." -ForegroundColor Cyan
        & $gradlewBat clean
        
        Write-Host ""
        Write-Host "构建Debug版本APK..." -ForegroundColor Cyan
        & $gradlewBat assembleDebug
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host ""
            Write-Host "======================================" -ForegroundColor Green
            Write-Host "✓ APK构建成功!" -ForegroundColor Green
            Write-Host "======================================" -ForegroundColor Green
            Write-Host ""
            Write-Host "APK文件位置:" -ForegroundColor Cyan
            Write-Host "$projectDir\app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor Yellow
            Write-Host ""
            
            # 检查文件是否存在
            $apkPath = "$projectDir\app\build\outputs\apk\debug\app-debug.apk"
            if (Test-Path $apkPath) {
                $apkSize = (Get-Item $apkPath).Length / 1MB
                Write-Host "APK文件大小: $([math]::Round($apkSize, 2)) MB" -ForegroundColor Green
                Write-Host ""
                Write-Host "您现在可以将APK安装到设备上了!" -ForegroundColor Green
                
                # 询问是否打开文件夹
                Write-Host ""
                Write-Host "是否打开APK所在文件夹? (Y/N)" -ForegroundColor Yellow
                $response = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
                if ($response.Character -eq 'Y' -or $response.Character -eq 'y') {
                    Invoke-Item "$projectDir\app\build\outputs\apk\debug\"
                }
            }
        } else {
            Write-Host ""
            Write-Host "✗ APK构建失败,请检查错误信息" -ForegroundColor Red
        }
        
    } catch {
        Write-Host "✗ 构建过程中出现错误: $_" -ForegroundColor Red
    }
    
} else {
    Write-Host "✗ 未找到gradlew.bat文件" -ForegroundColor Red
    Write-Host "请确认项目路径正确: $projectDir" -ForegroundColor Yellow
}

# 结束
Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "脚本执行完成" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "如需帮助,请查看: APK_BUILD_GUIDE_DETAILED.md" -ForegroundColor Yellow
Write-Host ""
