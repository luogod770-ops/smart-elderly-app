package com.smartelderly.community.data.network.interceptor

import com.smartelderly.community.BuildConfig
import com.smartelderly.community.data.preference.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 请求头拦截器
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        // 添加Token
        val token = PreferenceManager.getToken()
        if (token.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        // 添加通用请求头
        requestBuilder
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("App-Version", BuildConfig.VERSION_NAME)
            .addHeader("Device-Id", android.provider.Settings.Secure.ANDROID_ID)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
