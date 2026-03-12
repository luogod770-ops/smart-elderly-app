package com.smartelderly.community.data.preference

import android.content.Context
import com.tencent.mmkv.MMKV

/**
 * SharedPreferences管理器
 */
object PreferenceManager {

    private lateinit var mmkv: MMKV

    private const val KEY_TOKEN = "token"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_IS_LOGIN = "is_login"
    private const val KEY_ELDER_MODE = "elder_mode"
    private const val KEY_NIGHT_MODE = "night_mode"

    fun init(context: Context) {
        mmkv = MMKV.defaultMMKV()
    }

    fun saveToken(token: String) {
        mmkv.encode(KEY_TOKEN, token)
        mmkv.encode(KEY_IS_LOGIN, true)
    }

    fun getToken(): String {
        return mmkv.decodeString(KEY_TOKEN, "") ?: ""
    }

    fun isLogin(): Boolean {
        return mmkv.decodeBool(KEY_IS_LOGIN, false)
    }

    fun saveUserId(userId: Long) {
        mmkv.encode(KEY_USER_ID, userId)
    }

    fun getUserId(): Long {
        return mmkv.decodeLong(KEY_USER_ID, 0)
    }

    fun setElderMode(enabled: Boolean) {
        mmkv.encode(KEY_ELDER_MODE, enabled)
    }

    fun isElderMode(): Boolean {
        return mmkv.decodeBool(KEY_ELDER_MODE, false)
    }

    fun setNightMode(enabled: Boolean) {
        mmkv.encode(KEY_NIGHT_MODE, enabled)
    }

    fun isNightMode(): Boolean {
        return mmkv.decodeBool(KEY_NIGHT_MODE, false)
    }

    fun clear() {
        mmkv.clear()
    }
}
