# Git诊断脚本

Write-Host "======================================" -ForegroundColor Cyan
Write-Host "Git状态诊断" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

Set-Location "c:\Users\jd\CodeBuddy\20260311175444"

Write-Host "[1] 检查Git目录..." -ForegroundColor Yellow
if (Test-Path ".git") {
    Write-Host "✓ .git目录存在" -ForegroundColor Green
} else {
    Write-Host "✗ .git目录不存在" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "[2] 检查分支..." -ForegroundColor Yellow
$branches = git branch 2>&1
Write-Host $branches -ForegroundColor Cyan

Write-Host ""
Write-Host "[3] 检查提交历史..." -ForegroundColor Yellow
$log = git log --oneline -5 2>&1
Write-Host $log -ForegroundColor Cyan

Write-Host ""
Write-Host "[4] 检查远程仓库..." -ForegroundColor Yellow
$remotes = git remote -v 2>&1
Write-Host $remotes -ForegroundColor Cyan

Write-Host ""
Write-Host "[5] 检查未提交的更改..." -ForegroundColor Yellow
$status = git status 2>&1
Write-Host $status -ForegroundColor Cyan

Write-Host ""
Write-Host "[6] 检查已跟踪的文件..." -ForegroundColor Yellow
$tracked = git ls-files | Measure-Object
Write-Host "已跟踪文件数: $($tracked.Count)" -ForegroundColor Cyan

Write-Host ""
Write-Host "[7] 尝试推送..." -ForegroundColor Yellow
Write-Host "正在推送到GitHub..." -ForegroundColor Cyan

$pushResult = git push -u origin main 2>&1
Write-Host $pushResult -ForegroundColor White

if ($LASTEXITCODE -eq 0) {
    Write-Host "✓ 推送成功" -ForegroundColor Green
} else {
    Write-Host "✗ 推送失败 (错误码: $LASTEXITCODE)" -ForegroundColor Red
}

Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "诊断完成" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""
