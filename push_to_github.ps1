# 智慧养老社区APP - 推送到GitHub脚本

Write-Host "======================================" -ForegroundColor Cyan
Write-Host "推送代码到GitHub" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

# 进入项目目录
Set-Location "c:\Users\jd\CodeBuddy\20260311175444"

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
    Write-Host "错误: 仓库地址不能为空" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "[2/4] 添加远程仓库..." -ForegroundColor Yellow
git remote add origin $repoUrl

if ($LASTEXITCODE -ne 0) {
    Write-Host "错误: 添加远程仓库失败" -ForegroundColor Red
    exit 1
}

Write-Host "✓ 远程仓库已添加" -ForegroundColor Green
Write-Host ""

Write-Host "[3/4] 推送代码到GitHub..." -ForegroundColor Yellow
Write-Host "注意: 第一次推送时需要登录GitHub" -ForegroundColor Cyan
Write-Host ""

# 设置主分支
git branch -M main

# 推送代码
git push -u origin main

if ($LASTEXITCODE -eq 0) {
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
    Write-Host "按任意键打开GitHub仓库..." -ForegroundColor Yellow
    $null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")

    # 提取仓库地址并打开浏览器
    $url = $repoUrl -replace '\.git$', ''
    Start-Process $url

} else {
    Write-Host ""
    Write-Host "✗ 推送失败" -ForegroundColor Red
    Write-Host ""
    Write-Host "可能的原因:" -ForegroundColor Yellow
    Write-Host "1. 仓库地址不正确" -ForegroundColor White
    Write-Host "2. GitHub权限不足" -ForegroundColor White
    Write-Host "3. 网络连接问题" -ForegroundColor White
    Write-Host ""
    Write-Host "请检查后重试" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "脚本执行完成" -ForegroundColor Cyan
Write-Host ""
