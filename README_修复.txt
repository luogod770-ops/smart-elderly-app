# 一键修复 Gradle Wrapper 并构建 APK

## 使用方法

1. **双击运行此脚本**
2. **等待修复完成**
3. **查看构建结果**

## 脚本功能

- ✓ 自动下载 gradle-wrapper.jar
- ✓ 验证 Gradle Wrapper 文件
- ✓ 运行 assembleDebug 构建
- ✓ 显示 APK 输出位置

## 故障排除

如果脚本失败，请查看：
- `GRADLE_WRAPPER_修复指南.md` - 详细修复步骤
- `gradle-wrapper.log` - 错误日志

## APK 输出位置

构建成功后，APK 文件位于：
```
app\build\outputs\apk\debug\app-debug.apk
```
