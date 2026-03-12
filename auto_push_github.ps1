# 智慧养老社区APP - 自动推送到GitHub

Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "智慧养老社区APP - 推送到GitHub" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

# 进入项目目录
Set-Location "c:\Users\jd\CodeBuddy\20260311175444"

# 检查Git状态
Write-Host "[检查] Git仓库状态..." -ForegroundColor Yellow
$status = git status 2>&1
if ($LASTEXITCODE -ne 0) {
    Write-Host "错误: Git仓库未初始化" -ForegroundColor Red
    exit 1
}

# 检查是否已有远程仓库
$remotes = git remote 2>&1
$hasRemote = $remotes -match "origin"

if ($hasRemote) {
    Write-Host "检测到已配置的远程仓库" -ForegroundColor Green
    Write-Host "远程仓库地址: $remotes" -ForegroundColor Cyan
    Write-Host ""

    $useExisting = Read-Host "是否使用已有远程仓库? (Y/N)"
    if ($useExisting -eq "Y" -or $useExisting -eq "y") {
        Write-Host ""
        Write-Host "[1/3] 拉取最新代码..." -ForegroundColor Yellow
        git pull origin main 2>&1 | Out-Null

        Write-Host "[2/3] 推送代码到GitHub..." -ForegroundColor Yellow
        git push -u origin main

        if ($LASTEXITCODE -eq 0) {
            ShowSuccessMessage
            exit 0
        } else {
            ShowErrorMessage
            exit 1
        }
    }
}

Write-Host "[1/4] 请输入您的GitHub仓库地址" -ForegroundColor Yellow
Write-Host ""
Write-Host "请按以下格式输入:" -ForegroundColor White
Write-Host "https://github.com/你的用户名/仓库名.git" -ForegroundColor Green
Write-Host ""
Write-Host "示例:" -ForegroundColor White
Write-Host "https://github.com/johndoe/smart-elderly-app.git" -ForegroundColor Green
Write-Host ""

$repoUrl = Read-Host "请输入GitHub仓库地址"

if ([string]::IsNullOrWhiteSpace($repoUrl)) {
    Write-Host ""
    Write-Host "错误: 仓库地址不能为空" -ForegroundColor Red
    exit 1
}

# 验证URL格式
if ($repoUrl -notmatch "^https://github\.com/[^/]+/[^/]+\.git$") {
    Write-Host ""
    Write-Host "警告: 仓库地址格式可能不正确" -ForegroundColor Yellow
    $confirm = Read-Host "是否继续? (Y/N)"
    if ($confirm -ne "Y" -and $confirm -ne "y") {
        exit 1
    }
}

Write-Host ""
Write-Host "[2/4] 添加远程仓库..." -ForegroundColor Yellow

# 如果已存在origin,先删除
if (git remote | Select-String "origin") {
    git remote remove origin
}

git remote add origin $repoUrl

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "错误: 添加远程仓库失败" -ForegroundColor Red
    Write-Host "请检查仓库地址是否正确" -ForegroundColor Yellow
    exit 1
}

Write-Host "✓ 远程仓库已添加" -ForegroundColor Green
Write-Host ""

Write-Host "[3/4] 设置主分支..." -ForegroundColor Yellow
git branch -M main
Write-Host "✓ 主分支已设置" -ForegroundColor Green
Write-Host ""

Write-Host "[4/4] 推送代码到GitHub..." -ForegroundColor Yellow
Write-Host "注意: 第一次推送时需要登录GitHub" -ForegroundColor Cyan
Write-Host "请按照提示完成登录验证" -ForegroundColor Cyan
Write-Host ""

git push -u origin main

if ($LASTEXITCODE -eq 0) {
    ShowSuccessMessage
} else {
    ShowErrorMessage
}

function ShowSuccessMessage {
    Write-Host ""
    Write-Host "======================================" -ForegroundColor Green
    Write-Host "✓ 代码推送成功!" -ForegroundColor Green
    Write-Host "======================================" -ForegroundColor Green
    Write-Host ""
    Write-Host "下一步操作:" -ForegroundColor Yellow
    Write-Host "1. 访问您的GitHub仓库" -ForegroundColor White
    Write-Host "2. 点击 'Actions' 标签" -ForegroundColor White
    Write-Host "3. 等待APK构建完成 (约5-10分钟)" -ForegroundColor White
    Write-Host "4. 构建完成后,下载APK文件" -ForegroundColor White
    Write-Host ""
    Write-Host "APK文件位置: Artifacts → app-debug-apk" -ForegroundColor Cyan
    Write-Host ""

    $openRepo = Read-Host "是否打开GitHub仓库? (Y/N)"
    if ($openRepo -eq "Y" -or $openRepo -eq "y") {
        $url = $repoUrl -replace '\.git$', ''
        Start-Process $url
        Write-Host "已在浏览器中打开仓库页面" -ForegroundColor Green
    }

    Write-Host ""
    Write-Host "======================================" -ForegroundColor Cyan
    Write-Host "构建说明" -ForegroundColor Cyan
    Write-Host "======================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "首次构建可能需要较长时间 (10-15分钟)" -ForegroundColor Yellow
    Write-Host "后续构建会更快 (5-10分钟)" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "构建过程中可以查看日志了解进度" -ForegroundColor Cyan
    Write-Host "构建成功后会显示绿色勾号" -ForegroundColor Cyan
    Write-Host ""
}

function ShowErrorMessage {
    Write-Host ""
    Write-Host "✗ 推送失败" -ForegroundColor Red
    Write-Host ""
    Write-Host "可能的原因:" -ForegroundColor Yellow
    Write-Host "1. 仓库地址不正确" -ForegroundColor White
    Write-Host "2. GitHub权限不足" -ForegroundColor White
    Write-Host "3. 网络连接问题" -ForegroundColor White
    Write-Host "4. 需要登录验证" -ForegroundColor White
    Write-Host ""
    Write-Host "解决方案:" -ForegroundColor Yellow
    Write-Host "1. 检查仓库地址是否正确" -ForegroundColor White
    Write-Host "2. 确认您有该仓库的写入权限" -ForegroundColor White
    Write-Host "3. 尝试使用Personal Access Token" -ForegroundColor White
    Write-Host "4. 检查网络连接" -ForegroundColor White
    Write-Host ""
    Write-Host "如需帮助,请查看: FINAL_STEP.md" -ForegroundColor Cyan
    Write-Host ""
}

Write-Host ""
Write-Host "脚本执行完成" -ForegroundColor Cyan
Write-Host ""
