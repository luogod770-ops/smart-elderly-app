plugins {
    id("java")
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.smartelderly"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.aliyun.com/repository/public") }
}

dependencies {
    // Spring Boot核心
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    
    // MySQL驱动
    runtimeOnly("com.mysql:mysql-connector-j")
    
    // Druid数据源
    implementation("com.alibaba:druid-spring-boot-3-starter:1.2.20")
    
    // MyBatis Plus
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.5")
    
    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")
    
    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    
    // Hutool工具类
    implementation("cn.hutool:hutool-all:5.8.24")
    
    // Gson
    implementation("com.google.code.gson:gson:2.10.1")
    
    // 阿里云OSS
    implementation("com.aliyun.oss:aliyun-sdk-oss:3.17.4")
    
    // 阿里云短信
    implementation("com.aliyun:dysmsapi20170525:3.0.1")
    
    // 腾讯云IM
    implementation("com.tencentcloudapi:tencentcloud-sdk-java:3.1.893")
    
    // 支付宝SDK
    implementation("com.alipay.sdk:alipay-sdk-java:4.39.19.ALL")
    
    // 微信支付SDK
    implementation("com.github.wechatpay-apiv3:wechatpay-java:0.2.12")
    
    // ctwing平台对接
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3")
    
    // Swagger/Knife4j
    implementation("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter:4.4.0")
    
    // 文件上传
    implementation("commons-fileupload:commons-fileupload:1.5")
    implementation("commons-io:commons-io:2.15.1")
    
    // 二维码
    implementation("com.google.zxing:core:3.5.2")
    implementation("com.google.zxing:javase:3.5.2")
    
    // MapStruct对象映射
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    
    // 测试
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootJar {
    archiveFileName.set("smart-elderly-community-backend.jar")
}
