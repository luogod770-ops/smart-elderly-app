# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# 混淆基础配置
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

# Gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# 腾讯IM
-keep class com.tencent.imsdk.** { *; }

# 支付宝
-keep class com.alipay.sdk.** { *; }

# 微信
-keep class com.tencent.mm.opensdk.** { *; }

# 蓝牙
-dontwarn no.nordicsemi.android.**
-keep class no.nordicsemi.android.** { *; }

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# MMKV
-keep class com.tencent.mmkv.** { *; }
-keep class com.tencent.wcdb.** { *; }

# Keep model classes
-keep class com.smartelderly.community.data.model.** { *; }
-keepclassmembers class com.smartelderly.community.data.model.** { *; }

# Room
-dontwarn androidx.room.paging.**
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.paging.**
-keep class androidx.paging.** { *; }
