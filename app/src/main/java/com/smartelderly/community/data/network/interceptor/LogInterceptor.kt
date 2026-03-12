package com.smartelderly.community.data.network.interceptor

import com.smartelderly.community.BuildConfig
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * 日志拦截器
 */
class LogInterceptor : Interceptor {
    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        com.smartelderly.community.utils.LogUtil.d(message)
    }.apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return loggingInterceptor.intercept(chain)
    }
}
