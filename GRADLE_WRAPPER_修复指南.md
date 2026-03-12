# 修复 Gradle Wrapper 问题的详细解决方案

## 问题诊断

您遇到的错误是：
```
无法找到或加载主类 org.gradle.wrapper.GradleWrapperMain
原因：java.lang.ClassNotFoundException：org.gradle.wrapper.GradleWrapperMain
```

**根本原因：** 缺少 `gradle-wrapper.jar` 文件，这是 Gradle Wrapper 的核心组件。

---

## 解决方案（推荐顺序）

### 方案一：自动修复脚本（最简单）

1. **双击运行修复脚本**
   ```
   fix_gradle_wrapper.bat
   ```

2. **等待下载完成**
   - 脚本会自动下载 gradle-wrapper.jar
   - 如果失败，会提供备用方案

3. **验证修复**
   - 检查 `gradle\wrapper\gradle-wrapper.jar` 是否存在
   - 检查 `gradle\wrapper\gradle-wrapper.properties` 是否存在

4. **运行构建**
   ```bash
   gradlew.bat assembleDebug
   ```

---

### 方案二：手动下载（如果自动修复失败）

1. **下载 gradle-wrapper.jar**
   - 访问：https://github.com/gradle/gradle/raw/v8.3.0/gradle/wrapper/gradle-wrapper.jar
   - 保存到：`gradle\wrapper\gradle-wrapper.jar`

2. **验证配置文件**
   - 确认 `gradle\wrapper\gradle-wrapper.properties` 内容：
     ```
     distributionBase=GRADLE_USER_HOME
     distributionPath=wrapper/dists
     distributionUrl=https\://services.gradle.org/distributions/gradle-8.3-bin.zip
     networkTimeout=10000
     validateDistributionUrl=true
     zipStoreBase=GRADLE_USER_HOME
     zipStorePath=wrapper/dists
     ```

3. **运行构建**
   ```bash
   gradlew.bat assembleDebug
   ```

---

### 方案三：使用 Gradle 命令重新生成 Wrapper

1. **安装 Gradle**（如果尚未安装）
   - 下载：https://gradle.org/install/
   - 解压并配置环境变量

2. **重新生成 Wrapper**
   ```bash
   gradle wrapper --gradle-version 8.3
   ```

3. **运行构建**
   ```bash
   gradlew.bat assembleDebug
   ```

---

### 方案四：通过 Git 生成 APK（如果上述方法都失败）

1. **推送到 GitHub**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/luogod770-ops/smart-elderly-app.git
   git push -u origin main
   ```

2. **使用 GitHub Actions 自动构建**
   - 创建 `.github/workflows/build.yml`
   - GitHub Actions 会自动构建 APK

3. **下载构建产物**
   - 在 GitHub Actions 页面下载 APK

---

## 快速检查清单

运行以下命令检查文件是否完整：

```bash
# 检查核心文件
dir gradle\wrapper\gradle-wrapper.jar
dir gradle\wrapper\gradle-wrapper.properties
dir gradlew
dir gradlew.bat
```

**应该看到：**
- ✓ gradle-wrapper.jar（约60KB）
- ✓ gradle-wrapper.properties（配置文件）
- ✓ gradlew（Unix脚本）
- ✓ gradlew.bat（Windows脚本）

---

## 常见问题

### Q1: 下载速度慢或失败？
A: 使用镜像源，修改 `gradle-wrapper.properties`：
```
distributionUrl=https\://mirrors.cloud.tencent.com/gradle/gradle-8.3-bin.zip
```

### Q2: 提示 Java 版本不对？
A: 确保安装了 JDK 17 或更高版本：
```bash
java -version
```

### Q3: 网络超时？
A: 修改 `gradle.properties`，增加超时时间：
```
systemProp.http.connectionTimeout=60000
systemProp.http.socketTimeout=60000
```

---

## 下一步

1. 运行 `fix_gradle_wrapper.bat` 尝试自动修复
2. 如果失败，按照方案二手动下载
3. 验证文件完整性后，运行构建命令
4. 如果仍然失败，使用方案四通过 GitHub Actions 构建

---

**项目位置：** `c:\Users\jd\CodeBuddy\20260311175444`
**修复脚本：** `fix_gradle_wrapper.bat`
