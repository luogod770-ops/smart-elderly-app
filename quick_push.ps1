# 智慧养老社区APP - 一键推送脚本

Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "智慧养老社区APP - 推送到GitHub" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

# 进入项目目录
Set-Location "c:\Users\jd\CodeBuddy\20260311175444"

# 显示当前状态
Write-Host "当前状态:" -ForegroundColor Yellow
Write-Host "- Git仓库: 已初始化" -ForegroundColor Green
Write-Host "- 代码提交: 已完成" -ForegroundColor Green
Write-Host "- Actions配置: 已就绪" -ForegroundColor Green
Write-Host ""

Write-Host "======================================" -ForegroundColor Yellow
Write-Host "需要您的GitHub仓库地址" -ForegroundColor Yellow
Write-Host "======================================" -ForegroundColor Yellow
Write-Host ""

Write-Host "如果您还没有创建GitHub仓库,请先创建:" -ForegroundColor Cyan
Write-Host "1. 访问: https://github.com/new" -ForegroundColor White
Write-Host "2. 仓库名: smart-elderly-app" -ForegroundColor White
Write-Host "3. 不要初始化README" -ForegroundColor White
Write-Host ""
Write-Host "创建后,复制仓库的URL,格式如:" -ForegroundColor White
Write-Host "https://github.com/你的用户名/smart-elderly-app.git" -ForegroundColor Green
Write-Host ""

# 检查是否已有远程仓库
$remotes = git remote 2>&1
if ($remotes -match "origin") {
    Write-Host "检测到已配置的远程仓库:" -ForegroundColor Green
    Write-Host $remotes -ForegroundColor Cyan
    Write-Host ""

    $useExisting = Read-Host "是否使用已有远程仓库? (Y/N)"
    if ($useExisting -eq "Y" -or $useExisting -eq "y") {
        Write-Host ""
        Write-Host "[1/3] 推送代码到GitHub..." -ForegroundColor Yellow
        Write-Host "注意: 需要登录GitHub" -ForegroundColor Cyan
        Write-Host ""

        git push -u origin main

        if ($LASTEXITCODE -eq 0) {
            ShowSuccessMessage $remotes
            exit 0
        } else {
            ShowErrorMessage
            exit 1
        }
    }
}

Write-Host "请输入您的GitHub仓库地址:" -ForegroundColor Yellow
$repoUrl = Read-Host "仓库地址"

if ([string]::IsNullOrWhiteSpace($repoUrl)) {
    Write-Host ""
    Write-Host "错误: 仓库地址不能为空" -ForegroundColor Red
    Write-Host ""
    Write-Host "如需帮助,请查看: FINAL_STEP.md" -ForegroundColor Yellow
    pause
    exit 1
}

Write-Host ""
Write-Host "[1/3] 添加远程仓库..." -ForegroundColor Yellow

# 如果已存在origin,先删除
if (git remote | Select-String "origin") {
    git remote remove origin
}

git remote add origin $repoUrl

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "错误: 添加远程仓库失败" -ForegroundColor Red
    pause
    exit 1
}

Write-Host "✓ 远程仓库已添加" -ForegroundColor Green
Write-Host ""

Write-Host "[2/3] 设置主分支..." -ForegroundColor Yellow
git branch -M main
Write-Host "✓ 主分支已设置" -ForegroundColor Green
Write-Host ""

Write-Host "[3/3] 推送代码到GitHub..." -ForegroundColor Yellow
Write-Host "注意: 需要登录GitHub" -ForegroundColor Cyan
Write-Host "请按照提示完成登录验证" -ForegroundColor Cyan
Write-Host ""

git push -u origin main

if ($LASTEXITCODE -eq 0) {
    ShowSuccessMessage $repoUrl
} else {
    ShowErrorMessage
}

function ShowSuccessMessage($url) {
    Write-Host ""
    Write-Host "======================================" -ForegroundColor Green
    Write-Host "✓ 代码推送成功!" -ForegroundColor Green
    Write-Host "======================================" -ForegroundColor Green
    Write-Host ""
    Write-Host "下一步操作:" -ForegroundColor Yellow
    Write-Host "1. 点击下方链接打开GitHub仓库" -ForegroundColor White
    Write-Host "2. 点击 'Actions' 标签" -ForegroundColor White
    Write-Host "3. 等待APK构建完成 (约5-10分钟)" -ForegroundColor White
    Write-Host "4. 构建完成后,下载APK文件" -ForegroundColor White
    Write-Host ""

    $cleanUrl = $url -replace '\.git$', ''
    Write-Host "仓库地址: $cleanUrl" -ForegroundColor Cyan

    $openNow = Read-Host "是否立即打开仓库? (Y/N)"
    if ($openNow -eq "Y" -or $openNow -eq "y") {
        Start-Process $cleanUrl
        Write-Host "✓ 已在浏览器中打开仓库" -ForegroundColor Green
    }

    Write-Host ""
    Write-Host "======================================" -ForegroundColor Cyan
    Write-Host "APK下载说明" -ForegroundColor Cyan
    Write-Host "======================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "1. 在GitHub仓库页面,点击 'Actions' 标签" -ForegroundColor White
    Write-Host "2. 点击最新的构建任务" -ForegroundColor White
    Write-Host "3. 滚动到页面底部 'Artifacts' 部分" -ForegroundColor White
    Write-Host "4. 下载 'app-debug-apk' 文件" -ForegroundColor White
    Write-Host ""
    Write-Host "构建时间: 首次10-15分钟,后续5-10分钟" -ForegroundColor Yellow
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
    Write-Host "请检查后重试" -ForegroundColor Yellow
    Write-Host ""
}

Write-Host ""
