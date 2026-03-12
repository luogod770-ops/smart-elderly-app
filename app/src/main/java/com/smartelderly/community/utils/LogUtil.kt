package com.smartelderly.community.utils

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * 日志工具
 */
object LogUtil {

    fun init(tag: String) {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(2)
            .tag(tag)
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    fun d(message: String) {
        Logger.d(message)
    }

    fun i(message: String) {
        Logger.i(message)
    }

    fun w(message: String) {
        Logger.w(message)
    }

    fun e(message: String) {
        Logger.e(message)
    }

    fun e(throwable: Throwable, message: String = "") {
        Logger.e(throwable, message)
    }
}
