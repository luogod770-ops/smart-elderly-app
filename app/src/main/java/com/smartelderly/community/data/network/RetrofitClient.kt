package com.smartelderly.community.data.network

import com.google.gson.GsonBuilder
import com.smartelderly.community.BuildConfig
import com.smartelderly.community.data.network.interceptor.HeaderInterceptor
import com.smartelderly.community.data.network.interceptor.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit客户端
 */
object RetrofitClient {

    private const val DEFAULT_TIMEOUT = 30L

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(LogInterceptor())
            .retryOnConnectionFailure(true)
            .build()
    }

    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /**
     * 获取API服务
     */
    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    /**
     * 重新初始化
     */
    fun init() {
        // 触发懒加载
        okHttpClient
        retrofit
    }
}
