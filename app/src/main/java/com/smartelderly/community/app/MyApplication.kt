package com.smartelderly.community.app

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import com.smartelderly.community.data.network.RetrofitClient
import com.smartelderly.community.data.preference.PreferenceManager
import com.smartelderly.community.data.room.AppDatabase
import com.smartelderly.community.utils.CrashHandler
import com.smartelderly.community.utils.LogUtil

/**
 * Application类
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        instance = this

        // 初始化MMKV
        MMKV.initialize(this)

        // 初始化日志
        LogUtil.init("SmartElderly")

        // 初始化崩溃处理
        CrashHandler.init()

        // 初始化数据库
        AppDatabase.init(this)

        // 初始化网络
        RetrofitClient.init()

        // 初始化SharedPreferences
        PreferenceManager.init(this)
    }

    companion object {
        private var instance: MyApplication? = null
        lateinit var context: Context

        fun getInstance(): MyApplication {
            return instance!!
        }
    }
}
