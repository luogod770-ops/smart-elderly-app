plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.smartelderly.community"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.smartelderly.community"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true

        // 三个方服务配置 - 使用占位符
        buildConfigField("String", "ALIYUN_SMS_ACCESS_KEY_ID", "\"YOUR_ACCESS_KEY_ID\"")
        buildConfigField("String", "ALIYUN_SMS_ACCESS_KEY_SECRET", "\"YOUR_ACCESS_KEY_SECRET\"")
        buildConfigField("String", "ALIYUN_SMS_SIGN_NAME", "\"YOUR_SIGN_NAME\"")
        buildConfigField("String", "TENCENT_IM_SDK_APP_ID", "\"YOUR_SDK_APP_ID\"")
        buildConfigField("String", "TENCENT_MAP_KEY", "\"YOUR_MAP_KEY\"")
        buildConfigField("String", "API_BASE_URL", "\"http://your-server.com:8080/api\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // AndroidX核心
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    
    // Activity KTX
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    
    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    
    // 协程
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    
    // 网络请求
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    
    // Gson
    implementation("com.google.code.gson:gson:2.10.1")
    
    // 图片加载
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")
    
    // Room数据库
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
    // 权限请求
    implementation("com.permissionx.guolindev:permissionx:1.7.1")
    
    // 腾讯IM
    implementation("com.tencent.imsdk:imsdk-plus:8.2.6201")
    
    // 腾讯定位和地图
    implementation("com.tencent.map:tencent-map-vector-sdk:4.6.8.1")
    implementation("com.tencent.location:location:5.6.0.2")
    
    // 支付宝SDK
    implementation("com.alipay.sdk:alipaysdk-android:15.8.11")
    
    // 微信支付SDK
    implementation("com.tencent.mm.opensdk:wechat-sdk-android-without-mta:6.8.0")
    
    // 蓝牙
    implementation("no.nordicsemi.android:ble:2.7.2")
    
    // 图片选择
    implementation("io.github.lucksiege:pictureselector:v3.10.7")
    
    // 下拉刷新
    implementation("io.github.scwang90:refresh-layout-kernel:2.0.6")
    implementation("io.github.scwang90:refresh-header-classics:2.0.6")
    implementation("io.github.scwang90:refresh-footer-classics:2.0.6")
    
    // WebView
    implementation("com.github.Justson.AgentWeb:agentweb-core:v5.0.6-androidx")
    
    // 时间选择器
    implementation("com.github.loperSeven:DateTimePicker:0.6.3")
    
    // Banner
    implementation("com.github.zhpanvip:bannerviewpager:3.5.11")
    
    // BaseRecyclerViewAdapterHelper
    implementation("io.github.cymchad:BaseRecyclerViewAdapterHelper4:4.1.4")
    
    // Toast工具
    implementation("com.github.getActivity:Toaster:12.6")
    
    // 日志
    implementation("com.orhanobut:logger:2.2.0")
    
    // MMKV存储
    implementation("com.tencent:mmkv:1.3.2")
    
    // 测试
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
