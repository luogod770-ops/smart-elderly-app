package com.smartelderly.community.utils

import android.content.Context
import android.os.Process
import java.io.PrintWriter
import java.io.StringWriter

/**
 * 崩溃处理器
 */
object CrashHandler : Thread.UncaughtExceptionHandler {

    private var defaultHandler: Thread.UncaughtExceptionHandler? = null
    private var context: Context? = null

    fun init() {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        LogUtil.e(throwable, "Uncaught exception")

        // 保存崩溃日志
        saveCrashInfo(throwable)

        // 调用默认处理器
        defaultHandler?.uncaughtException(thread, throwable)
    }

    private fun saveCrashInfo(throwable: Throwable) {
        val stringWriter = StringWriter()
        val printWriter = PrintWriter(stringWriter)
        throwable.printStackTrace(printWriter)
        val errorLog = stringWriter.toString()
        LogUtil.e("Crash log: $errorLog")
    }
}
