# 重新初始化Git并推送

Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "重新初始化Git并推送到GitHub" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

Set-Location "c:\Users\jd\CodeBuddy\20260311175444"

Write-Host "[警告] 这将删除现有的Git历史并重新初始化" -ForegroundColor Red
Write-Host ""

$confirm = Read-Host "是否继续? (输入 YES 确认)"
if ($confirm -ne "YES") {
    Write-Host "已取消操作" -ForegroundColor Yellow
    exit 0
}

Write-Host ""
Write-Host "[1/6] 备份.git目录..." -ForegroundColor Yellow
if (Test-Path ".git") {
    Move-Item ".git" ".git.backup" -Force
    Write-Host "✓ 已备份.git目录" -ForegroundColor Green
}

Write-Host ""
Write-Host "[2/6] 重新初始化Git仓库..." -ForegroundColor Yellow
git init
Write-Host "✓ Git仓库已初始化" -ForegroundColor Green

Write-Host ""
Write-Host "[3/6] 添加所有文件..." -ForegroundColor Yellow
git add .
Write-Host "✓ 文件已添加" -ForegroundColor Green

Write-Host ""
Write-Host "[4/6] 提交代码..." -ForegroundColor Yellow
git commit -m "智慧养老社区APP - 完整项目代码

包含功能:
- 社区交流模块(版块、帖子、评论)
- 设备管理模块(手环绑定、蓝牙连接)
- 会员系统(套餐、订单、支付)
- 三方服务集成(短信、IM、地图)

技术栈:
- 后端: Spring Boot + Kotlin + MyBatis-Plus
- 前端: Android原生 + Kotlin
- 数据库: MySQL

API接口: 47个
数据库表: 20张"
Write-Host "✓ 代码已提交" -ForegroundColor Green

Write-Host ""
Write-Host "[5/6] 添加远程仓库..." -ForegroundColor Yellow
git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git
Write-Host "✓ 远程仓库已添加" -ForegroundColor Green

Write-Host ""
Write-Host "[6/6] 推送到GitHub..." -ForegroundColor Yellow
Write-Host "正在推送,请稍候..." -ForegroundColor Cyan

git push -u origin main --force

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "======================================" -ForegroundColor Green
    Write-Host "✓ 推送成功!" -ForegroundColor Green
    Write-Host "======================================" -ForegroundColor Green
    Write-Host ""
    Write-Host "仓库地址: https://github.com/luogod770-ops/smart-elderly-app" -ForegroundColor Cyan
    Write-Host ""

    $openRepo = Read-Host "是否打开GitHub仓库? (Y/N)"
    if ($openRepo -eq "Y" -or $openRepo -eq "y") {
        Start-Process "https://github.com/luogod770-ops/smart-elderly-app"
        Write-Host "✓ 已在浏览器中打开仓库" -ForegroundColor Green
    }
} else {
    Write-Host ""
    Write-Host "✗ 推送失败" -ForegroundColor Red
    Write-Host ""
    Write-Host "错误码: $LASTEXITCODE" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "可能的原因:" -ForegroundColor Yellow
    Write-Host "1. 仓库地址不正确" -ForegroundColor White
    Write-Host "2. GitHub权限不足" -ForegroundColor White
    Write-Host "3. 网络连接问题" -ForegroundColor White
    Write-Host "4. 需要登录验证" -ForegroundColor White
    Write-Host ""
    Write-Host "请检查后重试" -ForegroundColor Yellow
}

Write-Host ""
